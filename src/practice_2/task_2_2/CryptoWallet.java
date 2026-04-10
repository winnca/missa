package practice_2.task_2_2;

public record CryptoWallet(String address, String currency) implements PaymentMethod {

    @Override
    public String process(double amount) {
        return String.format("Криптоплатёж (%s): %.0f руб.", currency, amount);
    }

    @Override
    public double fee(double amount) {
        return amount * 0.015; // 1.5% комиссия
    }
}
