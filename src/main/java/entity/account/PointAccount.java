package entity.account;

import controller.exceptions.PointException;
import entity.user.UserAccount;
import controller.Judger;

import java.io.Serializable;

public class PointAccount extends Account implements Serializable {

    private long point;
    public PointAccount(UserAccount userAccount) {
        super(userAccount);
    }

    public long getPoint() {
        return point;
    }

    public boolean earnPoints(long amount) {
        // 포인트 적립 과정에서 입금이랑 어떤관계가 있었는지 모르겠음
        // 해당 if문 안의 조건식이 false여서 포인트 적립이 되지 않아도 해당 메소드는 true를 리턴함
        if (Judger.isPossibleToWithdraw(long balance,amount)) {
            this.point += (long) (amount * 0.03);
        }
        return true;
    }

    public boolean usePoints(long amount) throws PointException {
        // Exception을 발생시킬때 Judger에서 발생시키는 것이 아닌 해당 메소드 안에서 발생시키는 것이 좋음
        if (Judger.isPossibleToUsePoint(this.getBalance(), amount, this.point)){
            if (amount <= this.point) {
                this.point -= amount;

            } else {
                this.setBalance(this.getBalance() + this.point - amount);

            }
        }
        return true;
    }

    // usePoints 리팩토링 예시 코드
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