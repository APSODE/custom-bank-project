package controller;

import entity.account.PointAccount;
import entity.user.User;
import entity.user.UserAccount;

import java.util.concurrent.ExecutionException;

public class Judger {
    public static boolean isPossibleToWithdraw(long balance, long amount) throws BankException
    {
        if (amount > 0)
        {
            if(balance - amount >= 0)
                return true;
            else throw new BankException("잔액 부족");
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
    public static boolean isPossibleToUsePoint(PointAccount account, long amount) throws BankException
    {
        long currentPoint = account.getPoint();
        if (currentPoint <= 5000)
        {
            throw new BankException("포인트 부족");
        }
            if (account.getBalance() - currentPoint >= amount)
            {
                return true;
            }
            throw  new BankException("잔액 부족");
    }
}
