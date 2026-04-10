package practice_2.task_7_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RefactorStep1 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине (лямбда)
        cities.sort((a, b) -> Integer.compare(a.length(), b.length()));

        // 2. Вывод каждого элемента (лямбда)
        cities.forEach(city -> System.out.println(city));

        // 3. Преобразование в верхний регистр (лямбда)
        Function<String, String> toUpper = s -> s.toUpperCase();

        // 4. Проверка длины > 5 (лямбда)
        Predicate<String> isLong = s -> s.length() > 5;

        // 5. Формирование строки с восклицательным знаком (лямбда)
        Function<String, String> exclaim = s -> s + "!";

        // 6. Создание нового списка (лямбда)
        Supplier<List<String>> listFactory = () -> new ArrayList<>();

        // Использование
        List<String> result = listFactory.get();
        for (String city : cities) {
            if (isLong.test(city)) {
                result.add(toUpper.apply(city));
            }
        }
        System.out.println("Длинные города: " + result);

        // Демонстрация exclaim
        System.out.println("С восклицательным знаком: ");
        cities.stream().map(exclaim).forEach(s -> System.out.print(s + " "));
    }
}
