package entity.account;

import controller.exceptions.*;
import entity.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MinusAccountTest {
    private final UserAccount testUserAccount = UserAccount.CreateTestObject();
    private final MinusAccount testMinusAccount = MinusAccount.CreateObject(
            this.testUserAccount
    );

    @BeforeEach
    void minusAccountTestSetting() {
        this.testMinusAccount.setBalance(100000);
        this.testMinusAccount.setCredit(100000);
        this.testMinusAccount.setMaxRepayment(10000);
        this.testMinusAccount.setRate(0.1);
        this.testMinusAccount.setInterest();
    }

    @DisplayName("중도 상환 테스트")
    @Test
    void interimRepayment() {
        assertThatCode(() -> {
            this.testMinusAccount.setLoan(100);
            this.testMinusAccount.interimRepayment(
                    100,
                    this.testUserAccount.getPw()
            );
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            this.testMinusAccount.interimRepayment(
                    this.testMinusAccount.getMaxRepayment() + 1,
                    this.testUserAccount.getPw()
            );
        }).isInstanceOf(RepaymentException.class);

        assertThatThrownBy(() -> {
            this.testMinusAccount.setLoan(0);
            this.testMinusAccount.interimRepayment(
                    100,
                    this.testUserAccount.getPw()
            );
        }).isInstanceOf(NotExistLoanAmount.class);

    }

    @DisplayName("정상 상환 테스트")
    @Test
    void repayment() {
        assertThatCode(() -> {
            this.testMinusAccount.repayment(
                    this.testUserAccount.getPw()
            );
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            this.testMinusAccount.setLoan(10000);
            this.testMinusAccount.setBalance(this.testMinusAccount.getLoan() - 1);
            this.testMinusAccount.repayment(
                    this.testUserAccount.getPw()
            );
        }).isInstanceOf(RepaymentException.class);

        assertThatThrownBy(() -> {
            this.testMinusAccount.repayment(
                    "invalid password"
            );
        }).isInstanceOf(InvalidPasswordException.class);
    }

    @Test
    void revolving() {
    }

    @DisplayName("마이너스 계좌 출금 테스트")
    @Test
    void withdrawInMinusAccount() {
        assertThatCode(() -> {
            testMinusAccount.withdrawInMinusAccount(
                    100,
                    this.testUserAccount.getPw()
            );
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> {
            testMinusAccount.withdrawInMinusAccount(
                    1000000,
                    this.testUserAccount.getPw()
            );
        }).isInstanceOf(NoLongerAvailableMinusWithdraw.class);

        assertThatThrownBy(() -> {
            testMinusAccount.withdrawInMinusAccount(
                    100,
                    "invalid password"
            );
        }).isInstanceOf(InvalidPasswordException.class);

        assertThatThrownBy(() -> {
            testMinusAccount.withdrawInMinusAccount(
                    -100,
                    this.testUserAccount.getPw()
            );
        }).isInstanceOf(NegativeAmountException.class);

        assertThatThrownBy(() -> {
            testMinusAccount.withdrawInMinusAccount(
                    0,
                    this.testUserAccount.getPw()
            );
        }).isInstanceOf(ZeroAmountException.class);
    }
}