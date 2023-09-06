package entity.account;

import entity.user.UserAccount;
import exceptions.OverWithrawLimitException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class SavingAccountTest {
    private final UserAccount testUserAccount = UserAccount.CreateTestObject();


    @DisplayName("1회 이체 한도 적용 출금 테스트")
    @Test
    void withdrawWithLimit() {
        // given
        SavingAccount testSavingAccount = SavingAccount.CreateObject(
                this.testUserAccount
        );

        // when
        testSavingAccount.setLimit(10000);
        testSavingAccount.setBalance(100000);
        // testSavingAccount.withdrawWithLimit()

        // then
        assertThatCode(() -> {
            testSavingAccount.withdrawWithLimit(
                    1000,
                    this.testUserAccount.getPw()
            );
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            testSavingAccount.withdrawWithLimit(
                    10001,
                    this.testUserAccount.getPw()
            );
        }).isInstanceOf(OverWithrawLimitException.class);

    }
}