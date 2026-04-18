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
