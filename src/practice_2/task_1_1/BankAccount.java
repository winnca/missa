package practice_2.task_1_1;

public class BankAccount {
    private String owner;
    private double balance;
    private String accountNumber;

    private static int totalAccounts; // общий счётчик всех созданных счетов
    private static String bankName; // название банка, общее для всех счетов

    // статический блок
    static {
        bankName = "Java Bank";
        System.out.println("Банковская система инициализирована");
    }

    // блок инициализации
    {
        ++totalAccounts;
        System.out.println("Создание счёта #" + totalAccounts);
    }

    // конструктор
    BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
        this.accountNumber = "ACC-" + String.valueOf(totalAccounts);
    }

    // увеличивает баланс
    void deposit(double amount){
        if (amount <= 0){
            System.out.println("Ошибка: сумма должна быть положительной");
        } else{
            this.balance += amount;
        }
    }

    // уменьшает баланс
    void withdraw(double amount){
        if (this.balance - amount <= 0){
            System.out.println("Ошибка: недостаточно средств");
        } else{
            this.balance -= amount;
        }
    }

    // возвращает количество созданных счетов
    static int getTotalAccounts() {
        return totalAccounts;
    }

    @Override
    public String toString() {
        return '[' + accountNumber + ']' +
                " " + owner + ": " +
                String.format("%.2f", balance) + " руб.";
    }

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount("Алиса", 1000);
        BankAccount a2 = new BankAccount("Борис", 500);

        System.out.println(a1);
        System.out.println(a2);

        a1.deposit(500);
        System.out.println("После пополнения: " + a1);

        a1.withdraw(200);
        System.out.println("После снятия: " + a1);

        a1.withdraw(5000);

        a2.deposit(-100);

        System.out.println("Всего счетов: " + BankAccount.getTotalAccounts());
    }
}
