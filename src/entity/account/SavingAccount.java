package entity.account;

import entity.user.UserAccount;

import java.time.LocalDate;

public class SavingAccount extends Account{
    private float rate;
    private LocalDate lastWithdrawDate;

    public SavingAccount(UserAccount userAccount) {
        super(userAccount);
    }

    public float getRate(){

        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }


}
