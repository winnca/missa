package practice_2.task_4_1;

public class TextAnalyzer {

    private String text;

    TextAnalyzer(String text) {
        this.text = text;
    }
    int wordCount(){
        int count = 0;
        String[] arr = text.split(" ");
        return arr.length;
    }

    String longestWord(){
        int count = 0, max = 0;
        for (int i=0;i<text.length();i++){
            if (text.charAt(i) != ' '){
                count++;
            } else{
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        String res = "";
        String[] arr = text.split(" ");
        for (int i=0;i<arr.length;i++){
            if(arr[i].length() == max){
                res = arr[i];
                break;
            }
        }
        return res;
    }

    String reverseWords(){
        String res = "";
        int indexStart = text.length()-1, count = 0, counter = 0;
        for (int i =0;i<text.length();i++){
            if (text.charAt(i) == ' '){
                count++;
            }
        }
        for (int i = text.length()-1;i>=0;i--){
            if (text.charAt(i) == ' '){
                res = res + text.substring(i+1, indexStart+1) + " ";
                indexStart = i-1;
                ++counter;
            }
            if (count == counter && i == 0){
                res = res + text.substring(i, indexStart+1);
                break;
            }
        }
        return res;
    }

    int countOccurrences(String target){
        String res = text.toUpperCase();
        String targetRes = target.toUpperCase();
        int count=0, result = 0, j = 0;
        for (int i=0;i<res.length();i++){
            if (j<targetRes.length() && res.charAt(i) == targetRes.charAt(j)){
                count++;
                j++;

            } else{
                if (count == targetRes.length()){
                    result++;
                }
                count=0;
                j=0;
                if (res.charAt(i) == targetRes.charAt(0)){
                    count = 1;
                    j = 1;
                }
            }
        }
        if (count == targetRes.length()){
            result++;
        }
        return result;
    }

    boolean isPalindrome(){
        String res = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toUpperCase();
        String[] result = res.split("");
        String palindrome = "";
        for (int i=0;i<result.length;i++){
            palindrome = palindrome + result[i];
        }
        int count = 0;
        for (int i=0, j=palindrome.length()-1;i<=palindrome.length()-1 && j>=0;i++, j--){
            if (palindrome.charAt(i) == palindrome.charAt(j)){
                count++;
            }
        }
        if (count==palindrome.length()){
            return true;
        }
        return false;
    }
    // Напишите весь класс самостоятельно

    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer("Java Programming is Fun and Java is Powerful");

        System.out.println("Текст: " + ta);
        System.out.println("Слов: " + ta.wordCount());
        System.out.println("Самое длинное слово: " + ta.longestWord());
        System.out.println("Слова наоборот: " + ta.reverseWords());
        System.out.println("'Java' встречается: " + ta.countOccurrences("java") + " раз(а)");
        System.out.println("'is' встречается: " + ta.countOccurrences("is") + " раз(а)");
        System.out.println("Палиндром: " + ta.isPalindrome());

        System.out.println();

        TextAnalyzer palindrome = new TextAnalyzer("А роза упала на лапу Азора");
        System.out.println("Текст: " + palindrome);
        System.out.println("Палиндром: " + palindrome.isPalindrome());
    }

    @Override
    public String toString() {
        return text;
    }
}