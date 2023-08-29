package controller;

import entity.user.User;
import entity.user.UserAccount;

public class Judger {
    public static boolean isPossibleToWithdraw(long balance, long amount)
    {
        if (amount > 0)
        {
            return balance - amount >= 0;
        }
        return false;
    }
    public static boolean isPossibleToDeposit(long amount)
    {
        return amount > 0;
    }
    public static boolean isRightPw(UserAccount userAccount, String pw)
    {
        return pw.equals(userAccount.getPw());
    }
}
