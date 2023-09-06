package entity.account;

import entity.user.User;
import entity.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class PointAccountTest {
    private final UserAccount testUserAccount = UserAccount.CreateTestObject();

    @DisplayName("포인트 적립 테스트")
    @Test
    void earnPoints() {
        // given
        PointAccount testPointAccount = PointAccount.CreateObject(
                this.testUserAccount
        );

        // when
        testPointAccount.earnPoints(10000);

        // then
        assertEquals(300, testPointAccount.getPoint());
    }

    @DisplayName("포인트 사용 테스트")
    @Test
    void withdrawWithPoint() {
        PointAccount testPointAccount = PointAccount.CreateObject(
                this.testUserAccount
        );

        assertThatCode(() -> {
            testPointAccount.setBalance(1000000);
            testPointAccount.setPoint(10000);

            testPointAccount.withdrawWithPoint(
                    7000,
                    this.testUserAccount.getPw()
            );
        }).doesNotThrowAnyException();
    }
}