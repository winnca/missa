package practice_3.task_3_2;

public class BankResult {
    public static void main(String[] args){
        BankAccount account1 = new BankAccount("1234", 90000, "Sam", "4287");
        BankAccount account2 = new BankAccount("7777", 200000, "Dean", "7878");

        System.out.println(account1);
        System.out.println(account2);

        account1.withdraw("9999", 1000);  // Invalid PIN → failedAttempts=1
        account1.withdraw("8888", 1000);  // Invalid PIN → failedAttempts=2
        account1.withdraw("7777", 1000);  // Invalid PIN → failedAttempts=3 → BLOCKED

        account1.withdraw("4287", 1000);  // Account blocked

        account2.withdraw("7878", 50000); // Good operation
        account2.deposit(25000);          // Good operation
    }
}
