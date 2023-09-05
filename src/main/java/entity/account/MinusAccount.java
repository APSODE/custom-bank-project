package entity.account;

import controller.exceptions.*;
import controller.Judger;
import entity.user.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MinusAccount extends Account implements Serializable {
    private long credit;
    private long loan;
    private long maxRepayment;
    private long interest;
    private double rate;
    private LocalDateTime prevWorkDateTime;  // 여기에선 필요없지 않나..?
    private LocalDateTime repaymentDateTime;

    public MinusAccount(UserAccount userAccount) {
        super(userAccount);
        this.credit = 0;  //한도
        this.loan = 0;  //대출액
        this.maxRepayment = 0;  // 상환 한도
        this.interest = 0;  // 이자
        this.rate = 0;  //이자율
    }

    public MinusAccount(UserAccount userAccount, long credit, long loan, long maxRepayment, long interest, double rate) {
        super(userAccount);
        this.credit = credit;  //한도
        this.loan = loan;  //대출액
        this.maxRepayment = maxRepayment;  // 상환 한도
        this.interest = interest;  // 이자
        this.rate = rate;  //이자율
    }

    public static MinusAccount CreateObject(UserAccount userAccount, long credit, long loan, long maxRepayment, long interest, double rate) {
        return new MinusAccount(
                userAccount,
                credit,
                loan,
                maxRepayment,
                interest,
                rate
        );
    }

    public static MinusAccount CreateObject(UserAccount userAccount) {
        return new MinusAccount(userAccount);
    }

    public boolean withdrawInMinusAccount(long amount, String pw) throws NoLongerAvailableMinusWithdraw, NegativeAmountException, BalanceException, InvalidPasswordException, ZeroAmountException {
        if (!Judger.isPossibleMinusWithdraw(this.credit, this.loan, amount)) {
            throw new NoLongerAvailableMinusWithdraw("마이너스 계좌의 이용 한도를 초과하였습니다.");
        }

        if (!Judger.isPositiveArgument(amount)) {
            if (Judger.isZeroAmount(amount)) {
                throw new ZeroAmountException("출금액은 0원이 될 수 없습니다.");
            }
            throw new NegativeAmountException("출금액은 음수가 될 수 없습니다.");
        }

        if (Judger.isEnoughBalance(this.getBalance(), amount)) {
            return super.withdraw(amount, pw);

        } else {
            if (!Judger.isRightPw(this.getUserAccount(), pw)) {
                throw new InvalidPasswordException("비밀번호가 옳바르지 않습니다.");
            }

            if (!Judger.isPositiveArgument(amount)) {
                if (Judger.isZeroAmount(amount)) {
                    throw new ZeroAmountException("출금액은 0원이 될 수 없습니다.");
                }
                throw new NegativeAmountException("출금액은 음수가 될 수 없습니다.");
            }

            long remainderAmount = amount - this.getBalance();
            this.setBalance(0);
            this.loan += remainderAmount;
            return true;
        }
    }

    // IOException은 Account 클래스의 예외 발생 부분을 수정하면 throws에서 제거
    public boolean interimRepayment(long amount, String pw) throws RepaymentException, InvalidPasswordException, NegativeAmountException, NoLongerAvailableMinusWithdraw, NotExistLoanAmount {
        if (!Judger.isSmallerThanMaxRepayment(this.maxRepayment, amount)) {
            throw new RepaymentException("1회 상환한도를 초과하는 상환액입니다. 상환액을 재입력하여주십시오.");
        }

        if (!Judger.isExistLoanAmount(this.loan)) {
            throw new NotExistLoanAmount("현재 대출액이 존재하지 않습니다.");
        }

        if (!Judger.isRightPw(this.getUserAccount(), pw)) {
            throw new InvalidPasswordException("비밀번호가 옳바르지 않습니다.");
        }

        if (!Judger.isPositiveArgument(amount)) {
            if (Judger.isZeroAmount(amount)) {
                throw new ZeroAmountException("중도 상환 금액은 0원이 될 수 없습니다.");
            }
            throw new NegativeAmountException("중도 상환 금액은 음수가 될 수 없습니다.");
        }

        // 상환을 진행한 금액만큼 계좌에서 차감
        // 해당 라인은 boolean 리턴으로 정상작동 여부를 확인가능
        // 따라서 정상작동 검증 로직이 추가가 필요한경우 이를 이용하여야함.
        this.setBalance(this.getBalance() - amount);

        if (Judger.isLargerThanLoan(this.loan, amount)) {
            // 대출액을 초과한 상환액
            long repaymentRemainder = amount - this.loan;

            // loan이 amount보다 작으므로 연산을 진행하지 않고 0으로 설정
            this.loan = 0;

            // 남은 상환액을 계좌에 다시 추가한다.
            // 해당 라인은 boolean 리턴으로 정상작동 여부를 확인가능
            // 따라서 정상작동 검증 로직이 추가가 필요한경우 이를 이용하여야함.
            this.setBalance(this.getBalance() + repaymentRemainder);

        } else {
            this.loan -= amount;

        }

        return true;
    }

    public boolean repayment(String pw) throws RepaymentException, InvalidPasswordException{
        if (!Judger.isRightPw(super.getUserAccount(), pw)) {
            throw new InvalidPasswordException("비밀번호 오류, 비밀번호를 재확인하고 다시 시도하십시오.");
        }

        if (!Judger.isPossibleRepayment(this)) {
            throw new RepaymentException("상환금액 보다 계좌 잔액이 부족합니다.");
        }

        this.setBalance(
                this.getBalance() - this.loan
        );

        return true;
    }

    public void revolving() {
        // 상환일을 다음달로 변경
        this.repaymentDateTime = this.repaymentDateTime.plusMonths(1);

        // 남은 대출액에 이자를 더함.
        this.loan += this.interest;

        // 이자 재설정
        this.interest = (long) (this.loan * this.rate);
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public long getLoan() {
        return loan;
    }

    public void setLoan(long loan) {
        this.loan = loan;
    }

    public long getMaxRepayment() {
        return maxRepayment;
    }

    public void setMaxRepayment(long maxRepayment) {
        this.maxRepayment = maxRepayment;
    }

    public long getInterest() {
        return interest;
    }

    public void setInterest(long interest) {
        this.interest = interest;
    }

    public void setInterest() {
        this.interest += (long) (this.loan * this.rate);
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getPrevWorkDateTime() {
        return this.prevWorkDateTime;
    }

    public void setPrevWorkDateTime() {
        this.prevWorkDateTime = LocalDateTime.now();
    }

    public void setPrevWorkDateTime(LocalDateTime prevWorkDateTime) {
        this.prevWorkDateTime = prevWorkDateTime;
    }

    @Override
    public String toString() {
        return "AccountData" + super.toString() + "\n\n" +
                "MinusAccountData\n" +
                "credit : " + this.credit + "\n" +
                "loan : " + this.loan + "\n" +
                "max repayment : " + this.maxRepayment + "\n" +
                "interest : " + this.interest + "\n" +
                "rate : " + this.rate + "\n\n";

    }
}

