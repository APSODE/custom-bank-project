package entity.account;

import controller.Judger;
import entity.user.UserAccount;
import exceptions.*;

import java.io.Serializable;
import java.time.LocalDate;

public class SavingAccount extends Account implements Serializable {
    private double rate;
    private LocalDate lastWithdrawDate;

    public SavingAccount(UserAccount userAccount) {
        super(userAccount);
        this.rate = 0;
    }

    public static SavingAccount CreateObject(UserAccount userAccount) {
        return new SavingAccount(
                userAccount
        );
    }

    public double getRate() {

        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean withdrawWithLimit(long amount, String pw) throws OverWithrawLimitException, BalanceException, NegativeAmountException, InvalidPasswordException, ZeroAmountException {
        if (!Judger.isNotOverTheLimit(this.getLimit(), amount)) {
            // 이체금액 제한 초과시
            throw new OverWithrawLimitException("제한금액 초과");
        }

        return super.withdraw(amount, pw);
    }
}




