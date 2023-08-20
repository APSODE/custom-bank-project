package controller;

import entity.user.User;

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
    public boolean isRightPw(User user, String pw)
    {
        return pw.equals(user.getPw());
    }
}
