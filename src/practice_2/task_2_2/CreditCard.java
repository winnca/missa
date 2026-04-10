package practice_2.task_2_2;

public record CreditCard(String cardNumber, String holder) implements PaymentMethod {
    @Override
    public String process(double amount) {
        // Получаем последние 4 цифры номера карты
        String last4 = cardNumber.length() >= 4
                ? cardNumber.substring(cardNumber.length() - 4)
                : cardNumber;
        return String.format("Оплата картой *%s: %.0f руб.", last4, amount);
    }

    @Override
    public double fee(double amount) {
        return amount * 0.02; // 2% комиссия
    }
}
