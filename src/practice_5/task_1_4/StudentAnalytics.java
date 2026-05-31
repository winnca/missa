package practice_5.task_1_4;

import java.util.*;
import java.util.stream.*;

public class StudentAnalytics {

    record Student(String name, String faculty, int grade, int year) {}

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Анна Иванова", "ИТ", 95, 2),
                new Student("Борис Петров", "Математика", 78, 3),
                new Student("Вера Сидорова", "ИТ", 88, 1),
                new Student("Геннадий Козлов", "ИТ", 92, 2),
                new Student("Дина Новикова", "Математика", 65, 1),
                new Student("Егор Федоров", "Физика", 85, 3),
                new Student("Жанна Морозова", "ИТ", 71, 1),
                new Student("Зоя Лебедева", "Физика", 90, 2)
        );

        // Задание 1: Отфильтровать студентов ИТ с оценкой >= 85, отсортировать по имени
        System.out.println("=== Лучшие студенты ИТ (>=85) ===");
        students.stream()
                .filter(s -> s.faculty().equals("ИТ") && s.grade() >= 85)
                .sorted(Comparator.comparing(Student::name))
                .map(s -> s.name() + ": " + s.grade())
                .forEach(System.out::println);

        // Задание 2: Средняя оценка по факультетам
        System.out.println("\n=== Средний балл по факультетам ===");
        Map<String, Double> avgByFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::faculty,
                        Collectors.averagingInt(Student::grade)
                ));
        avgByFaculty.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.printf("%s: %.1f%n", e.getKey(), e.getValue()));

        // Задание 3: Количество студентов каждого курса
        System.out.println("\n=== Студентов по курсам ===");
        students.stream()
                .collect(Collectors.groupingBy(Student::year, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println("Курс " + e.getKey() + ": " + e.getValue() + " студентов"));

        // Задание 4: Лучший студент каждого факультета
        System.out.println("\n=== Лучший студент каждого факультета ===");
        Map<String, Optional<Student>> bestByFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::faculty,
                        Collectors.maxBy(Comparator.comparingInt(Student::grade))
                ));
        bestByFaculty.forEach((faculty, student) ->
                student.ifPresent(s ->
                        System.out.printf("%s: %s (%d)%n", faculty, s.name(), s.grade())
                )
        );

        // Задание 5: Все имена студентов в одну строку через запятую
        System.out.println("\n=== Все студенты ===");
        String allNames = students.stream()
                .map(Student::name)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(allNames);

        // Задание 6: Статистика оценок
        System.out.println("\n=== Статистика оценок ===");
        IntSummaryStatistics stats = students.stream()
                .mapToInt(Student::grade)
                .summaryStatistics();
        System.out.printf("Мин: %d, Макс: %d, Среднее: %.1f, Всего: %d%n",
                stats.getMin(), stats.getMax(), stats.getAverage(), stats.getCount());
    }
}