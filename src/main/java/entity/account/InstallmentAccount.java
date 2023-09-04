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

    private void dueProcess(LocalDate today)
    {
        if (today.isAfter(dueDate))
        {
            if(AccountToSend != null)
            {
                AccountToSend.setBalance(super.getBalance()+this.getBalance());
            }
            else
            {
                this.setBalance(this.getBalance()+ (long)(this.getBalance()*this.rate));
            }
        }
    }
}
