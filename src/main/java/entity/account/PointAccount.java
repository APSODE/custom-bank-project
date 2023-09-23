package entity.account;

import exceptions.InvalidPasswordException;
import exceptions.NegativeAmountException;
import exceptions.PointException;
import entity.user.UserAccount;
import controller.Judger;
import exceptions.ZeroAmountException;

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

    public void setPoint(long optionalPoint) {
        this.point = optionalPoint;
    }

    public boolean earnPoints(long amount) {
        // 포인트 적립 과정에서 입금이랑 어떤관계가 있었는지 모르겠음
        // 해당 if문 안의 조건식이 false여서 포인트 적립이 되지 않아도 해당 메소드는 true를 리턴함
        this.point += (long) (amount * 0.03);

        return true;
    }

    public boolean withdrawWithPoint(long amount, String  pw) throws PointException, InvalidPasswordException, ZeroAmountException, NegativeAmountException {
        if (!Judger.isRightPw(this.getUserAccount(), pw)) {
            throw new InvalidPasswordException("비밀번호 오류");
        }

        if (!Judger.isPositiveArgument(amount)) {
            if (Judger.isZeroAmount(amount)) {
                throw new ZeroAmountException("출금액은 0원이 될 수 없습니다.");
            }
            throw new NegativeAmountException("출금액은 음수가 될 수 없습니다.");
        }

        if (!Judger.isOverThanMinUsePoint(this.point)) {
            throw new PointException("포인트 사용은 5000포인트부터 사용이 가능합니다.");
        }

        if (!Judger.isShortOfBalance(this.getBalance(), amount, this.point)) {
            throw new PointException("계좌의 잔액이 부족합니다.");
        }

        if (this.point >= amount) {
            this.point -= amount;

        } else {
            long amountRemainder = amount - this.point;
            this.point = 0;
            this.setBalance(
                    this.getBalance() - amountRemainder
            );
        }
        return true;
    }
}