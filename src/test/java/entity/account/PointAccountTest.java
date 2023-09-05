package entity.account;

import entity.user.User;
import entity.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointAccountTest {
    private final UserAccount testUserAccount = UserAccount.CreateTestObject();
    private final PointAccount testPointAccount = PointAccount.CreateObject(
            this.testUserAccount
    );

    @BeforeEach
    void pointAccountTestSetting() {
        this.testPointAccount.setBalance(1000000);
    }

    @DisplayName("포인트 적립 테스트")
    @Test
    void earnPoints() {

    }

    @DisplayName("포인트 사용 테스트")
    @Test
    void withdrawWithPoint() {
    }
}