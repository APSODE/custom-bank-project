package entity.account;

import controller.exceptions.BalanceException;
import controller.Judger;
import controller.Printer;
import controller.exceptions.InvalidPasswordException;
import entity.user.UserAccount;
import controller.Inputter;

import java.io.IOException;
import java.io.Serializable;

public class Account implements Serializable {

    private UserAccount userAccount;
    private long balance;
    private long limit;

    public Account(UserAccount userAccount) {
        this.userAccount = userAccount;
        this.balance = 0;  // 생성자 파라미터로 입력을 받지 않으므로 기본값 0으로 세팅 (추후 변경 가능)
        this.limit = 0;  // 생성자 파라미터로 입력을 받지 않으므로 기본값 0으로 세팅 (추후 변경 가능)
    }

    public static Account CreateObject(UserAccount userAccount) {
        return new Account(
                userAccount
        );
    }

    public long getBalance() {
        return balance;
    }

    protected void setBalance(long amount) {
        this.balance = amount;
    }

    protected UserAccount getUserAccount() {
        return this.userAccount;
    }
    public boolean deposit(long amount, String pw) throws IOException {
        try {
            if (!Judger.isRightPw(userAccount, pw)) {
                Printer.print("비밀번호 오류");
                return false;
            }

            if (!Judger.isPossibleToDeposit(amount)) {
                Printer.print("음수 입력");
                return false;
            }

            balance += amount;
            return true;
        } catch (IllegalArgumentException exception) {
            Printer.print("음수 입력");
        } catch (IOException exception) {
            Printer.print("IO 오류");
        }
        return false;
    }

        public boolean withdraw (long amount, String pw) throws BalanceException, IllegalArgumentException, IOException
        {
            try {
                if (Judger.isPossibleToWithdraw(balance, amount)) {
                    balance -= amount;
                }
            } catch (BalanceException | IllegalArgumentException exception) {
                Printer.print(exception.getMessage());
            }
            return true;
        }


        // MinusAccount에서 사용할 입출금 메소드 ( MinusAccount에도 비밀번호 인증을 사용한다면 해당 메소드 제거 )
        protected boolean depositByOverRepayment ( long amount){
            int errorCount = 0;
            try {
                this.balance += amount;

            } catch (Exception error) {
                errorCount++;

                // Printer 클래스의 print메소드를 static으로 수정 후 해당 라인을 Printer클래스의 print메소드로 교체 진행
                Printer.print("알 수 없는 에러 발생.");

            } finally {
                return errorCount == 0;
            }
        }

        // MinusAccount에서 사용할 입출금 메소드 ( MinusAccount에도 비밀번호 인증을 사용한다면 해당 메소드 제거 )
        protected boolean withdrawByRepayment ( long amount) throws BalanceException {
            if (Judger.isPossibleToWithdraw(this.balance, amount)) {
                this.balance -= amount;
                return true;

            } else {
                return false;
            }
        }

        public long getLimit () {
            return limit;
        }

        public void setLimit ( long limit){
            this.limit = limit;
        }

        @Override
        public String toString () {
            return "balance : " + this.balance + "\n" +
                    "limit: " + this.limit + "\n";

        }

    }