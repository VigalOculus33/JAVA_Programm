import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Homework {

    public static class Account {
        protected double balance;

        public void put(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public void take(double amount) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
            }
        }

        public double getAmount() {
            return balance;
        }
    }

    public static class CreditAccount extends Account {
        @Override
        public void take(double amount) {
            double commission = amount * 0.01;
            super.take(amount + commission);
        }
    }

    public static class DepositAccount extends Account {
        private LocalDate lastWithdrawalDate;

        @Override
        public void take(double amount) {
            if (lastWithdrawalDate == null || ChronoUnit.DAYS.between(lastWithdrawalDate, LocalDate.now()) >= 30) {
                super.take(amount);
                lastWithdrawalDate = LocalDate.now();
            } else {
                System.out.println("You can't withdraw more than once a month!");
            }
        }
    }

    public static void main(String[] args) {
        // Тестирование классов
        Account acc = new Account();
        acc.put(200);
        System.out.println(acc.getAmount()); // 200

        CreditAccount creditAcc = new CreditAccount();
        creditAcc.put(200);
        creditAcc.take(100);
        System.out.println(creditAcc.getAmount()); // 99 (200 - 101)

        DepositAccount depositAcc = new DepositAccount();
        depositAcc.put(200);
        depositAcc.take(100);
        System.out.println(depositAcc.getAmount()); // 100
        depositAcc.take(50); // выводит сообщение о том, что нельзя снимать средства чаще, чем раз в месяц
    }
}

