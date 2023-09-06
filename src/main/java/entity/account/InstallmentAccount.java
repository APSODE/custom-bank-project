package entity.account;

import entity.user.UserAccount;

import java.io.Serializable;
import java.time.LocalDate;

public class InstallmentAccount extends Account implements Serializable {
    private double rate;
    private LocalDate dueDate;

    SavingAccount AccountToSend = null;

    public InstallmentAccount(LocalDate dueDate, double rate, UserAccount userAccount)
    {
        super(userAccount);
        this.dueDate = dueDate;
        this.rate = rate;
    }

    public InstallmentAccount(LocalDate dueDate, double rate, UserAccount userAccount, SavingAccount AccountToSend)
    {
        super(userAccount);
        this.AccountToSend = AccountToSend;
        this.rate = rate;
        this.dueDate = dueDate;
    }

    public static InstallmentAccount CreateObject(LocalDate dueDate, double rate, UserAccount userAccount) {
        return new InstallmentAccount(
                dueDate,
                rate,
                userAccount
        );
    }

    public static InstallmentAccount CreateObject(LocalDate dueDate, double rate, UserAccount userAccount, SavingAccount savingAccount) {
        return new InstallmentAccount(
                dueDate,
                rate,
                userAccount,
                savingAccount
        );
    }

    public double getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // 해당 객체 안에서 dueProcess메소드를 호출하는 코드가 존재하지 않으므로 해당 메소드를 사용하기위해서 public으로 접근제어자 변경
    public void dueProcess(LocalDate today)
    {
        if (today.isAfter(dueDate))
        {
            if(AccountToSend != null)
            {
                AccountToSend.setBalance(AccountToSend.getBalance() + this.getBalance());
            }
            else
            {
                this.setBalance(this.getBalance() + (long)(this.getBalance() * this.rate));
            }
        }
    }
}
