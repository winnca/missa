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
        //list2 = Arrays.copyOf(list1, 5);
        copyElements(list1, list2);
        System.out.println(list2);
    }
    public static <T> void copyElements(List<? extends T> source, List<? super T> destination){
        for (T el : source){
            destination.add(el);
        }
    }
}