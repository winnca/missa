# Содержание

### [Тест](#title13)

## Часть 1: Вложенные классы

### [Задание 1.1: Нестатический внутренний класс](#title0)

### [Задание 1.2: Статический вложенный класс — Builder Pattern](#title1)

### [Задание 1.3: Сравнение Inner vs Static Nested](#title2)

## Часть 2: Вложенные интерфейсы

### [Задание 2.1: Система событий](#title3)

## Часть 3: Обобщения (Generics)

### [Задание 3.1: Обобщённый стек](#title4)

### [Задание 3.2: Ограниченные параметры типа](#title5)

### [Задание 3.3: Wildcards — копирование коллекций](#title6)

## Часть 4: Исключения

### [Задание 4.1: Базовая обработка исключений](#title7)

### [Задание 4.2: Собственные исключения](#title8)

### [Задание 4.3: Try-with-resources](#title9)

### [Задание 4.4: Цепочка исключений](#title10)

## Часть 5: Самостоятельная работа

### [Задание 5.1: Обобщённый кэш](#title11)

### [Задание 5.2: Обобщённый Result тип](#title12)

* ### [Задание 6: Контрольные вопросы](#title14)

<br>
<br>

---

### <a id="title13">Тест</a>

<br>
<br>

<details open>
    <summary>result</summary>
    <br>
    <img src="img_49.png"/>
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
    <br>
    <img src="img_37.png"/>
    <br>
    <img src="img_38.png"/>
    <br>
    <img src="img_39.png"/>
    <br>
    <img src="img_40.png"/>
    <br>
    <img src="img_41.png"/>
    <br>
    <img src="img_42.png"/>
    <br>
    <img src="img_43.png"/>
    <br>
    <img src="img_44.png"/>
    <br>
    <img src="img_45.png"/>
    <br>
    <img src="img_46.png"/>
    <br>
    <img src="img_47.png"/>
    <br>
    <img src="img_48.png"/>
</details>

---

## Часть 1: Вложенные классы

### <a id="title0">Задание 1.1: Нестатический внутренний класс</a>

Реализуйте класс Library с полями name и capacity. Внутри него объявите нестатический внутренний класс Book с полями title (String), author (String), year (int). Метод getInfo() должен выводить: «Книга: [title] автора [author] ([year]) в библиотеке [Library.name]». Обратите внимание: Book обращается к полю name внешнего класса напрямую. Создайте экземпляр Library, затем экземпляр Book через lib.new Book(...), вызовите getInfo().

Ожидаемый вывод:

```
Книга: Война и мир автора Толстой Л.Н. (1869) в библиотеке Городская библиотека
```

Вопрос: Попробуйте добавить static к классу Book. Что изменится? Почему потеряется доступ к name?

### Решение

```
package practice_4.task_1_1;

public class Library {
    private String name;
    private int capacity;
    Library(String name, int capacity){
        this.name=name;
        this.capacity=capacity;
    }
    class Book{
        String title;
        String author;
        int year;
        Book(String title, String author, int year){
            this.title=title;
            this.author=author;
            this.year=year;
        }
        public void getInfo(){
            System.out.println("Книга: " + title + " автора " + author + " (" + year + ") " + "в библиотеке " + name);
        }
    }
    public static void main(String[] args){
        Library library = new Library("Городская библиотека", 100);
        Book book1 = library.new Book("Война и мир", "Толстой Л.Н.", 1869);
        book1.getInfo();
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img.png"/>
</details>

Ответ на вопрос:

* Если сделаем вложенный класс статическим, то сможем обращаться к внешнему классу только к статическим полям и методам. Нельзя создать его экземпляр через внешний экземпляр + new конструктор книги.

* Можно сделать поля во внешнем классе статическими и внутри класса статическими, убрать конструкторы, для полей добавить значения, которые используем.

* При создании экземпляры книги обращаемся к конструктору через внешний класс.

<details open>
    <summary>result</summary>
    <br>
    <img src="img_1.png"/>
    <br>
    <img src="img_2.png"/>
    <br>
    <img src="img_3.png"/>
    <br>
    <img src="img_4.png"/>
</details>

<br>

### <a id="title1">Задание 1.2: Статический вложенный класс — Builder Pattern</a>

Реализуйте класс Computer с final-полями: cpu (String), ram (int), storage (int, по умолчанию 256), gpu (String, по умолчанию null), ssd (boolean, по умолчанию true). Конструктор private — принимает Builder.

Реализуйте статический вложенный класс Builder с конструктором Builder(String cpu, int ram) и методами storage(int), gpu(String), ssd(boolean) (каждый возвращает this) и build() (возвращает новый Computer).

Переопределите toString() в формате: Computer{CPU='...', RAM=...GB, Storage=...GB SSD/HDD, GPU='...' / 'встроенная'}.

Создайте игровой компьютер (Core i9, 32GB, 2000GB, RTX 4080, SSD) и офисный (Core i5, 16GB, дефолтные значения) и выведите оба.

### Решение

```
package practice_4.task_1_2;

public class Computer {
    private final String cpu;
    private final int ram;
    private final int storage;
    private final String gpu;
    private final boolean ssd;
    private Computer(Builder builder){
        this.cpu= builder.cpu;
        this.ram= builder.ram;
        this.storage= builder.storage;
        this.gpu= builder.gpu;
        this.ssd= builder.ssd;
    }
    static class Builder{
        private final String cpu;
        private final int ram;
        private int storage = 256;
        private String gpu = null;
        private boolean ssd = true;
        Builder (String cpu, int ram){
            this.cpu=cpu;
            this.ram=ram;
        }
        Builder storage(int storage){
            this.storage=storage;
            return this;
        }
        Builder gpu(String gpu){
            this.gpu=gpu;
            return this;
        }
        Builder ssd(boolean ssd){
            this.ssd=ssd;
            return this;
        }
        Computer build(){return new Computer(this);}
    }
    @Override
    public String toString() {
        String storageType = ssd ? "SSD":"HDD";
        String gpuType = (gpu == null || gpu.isEmpty()) ? "встроенная" : "'" + gpu + "'";
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram=" + ram + "GB" +
                ", storage=" + storage + "GB" + storageType +
                ", gpu='" + gpuType + '\'' + '}';
    }
    public static void main(String[] args){
        Computer computerPlay = new Builder("Core i9", 32).storage(2000).gpu("RTX 4080").ssd(true).build();
        System.out.println(computerPlay);
        Computer computerOffice = new Builder("Core i5", 16).build();
        System.out.println(computerOffice);
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_5.png"/>
</details>

<br>

### <a id="title2">Задание 1.3: Сравнение Inner vs Static Nested</a>

Запустите следующий код и объясните результаты:

```
public class Memory {
    private int instanceData = 100;
    private static int staticData = 200;

    class InnerClass {
        void show() {
            System.out.println("Inner: instanceData = " + instanceData);  // ?
            System.out.println("Inner: staticData = " + staticData);      // ?
        }
    }

    static class StaticNested {
        void show() {
            // System.out.println("Static: instanceData = " + instanceData); // ?
            System.out.println("Static: staticData = " + staticData);     // ?
        }
    }

    public static void main(String[] args) {
        Memory memory = new Memory();

        // Нестатический — нужен экземпляр
        InnerClass inner = memory.new InnerClass();
        inner.show();

        // Статический — экземпляр не нужен
        StaticNested nested = new StaticNested();
        nested.show();
    }
}
```

Демонстрация без закомментированной строчки и с убиранием комментирования.

<details open>
    <summary>result</summary>
    <br>
    <img src="img_7.png"/>
    <br>
    <img src="img_6.png"/>
</details>

Ответьте письменно:

1. Почему закомментированная строка вызовет ошибку компиляции?

* Нельзя обращаться к нестатическим переменным внешнего класса из вложенного статического класса (статический вложенный класс не имеет ссылки на экземпляр внешнего класса).

2. В чём разница при создании экземпляров этих двух классов?

* При создании нестатического экземпляра всегда делаем через объект внешнего класса. 

* При создании статического экземпляра, создаём через статический вложенный класс, не привязан к внешнему классу.

<br>
<br>

---

## Часть 2: Вложенные интерфейсы

### <a id="title3">Задание 2.1: Система событий</a>

Реализуйте класс EventSystem со следующими вложенными элементами:

* Вложенный интерфейс EventHandler<T> с методом void handle(T event).

* Вложенный интерфейс EventFilter<T> с методом boolean accept(T event).

* Вложенный статический класс Event с полями type (String), data (String), timestamp (long, устанавливается при создании).

* В самом EventSystem: список обработчиков (List<EventHandler<Event>>), текущий фильтр (EventFilter<Event>), методы setFilter, addHandler, fire.

В методе fire(Event event): если фильтр есть и он не принимает событие — вывести «Событие отфильтровано: ...»; иначе — передать всем обработчикам.

В main(): установите фильтр, пропускающий только type == "ERROR". Добавьте обработчик, выводящий событие. Запустите 4 события (INFO, ERROR, DEBUG, ERROR).

### Решение

```
package practice_4.task_2_1;

import java.util.ArrayList;
import java.util.List;

public class EventSystem {
    private List<EventHandler<Event>> handlers = new ArrayList<>();
    private EventFilter<Event> filter;
    public interface EventHandler<T>{
        void handle(T event);
    }
    public interface EventFilter<T>{
        boolean accept(T event);
    }
    static class Event{
        private String type;
        private String data;
        private long timestamp;
        public Event(String type, String data){
            this.type=type;
            this.data=data;
            this.timestamp=System.currentTimeMillis();
        }
        public String getType() {return type;}
        public String getData() {return data;}
        public long getTimestamp() {return timestamp;}

        @Override
        public String toString() {return "Event{" + "type='" + type + '\'' + ", data='" + data + '\'' + ", timestamp=" + timestamp + '}';}
    }
    public void addHandlers(EventHandler<Event> handler) {handlers.add(handler);}
    public void setFilter(EventFilter<Event> filter) {this.filter = filter;}
    public void fire(Event event){
        if (event != null || !filter.accept(event)) System.out.println("Событие отфильтровано: " + event);
        else{
            for (EventHandler<Event> handler : handlers){
                handler.handle(event);
            }
        }
    }
    public static void main(String[] args){
        EventSystem system = new EventSystem();
        system.setFilter(event -> "ERROR".equals(event.getType()));
        system.addHandlers(event -> System.out.println("Обработано: " + event));
        system.fire(new Event("INFO", "Запуск сервера"));
        system.fire(new Event("ERROR", "Ошибка подключения к бд"));
        system.fire(new Event("DEBUG", "входим в API, в эндпоинт"));
        system.fire(new Event("ERROR", "Освободи памяти"));
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_8.png"/>
</details>

<br>
<br>

---

## Часть 3: Обобщения (Generics)

### <a id="title4">Задание 3.1: Обобщённый стек</a>

Реализуйте обобщённый стек Stack<T> (LIFO) на основе массива Object[]:

* Начальная ёмкость 10; при переполнении массив расширяется вдвое.

* push(T element) — добавляет на вершину.

* pop() — удаляет и возвращает вершину; бросает EmptyStackException если пуст.

* peek() — возвращает вершину без удаления; бросает EmptyStackException если пуст.

* isEmpty(), size(), toString().

Продемонстрируйте работу: создайте Stack<String>, добавьте три строки, вызовите peek() и pop(). Создайте Stack<Integer>, заполните числами 10–50, извлеките все элементы в цикле.

### Решение

```
package practice_4.task_3_1;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T>{
    private Object[] objects;
    private int size = 0;
    private static final int capacity = 10;
    public Stack(){
        this.objects = new Object[capacity];
    }
    public void push(T element){
        if (size == objects.length){
            resize();
        }
        objects[size++]=element;
    }
    public T pop(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        T element = (T) objects[--size];
        objects[size] = null;
        return element;
    }
    public T peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        T element = (T) objects[size-1];
        return element;
    }
    public boolean isEmpty(){return size == 0;}
    public int size() {return size;}
    public void resize(){
        int newCapacity = objects.length * 2;
        objects = Arrays.copyOf(objects, newCapacity);
    }
    @Override
    public String toString() {
        return "Stack{" +
                "objects=" + Arrays.toString(objects) +
                '}';
    }
}

class Test{
    public static void main(String[] args){
        Stack<String> stack1 = new Stack<>();
        stack1.push("hello");
        stack1.push("world");
        stack1.push("bye");
        System.out.println(stack1.peek());
        System.out.println(stack1.pop());

        Stack<Integer> stack2 = new Stack<>();
        for (int i=10;i<=50;i++) {
            stack2.push(i);
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop() + " ");
        }
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_9.png"/>
</details>

<br>

### <a id="title5">Задание 3.2: Ограниченные параметры типа</a>

Реализуйте утилитарный класс NumberUtils со следующими статическими методами:

* sum(List<? extends Number> numbers) → double — сумма элементов.

* average(List<? extends Number> numbers) → double — среднее; бросает IllegalArgumentException для пустого списка.

* <T extends Comparable<T>> T findMax(List<T> list) — максимальный элемент; бросает IllegalArgumentException для null или пустого списка.

* <T extends Comparable<T>> T findMin(List<T> list) — аналогично.

* fillWithIntegers(List<? super Integer> list, int n) — добавляет в список числа от 1 до n.

Протестируйте: сумма [1,2,3,4,5], сумма [1.5,2.5,3.5], среднее, максимум строк ["яблоко","апельсин","банан"], fillWithIntegers в List<Number>.

### Решение

```
package practice_4.task_3_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberUtils<T> {
    public static double sum(List<? extends Number> numbers){
        double sum = 0;
        for (Number number : numbers){
            sum = sum + number.doubleValue();
        }
        return sum;
    }
    public static double average(List<? extends Number> numbers){
        if (numbers == null || numbers.isEmpty()){
            throw new IllegalArgumentException("Пустой список");
        }
        return sum(numbers) / numbers.size();
    }
    public static <T extends Comparable<T>> T findMax(List<T> list){
        if (list == null || list.isEmpty()){
            throw new IllegalArgumentException("Пустой список");
        }
        T max = (T) list.get(0);
        for (int i=1;i<list.size();i++){
            if (list.get(i).compareTo(max) > 0){
                max = (T) list.get(i);
            }
        }
        return max;
    }
    public static <T extends Comparable<T>> T findMin(List<T> list){
        if (list == null || list.isEmpty()){
            throw new IllegalArgumentException();
        }
        T min = (T) list.get(0);
        for (int i=1;i<list.size();i++){
            if (list.get(i).compareTo(min) < 0){
                min = (T) list.get(i);
            }
        }
        return min;
    }
    public static void fillWithIntegers(List<? super Integer> list, int n){
        for (int i=1;i<=n;i++){
            list.add(i);
        }
    }
}
class TestNumberUtils{
    public static void main(String[] args){
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        double sumInt = NumberUtils.sum(intList);
        System.out.println("Сумма [1, 2, 3, 4, 5]: " + sumInt);

        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);
        double sumDouble = NumberUtils.sum(doubleList);
        System.out.println("Сумма [1.5, 2.5, 3.5]: " + sumDouble);

        double avgInt = NumberUtils.average(intList);
        double avgDouble = NumberUtils.average(doubleList);
        System.out.println("Среднее для intList: " + avgInt);
        System.out.println("Среднее для doubleList: " + avgDouble);

        List<String> stringList = Arrays.asList("яблоко", "апельсин", "банан");
        String maxString = NumberUtils.findMax(stringList);
        System.out.println("Максимум из строк: " + maxString);

        List<Number> numberList = new ArrayList<>();
        int n = 5;
        NumberUtils.fillWithIntegers(numberList, n);
        System.out.println("Итоговый заполненный List<Number>: " + numberList); // Ожидается: [1, 2, 3, 4, 5]
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_10.png"/>
</details>

<br>

### <a id="title6">Задание 3.3: Wildcards — копирование коллекций</a>

Реализуйте класс WildcardDemo с тремя статическими методами, правильно выбрав wildcards:

* printAll(List<?> list) — выводит все элементы (принимает список любого типа).

* sumNumbers(List<? extends Number> list) → double — сумма через Number::doubleValue.

* addDefaults(List<? super String> list) — добавляет "default1" и "default2".

Объясните в комментарии: почему List для Integer не присваивается переменной List для Number? Как это обойти с помощью wildcard? Продемонстрируйте корректную копию из List для Integer в List для Number.

### Решение

```
package practice_4.task_3_3;

import java.util.*;

public class WildcardDemo {
    public static void printAll(List<?> list){
        for (int i=0;i<list.size();i++){
            System.out.print(list.get(i));
        }
    }
    public static double sumNumbers(List<? extends Number> list){
        double sum = list.stream().mapToDouble(Number::doubleValue).sum();
        return sum;
    }
    public static void addDefaults(List<? super String> list){
        list.add("default1");
        list.add("default2");
    }
}
class Test{
    public static void main(String[] args){
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Number> list2 = new ArrayList<>();
        copyElements(list1, list2);
        System.out.println(list2);
    }
}
```

* В Java массивы являются ковариантными (например, Integer[] можно присвоить в Number[] = прозрачная коробка яблок разрешит набить табличку фрукты, но положить апельсины нельзя = яблоки инты, апельсины вещественные).

* Дженерики = инвариантны:
  
* List для Integer и List для Number — это два абсолютно разных, не связанных друг с другом типа, у которых общий предок только List<?> (закрытая сумка только для яблок с такой надписью, сразу говорит, что и для чего).

#### Правило PESC

* Источник — это коллекция, которая отдает (производит) данные. Мы из нее только читаем.

* Приемник — это коллекция, которая принимает (потребляет) данные. Мы в нее только записываем.

+ Используем extends, когда цель — безопасное чтение из коллекции. Нам важен общий верхний тип, к которому можно привести всё содержимое.

+ Используем super, когда цель — безопасная запись в коллекцию. Нам важно, чтобы коллекция могла принять наш конкретный тип.

Пример: Animal <- Dog <- Cat.

1. Есть метод, принимающий список собак, заставляя их всей лаять и пересчитывать их (источник).

```
public static void processDogs(List<? extends Dog> source) {
    for (Dog dog : source) {
        dog.bark(); // БЕЗОПАСНО: Кем бы ни были элементы, они как минимум Dog!
}
//source.add(new Dog()); // ОШИБКА компиляции! Почему?

```

Чтение разрешено:

* Метод может принять List для Dog или List для Corgi. Если передали List для Corgi, то каждый Корги — это Собака, поэтому вызвать метод bark() абсолютно безопасно.

Запись запрещена:

* Если бы компилятор разрешил строку source.add(new Dog()), а нам на вход передали чистый список List<Corgi>, то мы бы только что подселили обычную Собаку в вольер к Корги. Это сломало бы логику программы, где ожидаются только Корги.

2. Теперь мы пишем метод, который берет конкретную собаку сlass Corgi и добавляет ее в список-приемник (приёмник).

```
public static void addDogToKennel(List<? super Dog> destination) {
    destination.add(new Dog());   // БЕЗОПАСНО: Обычную собаку можно добавить
    destination.add(new Corgi()); // БЕЗОПАСНО: Корги тоже является собакой!

    // Dog dog = destination.get(0); // ОШИБКА компиляции! Почему?
}
```

Запись разрешена:

* Метод может принять List для Dog, List для Animal или List для Object. 

* В любой из этих трех списков можно абсолютно легально положить объект типа Dog или его наследника Corgi (ведь Корги — это тоже Собака и тоже Животное).

Чтение запрещено:

* Если нам на вход передали List для Animal, то там внутри уже могут лежать кошки, птицы и крокодилы.

* Попытка прочитать оттуда элемент и сразу привести его к типу Dog (строка Dog dog = destination.get(0)) приведет к катастрофе в runtime, если там окажется кошка.

* Единственное, что гарантированно можно оттуда прочитать — это Object.

<details open>
    <summary>result</summary>
    <br>
    <img src="img_11.png"/>
</details>

<br>
<br>

---

## Часть 4: Исключения

### <a id="title7">Задание 4.1: Базовая обработка исключений</a>

Напишите программу ExceptionBasics с тремя блоками:

* Деление: для массива делителей {2, 0, 5, 0, 1} вычислите 100 / divisor, обработав ArithmeticException.

* Массив: для индексов {0, 1, 5, 2, -1} обратитесь к массиву {10, 20, 30}, обработав ArrayIndexOutOfBoundsException.

* Парсинг: для строк {"42", "abc", "100", "3.14", "-7"} вызовите Integer.parseInt, обработав NumberFormatException.

Для каждого случая распечатайте результат или сообщение об ошибке.

### Решение

```
package practice_4.task_4_1;

public class ExceptionBasic {
    public static void main(String[] args){
        int[] arr1 = {2, 0, 5, 0, 1};
        for (int i=0;i<arr1.length;i++) {
            try {
                int divisor = arr1[i];
                System.out.println(100 / divisor);
            } catch (ArithmeticException e) {
                System.out.println("Делить на 0 нельзя" + e);
            }
        }

        int[] arr2 = {10,20,30};
        int[] arr2index = {0, 1, 5, 2, -1};
        for (int i=0;i<arr2index.length;i++) {
            try {
                System.out.println(arr2[arr2index[i]]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Выход за границы массива");
            }
        }

        String[] arr3 = {"42", "abc", "100", "3.14", "-7"};
        for (String s : arr3) {
            try {
                int temp = Integer.parseInt(s);
                System.out.println(temp);
            } catch (NumberFormatException e) {
                System.out.println("Не числа");
            }
        }
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_12.png"/>  
</details>

<br>

### <a id="title8">Задание 4.2: Собственные исключения</a>

Создайте систему исключений для банковского приложения:

### Решение

```
package practice_4.task_4_2;

public class BankException extends Exception{
    private String operationId;

    public BankException(String message, String operationId) {
        super(message);
        this.operationId = operationId;
    }

    public String getOperationId() { return operationId; }
}
```

```
package practice_4.task_4_2;

public class InsufficientFundsException extends BankException{
    private double required;
    private double available;

    public InsufficientFundsException(double required, double available, String operationId) {
        super(String.format("Недостаточно средств. Требуется: %.2f, доступно: %.2f",
                required, available), operationId);
        this.required = required;
        this.available = available;
    }

    public double getRequired() { return required; }
    public double getAvailable() { return available; }
}
```

```
package practice_4.task_4_2;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(double amount) {
        super("Сумма должна быть положительной, получено: " + amount);
    }
}
```

```
package practice_4.task_4_2;

public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId, double initialBalance) {
        if (initialBalance < 0) {
            throw new InvalidAmountException(initialBalance);
        }
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        balance += amount;
        System.out.printf("Пополнение на %.2f. Баланс: %.2f%n", amount, balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance, "WD-" + accountId);
        }
        balance -= amount;
        System.out.printf("Снятие %.2f. Баланс: %.2f%n", amount, balance);
    }

    public double getBalance() { return balance; }
    public String getAccountId() { return accountId; }
}
```

```
package practice_4.task_4_2;

public class BankTest {
    public static void main(String[] args){
        BankAccount account = new BankAccount("ACC-001", 1000.0);

        // Тест нормального пополнения и снятия
        account.deposit(500.0);
        try {
            account.withdraw(200.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест недостаточных средств
        try {
            account.withdraw(2000.0); // Должно бросить InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.printf("Не хватает: %.2f%n", e.getRequired() - e.getAvailable());
        }

        // Тест невалидной суммы (unchecked)
        try {
            account.deposit(-100); // Должно бросить InvalidAmountException
        } catch (InvalidAmountException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест невалидного начального баланса
        try {
            BankAccount badAccount = new BankAccount("BAD-001", -500);
        } catch (InvalidAmountException e) {
            System.out.println("Нельзя создать счёт: " + e.getMessage());
        }
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_13.png"/>  
</details>

<br>

### <a id="title9">Задание 4.3: Try-with-resources</a>

* Изучите класс FileLogger, реализующий AutoCloseable. 

* Запустите программу и убедитесь, что close() вызывается автоматически. 

Объясните: 

* (1) что выводится при close()? 

* (2) почему transient не применяется здесь, а flush() нужен?

### Решение

```
package practice_4.task_4_3;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements AutoCloseable {
    private PrintWriter writer;
    private String filename;
    private int entriesWritten = 0;

    public FileLogger(String filename) throws IOException {
        this.filename = filename;
        this.writer = new PrintWriter(new FileWriter(filename, true));
        System.out.println("Логгер открыт: " + filename);
    }

    public void log(String level, String message) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        writer.printf("[%s] [%s] %s%n", timestamp, level, message);
        writer.flush(); // Сбрасываем буфер
        entriesWritten++;
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void error(String message) {
        log("ERROR", message);
    }

    public void warning(String message) {
        log("WARNING", message);
    }

    @Override
    public void close() {
        writer.close();
        System.out.println("Логгер закрыт: " + filename + " (записей: " + entriesWritten + ")");
    }

    public static void main(String[] args) {
        try (FileLogger logger = new FileLogger("app.log")) {
            logger.info("Приложение запущено");
            logger.info("Инициализация завершена");
            logger.warning("Конфигурация не найдена, используются значения по умолчанию");

            // Симулируем ошибку
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                logger.error("Ошибка: " + e.getMessage());
            }

            logger.info("Работа завершена");
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл лога: " + e.getMessage());
        }
        // logger.close() вызвался автоматически!

        // Прочитаем записанный файл
        System.out.println("\n--- Содержимое app.log ---");
        try (BufferedReader reader = new BufferedReader(new FileReader("app.log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
    }
}
```

Что выводится при вызове close()?

* При автоматическом закрытии логгера срабатывает @Override метод close().

* Выводится строка о подтверждении закрытии файла и указания точного количества сделанных за сессию записей.

Почему transient здесь не применяется, а flush() нужен?

* Ключевое слово transient используется исключительно при сериализации объектов. Поле не нужно сохранять в байтовый поток при превращении объекта в файл.

* FileLogger не сериализуется. Поля writer и filename нужны для текущей работы запущенной программы, а не для сохранения состояния самого объекта логгера.

<details open>
    <summary>result</summary>
    <br>
    <img src="img_14.png"/>  
</details>

<br>

### <a id="title10">Задание 4.4: Цепочка исключений</a>

* Изучите и запустите трёхуровневую систему.

Объясните:

* (1) что выведет e.getCause().getMessage()? 

* (2) для чего используется цепочка исключений?

* (3) какова разница между getMessage() и getCause().getMessage()?

1) Что выведет `e.getCause().getMessage()`?

* Return сообщение корневого исключения, которое произошло на уровне базы данных.

2) Для чего используется цепочка исключений?

* **Инкапсуляция и абстракция**. Верхние слои приложения не должны знать о деталях реализации нижних слоев. Viewer слой получает ошибку бизнес-логики, а не ошибку базы данных.

* **Сохранение контекста**. Можно добавить важные высокоуровневые детали (например, id, при котором упал запрос), не теряя при этом техническую причину сбоя.

* **Упрощение отладки**. Можно увидеть в логах полный путь ошибки от места её зарождения до места перехвата.

3) Разница между `getMessage()` и `getCause().getMessage()`?

* getMessage(): return текстовое описание текущего исключения. Формируется на уровне сервиса и объясняет, какая бизнес-операция сорвалась.

* getCause().getMessage(): return текстовое описание предыдущего исключения, которое стало триггером для текущего.


### Решение

```
package practice_4.task_4_4;

class DatabaseLayer {
    public String fetchData(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("SQL Error: Invalid ID " + id);
        }
        return "данные_" + id;
    }
}

class DataService {
    private DatabaseLayer db = new DatabaseLayer();

    public String getData(int id) throws ServiceException {
        try {
            return db.fetchData(id);
        } catch (Exception e) {
            throw new ServiceException("Не удалось получить данные для id=" + id, e);
        }
    }
}

class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class Application {
    public static void main(String[] args) {
        DataService service = new DataService();

        try {
            System.out.println(service.getData(1));
        } catch (ServiceException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            System.out.println(service.getData(-1));
        } catch (ServiceException e) {
            System.out.println("Ошибка сервиса: " + e.getMessage());
            System.out.println("Причина: " + e.getCause().getMessage()); // Исходное исключение
            System.out.println("\nПолный стек:");
            e.printStackTrace();
        }
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_15.png"/>  
</details>

<br>
<br>

---

## Часть 5: Самостоятельная работа

### <a id="title11">Задание 5.1: Обобщённый кэш</a>

* Реализуйте обобщённый LRU-кэш SimpleCache<K, V> с ограниченным размером. 

* Используйте LinkedHashMap с accessOrder=true и переопределённым removeEldestEntry. Методы: put(K key, V value), get(K key), containsKey(K key), size(), clear().

* Продемонстрируйте: создайте кэш ёмкостью 3, добавьте a=1, b=2, c=3. Добавьте d=4 — должен вытеснить наименее используемый. Проверьте, что get("a") вернёт null.

### Решение

```
package practice_4.task_5_1;

import java.lang.reflect.Field;
import java.util.*;

public class SimpleCache<K, V>{
    private final LinkedHashMap<K, V> linkedHashMap;
    public SimpleCache(int capacity){
        this.linkedHashMap=new LinkedHashMap<K, V>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
                return size() > capacity;
            }
        };
        if (!getAccessOrderValue(this.linkedHashMap)){
            throw new IllegalArgumentException("accessOrder должен быть true");
        }
    }
    public static boolean getAccessOrderValue(LinkedHashMap<?,?> linkedHashMap){
        try {
            Field field = LinkedHashMap.class.getDeclaredField("accessOrder");
            field.setAccessible(true); // открывает доступ к private полю
            return (boolean) field.get(linkedHashMap);
        } catch (Exception e){
            throw new RuntimeException("Не удалось получить доступ к полю accessOrder", e);
        }
    }
    public void put(K key, V value){ linkedHashMap.put(key,value);}
    public V getKey(K key){ return linkedHashMap.get(key);}
    public boolean containsKey(K key){ return linkedHashMap.containsKey(key);}
    public int size(){ return linkedHashMap.size();}
    public void clear(){ linkedHashMap.clear();}

    @Override
    public String toString() {
        return "SimpleCache{" +
                "linkedHashMap=" + linkedHashMap +
                '}';
    }
}
class Test {
    public static void main(String[] args) {
        SimpleCache<String, Integer> cache = new SimpleCache<>(3);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        System.out.println("Кэш после добавления 3 элементов: " + cache);
        cache.put("d", 4);
        System.out.println("Кэш после добавления 4 элемента: " + cache);
        System.out.println("Получение элемента 'a' (ожидается null): " + cache.getKey("a"));
        System.out.println("Есть ли такой ключ 'b': " + cache.containsKey("b"));
        System.out.println("Размер: " + cache.size());
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_16.png"/>  
</details>

<br>

### <a id="title12">Задание 5.2: Обобщённый Result тип</a>

Реализуйте обобщённый тип Result<T> — обёртку для результата операции:

* Фабричные методы: static <T> Result<T> success(T value) и static <T> Result<T> failure(Exception error).

```
* isSuccess(), getValue(), getError().

* getOrDefault(T defaultValue) — возвращает значение при успехе, иначе defaultValue.

* <R> Result<R> map(Function<T, R> mapper) — трансформирует значение при успехе; при неуспехе возвращает Result.failure с той же ошибкой.

* toString().
```

Реализуйте static Result<Integer> divide(int a, int b), продемонстрируйте divide(10,2) и divide(10,0), getOrDefault, цепочку map.

### Решение

```
package practice_4.task_5_2;

import java.util.function.Function;

public class Result<T> {
    private final T value;
    private final Exception error;
    private Result(T value){
        this.value=value;
        this.error=null;
    }
    private Result(Exception error){
        this.value=null;
        this.error=error;
    }
    public static <T> Result<T> success(T value){ // фабричный метод для успешного результата
        return new Result<>(value);
    }
    public static <T> Result<T> failure(Exception error){ // фабричный метод для ошибки
        return new Result<>(error);
    }
    public boolean isSuccess(){ // проверка на успешность операции
        return error == null;
    }
    public T getValue(){ // получение значения
        return value;
    }
    public Exception getError(){ // получение ошибки
        return error;
    }
    public T getOrDefault(T defaultValue){ // при успехе получаем значение, при ... дефолтное
        return isSuccess() ? value:defaultValue;
    }
    public <R> Result<R> map(Function<T, R> mapper){
        if (!isSuccess()){
            return Result.failure(this.error);
        }
        try {
            return Result.success(mapper.apply(this.value));
        } catch (Exception e){
            return Result.failure(e);
        }
    }
    @Override
    public String toString(){
        if (isSuccess()){
            return "Result.success={value=" + value + "}";
        } else{
            return "Result.failure={error=" + error.getClass().getSimpleName() + "}";
        }
    }
}

class Test {
    public static Result<Integer> divide(int a, int b) {
        if (b == 0) {
            return Result.failure(new ArithmeticException("Деление на ноль невозможно"));
        }
        return Result.success(a / b);
    }

    public static void main(String[] args) {
        System.out.println("1. Проверка divide(10, 2)");
        Result<Integer> successResult = divide(10, 2);
        System.out.println("Результат операции: " + successResult);
        System.out.println("isSuccess(): " + successResult.isSuccess());
        System.out.println("getValue(): " + successResult.getValue());
        System.out.println("getOrDefault(100): " + successResult.getOrDefault(100));

        System.out.println("\n2. Проверка divide(10, 0)");
        Result<Integer> failureResult = divide(10, 0);
        System.out.println("Результат операции: " + failureResult);
        System.out.println("isSuccess(): " + failureResult.isSuccess());
        System.out.println("getError(): " + failureResult.getError());
        System.out.println("getOrDefault(100): " + failureResult.getOrDefault(100));

        System.out.println("\n3. Проверка цепочки map (Успех)");
        Result<Integer> chainSuccess = divide(10, 2).map(res -> "Результат: " + res).map(String::length);
        System.out.println("Итог цепочки map для успеха: " + chainSuccess);

        System.out.println("\n4. Проверка цепочки map (Ошибка)");
        Result<Integer> chainFailure = divide(10, 0).map(res -> "Результат: " + res).map(String::length);
        System.out.println("Итог цепочки map для ошибки: " + chainFailure);
    }
}
```

<details open>
    <summary>result</summary>
    <br>
    <img src="img_17.png"/>  
</details>

<br>
<br>

---

### <a id="title14">Контрольные вопросы</a>

1. В чём разница между нестатическим внутренним классом и статическим вложенным классом?

* Нестатический имеет доступ ко всем полям внешнего класса, статический только к статическим.

2. Почему для создания экземпляра нестатического внутреннего класса снаружи нужен экземпляр внешнего класса?

* Нестатический внутренний класс неявно хранит ссылку на экземпляр внешнего класса, который его создал.

* Дает прямой доступ к приватным полям и методам этого экземпляра (без родителя не может существовать).

3. Что такое стирание типов (type erasure)? Приведите пример того, что нельзя сделать из-за этого.

* Параметры типа существуют только во время компиляции. В байт-коде заменяются на Object (в случае <T>), а если extends по какому-то классу, например, Number, то и замена на него.

Что нельзя делать:

```
public class Container<T>{
T obj = new T();// НЕЛЬЗЯ создать экземпляр T
T[] array = new T[10];// НЕЛЬЗЯ создавать массивы типа Т
if (obj instanceof T){}// НЕЛЬЗЯ использовать instance с T
Class<T> c = T.class// НЕЛЬЗЯ полуить класс Т
}
```

Что можно делать:

```
public class Container<T>{
    T value; // МОЖНО объявлять переменные типа Т
    List<T> list = new ArrayList<>(); // МОЖНО создавать коллекции
}
```

Демонстрация стирания типов во время исполнения:

```
class TypeErasureDemo<T> {
    public void check(Object obj) {
        // if (obj instanceof T) {} — Ошибка: нельзя использовать instanceof с параметром типа
        System.out.println("Тип T стёрт до Object на этапе исполнения");
    }
}

// Использование:
TypeErasureDemo<String> ted = new TypeErasureDemo<>();
ted.check("Test");

```

4. Объясните принцип PECS. Когда использовать ? extends T и когда ? super T?

* PECS = wildcard, когда не знаем какого типа передаём структуры данных в дженериках. Такое правило, чтобы не возникало ошибок.

* extends T = для чтения.

* super T = для записи.

Копирование коллекции:

```
public static <T> void copy(List<? super T> dest, List<? extends T> src) {
    for (T item : src) {   // src — producer, читаем из него
        dest.add(item);    // dest — consumer, пишем в него
    }
}
```

5. Чем отличается throws от throw?

* throw = выбрасывает исключение. throws = обрабатывает случай, когда исключение может появиться (checked).

6. В каких случаях нужно создавать собственные checked исключения, а в каких — unchecked?

* Классического использования исключений мало. Иногда для конкретной бизнес-задачи, нужно создавать свои исключения.

7. Что произойдёт, если исключение возникнет внутри блока finally?

* Полное перекрытие предыдущего исключение, которое могло возникнуть в блоках.

8. Какой интерфейс должен реализовывать класс для использования в try-with-resources?

* Класс ресурса должен реализовывать интерфейс AutoCloseable (наследник Closeable).

9. Для чего используется цепочка исключений (exception chaining)?

* Польза в сохранении оригинальное исключение при выбрасывании нового.

* Не терять информацию о первой ошибки/причина возникновения при перемещении между уровнями кода.

10. Можно ли создать массив обобщённого типа (new T[10])? Объясните почему.

* Нет, из-за механизма стирания типов (Type Erasure) в Java.

* При работе программы информация о типе T исчезает. JVM не знает: массив - тип - размер в байтах, который нужно выделить в памяти.

* Массивы в Java должны жестко знать свой тип во время выполнения, а дженерики существуют только на этапе компиляции.