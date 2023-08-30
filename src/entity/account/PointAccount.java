package entity.account;

import controller.Exceptions.PointException;
import entity.user.UserAccount;
import controller.Judger;

public class PointAccount extends Account {

    private long point;
    public PointAccount(UserAccount userAccount) {
        super(userAccount);
    }

    public long getPoint() {
        return point;
    }

    public boolean earnPoints(long amount) {
        if (Judger.isPossibleToDeposit(amount)) {
            point += (long) (amount * 0.03);
        }
        return true;
    }

    public boolean usePoints(long point, long balance, long amount) throws PointException {
        if (Judger.isPossibleToUsePoint(balance, amount,point)){
            if (amount <= point)
                point -= amount;
            else balance += (point - amount);
        }
        return true;
    }
}