package entity.account;

import java.time.LocalDate;

public class InstallmentAccount {
    private float rate;
    private LocalDate dueDate;
    public InstallmentAccount(LocalDate dueDate)
    {
        this.dueDate = dueDate;
    }

}
