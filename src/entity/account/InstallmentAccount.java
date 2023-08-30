package entity.account;

import entity.user.UserAccount;

import java.time.LocalDate;

public class InstallmentAccount extends Account {
    private float rate;
    private LocalDate dueDate;


    public InstallmentAccount(LocalDate dueDate, entity.user.UserAccount userAccount)
    {
        super(userAccount);
        this.dueDate = dueDate;
    }

    public float getRate() {
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
}
