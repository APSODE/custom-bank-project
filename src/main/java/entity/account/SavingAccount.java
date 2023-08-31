package entity.account;

import controller.Judger;
import controller.Printer;
import entity.user.UserAccount;

import java.io.IOException;
import java.time.LocalDate;

public class SavingAccount extends Account{
    private float rate;
    private LocalDate lastWithdrawDate;

    public SavingAccount(UserAccount userAccount) {
        super(userAccount);
        this.rate = 0;
           }

    public float getRate(){
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean setWithdrawLimit(long limit) throws IOException {
        if (!Judger.isSmallerThanAmount(limit,long amount)){
            if()
        }
            Printer.print("1회 출금액을 초과하는 출금액입니다. 출금액을 재입력하여주십시오.");
        }
        return false;

    }


