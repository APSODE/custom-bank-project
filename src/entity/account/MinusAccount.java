package entity.account;

import entity.user.UserAccount;

public class MinusAccount extends Account {
    private long credit;
    private long loan;
    private long maxRepayment;
    private long interest;
    private double rate;

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

    public boolean isPossibleRepayment(long amount) {
        // 1회 상환한도인 maxRepayment값보다 현재 상환 시도 중인 상환액인 amount가 작거나 같아야함.
        return this.maxRepayment >= amount;
    }

    public boolean isLargerThanLoan(long amount) {
        // 현재 시도 중인 상환을 기준으로 상환액이 총 대출액을 초과했는지 확인
        return this.loan > amount;
    }

    public boolean repayment(long amount) {
        if (isPossibleRepayment(amount)) {
            if (isLargerThanLoan(amount)) {
                this.loan -= amount;
            } else {
                // 대출액을 초과한 상환액
                long repaymentRemainder = amount - this.loan;

                // loan이 amount보다 작으므로 연산을 진행하지 않고 0으로 설정
                this.loan = 0;

                // 남은 상환액을 계좌에 다시 추가한다.
                // 해당 라인은 boolean 리턴으로 정상작동 여부를 확인가능
                // 따라서 정상작동 검증 로직이 추가가 필요한경우 이를 이용하여야함.
                super.depositByOverRepayment(repaymentRemainder);

            }

            // 상환을 진행한 금액만큼 계좌에서 차감
            // 해당 라인은 boolean 리턴으로 정상작동 여부를 확인가능
            // 따라서 정상작동 검증 로직이 추가가 필요한경우 이를 이용하여야함.
            super.withdrawByRepayment(amount);

            return true;

        } else {
            return false;
        }

    }

    public void revolving() {
        // 현재 기능 미완성
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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

