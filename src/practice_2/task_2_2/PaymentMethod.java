package practice_2.task_2_2;

public sealed interface PaymentMethod permits CreditCard, BankTransfer, CryptoWallet {

    String process(double amount);

    double fee(double amount);
}
