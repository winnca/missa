package practice_3.task_4_1;

public class DiscountCalculator {

    // Перегрузка метода calculateDiscount:
    // 1. По типу клиента (String)
    public static double calculateDiscount(double price, String customerType) {
        return switch (customerType.toLowerCase()) {
            case "vip" -> price * 0.30;
            case "regular" -> price * 0.10;
            case "new" -> price * 0.05;
            default -> 0;
        };
    }

    // 2. По количеству покупок
    public static double calculateDiscount(double price, int purchaseCount) {
        if (purchaseCount >= 100) return price * 0.20;
        if (purchaseCount >= 50) return price * 0.15;
        if (purchaseCount >= 10) return price * 0.10;
        return 0;
    }

    // 3. По промокоду
    public static double calculateDiscount(double price, String promoCode, boolean isFirstOrder) {
        double discount = 0;
        if ("SAVE10".equals(promoCode)) discount = price * 0.10;
        if ("SAVE20".equals(promoCode)) discount = price * 0.20;
        if (isFirstOrder) discount += price * 0.05; // Дополнительная скидка новым
        return Math.min(discount, price * 0.50); // Не более 50%
    }

    public static double calculateDiscount(double price, int age, boolean isStudent){
        double discount = 0;
        if (age >= 18 && age <= 23) discount = price * 0.1;
        return discount;
    }

    public static void main(String[] args) {
        double price = 10000.0;

        System.out.println("Discount VIP-client: " + calculateDiscount(price, "vip") + " rub.");
        System.out.println("Discount 75 sales: " + calculateDiscount(price, 75) + " rub.");
        System.out.println("Discount SAVE20 + 1 order: " +
                calculateDiscount(price, "SAVE20", true) + " rub.");
        System.out.println("Discount for students: " + calculateDiscount(price, 19, true));
    }
}

