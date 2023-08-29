package controller;

import entity.user.User;
import entity.user.UserAccount;

import java.util.concurrent.ExecutionException;

public class Judger {
    public static boolean isPossibleToWithdraw(long balance, long amount) throws BalanceException
    {
        if (amount > 0)
        {
            if(balance - amount >= 0)
                return true;
            else throw new BalanceException("잔액 부족");
        }
        throw new IllegalArgumentException("음수인 값 입력");
    }
    public static boolean isPossibleToDeposit(long amount)
    {
        return amount > 0;
    }
    public static boolean isRightPw(UserAccount userAccount, String pw)
    {
        return pw.equals(userAccount.getPw());
    }

    static class BalanceException extends Exception
    {
        public BalanceException(String message)
        {
            super(message);
        }
    }
}
