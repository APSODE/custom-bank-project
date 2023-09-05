package entity.account;

import exceptions.InvalidPhoneNumberFormat;
import entity.user.User;
import entity.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InstallmentAccountTest {
    private InstallmentAccount testInstallmentAccount;
    private UserAccount testUserAccount;
    private String testUserAccountPw = "testPw";

    @BeforeEach
    void createTestObject() throws InvalidPhoneNumberFormat {
        this.testUserAccount = UserAccount.CreateObject(
                User.CreateObject(
                        "test user",
                        20,
                        "010-1234-5678"
                ),
                "test_id",
                this.testUserAccountPw
        );
    }

    @Test
    void dueProcess() {
        // given
        LocalDate yesterday = LocalDate.now().minusDays(1);

        InstallmentAccount testInstallmentAccount = InstallmentAccount.CreateObject(
                yesterday,
                0.1,
                this.testUserAccount
        );
        long beforeBalance = testInstallmentAccount.getBalance();
        long expectedBalance = beforeBalance + (long) (beforeBalance * testInstallmentAccount.getRate());


        // when
        testInstallmentAccount.dueProcess(LocalDate.now());


        // then
        assertEquals(expectedBalance, testInstallmentAccount.getBalance());
    }
}