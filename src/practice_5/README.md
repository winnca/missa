## Студенты

* Винницкий Александр Алексеевич = ПИ24-2в = №2.

* Руденко Виктория Николаевна = ПИ24-1в = №14.

* Пушкарёва Екатерина Дмитриевна = ПИ24-1в = №13.

# Содержание

## Лабораторная 5

### [Тест](#title12)

## Часть 1: Коллекции

### [Задание 1.1: List — Управление списком задач](#title0)

### [Задание 1.2: Set — Анализ текста](#title1)

### [Задание 1.3: Map — Телефонная книга](#title2)

### [Задание 1.4: Stream API — Обработка данных студентов](#title3)

## Часть 2: Потоки ввода/вывода

### [Задание 2.1: Работа с текстовыми файлами](#title4)

### [Задание 2.2: Сериализация объектов](#title5)

## Часть 3: Многопоточность

### [Задание 3.1: Создание и запуск потоков](#title6)

### [Задание 3.2: Синхронизация — Безопасный счётчик](#title7)

### [Задание 3.3: Производитель-Потребитель (Producer-Consumer)](#title8)

### [Задание 3.4: Прерывание потоков](#title9)

## Часть 4: Комплексное задание

### [Задание 4.1: Параллельный поиск в файлах](#title10)

* ### [Часть 5: Контрольные вопросы](#title11)

<br>
<br>

---

### <a id="title12">Тест</a>

<br>
<br>

<details open>
    <summary>result</summary>
    <br>
    <img src="img_14.png"/>
    <br>
    <img src="img_15.png"/>
    <br>
    <img src="img_16.png"/>
    <br>
    <img src="img_17.png"/>
    <br>
    <img src="img_17.png"/>
    <br>
    <img src="img_18.png"/>
    <br>
    <img src="img_19.png"/>
    <br>
    <img src="img_20.png"/>
    <br>
    <img src="img_21.png"/>
    <br>
    <img src="img_22.png"/>
    <br>
    <img src="img_23.png"/>
    <br>
    <img src="img_24.png"/>
    <br>
    <img src="img_25.png"/>
    <br>
    <img src="img_26.png"/>
    <br>
    <img src="img_27.png"/>
    <br>
    <img src="img_28.png"/>
    <br>
    <img src="img_29.png"/>
    <br>
    <img src="img_30.png"/>
    <br>
    <img src="img_31.png"/>
    <br>
    <img src="img_32.png"/>
    <br>
    <img src="img_33.png"/>
    <br>
    <img src="img_34.png"/>
    <br>
    <img src="img_35.png"/>
    <br>
    <img src="img_36.png"/>
</details>

---

## Часть 1: Коллекции

### <a id="title0">Задание 1.1: List — Управление списком задач</a>

Реализуйте класс TaskManager, хранящий список задач в ArrayList<String>. Реализуйте методы:

* addTask(String task) — добавить задачу в конец.

* addTaskAtPosition(String task, int position) — вставить на указанную позицию; если позиция недопустима — добавить в конец.

* removeTask(String task) — удалить задачу по имени, вернуть true если удалено.

* getTask(int index) — получить по индексу или null если вне диапазона.

* searchTasks(String keyword) — найти задачи, содержащие ключевое слово (без учёта регистра).

* sortTasks() — сортировка по алфавиту.

* printAll() — вывод с нумерацией.

Протестируйте: добавьте 4 задачи, вставьте срочную задачу в начало, найдите по ключевому слову «тест», отсортируйте.

### Решение

```
package practice_5.task_1_1;

import java.util.*;

public class TaskManager {
    ArrayList<String> taskList;
    public TaskManager(){this.taskList = new ArrayList<>();}
    public void addTask(String task){ taskList.add(task);}
    public void addTaskPosition(String task, int position){ // недопустимый индекс = который не укладывается в диапазон arraylist
        if (position >= 0 && position <= taskList.size()) taskList.add(position, task);
        else taskList.add(task);
    }
    public boolean removeTask(String task){return taskList.remove(task);}
    public String getTask(int index){
        try{
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e){
            //System.out.println("Вышли за границы массива" + e);
            return null;
        }
    }
    public void searchTasks(String keyword){
        if (keyword == null || keyword.isEmpty()) return;
        String keywordHelp = keyword.toLowerCase();
        for (String s : taskList){
            if (s.toLowerCase().contains(keywordHelp)) System.out.println(s+"\n");
        }
    }
    public void sortTasks(){ Collections.sort(taskList);}
    public void printAll(){
        if (taskList.isEmpty()) System.out.println("Список пуст");
        else{
            for (int i=0;i<taskList.size();i++){
                System.out.println((i+1) + " " + taskList.get(i));
            }
        }
        System.out.println();
    }
}

class Main{
    public static void main(String[] args){
        TaskManager taskManager = new TaskManager();

        taskManager.printAll();
        taskManager.addTask("Сделать домашку");
        taskManager.addTask("Погулять с собакой");
        taskManager.addTask("Покормить домашних животных");
        taskManager.addTask("Отправить Тест преподавателю");
        taskManager.printAll();

        taskManager.addTaskPosition("Сдать дз сегодня по Java", 0);
        taskManager.printAll();

        taskManager.searchTasks("тест");
        taskManager.printAll();

        taskManager.sortTasks();
        taskManager.printAll();
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img.png"/>
</details>

<br>

### <a id="title1">Задание 1.2: Set — Анализ текста</a>

Реализуйте класс TextAnalyzer со статическими методами:

* getUniqueWords(String text) → Set<String> — все уникальные слова (в нижнем регистре, через TreeSet для сортировки), разделённые по [\\s.,!?;:\"'()-]+.

* commonWords(String text1, String text2) → Set<String> — пересечение уникальных слов обоих текстов.

* uniqueToFirst(String text1, String text2) → Set<String> — слова, есть только в первом тексте.

* wordFrequency(String text) → Map<String, Integer> — частота каждого слова.

* topWords(String text, int n) → List<Map.Entry<String, Integer>> — топ-N слов по убыванию частоты.

Протестируйте на двух текстах про Java и Python из условия. Выведите уникальные слова текста 1, общие слова, слова только в тексте 1, топ-5 частых слов текста 1.

### Решение

```
package practice_5.task_1_2;

import java.util.*;

public class TaskAnalyzer {
    private static final String REGEX = "[\\s.,!?;:\"'()\\-]+";
    public static Set<String> getUniqueWords(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Return пустой TreeSet");
            return new TreeSet<>();
        }
        Set<String> set = new TreeSet<>();
        String[] arrays = text.toLowerCase().split(REGEX);
        for (String s : arrays) {
            if (!s.isEmpty()) set.add(s);
        }
        return set;
    }

    public static Set<String> commonWords(String text1, String text2) {
        Set<String> set1 = getUniqueWords(text1);
        Set<String> set2 = getUniqueWords(text2);
        set1.retainAll(set2);
        return set1;
    }

    public static Set<String> uniqueToFirst(String text1, String text2) {
        Set<String> set1 = getUniqueWords(text1);
        Set<String> set2 = getUniqueWords(text2);
        set1.removeAll(set2);
        return set1;
    }
    public static Map<String, Integer> wordFrequency(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Return пустой Map");
            return new HashMap<>();
        }
        Map<String, Integer> map = new HashMap<>();
        String[] arrays = text.toLowerCase().split(REGEX);
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].isEmpty()) continue;
            if (map.containsKey(arrays[i])) continue;
            int count = 1;
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[i].equals(arrays[j])) count++;
            }
            map.put(arrays[i], count);
        }
        return map;
    }
    public static List<Map.Entry<String, Integer>> topWords(String text, int n) {
        Map<String, Integer> frequencyMap = wordFrequency(text);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        for (int i = 0; i < n && i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }
}

class Main{
    public static void main(String[] args){
        String text1 = "Сегодня по плану сделать домашку 4 и 5 по СТП, выложить на gitflic для преподавателя. Также заняться подготовкой к зачёту по ММММ, потому что это пирамида Мавроди";
        String text2 = "На самом деле в университетах есть негласное правило отчислять парней с очного и очно-заочного обучения. Делается для того, чтобы набирать парней в армию";

        System.out.println(TaskAnalyzer.getUniqueWords(text1));
        System.out.println(TaskAnalyzer.getUniqueWords(text2));

        System.out.println(TaskAnalyzer.commonWords(text1, text2));

        System.out.println(TaskAnalyzer.uniqueToFirst(text1, text2));

        System.out.println(TaskAnalyzer.topWords(text1, 2));
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_1.png"/>
</details>

### <a id="title2">Задание 1.3: Map — Телефонная книга</a>

Реализуйте класс PhoneBook, где contacts — это TreeMap<String, List<String>> (имя → список номеров). Реализуйте методы:

* addContact(String name, String phone) — добавить номер (если имя уже есть — добавить номер к списку, иначе создать новую запись).

* findByName(String name) → список номеров или пустой список.

* searchByName(String partialName) → Map<String, List<String>> — все записи, где имя содержит partialName (без учёта регистра).

* removePhone(String name, String phone) — удалить конкретный номер; если больше нет номеров — удалить контакт. Вернуть true при успехе.

* findDuplicatePhones() → List<String> — номера, встречающиеся у нескольких контактов.

* printStats() — всего контактов, всего номеров, среднее номеров на контакт.

Протестируйте: добавьте 5 контактов (включая дублирующий номер), найдите по «анна», найдите дублирующие номера, выведите статистику.

### Решение

```
package practice_5.task_1_3;

import java.util.*;

public class PhoneBook {
    private TreeMap<String, List<String>> contacts;
    public PhoneBook(){this.contacts=new TreeMap<>();}
    public void addContact(String name, String phone){
        if (name == null || phone == null || name.isEmpty() || phone.isEmpty()) return;
        contacts.computeIfAbsent(name, el -> new ArrayList<>()).add(phone);
    }
    public List<String> findByName(String name){
        if (name == null || name.isEmpty() || !contacts.containsKey(name)) {
            System.out.println("Список пуст");
            return new ArrayList<>();
        }
        return contacts.get(name);
    }
    public Map<String, List<String>> searchByName(String partialName){
        if (partialName == null || partialName.isEmpty()) {
            System.out.println("Пуст");
            return new HashMap<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        String help = partialName.toLowerCase();
        for (Map.Entry<String, List<String>> entry : contacts.entrySet()){
            if (entry.getKey().toLowerCase().contains(help)) map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
    public boolean removePhone(String name, String phone){
        if (name == null || phone == null || name.isEmpty() || phone.isEmpty() || !contacts.containsKey(name)){
            System.out.println("Некорректные данные");
            return false;
        }
        List<String> phones = contacts.get(name);
        boolean flag = phones.remove(phone);
        if (flag && phones.isEmpty()) {
            contacts.remove(name);
        }
        return flag;
    }
    public List<String> findDuplicatePhones() {
        List<String> allPhones = new ArrayList<>();
        for (List<String> phoneList : contacts.values()) {
            allPhones.addAll(phoneList);
        }
        List<String> duplicates = new ArrayList<>();

        for (int i = 0; i < allPhones.size(); i++) {
            String currentPhone = allPhones.get(i);
            if (currentPhone.isEmpty()) continue;
            if (duplicates.contains(currentPhone)) continue;
            int count = 1;
            for (int j = i + 1; j < allPhones.size(); j++) {
                if (currentPhone.equals(allPhones.get(j))) {
                    count++;
                }
            }
            if (count > 1) {
                duplicates.add(currentPhone);
            }
        }
        for (int i = 0; i < duplicates.size() - 1; i++) {
            for (int j = 0; j < duplicates.size() - i - 1; j++) {
                if (duplicates.get(j).compareTo(duplicates.get(j + 1)) > 0) {
                    String temp = duplicates.get(j);
                    duplicates.set(j, duplicates.get(j + 1));
                    duplicates.set(j + 1, temp);
                }
            }
        }
        return duplicates;
    }
    public void printStats() {
        int countPhones = 0;
        for (List<String> list : contacts.values()) {
            countPhones += list.size();
        }
        double averagePhones = contacts.isEmpty() ? 0 : (double) countPhones / contacts.size();
        System.out.println("Всего контактов: " + contacts.size());
        System.out.println("Всего номеров: " + countPhones);
        System.out.println("Среднее количество номеров на контакт:" + averagePhones);
    }
    public void printAll(){
        if (contacts == null || contacts.isEmpty()){
            System.out.println("Заполни контакты");
            return;
        }
        for (Map.Entry<String, List<String>> entry: contacts.entrySet()) System.out.println(entry.getKey() + " " + entry.getValue());
        System.out.println();
    }
}

class Main{
    public static void main(String[] args){
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addContact("Александр Винницкий", "+7 (926) 777-88-99");
        phoneBook.addContact("Александр Винницкий", "+7 (926) 777-17-99");
        phoneBook.addContact("Екатерина Пушкарёва", "+7 (926) 777-88-13");
        phoneBook.addContact("Виктория Руденко", "+7 (916) 888-90-32");

        phoneBook.addContact("Анна Хилькевич", "+7 (926) 777-88-13");
        phoneBook.addContact("Максимилиан", "+7 (916) 333-90-32");

        phoneBook.printAll();

        Map<String, List<String>> map = phoneBook.searchByName("анна");
        System.out.println(map + "\n");

        System.out.println(phoneBook.findDuplicatePhones());

        phoneBook.printStats();
        System.out.println();
        phoneBook.removePhone("Анна Хилькевич", "+7 (926) 777-88-13");
        System.out.println();
        phoneBook.printAll();
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_2.png"/>
</details>

<br>

### <a id="title3">Задание 1.4: Stream API — Обработка данных студентов</a>

Изучите и запустите программу анализа данных студентов. Убедитесь в правильности вывода для каждого из 6 заданий. Ответьте: 

* (1) чем Collectors.groupingBy отличается от ручной группировки циклом? 

* (2) что возвращает summaryStatistics()?

Ответы:

1) Вместо вложенных циклов и если или вместо computeIfAbsent заменяет на 1 понятную строку. Можно подсчитывать внутри значения или максимум высчитывать.

2) Return объект-контейнер IntSummaryStatistics. За проход по stream получаем следующее: количество, сумму, мин, макс, среднее.

### Решение

```
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
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_3.png"/>
</details>

<br>
<br>

---

## Часть 2: Потоки ввода/вывода

### <a id="title4">Задание 2.1: Работа с текстовыми файлами</a>

Реализуйте класс FileOperations со статическими методами:

* writeLines(String filename, List<String> lines) — запись всех строк в файл через PrintWriter(BufferedWriter(FileWriter(...))).

* readLines(String filename) → List<String> — чтение всех строк через BufferedReader.

* printFileStats(String filename) — вывести число строк, слов и символов (слова считайте через split("\\s+")).

* grep(String filename, String keyword) → List<String> — строки, содержащие ключевое слово.

* copyFile(String source, String destination) — побайтовое копирование через FileInputStream/FileOutputStream с буфером.

Все методы бросают IOException. Используйте try-with-resources. Протестируйте: запишите 5 строк, прочитайте их, выведите статистику, найдите строки с «Java», скопируйте файл.

### Решение

```
package practice_5.task_2_1;

import java.io.*;
import java.util.*;

public class FileOperations {
    public static void writeLines(String filename, List<String> lines) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }
    public static List<String> readLines(String filename) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }
    public static void printFileStats(String filename) throws IOException {
        int lines = 0;
        int words = 0;
        int chars = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                chars += line.length();
                String cleanLine = line.trim();
                if (!cleanLine.isEmpty()) {
                    String[] wordArray = cleanLine.split("\\s+");
                    words += wordArray.length;
                }
            }
        }
        System.out.println("Файл: " + filename);
        System.out.println("Строк: " + lines);
        System.out.println("Слов: " + words);
        System.out.println("Символов: " + chars);
    }
    public static List<String> grep(String filename, String keyword) throws IOException {
        List<String> result = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) return result;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(keyword)) {
                    result.add(line);
                }
            }
        }
        return result;
    }
    public static void copyFile(String source, String destination) throws IOException {
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[128];
            int count;

            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        String file = "rome.txt";
        String copyOfFile = "rome_copy.txt";
        List<String> text = new ArrayList<>();
        text.add("Сегодня гуляем с собакой.");
        text.add("Занимаемся спортом вместе с собакой");
        text.add("Учимся плавать.");
        text.add("Не пропускаем занятия по СТП.");
        text.add("Финал сессии уже рядом.");
        try {
            FileOperations.writeLines(file, text);
            System.out.println("Файл создан и записан.\n");

            System.out.println("Чтение файла");
            List<String> linesFromFile = FileOperations.readLines(file);
            for (String s : linesFromFile) {
                System.out.println(s);
            }
            System.out.println();
            
            System.out.println("Статистика");
            FileOperations.printFileStats(file);
            System.out.println();

            System.out.println("Результаты поиска по слову");
            List<String> foundLines = FileOperations.grep(file, "собакой");
            for (String s : foundLines) {
                System.out.println(s);
            }
            
            System.out.println();
            FileOperations.copyFile(file, copyOfFile);
            System.out.println("Копирование файла: " + copyOfFile);
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_4.png"/>
    <br>
    <img src="img_5.png"/>
    <br>
    <img src="img_6.png"/>
</details>

<br>

### <a id="title5">Задание 2.2: Сериализация объектов</a>

```
package practice_5.task_2_2;

import java.io.*;
import java.util.*;

public class SerializationDemo {

    // Класс должен реализовывать Serializable для сериализации
    static class StudentRecord implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private int grade;
        private String faculty;
        private transient String password; // transient — не сериализуется

        public StudentRecord(String name, int grade, String faculty, String password) {
            this.name = name;
            this.grade = grade;
            this.faculty = faculty;
            this.password = password;
        }

        // Геттеры
        public String getName() { return name; }
        public int getGrade() { return grade; }
        public String getFaculty() { return faculty; }
        public String getPassword() { return password; } // null после десериализации

        @Override
        public String toString() {
            return String.format("StudentRecord{name='%s', grade=%d, faculty='%s', password=%s}",
                name, grade, faculty, password);
        }
    }

    // Сохранить список объектов в файл
    public static void saveStudents(String filename, List<StudentRecord> students) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        }
    }

    // Загрузить список объектов из файла
    @SuppressWarnings("unchecked")
    public static List<StudentRecord> loadStudents(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<StudentRecord>) ois.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<StudentRecord> students = new ArrayList<>();
        students.add(new StudentRecord("Анна", 95, "ИТ", "secret123"));
        students.add(new StudentRecord("Борис", 78, "Математика", "pass456"));

        System.out.println("До сериализации:");
        students.forEach(System.out::println);

        String filename = "students.ser";
        saveStudents(filename, students);
        System.out.println("\nСохранено в " + filename);

        List<StudentRecord> loaded = loadStudents(filename);
        System.out.println("\nПосле десериализации:");
        loaded.forEach(System.out::println);
        // Обратите внимание: password == null (transient поле!)

        new File(filename).delete();
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_7.png"/>
</details>

<br>
<br>

---

## Часть 3: Многопоточность

### <a id="title6">Задание 3.1: Создание и запуск потоков</a>

Изучите и запустите программу параллельной загрузки. Объясните: 

(1) почему строки прогресса разных потоков могут перемежаться? 

(2) что произойдёт, если убрать join()? 

(3) чем отличается extends Thread от implements Runnable?

Ответы:

1) Потому что при команде старт() управление переда тся планировщику, сам решает какому потоку в какое время дать выполняться.

* Каждый поток засыпает на 100 мс, просыпаются +- в одно время, кто первый добежал до консоли, тот и sout.

2) join() заставляет main ждать, пока поток завершит run(). Если убрать, то вначале выведется финальная строка, а затем пойдёт загрузка.

3) extends Thread = создаём класс, который является потоком (но в Java нельзя иметь множественное наследование).

4) implements Runnable = отделяем конкретную реализацию задачи от потока. Место наследования свободно.

### Решение

```
package practice_5.task_3_1;

public class ThreadBasics {

    static class DownloadSimulator extends Thread {
        private String filename;
        private int sizeKB;
        private int downloadedKB = 0;

        public DownloadSimulator(String filename, int sizeKB) {
            super("Downloader-" + filename);
            this.filename = filename;
            this.sizeKB = sizeKB;
        }

        @Override
        public void run() {
            System.out.printf("[%s] Начало загрузки %s (%d KB)%n",
                    getName(), filename, sizeKB);

            while (downloadedKB < sizeKB) {
                try {
                    Thread.sleep(100); // Симулируем задержку
                    downloadedKB = Math.min(downloadedKB + 100, sizeKB);
                    System.out.printf("[%s] Загружено: %d/%d KB (%.0f%%)%n",
                            getName(), downloadedKB, sizeKB,
                            (double) downloadedKB / sizeKB * 100);
                } catch (InterruptedException e) {
                    System.out.printf("[%s] Загрузка прервана!%n", getName());
                    return;
                }
            }

            System.out.printf("[%s] Загрузка %s завершена!%n", getName(), filename);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Запускаем параллельные загрузки...\n");

        Thread t1 = new DownloadSimulator("document.pdf", 300);
        Thread t2 = new DownloadSimulator("image.jpg", 500);
        Thread t3 = new DownloadSimulator("video.mp4", 1000);

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Все загрузки запущены параллельно!");

        t1.join();
        t2.join();
        t3.join();

        System.out.println("\nВсе загрузки завершены!");
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_8.png"/>
</details>

<br>

### <a id="title7">Задание 3.2: Синхронизация — Безопасный счётчик</a>

Изучите и запустите обе версии счётчика. Объясните: 

(1) почему небезопасный счётчик даёт неверный результат? 

(2) что гарантирует ключевое слово synchronized? 

(3) запустите тест несколько раз — всегда ли небезопасный счётчик ошибается?

Ответы:

1) Операция по подсчёту не атомарна. Когда много потоков видят одно и то же значение, начинают выполнять операцию, в итоге, каждый поток имеют один на выходе результат = Race conditions.

2) Чтобы подобно не было (пункт 1), ключевое слово гарантирует,что доступ разрешён только 1 потоку, другие потоки не войдут.

3) Race Condition всегда = поведение кода непредсказуемо (скрины).

### Решение

```
package practice_5.task_3_2;

public class SynchronizationDemo {

    // Небезопасный счётчик
    static class UnsafeCounter {
        private int count = 0;
        public void increment() { count++; }
        public int getCount() { return count; }
    }

    static class SafeCounter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    static void testCounter(Object counter, int threads, int incrementsPerThread)
            throws InterruptedException {
        Thread[] threadArray = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            threadArray[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    if (counter instanceof UnsafeCounter uc) uc.increment();
                    else if (counter instanceof SafeCounter sc) sc.increment();
                }
            });
        }

        for (Thread t : threadArray) t.start();
        for (Thread t : threadArray) t.join();

        int expected = threads * incrementsPerThread;
        int actual = counter instanceof UnsafeCounter uc ? uc.getCount()
                : counter instanceof SafeCounter sc ? sc.getCount() : -1;
        System.out.printf("Ожидаем: %d, Получили: %d, %s%n",
                expected, actual,
                actual == expected ? "КОРРЕКТНО" : "ОШИБКА (потеряно " + (expected - actual) + ")");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Небезопасный счётчик ===");
        for (int i = 0; i < 5; i++) {
            testCounter(new UnsafeCounter(), 10, 1000);
        }

        System.out.println("\n=== Безопасный счётчик ===");
        for (int i = 0; i < 5; i++) {
            testCounter(new SafeCounter(), 10, 1000);
        }
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_9.png"/>
    <br>
    <img src="img_10.png"/>
    <br>
    <img src="img_11.png"/>
</details>

<br>

### <a id="title8">Задание 3.3: Производитель-Потребитель (Producer-Consumer)</a>

Изучите две реализации паттерна Производитель-Потребитель. Запустите программу с BlockingQueue. Объясните: 

(1) зачем в ручной реализации while вместо if при проверке условия? 

(2) почему BlockingQueue предпочтительнее? 

(3) что произойдёт, если производитель быстрее потребителя?

Ответы:

1) Проснувшийся поток проверил условие перед действием. 

 * Если бы другой поток успел забрать объект или забить буфер, то while заставит поток уснуть обратно (if привел бы к ошибке).

2) Скрывает синхронизацию под капотом. Не нужно писать synchronized, ... Вызываешь безопасные методы .put() и .take().

3) Очередь заполнится до максимума, после чего метод .put() поставит производителя на паузу. Проснётся, как только освободится одно место.

### Решение

```
package practice_5.task_3_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerDemo {

    // Вариант 1: Ручная реализация с synchronized/wait/notify
    static class SharedBuffer {
        private final Queue<Integer> buffer = new LinkedList<>();
        private final int capacity;

        public SharedBuffer(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void put(int item) throws InterruptedException {
            while (buffer.size() >= capacity) {
                wait();
            }
            buffer.offer(item);
            notifyAll();
        }

        public synchronized int take() throws InterruptedException {
            while (buffer.isEmpty()) {
                wait();
            }
            int item = buffer.poll();
            notifyAll();
            return item;
        }
    }

    // Вариант 2: Используя BlockingQueue (рекомендуемый подход)
    static void demonstrateBlockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        // Производитель
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    String task = "Задача-" + i;
                    queue.put(task); // Блокирует если очередь полна
                    System.out.println("[Производитель] Добавил: " + task
                            + " (в очереди: " + queue.size() + ")");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Потребитель
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String task = queue.take(); // Блокирует если очередь пуста
                    System.out.println("[Потребитель] Обработал: " + task);
                    Thread.sleep(200); // Потребитель медленнее
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Все задачи обработаны!");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== BlockingQueue Producer-Consumer ===");
        demonstrateBlockingQueue();
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_12.png"/>
</details>

<br>

### <a id="title9">Задание 3.4: Прерывание потоков</a>

Изучите и запустите программу с прерыванием потока. Объясните: 

(1) чем thread.interrupt() отличается от немедленной остановки? 

(2) почему в catch (InterruptedException) вызывается Thread.currentThread().interrupt()? 

(3) что означает isInterrupted()?

Ответы:

1) Немедленная остановка убивает поток. interrupt() = флаг, что нужно остановить поток. Считывает флаг и закрывает поток, например, после сетевого соединения.

2) Автоматически стирает флаг (false). Повторный вызов = true, флаг return (чтобы другие завершили работу). 

3) метод проверка, который return true или false (есть ли флаг у потока).

### Решение

```
package practice_5.task_3_4;

public class InterruptionDemo {

    static class LongRunningTask implements Runnable {
        private final String taskName;
        private final int totalSteps;

        public LongRunningTask(String taskName, int totalSteps) {
            this.taskName = taskName;
            this.totalSteps = totalSteps;
        }

        @Override
        public void run() {
            System.out.printf("[%s] Старт, шагов: %d%n", taskName, totalSteps);

            for (int step = 1; step <= totalSteps; step++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.printf("[%s] Обнаружено прерывание, завершение%n", taskName);
                    return;
                }

                System.out.printf("[%s] Шаг %d/%d%n", taskName, step, totalSteps);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.printf("[%s] Прервано на шаге %d%n", taskName, step);
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.printf("[%s] Завершено!%n", taskName);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread task1 = new Thread(new LongRunningTask("Задача-А", 10), "Thread-A");
        Thread task2 = new Thread(new LongRunningTask("Задача-Б", 10), "Thread-B");

        task1.start();
        task2.start();

        Thread.sleep(1500); // Ждём 1.5 секунды

        System.out.println("\n--- Прерываем Задачу-А ---");
        task1.interrupt(); // Прерываем один поток

        task1.join();
        task2.join();

        System.out.printf("Задача-А прервана: %s%n", !task1.isAlive());
        System.out.printf("Задача-Б завершена: %s%n", !task2.isAlive());
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_13.png"/>
</details>

<br>
<br>

---

## Часть 4: Комплексное задание

### <a id="title10">Задание 4.1: Параллельный поиск в файлах</a>

Реализуйте параллельный поиск текста в нескольких файлах:

* Класс FileSearchTask implements Callable<List<String>>: ищет keyword в файле построчно, возвращает список строк в формате «filename:номер_строки: текст_строки». Если файл не найден — добавляет «filename: файл не найден».

* Метод searchInFiles(List<String> files, String keyword): создаёт ExecutorService с фиксированным пулом потоков, запускает FileSearchTask через executor.submit(), собирает результаты через Future.get(), выводит все найденные строки, закрывает executor.

Создайте 3 тестовых файла с содержимым из условия, выполните поиск по «java», удалите файлы.

### Решение

```
package practice_5.task_4_1;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class FileSearchTask implements Callable<List<String>> {
    private String filename;
    private String keyword;

    public FileSearchTask(String filename, String keyword) {
        this.filename = filename;
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public List<String> call() {
        List<String> result = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            result.add(filename + ": файл не найден");
            return result;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(keyword)) {
                    result.add(filename + ":" + currentLine + ": " + line);
                }
                currentLine++;
            }
        } catch (IOException e) {
            result.add(filename + ": ошибка чтения файла");
        }

        return result;
    }
}

class ParallelSearch {
    public static void searchInFiles(List<String> files, String keyword) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<List<String>>> futures = new ArrayList<>();
        for (String f : files) {
            FileSearchTask task = new FileSearchTask(f, keyword);
            futures.add(executor.submit(task));
        }
        for (Future<List<String>> future : futures) {
            try {
                List<String> lines = future.get();
                for (String s : lines) {
                    System.out.println(s);
                }
            } catch (Exception e) {
                System.out.println("Ошибка потока: " + e.getMessage());
            }
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        String file1 = "Vinni.txt";
        String file2 = "Rud.txt";
        String file3 = "Push.txt";
        String file4 = "Unknown.txt";

        try {
            PrintWriter p1 = new PrintWriter(new FileWriter(file1));
            p1.println("Winni");
            p1.println("Rud");
            p1.println("Push");
            p1.close();

            PrintWriter p2 = new PrintWriter(new FileWriter(file2));
            p2.println("Java");
            p2.println("Python");
            p2.println("JS");
            p2.close();

            PrintWriter p3 = new PrintWriter(new FileWriter(file3));
            p3.println("Backend.");
            p3.println("numpy+pandas");
            p3.println("frontend");
            p3.close();

            List<String> allFiles = new ArrayList<>();
            allFiles.add(file1);
            allFiles.add(file2);
            allFiles.add(file3);
            allFiles.add(file4);

            searchInFiles(allFiles, "java");

        } catch (IOException e) {
            System.out.println("Ошибка при создании файлов: " + e.getMessage());
        } finally {
            new File(file1).delete();
            new File(file2).delete();
            new File(file3).delete();
            System.out.println("\nТестовые файлы удалены.");
        }
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_37.png"/>
</details>

### <a id="title11">Часть 5: Контрольные вопросы</a>

**1. В чём разница между ArrayList и LinkedList? Когда использовать каждый?**

* ArrayList внутри — обычный массив. Быстро получить по индексу (O(1)), но вставка/удаление из середины — медленно (O(n)).

  * LinkedList — цепочка узлов со ссылками на соседей. Доступ по индексу медленный (O(n) от начала), вставить/удалить в начале/середине — быстро (O(1))

  * ArrayList, когда много чтения по индексу и редкие изменения в конце. 

  * LinkedList — когда частые вставки/удаления где угодно.

**2. Почему HashSet не гарантирует порядок элементов?**

* HashSet кидает элементы в бакеты (ячейки внутреннего массива) по хэш-коду. 

  * Порядок обхода зависит от порядка бакетов и элементов внутри них — никак не связан с порядком добавления. 

  * При расширении таблицы все элементы перехешируются в новые бакеты. Порядок меняется полностью.

**3. Что такое equals() и hashCode() и почему их нужно переопределять вместе для ключей HashMap?**

* Контракт: если equals() говорит "равны", то hashCode() обязан быть одинаковым. 

  * HashMap ищет ключ: сначала по hashCode() определяет бакет, потом внутри сравнивает через equals(). 

  * Переопределили equals() без hashCode() — два равных объекта получат разный хэш, попадут в разные бакеты. HashMap один через другой не найдёт.

**4. Чем Stream API отличается от for-each? Можно ли изменять коллекцию через Stream?**

* Stream — говорим "что сделать", for-each — "как сделать". 

  * Через stream нельзя менять исходную коллекцию = break обработку (ConcurrentModificationException). 

  * Источник должен быть неизменным: стрим создаёт новую коллекцию или изменения вносятся до/после.

**5. В чём разница между BufferedReader и FileReader?**

* FileReader читает символы по одному, каждый раз дёргая диск — медленно. 

  * BufferedReader оборачивает его и читает большими блоками в буфер, а нам выдаёт оттуда. 

  * FileReader без буфера почти не применяют — всегда оборачивают в BufferedReader для readLine() и нормальной скорости.

**6. Почему нужно закрывать потоки ввода/вывода? Что произойдёт, если не закрыть?**

* Когда открываешь файл, ОС даёт программе специальный номер — файловый дескриптор. Их количество ограничено. 

  * Если не закрывать поток, дескрипторы заканчиваются — программа падает с ошибкой "too many open files". 

  * Данные из буфера могут не успеть записаться в файл и потеряться. Сборщик мусора тут не помогает, поэтому потоки надо закрывать всегда. 

  * Самый надёжный способ — try-with-resources, он закроет поток даже при ошибке.

**7. В чём разница между Thread и Runnable? Почему Runnable предпочтительнее?**

* Thread — это сам поток.

  * Runnable — просто задача + метод run(). 

  * Runnable не тратит единственное наследование, отделяет "что делать" от "как запускать", легко переиспользовать в пулах потоков и запускать один Runnable в нескольких потоках.

**8. Почему t.run() не создаёт новый поток, а t.start() — создаёт?**

* t.run() — вызов метода в том же потоке.

  * t.start() — создаёт новый системный поток, выделяет стек и вызывает run(). 

  * start() даёт параллельность. Вызвать start() дважды нельзя — исключение.

**9. Что такое "состояние гонки" (race condition)? Приведите пример.**

* Когда результат зависит от случайного порядка выполнения операций в разных потоках. 

  * Пример: два потока делают counter++ (прочитал-увеличил-записал). Оба прочитали 0, увеличили до 1, записали — итог 1 вместо 2. Лечится синхронизацией.

**10. Чем BlockingQueue удобнее чем ручная реализация с wait/notify?**

* BlockingQueue — готовая очередь: put() блокируется, если полна, take() — если пуста. 

  * С ручным wait/notify надо писать циклы, проверять условия, помнить про ложные пробуждения — куча мест для ошибок. 

  * BlockingQueue всё это решает и даёт таймауты плюс реализации на все случаи (с приоритетом, ограниченные и т.д.).

**11. Thread.sleep() и мониторы**

* sleep() приостанавливает поток, но не отпускает synchronized-блокировки. 

  * Заснул внутри синхронизированного блока — другие потоки туда не войдут, пока не проснётся.

  * wait() в отличие от sleep() монитор временно отдаёт.

**12. transient при сериализации**

* Поле с `transient` пропускается при стандартной сериализации. 

  * При восстановлении получает значение по умолчанию (0, null, false). 

  * Используется для того, что бессмысленно или опасно сохранять: кэши, пароли, ...