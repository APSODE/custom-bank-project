package entity.account;

public class Account {
        private String name;
        private int balance;

        public Account(String name, int balance) {
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
