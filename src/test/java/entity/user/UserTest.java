package entity.user;

import controller.exceptions.InvalidPhoneNumberFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class UserTest {
    private final String testUserName = "test user";
    private final int testUserAge = 20;

    @Test
    void createObject() {
        assertThatThrownBy(() -> {
            User invalidPhoneNumberUser = User.CreateObject(
                    testUserName,
                    testUserAge,
                    "123123123131"
            );
        }).isInstanceOf(InvalidPhoneNumberFormat.class);

        assertThatCode(() -> {
            User validPhoneNumberUser = User.CreateObject(
                    testUserName,
                    testUserAge,
                    "010-1234-5678"
            );
        }).doesNotThrowAnyException();
    }

    @Test
    void setPhoneNumber() {
        assertThatThrownBy(() -> {
            User validPhoneNumberUser = User.CreateObject(
                    testUserName,
                    testUserAge,
                    "010-1234-5678"
            );

            validPhoneNumberUser.setPhoneNumber("112343113431434");
        }).isInstanceOf(InvalidPhoneNumberFormat.class);

        assertThatCode(() -> {
            User validPhoneNumberUser = User.CreateObject(
                    testUserName,
                    testUserAge,
                    "010-1234-5678"
            );

            validPhoneNumberUser.setPhoneNumber("010-1234-5678");

        }).doesNotThrowAnyException();
    }
}