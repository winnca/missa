package practice_3.task_2_3;

public class StringProcessor {
    static int countVowels(String text){
        int count = 0;
        String res = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toUpperCase();
        String[] el = res.split("");
        for (int i=0; i<el.length; i++){
            if(el[i].equals("А") || el[i].equals("У") || el[i].equals("Е")
                    || el[i].equals("Ы") || el[i].equals("О") || el[i].equals("Э")
                    || el[i].equals("Я") || el[i].equals("И") || el[i].equals("Ю")
                    || el[i].equals("A") || el[i].equals("E") || el[i].equals("Y")
                    || el[i].equals("U") || el[i].equals("I") || el[i].equals("O")) count++;
        }
        return count;
    }
    static boolean isPalindrome(String text){
        String res = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toUpperCase();
        for (int i=0,j=res.length()-1; i<j;i++,j--){
            if (res.charAt(i) != res.charAt(j)) return false;
        }
        return true;
    }

    static String reverse(String text) {
        char[] array = text.toCharArray();
        for (int i=0,j=text.length()-1; i<j;i++,j--){
            char temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        return new String(array);
    }

    static String findLongestWord(String sentence){
        int count = 0, max = 0;
        for (int i=0;i<sentence.length();i++){
            if (sentence.charAt(i) != ' '){
                count++;
            } else{
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        String res = "";
        String[] arr = sentence.split(" ");
        for (int i=0;i<arr.length;i++){
            if(arr[i].length() == max){
                res = arr[i];
                break;
            }
        }
        return res;
    }
    public static void main(String[] args){
        String[] array = {"Привет, Java-разработчик!", "топот", "Madam", "hello", "А роза упала на лапу Азора", "The quick brown fox jumps over the lazy dog"};
        for (String el : array){
            System.out.println("подсчёт гласных букв (русских и английских) = " + countVowels(el));
            System.out.println("проверка на палиндром без учёта регистра и знаков препинания (используйте сравнение символов с двух концов строки) = " + isPalindrome(el));
            System.out.println("реверс строки без StringBuilder (с двумя указателями) = " + reverse(el));
            System.out.println("самое длинное слово в предложении = " + findLongestWord(el));
            System.out.println();
        }
    }
}
