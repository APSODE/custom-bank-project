package entity.account;

public class account {
        private String name;
        private int balance;

        public account(String name, int balance) {
            this.name = name;
            this.balance = balance;
        }

        public boolean withdraw(int money) {
            if (money > 0 && balance >= money) {
                balance -= money;
                return true;
            }
            return false;
        }
    }
