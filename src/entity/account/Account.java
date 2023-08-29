package entity.account;

import controller.Judger;
import controller.Printer;
import entity.user.UserAccount;
import controller.Printer;
import controller.Inputter;
import entity.user.UserAccount;
import java.io.IOException;
import controller.BalanceException;

public class Account {

    private UserAccount userAccount;
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
        Printer.print("비밀번호를 입력하세요.");
        String password = Inputter.inpString();
        if (Judger.isRightPw(userAccount, password)) {
            Printer.print("비밀번호가 확인되었습니다.");
        } else {
            Printer.print("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
        }

        Printer.print("잔액을 확인하시겠습니까?(y/n)");
        String answer = Inputter.inpString();
        if (answer == "y") {
            Printer.print(balance);
        }
        Printer.print("입금할 금액을 입력해 주세요.");
        if (Judger.isPossibleToDeposit(amount)) {
            balance += amount;
        } else {
            Printer.print("0보다 작은 금액은 입금할 수 없습니다.");
        }

        return true;
    }
    public boolean withdraw(long amount, String pw) throws IOException {
        Printer.print("출금할 입력을 입력해 주세요.");
        try{
            if(Judger.isPossibleToWithdraw(balance,amount)) {
                balance -= amount;
            }
        }
        catch (BalanceException|IllegalArgumentException exception){
                System.out.println(exception.getMessage());
            }
        return true;
        }



    // MinusAccount에서 사용할 입출금 메소드 ( MinusAccount에도 비밀번호 인증을 사용한다면 해당 메소드 제거 )
    protected boolean depositByOverRepayment(long amount) {
        int errorCount = 0;
        try {
            this.balance += amount;

        } catch (Exception error) {
            errorCount++;

            // Printer 클래스의 print메소드를 static으로 수정 후 해당 라인을 Printer클래스의 print메소드로 교체 진행
            System.out.println("알 수 없는 에러 발생.");

        } finally {
            return errorCount == 0;
        }
    }

    // MinusAccount에서 사용할 입출금 메소드 ( MinusAccount에도 비밀번호 인증을 사용한다면 해당 메소드 제거 )
    protected boolean withdrawByRepayment(long amount) {
        if (Judger.isPossibleToWithdraw(this.balance, amount)) {
            this.balance -= amount;
            return true;

        } else {
            return false;
        }
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