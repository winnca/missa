package practice_3.task_3_2;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final String owner;
    private int failedAttempts;
    private boolean blocked;
    private String pin;

    public BankAccount(String accountNumber, double balance, String owner, String pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.failedAttempts = 0;
        this.blocked = false;
        this.pin = pin;
    }

    public void withdraw(String enteredPin, double amount){
        if (blocked) {
            System.out.println("Account blocked");
            return;
        }
        if (!validatePin(enteredPin)){
            failedAttempts++;
            if (failedAttempts == 3) {
                System.out.println("Account blocked");
                blocked = true;
            }
            return;
        }
        failedAttempts = 0;
        if (balance - amount > 0){
            balance -= amount;
            System.out.println("Good operation");
        } else System.out.println("Balance error");
    }
    public boolean validatePin(String pin){
        return this.pin.equals(pin);
    }
    public void deposit(double amount){
        if (blocked) System.out.println("Account blocked");
        else if (amount <= 0) System.out.println("Can't operation");
        else{
            balance += amount;
            System.out.println("Good operation");
        }
    }
    public void setBalance(double balance) { this.balance = balance;}
    public void setFailedAttempts(int failedAttempts) {this.failedAttempts = failedAttempts;}
    public void setBlocked(boolean blocked) {this.blocked = blocked;}
    public String getAccountNumber() {return accountNumber;}
    public double getBalance() {return balance;}
    public String getOwner() {return owner;}
    public int getFailedAttempts() {return failedAttempts;}
    public boolean isBlocked() {return blocked;}
    public String getMaskedBalance(){
        if (balance >= 100000) return "********";
        else return String.valueOf(balance);
    }
    @Override
    public String toString() {
        String status = blocked ? " [BLOCKED]" : "";
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + getMaskedBalance() +
                ", failedAttempts=" + failedAttempts +
                status +
                '}';
    }
}
