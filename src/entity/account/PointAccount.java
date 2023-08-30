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
            this.point += (long) (amount * 0.03);
        }
        return true;
    }

    public boolean usePoints(long amount) throws PointException {
        if (Judger.isPossibleToUsePoint(this.getBalance(), amount, this.point)){
            if (amount <= this.point) {
                this.point -= amount;

            } else {
                this.setBalance(this.getBalance() + this.point - amount);

            }
        }
        return true;
    }
}