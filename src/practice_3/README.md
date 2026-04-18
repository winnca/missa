# Практическое занятие 3: Ветвление, Циклы и ООП


## Тест

<details>
    <summary>result</summary>
    <img src="img_11.png"/>
    <img src="img_14.png"/>
    <img src="img_15.png"/>
    <img src="img_16.png"/>
    <img src="img_17.png"/>
    <img src="img_18.png"/>
    <img src="img_19.png"/>
    <img src="img_20.png"/>
    <img src="img_21.png"/>
    <img src="img_22.png"/>
    <img src="img_23.png"/>
    <img src="img_24.png"/>
    <img src="img_25.png"/>
    <img src="img_26.png"/>
    <img src="img_27.png"/>
    <img src="img_28.png"/>
    <img src="img_29.png"/>
    <img src="img_30.png"/>
    <img src="img_31.png"/>
    <img src="img_32.png"/>
    <img src="img_33.png"/>
    <img src="img_34.png"/>
    <img src="img_35.png"/>
    <img src="img_36.png"/>
    <img src="img_37.png"/>
    <img src="img_38.png"/>
    <img src="img_39.png"/>
    <img src="img_40.png"/>
    <img src="img_41.png"/>
</details>

## Часть 1: Ветвление

### <a id="title0">Задание 1.1: if/else — Классификация числа</a>

Напишите класс NumberClassifier с методом static String classify(int number), который классифицирует число: "отрицательное" если < 0; "ноль" если == 0; "однозначное" если от 1 до 9; "двузначное" если от 10 до 99; "трёхзначное" если от 100 до 999; "большое" если >= 1000. Продемонстрируйте работу на числах: −5, 0, 7, 42, 100, 1000, −999.

Ожидаемый вывод:

```
-5 -> отрицательное
0 -> ноль
7 -> однозначное
42 -> двузначное
100 -> трёхзначное
1000 -> большое
-999 -> отрицательное
```

### Решение

```
package practice_3.task_1_1;

public class NumberClassifer {
    static String classify(int number){
        if (number < 0) return "отрицательное";
        else if (number == 0) return "ноль";
        else if (number < 10) return "однозначное";
        else if (number < 100) return "двузначное";
        else if (number < 1000) return "трёхзначное";
        else return "большое";
    }
    public static void main(String[] args){
        System.out.println(classify(-5));
        System.out.println(classify(0));
        System.out.println(classify(7));
        System.out.println(classify(42));
        System.out.println(classify(100));
        System.out.println(classify(1000));
        System.out.println(classify(-999));
    }
}
```

<details>
    <summary>result</summary>
    <img src="img.png"/>
</details>

<br>

### <a id="title1">Задание 1.2: switch — Оценка успеваемости</a>

Реализуйте класс GradeChecker с двумя вариантами метода static String getGrade(int score): первый — через классический switch, второй — через стрелочный switch (Java 14+). Диапазоны оценок: 90–100 → "Отлично (A)", 80–89 → "Хорошо (B)", 70–79 → "Удовлетворительно (C)", 60–69 → "Слабо (D)", иначе → "Неудовлетворительно (F)". Протестируйте на значениях 95, 85, 73, 62, 45, 100, 0.

### Решение

```
package practice_3.task_1_2;

public class GradeChecker {
    static String getGrade(int score){
        int gradeChecker;
        if (score >= 90 && score <= 100) {
            gradeChecker = 5;
        } else if (score >= 80 && score <= 89){
            gradeChecker = 4;
        } else if (score >= 70 && score <= 79){
            gradeChecker = 3;
        } else if (score >= 60 && score <= 69){
            gradeChecker = 2;
        } else gradeChecker = 0;
        switch (gradeChecker){
            case 5:
                return "Отлично (A)";
            case 4:
                return "Хорошо (B)";
            case 3:
                return "Удовлетворительно (C)";
            case 2:
                return "Слабо (D)";
            default:
                return "Неудовлетворительно (F)";
        }
    }
    static String getGradeArrow(int score){
        int gradeChecker = 0;
        if (score >= 90 && score <= 100) gradeChecker = 5;
        else if (score >= 80 && score <= 89) gradeChecker = 4;
        else if (score >= 70 && score <= 79) gradeChecker = 3;
        else if (score >= 60 && score <= 69) gradeChecker = 2;
        else gradeChecker = 1;
        return switch (gradeChecker){
            case 5 -> "Отлично (A)";
            case 4 -> "Хорошо (B)";
            case 3 -> "Удовлетворительно (C)";
            case 2 -> "Слабо (D)";
            default -> "Неудовлетворительно (F)";
        };
    }

    public static void main(String[] args) {
        int[] testScores = {95, 85, 73, 62, 45, 100, 0};
        for (int score : testScores){
            System.out.println(score + " " + getGrade(score));
        }
        System.out.println();
        for (int score : testScores){
            System.out.println(score + " " + getGradeArrow(score));
        }
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_1.png"/>
</details>

<br>

### <a id="title2">Задание 1.3: switch с паттерн-матчингом (Java 17+)"</a>

Напишите метод static String describe(Object obj) с использованием switch с паттерн-матчингом (Java 17+). Обрабатываемые случаи: null, Integer i (с пометкой положительное/не положительное), String s когда пустая, String s непустая, Double d, int[] arr, иные объекты (через default). Протестируйте на: null, 42, −5, "", "Привет", 3.14, new int[]{1,2,3}, true.

### Решение

```
package practice_3.task_1_3;

import java.util.Arrays;

public class TaskOneAndThree {
    static String describe(Object obj){
        return switch (obj) {
            case null -> null + " это null";
            case String s when s.isEmpty() -> s + " это строка: пустая";
            case String s when true -> s + " это строка: непустая";
            case Integer i when i > 0 -> i + " это целое число: положительное";
            case Integer i when true -> i + " это целое число: неположительное";
            case Double d -> d + " это вещественное число";
            case int[] arr -> Arrays.toString(arr) + " это массив int";
            default -> obj + " это неизвестный тип";
        };
    }
    public static void main(String[] args){
        Object[] tests = {
                null, 42, -5, "", "Это строка", 3.14, new int[]{1,2,3}, true
        };
        for (Object obj: tests){
            System.out.println(describe(obj));
        }
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_4.png"/>
</details>

<br>
<br>

---

## Часть 2: Циклы

### <a id="title3">Задание 2.1: Таблица умножения</a>

Напишите программу, которая выводит таблицу умножения от 1 до 10 в форматированном виде (числа выровнены по столбцам шириной 4). Используйте вложенные for-циклы. Каждая строка начинается с номера строки, затем все произведения.

### Решение

```
package practice_3.task_2_1;

public class Table {
    public static void main(String[] args){
        for (int i=1;i<=10;i++){
            for (int j=1;j<=10;j++){
                System.out.printf("%4d", i*j);
            }
            System.out.println();
        }
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_2.png"/>
</details>

<br>

### <a id="title4">Задание 2.2: Числа Фибоначчи</a>

Реализуйте класс Fibonacci с методами: fibIterative(int n) — итеративное вычисление с while-циклом; fibFor(int n) — с for-циклом. Выведите F(0)..F(15). Найдите первое число Фибоначчи, превышающее 1000.

### Решение

```
package practice_3.task_2_2;

public class Fibonacci {
    static void fibIterative(int n){
        int i=2, previous = 0, current = 1, res = 0;
        if(n == 0)System.out.println(0);
        else if(n==1)System.out.println(1);
        else{
            while (i <= n){
                res = previous + current;
                previous = current;
                i++;
                current = res;
            }
            System.out.println(res);
        }
    }

    static void fibFor(int n){
        int previous = 0, current = 1, res = 0;
        if (n == 0)System.out.println(0);
        else if (n==1) System.out.println(1);
        else{
            for (int i=2;i<=n;i++){
                res = previous + current;
                previous = current;
                current = res;
            }
            System.out.println(res);
        }
    }

    public static void main(String[] args){
        fibIterative(7);
        fibFor(7);
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_3.png"/>
</details>

<br>

### <a id="title5">Задание 2.3: Работа со строками в цикле</a>

Реализуйте класс StringProcessor со статическими методами: countVowels(String text) — подсчёт гласных букв (русских и английских); isPalindrome(String text) — проверка на палиндром без учёта регистра и знаков препинания (используйте сравнение символов с двух концов строки); reverse(String text) — реверс строки без StringBuilder (с двумя указателями); findLongestWord(String sentence) — самое длинное слово в предложении. Протестируйте на: "Привет, Java-разработчик!", "топот", "Madam", "hello", "А роза упала на лапу Азора", "The quick brown fox jumps over the lazy dog".

### Решение

```
package practice_3.task_2_3;

public class StringProcessor {
    static int countVowels(String text){
        int count = 0;
        String res = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toUpperCase();
        String[] el = res.split("");
        for (int i=0; i<el.length; i++){
            if(el[i].equals("А") || el[i].equals("У") || el[i].equals("Е")
                    || el[i].equals("Ы") || el[i].equals("О") || el[i].equals("Э")
                    || el[i].equals("Я") || el[i].equals("И") || el[i].equals("Ю")
                    || el[i].equals("A") || el[i].equals("E") || el[i].equals("Y")
                    || el[i].equals("U") || el[i].equals("I") || el[i].equals("O")) count++;
        }
        return count;
    }
    static boolean isPalindrome(String text){
        String res = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toUpperCase();
        for (int i=0,j=res.length()-1; i<j;i++,j--){
            if (res.charAt(i) != res.charAt(j)) return false;
        }
        return true;
    }

    static String reverse(String text) {
        char[] array = text.toCharArray();
        for (int i=0,j=text.length()-1; i<j;i++,j--){
            char temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        return new String(array);
    }

    static String findLongestWord(String sentence){
        int count = 0, max = 0;
        for (int i=0;i<sentence.length();i++){
            if (sentence.charAt(i) != ' '){
                count++;
            } else{
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        String res = "";
        String[] arr = sentence.split(" ");
        for (int i=0;i<arr.length;i++){
            if(arr[i].length() == max){
                res = arr[i];
                break;
            }
        }
        return res;
    }
    public static void main(String[] args){
        String[] array = {"Привет, Java-разработчик!", "топот", "Madam", "hello", "А роза упала на лапу Азора", "The quick brown fox jumps over the lazy dog"};
        for (String el : array){
            System.out.println("подсчёт гласных букв (русских и английских) = " + countVowels(el));
            System.out.println("проверка на палиндром без учёта регистра и знаков препинания (используйте сравнение символов с двух концов строки) = " + isPalindrome(el));
            System.out.println("реверс строки без StringBuilder (с двумя указателями) = " + reverse(el));
            System.out.println("самое длинное слово в предложении = " + findLongestWord(el));
            System.out.println();
        }
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_5.png"/>
</details>

<br>

### <a id="title6">Задание 2.4: break, continue и метки</a>

Изучите и запустите программу. Объясните: (1) как работают метки outer: и search:; (2) что делает continue outer на строке с проверкой делителей; (3) какой результат выведет каждый из трёх блоков программы?

```
package practice_3.task_2_4;

public class LoopControl {
    public static void main(String[] args) {
        // Блок 1: Найти первое простое число больше 100
        int n = 101;
        outer:
        while (true) {
            if (n % 2 == 0 && n != 2) {
                n++;
                continue;
            }
            for (int d = 3; d * d <= n; d += 2) {
                if (n % d == 0) {
                    n++;
                    continue outer; // Перейти к следующей итерации while
                }
            }
            break; // n — простое
        }
        System.out.println("Первое простое > 100: " + n);

        // Блок 2: Распечатать только нечётные числа, пропуская кратные 3
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) continue; // Пропустить чётные
            if (i % 3 == 0) continue; // Пропустить кратные 3
            System.out.print(i + " ");
        }
        System.out.println();

        // Блок 3: Поиск в матрице — найти и сразу выйти из обоих циклов
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int target = 5;
        int foundRow = -1, foundCol = -1;

        search:
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == target) {
                    foundRow = row;
                    foundCol = col;
                    break search; // Выйти из ОБОИХ циклов
                }
            }
        }

        if (foundRow != -1) {
            System.out.printf("Число %d найдено на позиции [%d][%d]%n", target, foundRow, foundCol);
        }
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_6.png"/>
</details>

Пояснение:

1. Проверка на простое: d принимает значение 3, 5, 7, 9. 101 не делится ни на одно из них. Выход из цикла for, break. Ответ 101.

2. Нечётные будет пропускать чётные и делящиеся на 3. Ответ: 1, 5, 7, 11, 13, 17, 19.

3. break search сработает. Выйдет из обоих циклов. Зайдёт в if и выведет ответ.

<br>
<br>

---

## Часть 3: Наследование и инкапсуляция

### <a id="title7">Задание 3.1: Иерархия транспортных средств</a>

Спроектируйте и реализуйте иерархию классов для автопарка:

1. Абстрактный класс Vehicle: поля brand, model, year (private), fuelLevel (0.0–1.0); геттеры для всех полей; сеттер для fuelLevel с проверкой диапазона; абстрактные методы getFuelConsumption() (л/100км) и getType(); конкретные методы calculateFuelNeeded(double distanceKm) и canTravel(double distanceKm, double tankCapacityLiters); переопределённый toString().

2. Класс Car extends Vehicle: поля doors, automatic; getFuelConsumption() возвращает 9.5 (авт.) или 8.0 (мех.); метод honk().

3. Класс Truck extends Vehicle: поле cargoCapacityTons; getFuelConsumption() = 20 + cargoCapacity × 3.

4. Интерфейс Electric: методы getBatteryLevel(), getRangeKm(), charge(double hours).

5. Класс ElectricCar extends Car implements Electric: поля batteryLevel, maxRangeKm; charge увеличивает батарею на 20%/час (макс. 100%); getFuelConsumption() возвращает 0.

Создайте List<Vehicle> с четырьмя машинами (Toyota Camry, Lada Vesta, Kamaz, Tesla Model 3) и выведите для каждой: тип, расход топлива на 500 км, и для электромобилей — запас хода. Продемонстрируйте полиморфизм: для Car вызовите honk(), для Electric — getRangeKm().

### Решение

```
package practice_3.task_3_1;

public class Truck extends Vehicle{
    private double cargoCapacityTons;

    public Truck(String brand, String model, int year, double fueLevel, double cargoCapacityTons){
        super(brand, model, year, fueLevel);
        this.cargoCapacityTons=cargoCapacityTons;
    }

    @Override
    public double getFuelConsumption() {
        return 20 + cargoCapacityTons * 3;
    }

    @Override
    public String getType() {
        return "Truck";
    }
    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    public void setCargoCapacityTons(float cargoCapacityTons) {
        this.cargoCapacityTons = cargoCapacityTons;
    }
}
```

```
package practice_3.task_3_1;

public interface Electric {
    double getBatteryLevel();
    double getRangeKm();
    void charge(double hours);
}
```

```
package practice_3.task_3_1;

public abstract class Vehicle {
    private String brand;
    private String model;
    private int year;
    private double fueLevel;

    public Vehicle(String brand, String model, int year, double fueLevel) {
        this.brand=brand;
        this.model=model;
        this.year=year;
        setFueLevel(fueLevel);
    }
    public Vehicle(String brand, String model, int year) {
        this.brand=brand;
        this.model=model;
        this.year=year;
    }
    public String getBrand() {
        return brand;
    }

    public String getModel() { return model; }

    public int getYear() { return year; }
    public double getFueLevel() { return fueLevel;}
    public void setFueLevel(double fueLevel) {
        if (fueLevel >= 0 && fueLevel <= 1) this.fueLevel = fueLevel;
        else System.out.println("Invalid fuel level. Must be between 0 and 1.");
    }
    public double calculateFuelNeeded(double distanceKm){
        return getFuelConsumption()*(distanceKm/100);
    }
    public void canTravel(double distanceKm, double tankCapacityLiters){
        if (distanceKm*getFuelConsumption()<=tankCapacityLiters) System.out.println("Can travel");
        else System.out.println("Can't travel");
    }
    public abstract double getFuelConsumption();
    public abstract String getType();

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fueLevel=" + fueLevel +
                '}';
    }
}
```

```
package practice_3.task_3_1;

public class ElectricCar extends Car implements Electric{
    private double maxRangeKm;
    private double batteryLevel;
    public ElectricCar(String brand, String model, int year, double rangeKm, double batteryLevel) {
        super(brand, model, year);
        this.maxRangeKm=maxRangeKm;
        setBatteryLevel(batteryLevel);
    }

    @Override
    public double getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public double getRangeKm() {
        return maxRangeKm;
    }

    public void setBatteryLevel(double batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 1) this.batteryLevel = batteryLevel;
        else System.out.println("Invalid battery level. Must be between 0 and 1.");
    }

    @Override
    public void charge(double hours) {
        this.batteryLevel += 0.2 * hours;
        if (this.batteryLevel > 1.0) this.batteryLevel = 1.0;
        System.out.println("Charging complete. Current charge: " + (batteryLevel * 100) + "%");
    }

    @Override
    public double getFuelConsumption() {
        return 0;
    }
}

```

```
package practice_3.task_3_1;

public class Car extends Vehicle {
    private String fuelType;
    private int doors;
    private boolean automatic;

    public Car(String brand, String model, int year, double fuelLevel) {
        super(brand, model, year, fuelLevel);
    }

    public Car(String brand, String model, int year) {
        super(brand, model, year);
    }

    public Car(String brand, String model, int year, String fuelType, int doors, boolean automatic) {
        super(brand, model, year);
        setFuelType(fuelType);
        setDoors(doors);
        this.automatic = automatic;
    }

    @Override
    public double getFuelConsumption() {
        if (automatic) {
            return 9.5;
        } else {
            return 8.0;
        }
    }

    public void setFuelType(String fuelType) {
        if ("gasoline".equals(fuelType) || "diesel".equals(fuelType) || "gas".equals(fuelType)) {
            this.fuelType = fuelType;
        } else {
            System.out.println("Invalid fuel type: " + fuelType);
        }
    }

    @Override
    public String getType() {
        return fuelType;
    }

    public void honk() {
        System.out.println("Beep-beep-beep");
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        if (doors > 0) {
            this.doors = doors;
        } else {
            System.out.println("Invalid number of doors");
        }
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }
}
```

```
package practice_3.task_3_1;

import java.util.ArrayList;
import java.util.List;

public class Result {
    public static void main(String[] args) {
        List<Vehicle> list = new ArrayList<>();

        // Toyota Camry
        Car camry = new Car("Toyota", "Camry", 2022, 0.8);
        camry.setAutomatic(true);
        camry.setFuelType("gasoline");
        camry.setDoors(4);

        // Lada Vesta
        Car vesta = new Car("Lada", "Vesta", 2021, 0.5);
        vesta.setAutomatic(false);
        vesta.setFuelType("gasoline");
        vesta.setDoors(4);

        // Kamaz
        Truck kamaz = new Truck("Kamaz", "5320", 2015, 0.6, 10.0);

        // Tesla Model 3
        ElectricCar tesla = new ElectricCar("Tesla", "Model 3", 2023, 500.0, 0.8);
        tesla.setFuelType("electric");
        tesla.setDoors(4);

        list.add(camry);
        list.add(vesta);
        list.add(kamaz);
        list.add(tesla);

        for (Vehicle v : list) {
            System.out.println(v.toString());
            System.out.println("Type: " + v.getType());
            System.out.printf("Fuel consumption for 500 km: %.2f liters/units\n", v.calculateFuelNeeded(500));
            if (v instanceof Car) { // v is car
                ((Car) v).honk();
            }
            if (v instanceof Electric) {
                System.out.printf("Range: %.2f km\n", ((Electric) v).getRangeKm());
            }
            System.out.println();
        }
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_7.png"/>
</details>

<br>

### <a id="title8">Задание 3.2: Инкапсуляция — Банковский счёт</a>

Реализуйте класс BankAccount с полями (все private): accountNumber (final String), balance (double), owner (final String), failedAttempts (int), blocked (boolean), pin (String — нет публичного геттера). 

Методы: 

* withdraw(String enteredPin, double amount) — если счёт заблокирован, отказ; 

* при неверном PIN увеличивает failedAttempts, при 3 неверных блокирует счёт; 

* при верном PIN сбрасывает failedAttempts, проверяет сумму и вычитает; 

* deposit(double amount) — проверяет amount > 0; 

* validatePin(String pin), getMaskedBalance() — скрывает суммы свыше 100 000. 

Напишите toString() с пометкой [ЗАБЛОКИРОВАН] если счёт заблокирован.

### Решение

```
package practice_3.task_3_2;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final String owner;
    private int failedAttempts;
    private boolean blocked;
    private String pin;

    public BankAccount(String accountNumber, double balance, String owner, String pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.failedAttempts = 0;
        this.blocked = false;
        this.pin = pin;
    }

    public void withdraw(String enteredPin, double amount){
        if (blocked) {
            System.out.println("Account blocked");
            return;
        }
        if (!validatePin(enteredPin)){
            failedAttempts++;
            if (failedAttempts == 3) {
                System.out.println("Account blocked");
                blocked = true;
            }
            return;
        }
        failedAttempts = 0;
        if (balance - amount > 0){
            balance -= amount;
            System.out.println("Good operation");
        } else System.out.println("Balance error");
    }
    public boolean validatePin(String pin){
        return this.pin.equals(pin);
    }
    public void deposit(double amount){
        if (blocked) System.out.println("Account blocked");
        else if (amount <= 0) System.out.println("Can't operation");
        else{
            balance += amount;
            System.out.println("Good operation");
        }
    }
    public void setBalance(double balance) { this.balance = balance;}
    public void setFailedAttempts(int failedAttempts) {this.failedAttempts = failedAttempts;}
    public void setBlocked(boolean blocked) {this.blocked = blocked;}
    public String getAccountNumber() {return accountNumber;}
    public double getBalance() {return balance;}
    public String getOwner() {return owner;}
    public int getFailedAttempts() {return failedAttempts;}
    public boolean isBlocked() {return blocked;}
    public String getMaskedBalance(){
        if (balance >= 100000) return "********";
        else return String.valueOf(balance);
    }
    @Override
    public String toString() {
        String status = blocked ? " [BLOCKED]" : "";
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + getMaskedBalance() +
                ", failedAttempts=" + failedAttempts +
                status +
                '}';
    }
}
```

```
package practice_3.task_3_2;

public class BankResult {
    public static void main(String[] args){
        BankAccount account1 = new BankAccount("1234", 90000, "Sam", "4287");
        BankAccount account2 = new BankAccount("7777", 200000, "Dean", "7878");

        System.out.println(account1);
        System.out.println(account2);

        account1.withdraw("9999", 1000);  // Invalid PIN → failedAttempts=1
        account1.withdraw("8888", 1000);  // Invalid PIN → failedAttempts=2
        account1.withdraw("7777", 1000);  // Invalid PIN → failedAttempts=3 → BLOCKED

        account1.withdraw("4287", 1000);  // Account blocked

        account2.withdraw("7878", 50000); // Good operation
        account2.deposit(25000);          // Good operation
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_8.png"/>
</details>

<br>
<br>

---

## Часть 4: Полиморфизм

### <a id="title9">Задание 4.1: Система скидок (перегрузка методов)</a>

```
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

    public static void main(String[] args) {
        double price = 10000.0;

        System.out.println("Скидка VIP-клиента: " + calculateDiscount(price, "vip") + " руб.");
        System.out.println("Скидка за 75 покупок: " + calculateDiscount(price, 75) + " руб.");
        System.out.println("Скидка SAVE20 + первый заказ: " +
            calculateDiscount(price, "SAVE20", true) + " руб.");
    }
}
```

Дополните класс четвёртой перегрузкой calculateDiscount (например, по возрасту клиента, сезону или категории товара). Запустите программу и убедитесь, что все перегрузки работают корректно.

### Решение

* Если вставить 2 параметра: цену и возраст, цену и сезон, цену или категория товара. Будет ошибка. Сигнатура метода должна быть одинаковой, но количество параметров или содержимое должно различаться.

* Иными словами, Error.

Correct code:

```
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
```

<details>
    <summary>result</summary>
    <img src="img_9.png"/>
</details>

<br>

### <a id="title10">Задание 4.2: Геометрические фигуры (переопределение методов)</a>

Изучите классы Circle и Rectangle. Реализуйте Square extends Rectangle: конструктор принимает одну сторону side и передаёт её как оба параметра в конструктор Rectangle; переопределите draw(), чтобы выводилось слово «квадрат» вместо «прямоугольник». Запустите ShapeTest с добавленным Square.

```
public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double area();
    public abstract double perimeter();
    public abstract void draw(); // Симуляция рисования

    // Метод сравнения по площади
    public int compareArea(Shape other) {
        return Double.compare(this.area(), other.area());
    }

    @Override
    public String toString() {
        return String.format("%s[цвет=%s, S=%.2f, P=%.2f]",
            getClass().getSimpleName(), color, area(), perimeter());
    }
}

public class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть > 0");
        this.radius = radius;
    }

    @Override
    public double area() { return Math.PI * radius * radius; }

    @Override
    public double perimeter() { return 2 * Math.PI * radius; }

    @Override
    public void draw() {
        System.out.println("Рисую " + color + " круг с радиусом " + radius);
    }
}

public class Rectangle extends Shape {
    protected double width;
    protected double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() { return width * height; }

    @Override
    public double perimeter() { return 2 * (width + height); }

    @Override
    public void draw() {
        System.out.printf("Рисую %s прямоугольник %.1f x %.1f%n", color, width, height);
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle("красный", 5));
        shapes.add(new Rectangle("синий", 4, 6));
        shapes.add(new Square("зелёный", 5));
        shapes.add(new Circle("жёлтый", 3));

        System.out.println("=== Все фигуры ===");
        for (Shape s : shapes) {
            s.draw();
            System.out.println("  " + s);
        }

        System.out.println("\n=== Сортировка по площади ===");
        shapes.sort(Shape::compareArea);
        shapes.forEach(System.out::println);

        System.out.println("\n=== Только круги ===");
        for (Shape s : shapes) {
            if (s instanceof Circle c) {
                System.out.printf("Круг с радиусом: проверьте через toString: %s%n", c);
            }
        }

        double totalArea = shapes.stream().mapToDouble(Shape::area).sum();
        System.out.printf("%nОбщая площадь: %.2f%n", totalArea);
    }
}
```

### Решение

```
package practice_3.task_4_2;

public class Square extends Rectangle{
    public Square(String color, double side){
        super(color, side, side); // Передача в конструктор Rectangle
    }
    @Override
    public void draw(){
        System.out.println("Square");
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_10.png"/>
</details>

<br>
<br>

---

### <a id="title11">Задание 5.1: Система управления зоопарком</a>

Реализуйте мини-систему управления зоопарком, применив все концепции лекции:

1. Абстрактный класс Animal: поля name, age, weight, energyLevel (0–100); методы eat(int calories), sleep(int hours), makeSound().

2. Подкласс Predator: метод hunt() — повышает energyLevel на 30, снижает вес на 0.5.

3. Подкласс Herbivore: метод graze() — повышает energyLevel на 15.

4. Lion extends Predator: makeSound() → "Р-р-р!", метод roar().

5. Elephant extends Herbivore: makeSound() → "Тууут!", метод trumpet().

6. Интерфейс Trainable: train(String command), listCommands(). Lion реализует Trainable.

7. Класс Zoo: addAnimal(Animal a), feedAll(), makeNoise(), getHungryAnimals() (energyLevel < 30), findAnimal(String name) (возвращает Optional<Animal>).
Продемонстрируйте работу системы в main().

### Решение

```
package practice_3.task_5_1;

import java.util.*;

public abstract class Animal {
    private String name;
    private int age;
    private double weight;
    private int energyLevel;

    public Animal(String name, int age, double weight, int energyLevel) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.energyLevel = energyLevel;
    }

    public void eat(int calories){System.out.println("Съел столько то калорий: " + calories);}
    public void sleep(int hours){ System.out.println("Сплю столько часов: " + hours);}
    public void makeSound(){ System.out.println("Умею издавать звуки");}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() { return age;}
    public void setAge(int age) { this.age = age;}
    public double getWeight() { return weight;}
    public void setWeight(double weight) { this.weight = weight;}
    public int getEnergyLevel() { return energyLevel;}
    public void setEnergyLevel(int energyLevel) { this.energyLevel = energyLevel; }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", energyLevel=" + energyLevel +
                '}';
    }
}

class Predator extends Animal{
    public Predator(String name, int age, double weight, int energyLevel){ // хищник
        super(name, age, weight, energyLevel);
    }
    public void hunt(){ // охотиться
        setEnergyLevel(getEnergyLevel()+30);
        setWeight(getWeight()-0.5);
        System.out.println("Охота повышает energyLevel = " + getEnergyLevel() + "; снижает weight = " + getWeight());
    }
}

class Herbivore extends Animal{
    public Herbivore(String name, int age, double weight, int energyLevel){
        super(name, age, weight, energyLevel);
    }
    public void graze(){ // пасётся
        setEnergyLevel(getEnergyLevel()+15);
        System.out.println("Пастбище повышает energyLevel = " + getEnergyLevel());
    }
}

class Lion extends Predator implements Trainable{ // лев
    public Lion(String name, int age, double weight, int energyLevel){
        super(name, age, weight, energyLevel);
    }
    @Override
    public void makeSound(){
        System.out.println("Р-р-р!");
    }
    public void roar(){ // рёв
        System.out.println("Рёв");
    }

    @Override
    public void train(String command) {
        System.out.println("Команда = " + command);
    }

    @Override
    public void listCommands() {
        train("Сидеть");
        train("Лежать");
        train("Бегать");
    }
}

class Elephant extends Herbivore{
    public Elephant(String name, int age, double weight, int energyLevel){
        super(name, age, weight, energyLevel);
    }
    @Override
    public void makeSound(){
        System.out.println("Тууут!");
    }

    public void trumpet(){ // трубит
        System.out.println("Трубит");
    }
}

interface Trainable{
    void train(String command);
    void listCommands();
}

class Zoo{ // репозиторий
    List<Animal> animals = new ArrayList<>();
    public void addAnimal(Animal a){ // добавляем животное
        animals.add(a);
        System.out.println("Добавили животное: " + a.getClass().getSimpleName() + "; имя = " + a.getName());
    }
    public void feedAll(){ // кормить всех животных
        for (Animal a : animals){
            System.out.println("Покормили животное = " + a.getClass().getSimpleName() + "; имя = " + a.getName());
            a.eat(100);
        }
    }
    public void makeNoise(){ // шумит
        for (Animal a : animals){
            a.makeSound();
            System.out.println("Шумело животное = " + a.getClass().getSimpleName() + "; имя = " + a.getName());
        }
    }
    public List<Animal> getHungryAnimals(){
        List<Animal> hungryAnimals = new ArrayList<>();
        for (Animal a: animals){
            if(a.getEnergyLevel() < 30) {
                System.out.println("Животное голодное = " + a.getClass().getSimpleName() + "; имя = " + a.getName());
                hungryAnimals.add(a);
            }
        }
        return hungryAnimals;
    }

    public Optional<Animal> findAnimal(String name){ // обёртка на null, чтобы не было исключения NullPointerException
        for (Animal a: animals){
            if(a.getName().equalsIgnoreCase(name)) return Optional.of(a); // создаёт коллекцию с ненулевым значением, если a == null, то вызовется исключение NullPointerException.
            // equalsIgnoreCase сравнивает без учёта регистров. Лев Пумба = лев пумба
        }
        return Optional.empty(); // пустая коллекция
    }
}

class ZooTest{
    public static void main(String[] args){
        Zoo zoo = new Zoo();

        // Создаём животных
        Lion simba = new Lion("Simba", 5, 150.0, 50);
        Elephant dumbo = new Elephant("Dambo", 10, 3000.0, 20);
        Lion leo = new Lion("Leo", 3, 120.0, 70);
        Elephant babar = new Elephant("Neo", 8, 2800.0, 15);

        // Добавляем в зоопарк
        zoo.addAnimal(simba);
        zoo.addAnimal(dumbo);
        zoo.addAnimal(leo);
        zoo.addAnimal(babar);

        // Демонстрация звуков
        zoo.makeNoise();

        // Голодные животные (энергия < 30)
        for (Animal a : zoo.getHungryAnimals()) {
            System.out.println(a.getName() + " (energy: " + a.getEnergyLevel() + ")");
        }

        // Кормление
        zoo.feedAll();
        System.out.println("\nAfter eating:");
        for (Animal a : zoo.getHungryAnimals()) {
            if (a.getEnergyLevel() < 30) System.out.println(a.getName() + " hungry: " + a.getEnergyLevel());
            else System.out.println(a.getName() + " not hungry: " + a.getEnergyLevel());
        }

        // Специфические действия льва
        simba.hunt();
        simba.roar();
        simba.listCommands();

        // Специфические действия слона
        dumbo.graze();
        dumbo.trumpet();

        // Поиск животного по имени
        Optional<Animal> found = zoo.findAnimal("Dambo");
        found.ifPresentOrElse(a -> System.out.println("Found: " + a), () -> System.out.println("Animal Not Found"));

        found = zoo.findAnimal("Does not exists");
        found.ifPresentOrElse(a -> System.out.println("Found: " + a), () -> System.out.println("Animal Not Found"));
    }
}
```

<details>
    <summary>result</summary>
    <img src="img_12.png"/>
    <br>
    <img src="img_13.png"/>
</details>