package controller;

import entity.user.User;
import entity.user.UserAccount;

public class Judger {
    public boolean isPossibleToWithdraw(long balance, long amount)
    {
        if (amount > 0)
        {
            return balance - amount >= 0;
        }
        return false;
    }
    public boolean isPossibleToDeposit(long amount)
    {
        return amount > 0;
    }
    public boolean isRightPw(UserAccount user, String pw)
    {
        return pw.equals(user.getPw());
    }
}
