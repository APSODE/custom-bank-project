package entity.account;

import controller.Judger;
import controller.Printer;
import entity.user.UserAccount;

import controller.Printer;
import controller.Inputter;
import entity.user.UserAccount;
import java.io.IOException;

public class Account {
    private UserAccount UserAccount;
    private long balance;
    private long limit;

    public Account(UserAccount userAccount) {
        this.userAccount = userAccount;
        this.balance = 0;  // 생성자 파라미터로 입력을 받지 않으므로 기본값 0으로 세팅 (추후 변경 가능)
        this.limit = 0;  // 생성자 파라미터로 입력을 받지 않으므로 기본값 0으로 세팅 (추후 변경 가능)
    }

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