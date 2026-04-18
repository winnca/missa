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
