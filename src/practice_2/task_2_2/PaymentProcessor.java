package practice_2.task_2_2;

public class PaymentProcessor {

    public static void describe(PaymentMethod pm) {
        // Используем switch с паттерн-матчингом (Java 21+)
//        switch (pm) {
//            case CreditCard cc -> System.out.printf(
//                    "  Описание: кредитная карта, владелец: %s, номер: ****%s%n",
//                    cc.holder(),
//                    cc.cardNumber().substring(cc.cardNumber().length() - 4)
//            );
//
//            case BankTransfer bt -> System.out.printf(
//                    "  Описание: банковский перевод, банк: %s, IBAN: %s%n",
//                    bt.bankName(),
//                    bt.iban()
//            );
//
//            case CryptoWallet cw -> System.out.printf(
//                    "  Описание: криптокошелёк, адрес: %s, валюта: %s%n",
//                    cw.address(),
//                    cw.currency()
//            );
//        }
    }
}
