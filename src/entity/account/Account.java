package entity.account;

public class Account {
    private userAccount UsserAccount;
    private long balance;
    private long limit;

    public long getBalance() {
        return balance;

    }
    public boolean deposit(long amount, String pw){
    }
    public boolean withdraw(long amount, String pw){

    }
    public long getLimit() {
        return limit;
    }
    public void setLimit(long limit){
        this.limit = limit;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}