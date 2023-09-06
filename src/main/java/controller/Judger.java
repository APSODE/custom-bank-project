package controller;

import exceptions.BalanceException;
import exceptions.PointException;
import entity.account.MinusAccount;
import entity.user.UserAccount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Judger {
    // 해당 메소드에서 Exception을 발생시키는 것이 아닌 해당 메소드를 사용하는 클래스에서 Exception을 발생시키도록 수정해여야 한다.
    public static boolean isPossibleToWithdraw(long balance, long amount) throws BalanceException {
        if (amount > 0) {
            if (balance - amount >= 0)
                return true;
            throw new BalanceException("잔액 부족");
        }
        throw new IllegalArgumentException("음수인 값 입력");
    }

    public static boolean isEnoughBalance(long balance, long amount) {
        return balance >= amount;
    }

    public static boolean isPositiveArgument(long amount) {
        return amount > 0;
    }

    public static boolean isZeroAmount(long amount) {
        return amount == 0;
    }

    public static boolean isRightPw(UserAccount userAccount, String pw) {
        return pw.equals(userAccount.getPw());
    }

    public static boolean isOverThanMinUsePoint(long point) {
        return point >= 5000;
    }

    public static boolean isShortOfBalance(long balance, long amount, long point) {
        return (balance - point - amount) >= 0;
    }

    public static boolean isPossibleToUsePoint(long balance, long amount, long point) throws PointException {
        if (point <= 5000) {
            throw new PointException("포인트 부족");
        }
        if (balance - point - amount >= 0) {
            return true;
        }
        throw new PointException("잔액 부족");
    }

    public static boolean isSmallerThanMaxRepayment(long maxRepayment, long amount) {
        // 1회 상환한도인 maxRepayment값보다 현재 상환 시도 중인 상환액인 amount가 작거나 같아야함.
        return maxRepayment >= amount;
    }

    public static boolean isLargerThanLoan(long loan, long amount) {
        // 현재 시도 중인 상환을 기준으로 상환액이 총 대출액을 초과했는지 확인
        return !(loan > amount);
    }

    public static boolean isPossibleRepayment(MinusAccount minusAccount) {
        long currentTotalLoan = minusAccount.getLoan();
        long currentBalance = minusAccount.getBalance();

        return currentBalance >= currentTotalLoan;
    }

    public static boolean isNotOverTheLimit(long limit, long amount) {
        return limit >= amount;
    }

    public static boolean isValidPhoneNumber(String inputPhoneNumber) {
        String phoneNumberRegex = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-(?:\\d{4})$";
        Pattern phoneNumberRegexPattern = Pattern.compile(phoneNumberRegex);
        Matcher phoneNumberPatternMatcher = phoneNumberRegexPattern.matcher(inputPhoneNumber);

        return phoneNumberPatternMatcher.matches();
    }

    public static boolean isPossibleMinusWithdraw(long minusWithdrawLimit, long currentLoan, long amount) {
        return minusWithdrawLimit >= (currentLoan + amount);
    }

    public static boolean isExistLoanAmount(long currentLoanAmount) {
        return currentLoanAmount > 0;
    }
}
