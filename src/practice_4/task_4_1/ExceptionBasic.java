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