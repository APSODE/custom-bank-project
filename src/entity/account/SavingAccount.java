package entity.account;

import java.time.LocalDate;

public class SavingAccount extends Account{
    private float rate;
    private LocalDate lastWithdrawDate;

    public float getRate(){

        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }


}
