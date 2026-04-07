# Cодержание

## [Тест](#title21) 

### Часть 1: Первая программа:

* #### [Задание 1.1: Hello World](#title0)

* #### [Задание 1.2: Компиляция и запуск](#title1)

* #### [Задание 1.3: Ошибки компиляции](#title2)

### Часть 2: Знакомство с jshell

* #### [Задание 2.1: Запуск jshell](#title3)

* #### [Задание 2.2: Переменные в jshell](#title4)

### Часть 3: Примитивные типы данных

* #### [Задание 3.1: Объявление переменных](#title5)

* #### [Задание 3.2: Переполнение (Overflow)](#title6)

* #### [Задание 3.3: Точность дробных чисел](#title7)

* #### [Задание 3.4: Литералы в разных системах счисления](#title8)

### Часть 4: Операторы

* #### [Задание 4.1: Арифметические операторы](#title9)

* #### [Задание 4.2: Инкремент и декремент](#title10)

* #### [Задание 4.3: Операторы сравнения и логические](#title11)

* #### [Задание 4.4: Побитовые операторы](#title12)

### Часть 5: Строки и сравнение объектов

* #### [Задание 5.1: == vs .equals()](#title13)

* #### [Задание 5.2: String Pool](#title14)

### Часть 6: Самостоятельная работа

* #### [Задание 6.1: Калькулятор BMI](#title15)

* #### [Задание 6.2: Конвертер температуры](#title16)

* #### [Задание 6.3: Обмен значений переменных](#title17)

### Часть 7: Дополнительные задания

* #### [Задание 7.1: Проверка чётности без условных операторов](#title18)

* #### [Задание 7.2: Абсолютное значение без Math.abs()](#title19)

### [Часть 8: Контрольные вопросы](#title20)

<br>
<br>

---

### <a id="title21">Тест</a>

<details>
    <summary>result</summary>
    <img src= "img_57.png">
</details>

<details>
    <summary>test</summary>
    <img src= "img_31.png">
    <br>
    <img src= "img_32.png">
    <br>
    <img src= "img_33.png">
    <br>
    <img src= "img_34.png">
    <br>
    <img src= "img_35.png">
    <br>
    <img src= "img_36.png">
    <br>
    <img src= "img_37.png">
    <br>
    <img src= "img_38.png">
    <br>
    <img src= "img_39.png">
    <br>
    <img src= "img_40.png">
    <br>
    <img src= "img_41.png">
    <br>
    <img src= "img_42.png">
    <br>
    <img src= "img_43.png">
    <br>
    <img src= "img_44.png">
    <br>
    <img src= "img_45.png">
    <br>
    <img src= "img_46.png">
    <br>
    <img src= "img_47.png">
    <br>
    <img src= "img_48.png">
    <br>
    <img src= "img_49.png">
    <br>
    <img src= "img_50.png">
    <br>
    <img src= "img_51.png">
    <br>
    <img src= "img_52.png">
    <br>
    <img src= "img_53.png">
    <br>
    <img src= "img_54.png">
    <br>
    <img src= "img_55.png">
    <br>
    <img src= "img_56.png">
</details>

<br>
<br>

---

## Часть 1: Первая программа:

### <a id="title0">Задание 1.1: Hello World</a>

1. Создайте файл HelloWorld.java
2. Напишите программу, которая выводит ваше имя и группу:

```
Привет! Меня зовут [Ваше имя]
Я студент группы [Номер группы]
```

Подсказка: Используйте System.out.println() для каждой строки.

#### Решение:

```
package practice_1;

public class HelloWorld {
    public static void main(String[] args){
        System.out.println("Привет! Меня зовут Александр Винницкий");
        System.out.println("Я студент группы ПИ24-2в");
    }
}
```

<details>
    <summary>result</summary>
    <img src= "img.png">
</details>

<br>

### <a id="title1">Задание 1.2: Компиляция и запуск</a>

Выполните в терминале:

```
# Скомпилируйте программу
javac HelloWorld.java

# Запустите программу
java HelloWorld
```

Вопрос: Какой файл появился после компиляции? Что в нём содержится?

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_1.png">
    <br>
    <img src= "img_2.png">
    <br>
    <img src= "img_3.png">
    <br>
    <img src= "img_4.png">
    <br>
    <img src= "img_5.png">
</details>

```
Какой файл появился после компиляции? Что в нём содержится?
```

Ответ:

1. Первой командой `javac HelloWorld.java` скомпилировали Java-код в байт-код, появился файл `HelloWorld.class` (проверял, если в коде ошибки; всё ли в порядке). 

2. `HelloWorld.class` с байт-кодом = набор инструкций понятный JVM.

3. Второй командой `java practice_1.HelloWorld` (исполнение файла) даём команду JVM на интерпертирование байт-кода и выполнение программы.

Замечание: из пакета запуск программы не выйдет, нужно выйти из него и прописать команду из скриншота.

<br>

### <a id="title2">Задание 1.3: Ошибки компиляции</a>

Попробуйте скомпилировать код с ошибками. Исправьте их:

```
public class Errors {
    public static void main(String[] args) {
        System.out.println("Привет мир!")
        system.out.println("Вторая строка");
        System.out.println(Без кавычек);
    }
}
```

Запишите: Какие ошибки выдал компилятор? Как вы их исправили?

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_6.png">
    <br>
    <img src= "img_7.png">
</details>

```
Запишите: Какие ошибки выдал компилятор? Как вы их исправили?
```

Ответ:

1. Добавить точку с запятой в первой строке.

2. Во второй строке изменить s на S.

3. В третьей строке добавить кавычки для вывода строки.

```
package practice_1;

public class Error {
    public static void main(String[] args){
        System.out.println("Привет мир!");
        System.out.println("Вторая строка");
        System.out.println("Без кавычек");
    }
}
```

<details>
    <summary>result</summary>
    <img src= "img_8.png">
</details>

<br>
<br>

---

### Часть 2: Знакомство с jshell

### <a id="title3">Задание 2.1: Запуск jshell</a>

Откройте терминал и введите:

```
jshell
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_9.png">
</details>

Выполните следующие команды и запишите результаты:

```
jshell> 2 + 2
jshell> 10 / 3
jshell> 10.0 / 3
jshell> "Hello" + " " + "World"
jshell> Math.sqrt(16)
jshell> Math.PI
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_10.png">
</details>

<br>

### <a id="title4">Задание 2.2: Переменные в jshell</a>

Создайте переменные и выполните операции:

```
jshell> int age = 20
jshell> String name = "Студент"
jshell> System.out.println("Имя: " + name + ", Возраст: " + age)
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_11.png">
    <br>
    <img src= "img_12.png">
    <br>
    <img src= "img_13.png">
    <br>
    <img src= "img_14.png">
</details>

<br>
<br>

---

### Часть 3: Примитивные типы данных

### <a id="title5">Задание 3.1: Объявление переменных</a>

Создайте файл DataTypes.java и объявите переменные всех примитивных типов:

```
public class DataTypes {
    public static void main(String[] args) {
        // Целочисленные типы
        byte myByte = ____;      // от -128 до 127
        short myShort = ____;    // от -32768 до 32767
        int myInt = ____;        // от -2.1 млрд до 2.1 млрд
        long myLong = ____L;     // большие числа (не забудьте L!)

        // Дробные типы
        float myFloat = ____f;   // не забудьте f!
        double myDouble = ____;

        // Символ и логический тип
        char myChar = '____';
        boolean myBoolean = ____;

        // Выведите все переменные
        System.out.println("byte: " + myByte);
        // ... продолжите для остальных
    }
}
```

#### Решение:

```
package practice_1;

public class DataTypes {
    public static void main(String[] args) {
        // Целочисленные типы
        byte myByte = 120;      // от -128 до 127
        short myShort = 24000;    // от -32768 до 32767
        int myInt = 1000000;        // от -2.1 млрд до 2.1 млрд
        long myLong = 4000000000L;     // большие числа (не забудьте L!)

        // Дробные типы
        float myFloat = 15.5f;   // не забудьте f!
        double myDouble = 4000000000.9;

        // Символ и логический тип
        char myChar = 'A';
        boolean myBoolean = true;

        // Выведите все переменные
        System.out.println("byte: " + myByte);
        // ... продолжите для остальных
        System.out.println("short: " + myShort);
        System.out.println("int: " + myInt);
        System.out.println("long: " + myLong);
        System.out.println("float: " + myFloat);
        System.out.println("double: " + myDouble);
        System.out.println("char: " + myChar);
        System.out.println("boolean: " + myBoolean);
    }
}
```

<details>
    <summary>result</summary>
    <img src= "img_15.png">
</details>

<br>

### <a id="title6">Задание 3.2: Переполнение (Overflow)</a>

Выполните в jshell и объясните результаты:

```
jshell> byte b = 127
jshell> b++
jshell> b

jshell> int max = Integer.MAX_VALUE
jshell> max + 1
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_16.png">
    <br>
    <img src= "img_17.png">
</details>

```
Вопрос: Почему 127 + 1 для byte даёт -128?
```

Ответ: 

* происходит переполнение, Java не выбросит исключение, просто продолжит работу с минимальным значением из диапозона этого типа данных.

<br>

### <a id="title7">Задание 3.3: Точность дробных чисел</a>

Проверьте в jshell:

```
jshell> 0.1 + 0.2
jshell> 0.1 + 0.2 == 0.3
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_18.png">
</details>

```
Вопрос: Почему результат не равен 0.3? Как это учитывать при написании программ?
```

Ответ: 

* float и double хранят числа в двоичном формате IEEE 754. Что иногда приводит к подобным резульататм. 

* Лучше использовать класс BigDecimal или округление или привидение типов для сравнения.

<br>

### <a id="title8">Задание 3.4: Литералы в разных системах счисления</a>

Создайте файл NumberSystems.java:

```
public class NumberSystems {
    public static void main(String[] args) {
        int decimal = 42;           // десятичная
        int hex = 0x2A;             // шестнадцатеричная (0x)
        int octal = 052;            // восьмеричная (0)
        int binary = 0b101010;      // двоичная (0b)

        System.out.println("Десятичная: " + decimal);
        System.out.println("Шестнадцатеричная: " + hex);
        System.out.println("Восьмеричная: " + octal);
        System.out.println("Двоичная: " + binary);

        // Все ли значения равны?
        System.out.println("Все равны: " + (decimal == hex && hex == octal && octal == binary));
    }
}
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_19.png">
</details>

<br>
<br>

---

### Часть 4: Операторы

### <a id="title9">Задание 4.1: Арифметические операторы</a>

Создайте файл Calculator.java:

```
public class Calculator {
    public static void main(String[] args) {
        int a = 17;
        int b = 5;

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + ____);
        System.out.println("a * b = " + ____);
        System.out.println("a / b = " + ____);  // Что будет?
        System.out.println("a % b = " + ____);  // Остаток от деления

        // Как получить 3.4 при делении 17 на 5?
        System.out.println("a / b (дробный результат) = " + ____);
    }
}
```

#### Решение:

```
package practice_1;

public class Calculator {
    public static void main(String[] args) {
        int a = 17;
        int b = 5;

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));  // Что будет?
        System.out.println("a % b = " + (a % b));  // Остаток от деления

        // Как получить 3.4 при делении 17 на 5?
        System.out.println("a / b (дробный результат) = " + (a/(float)b));
    }
}
```

<details>
    <summary>result</summary>
    <img src= "img_20.png">
</details>

<br>

### <a id="title10">Задание 4.2: Инкремент и декремент</a>

Предскажите результат, затем проверьте в jshell:

```
int x = 5;
System.out.println(x++);  // Что выведет? ____
System.out.println(x);    // Что выведет? ____
System.out.println(++x);  // Что выведет? ____
System.out.println(x);    // Что выведет? ____
```

#### Решение:

* инкремент пост = х++ = после выполнения операции.
* инкремент пре ++х = до выполнения операции

<details>
    <summary>result</summary>
    <img src= "img_21.png">
</details>

<br>

### <a id="title11">Задание 4.3: Операторы сравнения и логические</a>

Создайте файл Comparison.java:

```
public class Comparison {
    public static void main(String[] args) {
        int age = 20;
        boolean hasLicense = true;

        // Заполните пропуски, чтобы получить true
        System.out.println(age ____ 18);           // age >= 18
        System.out.println(age ____ 25);           // age < 25
        System.out.println(age >= 18 ____ hasLicense);  // оба условия истинны

        // Может ли человек водить машину?
        boolean canDrive = (age >= 18) && hasLicense;
        System.out.println("Может водить: " + canDrive);
    }
}
```

#### Решение:

```
package practice_1;

public class Comparison {
    public static void main(String[] args) {
        int age = 20;
        boolean hasLicense = true;

        // Заполните пропуски, чтобы получить true
        System.out.println(age >=  18);           // age >= 18
        System.out.println(age < 25);           // age < 25
        System.out.println(age >= 18 && hasLicense);  // оба условия истинны

        // Может ли человек водить машину?
        boolean canDrive = (age >= 18) && hasLicense;
        System.out.println("Может водить: " + canDrive);
    }
}
```

<details>
    <summary>result</summary>
    <img src= "img_22.png">
</details>

<br>

### <a id="title12">Задание 4.4: Побитовые операторы</a>

Выполните в jshell:

```
jshell> int a = 5        // в двоичной: 0101
jshell> int b = 3        // в двоичной: 0011

jshell> a & b            // побитовое И
jshell> a | b            // побитовое ИЛИ
jshell> a ^ b            // побитовое исключающее ИЛИ

jshell> a << 1           // сдвиг влево (умножение на 2)
jshell> a >> 1           // сдвиг вправо (деление на 2)
```

Задание: Переведите числа в двоичную систему и объясните результаты.

#### Решение:

```
AND
  0101  (5)
& 0011  (3)
--------
  0001  (1)
```

```
OR
  0101  (5)
| 0011  (3)
--------
  0111  (7)
```

```
XOR
  0101  (5)
^ 0011  (3)
--------
  0110  (6)
```

```
умножение 2^n
  0101  (5)
<< 1
--------
  1010  (10)
```

```
деление на 2^n
  0101  (5)
>> 1
--------
  0010  (2)
```

<details>
    <summary>result</summary>
    <img src= "img_23.png">
</details>

<br>
<br>

---

### Часть 5: Строки и сравнение объектов

### <a id="title13">Задание 5.1: == vs .equals()</a>

```
public class StringComparison {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = new String("Hello");

        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s1 == s3: " + (s1 == s3));
        System.out.println("s3 == s4: " + (s3 == s4));

        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("s1.equals(s3): " + s1.equals(s3));
        System.out.println("s3.equals(s4): " + s3.equals(s4));
    }
}
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_24.png">
</details>

**Вопросы:**

```
Почему s1 == s2 возвращает true?
```

Ответ:

* s1 и s2 ссылаются на один и тот же объект в памяти, это и есть String Pool. так Java экономит память.

```
Почему s3 == s4 возвращает false?
```

Ответ:

* s3 и s4 сравнивает адреса в памяти, они разные, поэтому такой результат.

```
Какой метод нужно использовать для сравнения содержимого строк?
```

Ответ:

* equals() для сравнения. String переопределит equals() сравнения посимвольно.

<br>

### <a id="title14">Задание 5.2: String Pool</a>

Нарисуйте схему памяти для следующего кода:

```
String a = "Java";
String b = "Java";
String c = new String("Java");

```

#### Решение:

Иными словами, в String Pool наодиться в кучи, в нём будет лежать 1 объект - на него ссылаются 2 переменные a и b. В куче лежит объект, на который ссылается переменная с. Всего 2 объекта в памяти.

<br>
<br>

---

### Часть 6: Самостоятельная работа

### <a id="title15">Задание 6.1: Калькулятор BMI</a>

Напишите программу BMICalculator.java, которая:

* Объявляет переменные для роста (в метрах) и веса (в кг)

* Вычисляет индекс массы тела: BMI = вес / (рост * рост)

* Выводит результат

```
public class BMICalculator {
    public static void main(String[] args) {
        double weight = 70.0;  // кг
        double height = 1.75;  // метры

        // Вычислите BMI
        double bmi = ____;

        System.out.println("Вес: " + weight + " кг");
        System.out.println("Рост: " + height + " м");
        System.out.println("BMI: " + bmi);
    }
}
```

#### Решение:

```
package practice_1;

public class BMICalculator {
    public static void main(String[] args) {
        double weight = 70.0;  // кг
        double height = 1.75;  // метры

        // Вычислите BMI
        double bmi = weight / (height*height);

        System.out.println("Вес: " + weight + " кг");
        System.out.println("Рост: " + height + " м");
        System.out.println("BMI: " + bmi);
    }
}
```

<details>
    <summary>result</summary>
    <img src= "img_25.png">
</details>

<br>

### <a id="title16">Задание 6.2: Конвертер температуры</a>

Напишите программу TemperatureConverter.java, которая:

* Переводит температуру из Цельсия в Фаренгейт: F = C * 9/5 + 32

* Переводит из Фаренгейта в Цельсий: C = (F - 32) * 5/9

```
public class TemperatureConverter {
    public static void main(String[] args) {
        double celsius = 25.0;
        double fahrenheit = 77.0;

        // Конвертируйте и выведите результаты
        double celsiusToF = ____;
        double fahrenheitToC = ____;

        System.out.println(celsius + "°C = " + celsiusToF + "°F");
        System.out.println(fahrenheit + "°F = " + fahrenheitToC + "°C");
    }
}
```

#### Решение:

```
package practice_1;

public class TemperatureConverter {
    public static void main(String[] args) {
        double celsius = 25.0;
        double fahrenheit = 77.0;

        // Конвертируйте и выведите результаты
        double celsiusToF = celsius * 9.5 + 32;
        double fahrenheitToC = (fahrenheit - 32) * 5 / 9;

        System.out.println(celsius + "°C = " + celsiusToF + "°F");
        System.out.println(fahrenheit + "°F = " + fahrenheitToC + "°C");
    }
}
```

<details>
    <summary>result</summary>
    <img src= "img_26.png">
</details>

<br>

### <a id="title17">Задание 6.3: Обмен значений переменных</a>

Напишите программу, которая меняет местами значения двух переменных без использования третьей переменной (используйте побитовые операции или арифметику):

```
public class SwapValues {
    public static void main(String[] args) {
        int a = 10;
        int b = 25;

        System.out.println("До обмена: a = " + a + ", b = " + b);

        // Обменяйте значения без третьей переменной
        // ____
        // ____
        // ____

        System.out.println("После обмена: a = " + a + ", b = " + b);
    }
}
```

#### Решение:

```
package practice_1;

public class SwapValues {
    public static void main(String[] args) {
        int a = 10;
        int b = 25;

        System.out.println("До обмена: a = " + a + ", b = " + b);
        
        // Вариант 1
        a = a + b;
        b = a - b;
        a = a - b;
        
        // Вариант 2
//        a = a - (b - (a + a));
//        b = b / a;
//        a = b * a;
//        b = a - b - b - b;

        System.out.println("После обмена: a = " + a + ", b = " + b);
    }
}
```

<details>
    <summary>result</summary>
    <img src= "img_27.png">
    <br>
    <img src= "img_28.png">
</details>

<br>
<br>

---

### Часть 7: Дополнительные задания

### <a id="title18">Задание 7.1: Проверка чётности без условных операторов</a>

Напишите выражение, которое возвращает true, если число чётное, используя только побитовые операции:

```
int n = 42;
boolean isEven = ____; // используйте побитовое И
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_30.png">
</details>

<br>

### <a id="title19">Задание 7.2: Абсолютное значение без Math.abs()</a>

Найдите способ получить абсолютное значение числа без использования Math.abs() и условных операторов:

```
int n = -42;
int abs = ____; // только арифметика и побитовые операции
```

#### Решение:

<details>
    <summary>result</summary>
    <img src= "img_29.png">
</details>

<br>
<br>

---

## <a id="title20">Часть 8: Контрольные вопросы</a>

```
1. Чем отличается JDK от JRE?
```

Ответ: 

* JDK включает в себя JRE. С помощью JRE можем запускать программы, написанные на Java, Groovy, Kotlin - все используют JVM. JVM + бибилотеки по запуску программ. JDK используется для написания не только для запуска, но и для разработки программ.

```
2. Что такое байт-код и зачем он нужен?
```

Ответ: 

* байт-код это набор инструкций, который понимает JVM (не машинный код). C помощью них, она может скомпилированный код выполнить. Позволяет Java быть платформонезависимой: один .class = байт-код работает на любой ОС с JVM

```
3. Какие 8 примитивных типов есть в Java?
```

Ответ: 

* int, short, long, byte, float, double, char, boolean.

```
4. Почему 0.1 + 0.2 != 0.3 в Java?
```

Ответ: 

* Java числа с плавающей точкой храняться в двоичном формате IEEE 754. Не всегда значения сравниваются правильно. Для точности в финансовых операция лучше использовать BigDecimal и переопределённый метод compareTo().

```
5. В чём разница между ++x и x++?
```

Ответ: 

* ++x выполняет до операции, то есть x = 5, ++x, то при выводе будет 6. Если x = 5, x++ выполняет после операции, то при выводе x будет 5.

```
6. Когда нужно использовать .equals() вместо ==?
```

Ответ: 

* == используется для сравнения таких примитивных типов, как int, byte, boolean. .equals() больше подходит для сравнения объектов (их содержимого), потому что == будет сравнивать ссылки на адреса в памяти.

```
7. Что такое String Pool?
```

Ответ: 

* используеся для экономии памяти, когда несколько переменных имеют один и тот же объект в памяти. Находиться в куче и работает только со строковыми литералами (""). Не работает с объектами, созданные через new.

```
8. Что произойдёт при переполнении типа int?
```

Ответ: 

* присвоит себе минимальное значение в своём диапозоне.
