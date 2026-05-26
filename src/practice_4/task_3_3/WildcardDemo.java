package practice_4.task_3_3;

import java.util.*;

public class WildcardDemo {
    public static void printAll(List<?> list){ // выводит список всех элементов, принимая любой тип
        for (int i=0;i<list.size();i++){
            System.out.print(list.get(i));
        }
    }
    public static double sumNumbers(List<? extends Number> list){ //
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
        //list2 = list1;
        /*
        В Java массивы являются ковариантными (например, Integer[] можно присвоить в Number[]), но дженерики (Generics) — инвариантны.
        Это значит, что List<Integer> и List<Number> — это два абсолютно разных, не связанных друг с другом типа, у которых общий предок только List<?>.
         */
//        list2 = Arrays.copyOf(list1, 5);
        copyElements(list1, list2);
        System.out.println(list2);
    }
    /*
    Правило PESC

    Источник — это коллекция, которая отдает (производит) данные. Мы из нее только читаем.
    Приемник — это коллекция, которая принимает (потребляет) данные. Мы в нее только записываем.

    Используем extends, когда цель — безопасное чтение из коллекции. Нам важен общий верхний тип, к которому можно привести всё содержимое.
    Используем super, когда цель — безопасная запись в коллекцию. Нам важно, чтобы коллекция могла принять наш конкретный тип.

    Пример: Animal <- Dog <- Cat.

    1. Есть метод, принимающий список собак, заставляя их всей лаять и пересчитывать их (источник).
    public static void processDogs(List<? extends Dog> source) {
        for (Dog dog : source) {
            dog.bark(); // БЕЗОПАСНО: Кем бы ни были элементы, они как минимум Dog!
    }
    //source.add(new Dog()); // ОШИБКА компиляции! Почему?

    Чтение разрешено:
                        Метод может принять List<Dog> или List<Corgi>.
    Если передали List<Corgi>, то каждый Корги — это Собака, поэтому вызвать метод bark() абсолютно безопасно.

    Запись запрещена:
                        Если бы компилятор разрешил строку source.add(new Dog()), а нам на вход передали чистый список List<Corgi>,
    то мы бы только что подселили обычную Собаку в вольер к Корги. Это сломало бы логику программы, где ожидаются только Корги.


    2.Теперь мы пишем метод, который берет конкретную собаку сlass Corgi и добавляет ее в список-приемник (приёмник).
    public static void addDogToKennel(List<? super Dog> destination) {
        destination.add(new Dog());   // БЕЗОПАСНО: Обычную собаку можно добавить
        destination.add(new Corgi()); // БЕЗОПАСНО: Корги тоже является собакой!

        // Dog dog = destination.get(0); // ОШИБКА компиляции! Почему?
    }

    Запись разрешена:
                        Метод может принять List<Dog>, List<Animal> или List<Object>.
    В любой из этих трех списков можно абсолютно легально положить объект типа Dog или его наследника Corgi (ведь Корги — это тоже Собака и тоже Животное).

    Чтение запрещено:
                        Если нам на вход передали List<Animal>, то там внутри уже могут лежать кошки, птицы и крокодилы.
    Попытка прочитать оттуда элемент и сразу привести его к типу Dog (строка Dog dog = destination.get(0)) приведет к катастрофе в рантайме, если там окажется кошка.
    Единственное, что гарантированно можно оттуда прочитать — это Object.

     */
    public static <T> void copyElements(List<? extends T> source, List<? super T> destination){
        for (T el : source){
            destination.add(el);
        }
    }
}