package entity.account;

import entity.user.UserAccount;

public class PointAccount extends Account{

    private long point;

    public PointAccount(UserAccount userAccount) {
        super(userAccount);
    }

    protected boolean IsPossibleToUsePoint(long amount){ }
    public void setPoint(long point){
        this.point = point;
    }
}
