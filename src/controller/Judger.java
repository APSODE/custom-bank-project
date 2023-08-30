package controller;

import controller.Exceptions.BalanceException;
import controller.Exceptions.PointException;
import entity.user.UserAccount;

public class Judger {
    public static boolean isPossibleToWithdraw(long balance, long amount) throws BalanceException
    {
        if (amount > 0)
        {
            if(balance - amount >= 0)
                return true;
            throw new BalanceException("잔액 부족");
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
    public static boolean isPossibleToUsePoint(long balance, long amount, long point) throws PointException
    {
        if (point <= 5000)
        {
            throw new PointException("포인트 부족");
        }
            if (balance - point - amount >= 0)
            {
                return true;
            }
            throw  new PointException("잔액 부족");
    }
}
