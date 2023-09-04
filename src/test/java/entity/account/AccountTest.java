package entity.account;

import controller.exceptions.BalanceException;
import controller.exceptions.InvalidPasswordException;
import controller.exceptions.NegativeAmountException;
import entity.user.User;
import entity.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class AccountTest {
    private Account testAccount;
    private String testAccountPw = "testPw";
    @BeforeEach
    void createTestAccountObject() {
        this.testAccount = Account.CreateObject(
                UserAccount.CreateObject(
                        User.CreateObject(
                                "test user",
                                20,
                                "010-1234-5678"
                        ),
                        "testId",
                        this.testAccountPw
                )
        );
    }

    @DisplayName("입금 테스트")
    @Test
    void deposit() {
        //given
        long beforeBalance = this.testAccount.getBalance();

        assertThatCode(() -> {
            this.testAccount.deposit(1000, this.testAccountPw);
        }).doesNotThrowAnyException();

        assertEquals(this.testAccount.getBalance() - beforeBalance, this.testAccount.getBalance());

        assertThatThrownBy(() -> {
            this.testAccount.deposit(1000, "invalid password");
        }).isInstanceOf(InvalidPasswordException.class);

        assertThatThrownBy(() -> {
            this.testAccount.deposit(-1000, this.testAccountPw);
        }).isInstanceOf(NegativeAmountException.class);
    }

    @DisplayName("출금 테스트")
    @Test
    void withdraw() {
        assertThatCode(() -> {
            this.testAccount.deposit(10000, this.testAccountPw);
        }).doesNotThrowAnyException();

        long beforeBalance = this.testAccount.getBalance();

        assertThatCode(() -> {
            this.testAccount.withdraw(1000, this.testAccountPw);
        }).doesNotThrowAnyException();

        assertEquals(9000, this.testAccount.getBalance());

        assertThatThrownBy(() -> {
            this.testAccount.withdraw(100000, this.testAccountPw);
        }).isInstanceOf(BalanceException.class);

        assertThatThrownBy(() -> {
            this.testAccount.withdraw(-1000, this.testAccountPw);
        }).isInstanceOf(NegativeAmountException.class);

        assertThatThrownBy(() -> {
            this.testAccount.withdraw(1000, "invalid password");
        }).isInstanceOf(InvalidPasswordException.class);
    }
}