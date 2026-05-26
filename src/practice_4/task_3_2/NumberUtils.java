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
        System.out.println("Итоговый заполненный List<Number>: " + numberList);
    }
}
