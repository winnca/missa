package practice_2.task_7_1;

import java.util.*;
import java.util.function.*;

public class RefactorStep2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине
        // Нельзя заменить на ссылку на метод, т.к. используется внешний Comparator.comparingInt
        cities.sort(Comparator.comparingInt(String::length));

        // 2. Вывод каждого элемента (ссылка на метод)
        cities.forEach(System.out::println);

        // 3. Преобразование в верхний регистр (ссылка на метод)
        Function<String, String> toUpper = String::toUpperCase;

        // 4. Проверка длины > 5
        // Нельзя заменить на ссылку на метод, т.к. есть дополнительное условие (> 5)
        Predicate<String> isLong = s -> s.length() > 5;

        // 5. Формирование строки с восклицательным знаком
        // Нельзя заменить на ссылку на метод, т.к. выполняется конкатенация строк
        Function<String, String> exclaim = s -> s + "!";

        // 6. Создание нового списка (ссылка на конструктор)
        Supplier<List<String>> listFactory = ArrayList::new;

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
