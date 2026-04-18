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
