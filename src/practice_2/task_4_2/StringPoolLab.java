package practice_2.task_4_2;

public class StringPoolLab {
    public static void main(String[] args){
        String s1 = "Hello";
        /*
        Переменная s1 будет содержать ссылку на объект в памяти = в куче в String Pool, где будет храниться объект.
         */
            String s2 = "Hello";
        /*
        Переменная s2 будет содержать ссылку на уже созданный объект в памяти = в куче в String Pool, где будет храниться объект.
         */
            String s3 = new String("Hello");
        /*
        Переменная s3 создаст новый объект и не поместит его в String Pool, поместит в кучу.
         */
            String s4 = new String("Hello");
        /*
        Переменная s4 создаст новый объект и не поместит его в String Pool, поместит в кучу. s4 и s3 2 разных объекта в памяти. s1 и s2 указывают на одини тот же объект в памяти.
         */
            String s5 = s3.intern();
        /*
        Переменная s5 будет искать содержимое объекта s3, найдя вернёт ссылку для объекта, который лежит в String Pool (вместо создания нового объекта).
         */
            String s6 = "Hel" + "lo";
        /*
        Переменная s6 сделает конкатенацию 2 строк и положит в String Pool.
         */
        String half = "Hel";
        String s7 = half + "lo";
        /*
        Переменная s7 сделает конкатенацию строк и положит в кучу не в String Pool
         */
        System.out.println(s1 == s2); // сравнивает адреса в памяти, return true
        System.out.println(s1.equals(s2)); // сравнивает содержимое, return true
        System.out.println(s1 == s3); // сравнивает адреса в памяти, return false
        System.out.println(s1.equals(s3)); // сравнивает содержимое, return true
        System.out.println(s3 == s4); // сравнивает адреса в памяти, return false
        System.out.println(s3.equals(s4)); // сравнивает содержимое, return true
        System.out.println(s1 == s5); // сравнивает адреса в памяти, return true
        System.out.println(s1.equals(s5)); // сравнивает содержимое, return true
        System.out.println(s1 == s7); // сравнивает адреса в памяти, return false
        System.out.println(s1.equals(s7)); // сравнивает содержимое, return true
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("H");
        System.out.println(stringBuilder);
        stringBuilder.append("e");
        System.out.println(stringBuilder);
        stringBuilder.append("l");
        System.out.println(stringBuilder);
        stringBuilder.append("l");
        System.out.println(stringBuilder);
        stringBuilder.append("o");
        System.out.println(stringBuilder);
        //System.out.println(s1 == stringBuilder); // нельзя сравнивать
        System.out.println(s1.equals(stringBuilder)); // false
        String s8 = stringBuilder.toString();
        System.out.println(s8 == s1); // сравнивает адреса в памяти, return false
        System.out.println(s8.equals(s1)); // сравнивает содержимое, return true
    }
}
