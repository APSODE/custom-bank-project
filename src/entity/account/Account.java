package entity.account;

import controller.Printer;
import controller.Inputter;
import entity.user.UserAccount;
import java.io.IOException;

public class Account {
    private UserAccount UserAccount;
    private long balance;
    private long limit;

    public long getBalance() {
        return balance;

    }
    public boolean deposit(long amount, String pw) throws IOException {
        Printer printer = new Printer();
        printer.print("잔액을 확인하시겠습니까?(y/n)");
        Inputter inputter = new Inputter();
        String answer = inputter.inpString();
        if(answer == "y" ){
            printer.print(balance);
        }
    }
    public boolean withdraw(long amount, String pw){
    }

    public long getLimit() {
        return limit;
    }
    public void setLimit(long limit){
        this.limit = limit;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}