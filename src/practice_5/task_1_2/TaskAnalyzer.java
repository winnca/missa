package practice_5.task_1_2;

import java.util.*;

public class TaskAnalyzer {
    private static final String REGEX = "[\\s.,!?;:\"'()\\-]+";
    public static Set<String> getUniqueWords(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Return пустой TreeSet");
            return new TreeSet<>();
        }
        Set<String> set = new TreeSet<>();
        String[] arrays = text.toLowerCase().split(REGEX);
        for (String s : arrays) {
            if (!s.isEmpty()) set.add(s);
        }
        return set;
    }

    public static Set<String> commonWords(String text1, String text2) {
        Set<String> set1 = getUniqueWords(text1);
        Set<String> set2 = getUniqueWords(text2);
        set1.retainAll(set2);
        return set1;
    }

    public static Set<String> uniqueToFirst(String text1, String text2) {
        Set<String> set1 = getUniqueWords(text1);
        Set<String> set2 = getUniqueWords(text2);
        set1.removeAll(set2);
        return set1;
    }
    public static Map<String, Integer> wordFrequency(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Return пустой Map");
            return new HashMap<>();
        }
        Map<String, Integer> map = new HashMap<>();
        String[] arrays = text.toLowerCase().split(REGEX);
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].isEmpty()) continue;
            if (map.containsKey(arrays[i])) continue;
            int count = 1;
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[i].equals(arrays[j])) count++;
            }
            map.put(arrays[i], count);
        }
        return map;
    }
    public static List<Map.Entry<String, Integer>> topWords(String text, int n) {
        Map<String, Integer> frequencyMap = wordFrequency(text);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        for (int i = 0; i < n && i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }
}

class Main{
    public static void main(String[] args){
        String text1 = "Сегодня по плану сделать домашку 4 и 5 по СТП, выложить на gitflic для преподавателя. Также заняться подготовкой к зачёту по ММММ, потому что это пирамида Мавроди";
        String text2 = "На самом деле в университетах есть негласное правило отчислять парней с очного и очно-заочного обучения. Делается для того, чтобы набирать парней в армию";

        System.out.println("Уникальные слова текста 1");
        System.out.println(TaskAnalyzer.getUniqueWords(text1));

        System.out.println("\nОбщие слова");
        System.out.println(TaskAnalyzer.commonWords(text1, text2) + "\n");

        System.out.println("\nСлова только в тексте 1");
        System.out.println(TaskAnalyzer.uniqueToFirst(text1, text2) + "\n");

        System.out.println("\nТоп-5 частых слов текста 1");
        System.out.println(TaskAnalyzer.topWords(text1, 2));
    }
}
