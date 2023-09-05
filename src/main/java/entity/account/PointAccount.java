package entity.account;

import exceptions.PointException;
import entity.user.UserAccount;
import controller.Judger;

import java.io.Serializable;

public class PointAccount extends Account implements Serializable {

    private long point;

    public PointAccount(UserAccount userAccount) {
        super(userAccount);
    }

    public static PointAccount CreateObject(UserAccount userAccount) {
        return new PointAccount(
                userAccount
        );
    }

    public long getPoint() {
        return point;
    }

    public boolean earnPoints(long amount) {
        // 포인트 적립 과정에서 입금이랑 어떤관계가 있었는지 모르겠음
        // 해당 if문 안의 조건식이 false여서 포인트 적립이 되지 않아도 해당 메소드는 true를 리턴함
        this.point += (long) (amount * 0.03);

        return true;
    }

    private boolean exampleUsePointsMethod(long amount) throws PointException {
        if (!Judger.isOverThanMinUsePoint(this.point)) {
            throw new PointException("포인트 사용은 5000포인트부터 사용이 가능합니다.");
        }

        if (!Judger.isShortOfBalance(this.getBalance(), amount, this.point)) {
            throw new PointException("계좌의 잔액이 부족합니다.");
        }

        if (amount <= this.point) {
            this.point -= amount;
        } else {
            this.setBalance(
                    this.getBalance() + this.point - amount
            );
        }

        return true;
    }
}