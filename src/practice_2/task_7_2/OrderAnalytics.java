package practice_2.task_7_2;

import java.util.*;
import java.util.stream.*;

public class OrderAnalytics {

    // Record Order с методом total()
    record Order(String customer, String product, double price, int quantity, String category) {
        double total() {
            return price * quantity;
        }
    }

    public static void main(String[] args) {
        // Создание списка заказов (10+)
        List<Order> orders = Arrays.asList(
                new Order("Иван Петров", "Ноутбук", 45000, 1, "Электроника"),
                new Order("Мария Иванова", "Смартфон", 25000, 2, "Электроника"),
                new Order("Иван Петров", "Мышь", 1500, 3, "Компьютерные аксессуары"),
                new Order("Алексей Смирнов", "Клавиатура", 3500, 1, "Компьютерные аксессуары"),
                new Order("Елена Козлова", "Кофеварка", 12000, 1, "Бытовая техника"),
                new Order("Дмитрий Новиков", "Чайник", 3000, 2, "Бытовая техника"),
                new Order("Анна Морозова", "Пылесос", 15000, 1, "Бытовая техника"),
                new Order("Иван Петров", "Монитор", 18000, 1, "Компьютерные аксессуары"),
                new Order("Мария Иванова", "Наушники", 5000, 1, "Аудиотехника"),
                new Order("Сергей Волков", "Планшет", 30000, 1, "Электроника"),
                new Order("Ольга Соколова", "Фен", 2500, 2, "Бытовая техника"),
                new Order("Игорь Козлов", "Внешний диск", 6000, 1, "Компьютерные аксессуары")
        );

        // 1. Заказы дороже 5000 руб.
        System.out.println("1. Заказы дороже 5000 руб.:");
        orders.stream()
                .filter(order -> order.total() > 5000)
                .forEach(System.out::println);

        // 2. Уникальные имена клиентов, отсортированные по алфавиту
        System.out.println("\n2. Уникальные клиенты (по алфавиту):");
        List<String> uniqueCustomers = orders.stream()
                .map(Order::customer)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        uniqueCustomers.forEach(System.out::println);

        // 3. Общая выручка
        System.out.println("\n3. Общая выручка:");
        double totalRevenue = orders.stream()
                .mapToDouble(Order::total)
                .sum();
        System.out.printf("Общая выручка: %.2f руб.%n", totalRevenue);

        // 4. Самый дорогой заказ
        System.out.println("\n4. Самый дорогой заказ:");
        orders.stream()
                .max(Comparator.comparingDouble(Order::total))
                .ifPresent(System.out::println);

        // 5. Количество заказов в каждой категории
        System.out.println("\n5. Количество заказов по категориям:");
        Map<String, Long> ordersByCategory = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.counting()
                ));
        ordersByCategory.forEach((category, count) ->
                System.out.printf("  %s: %d заказ(ов)%n", category, count));

        // 6. Средняя стоимость заказа по каждому клиенту
        System.out.println("\n6. Средняя стоимость заказа по клиентам:");
        Map<String, Double> avgOrderByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.averagingDouble(Order::total)
                ));
        avgOrderByCustomer.forEach((customer, avg) ->
                System.out.printf("  %s: %.2f руб.%n", customer, avg));

        // 7. Разделение на дорогие и дешёвые заказы
        System.out.println("\n7. Разделение заказов (дорогие > 3000 руб.):");
        Map<Boolean, List<Order>> partitionedOrders = orders.stream()
                .collect(Collectors.partitioningBy(
                        order -> order.total() > 3000
                ));

        System.out.println("Дорогие заказы (> 3000 руб.):");
        partitionedOrders.get(true)
                .forEach(order -> System.out.println("  " + order));

        System.out.println("\nДешёвые заказы (<= 3000 руб.):");
        partitionedOrders.get(false)
                .forEach(order -> System.out.println("  " + order));

        // 8. Список товаров дороже 1000 руб., без дубликатов, в верхнем регистре
        System.out.println("\n8. Уникальные товары дороже 1000 руб. (в верхнем регистре):");
        List<String> expensiveProducts = orders.stream()
                .filter(order -> order.price() > 1000)
                .map(Order::product)
                .distinct()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        expensiveProducts.forEach(System.out::println);
    }
}
