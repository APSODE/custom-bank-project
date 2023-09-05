package entity.account;

import exceptions.*;
import controller.Judger;
import entity.user.UserAccount;
import exceptions.BalanceException;
import exceptions.InvalidPasswordException;
import exceptions.NegativeAmountException;
import exceptions.ZeroAmountException;

import java.io.Serializable;

public class Account implements Serializable {

    private final UserAccount userAccount;
    private long balance;
    private long limit;

    public Account(UserAccount userAccount) {
        this.userAccount = userAccount;
        this.balance = 0;  // 생성자 파라미터로 입력을 받지 않으므로 기본값 0으로 세팅 (추후 변경 가능)
        this.limit = 0;  // 생성자 파라미터로 입력을 받지 않으므로 기본값 0으로 세팅 (추후 변경 가능)
    }

    public static Account CreateObject(UserAccount userAccount) {
        return new Account(
                userAccount
        );
    }

    public long getBalance() {
        return balance;
    }

    protected void setBalance(long amount) {
        this.balance = amount;
    }

    protected UserAccount getUserAccount() {
        return this.userAccount;
    }

    public boolean deposit(long amount, String pw) throws InvalidPasswordException, NegativeAmountException, ZeroAmountException {
        if (!Judger.isRightPw(userAccount, pw)) {
            // Printer.print("비밀번호 오류");
            // 이런식으로 출력하는 것이 아닌 exception을 throw를 이용해 던지는 것으로 끝내기만 하면됨.
            throw new InvalidPasswordException("비밀번호 오류");
        }

        if (!Judger.isPositiveArgument(amount)) {
            // Printer.print("음수 입력");
            // 이런식으로 출력하는 것이 아닌 exception을 throw를 이용해 던지는 것으로 끝내기만 하면됨.
            if (Judger.isZeroAmount(amount)) {
                throw new ZeroAmountException("입금액은 0원이 될 수 없습니다.");
            }

            throw new NegativeAmountException("음수 입력");

        }

        balance += amount;
        return true;
    }

    // 출금 메소드도 입금 메소드와 동일하게 Exception을 발생시키는 방식과 값 검증 과정의 수정이 필요함.
    public boolean withdraw (long amount, String pw) throws BalanceException, NegativeAmountException, InvalidPasswordException, ZeroAmountException {
        if (!Judger.isRightPw(this.userAccount, pw)) {
            throw new InvalidPasswordException("비밀번호가 옳바르지 않습니다.");
        }

        if (!Judger.isPositiveArgument(amount)) {
            if (Judger.isZeroAmount(amount)) {
                throw new ZeroAmountException("출금액은 0원이 될 수 없습니다.");
            }
            throw new NegativeAmountException("출금액은 음수가 될 수 없습니다.");
        }

        if (!Judger.isEnoughBalance(this.balance, amount)) {
            throw new BalanceException("잔액이 부족합니다.");
        }

        this.balance -= amount;
        return true;

    }

    public long getLimit () {
        return limit;
    }

    public void setLimit ( long limit){
        this.limit = limit;
    }

    @Override
    public String toString () {
        return "balance : " + this.balance + "\n" +
                "limit: " + this.limit + "\n";

    }

}