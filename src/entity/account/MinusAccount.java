package entity.account;

public class MinusAccount extends Account {
    private long credit;
    private long loan;
    private long maxRepayment;
    private long interest;
    private float rate;

    public MinusAccount(long credit, long loan, long maxRepayment, long interest, float rate) {
        this.credit = credit;
        this.loan = loan;
        this.maxRepayment = maxRepayment;
        this.interest = interest;
        this.rate = rate;
    }

    public static MinusAccount CreateObject(long credit, long loan, long maxRepayment, long interest, float rate) {
        return new MinusAccount(
                credit,
                loan,
                maxRepayment,
                interest,
                rate
        );
    }

    public boolean repayment() {
        // 현재 기능 미완성
        return true;
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
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

