package controller;

import entity.account.Account;
import entity.account.MinusAccount;
import entity.user.User;
import entity.user.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.jodatime.api.Assertions.assertThat;

class SerializerTest {
    @DisplayName("객체 저장 테스트")
    @Test
    void saveObject() throws IOException {
        //given
        User testUser = User.CreateObject(
                "test user",
                20
        );

        UserAccount testUserAccount = UserAccount.CreateObject(
                testUser,
                "testId",
                "testPw"
        );

        Account testAccount = Account.CreateObject(
                testUserAccount
        );

        MinusAccount testMinusAccount = MinusAccount.CreateObject(
                testUserAccount
        );

        //when
        Serializer<User> userSerializer = new Serializer<>(User.class);
        userSerializer.saveObject(
                testUser,
                "UserObjectData.cbp"
        );

        //then
        //정상적으로 오브젝트 데이터 파일이 생성되었는지 확인
        File objectDataFile = new File("\\UserObjectData.cbp");
        assertEquals("\\UserObjectData.cbp", objectDataFile.getPath());


    }

    @Test
    void loadObject() {
        //given

        //when

        //then
    }
}