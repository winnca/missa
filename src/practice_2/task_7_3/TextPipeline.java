package practice_2.task_7_3;

import java.util.*;
import java.util.function.Function;

public class TextPipeline {

    public static void main(String[] args) {

        Function<String, String> trim = new Function<>() {
            @Override
            public String apply(String s) {
                return s.trim();
            }
        };

        Function<String, String> lower = new Function<>() {
            @Override
            public String apply(String s) {
                return s.toLowerCase();
            }
        };

        Function<String, String> removeExtraSpaces = new Function<>() {
            @Override
            public String apply(String s) {
                return s.replaceAll("\\s+", " ");
            }
        };

        Function<String, String> capitalize = new Function<>() {
            @Override
            public String apply(String s) {
                if (s == null || s.isEmpty()) return s;
                return s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        };

        Function<String, String> normalize = trim
                .andThen(lower)
                .andThen(removeExtraSpaces)
                .andThen(capitalize);


        String[] testStrings = {
                "  ПРИВЕТ    МИР  ",
                "   JAVA    STREAM    API   ",
                "  ФУНКЦИОНАЛЬНОЕ   ПРОГРАММИРОВАНИЕ   это   круто  ",
                "   \t  HELLO   WORLD  \n  ",
                "   МНОГО    ПРОБЕЛОВ    МЕЖДУ    СЛОВАМИ   "
        };

        for (String s : testStrings) {
            System.out.println("Было:  " + s);
            System.out.println("Стало: " + normalize.apply(s));
            System.out.println();
        }

        // Локальный класс внутри main
        class WordCounter {

            private String text;
            private Map<String, Integer> cache; // чтобы не считать дважды

            public WordCounter(String text) {
                this.text = text;
                this.cache = null;
            }

            public Map<String, Integer> count() {
                // если уже считали - возвращаем готовое
                if (cache != null) {
                    return cache;
                }

                Map<String, Integer> map = new HashMap<>();

                if (text == null || text.trim().isEmpty()) {
                    cache = map;
                    return map;
                }

                // разбив по пробелам
                String[] words = text.split(" ");

                for (String w : words) {
                    if (w.isEmpty()) continue;
                    // если слово уже есть - плюсую, если нет - ставлю 1
                    map.put(w, map.getOrDefault(w, 0) + 1);
                }

                cache = map;
                return map;
            }

            public String mostFrequent() {
                Map<String, Integer> freq = count();

                if (freq.isEmpty()) {
                    return "Нет слов";
                }

                String topWord = null;
                int maxCount = 0;

                // тупо перебором поиск максимум
                for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                    if (entry.getValue() > maxCount) {
                        maxCount = entry.getValue();
                        topWord = entry.getKey();
                    }
                }

                return topWord;
            }
        }

        // Тестирую на нормализованной строке
        String longText = "  JAVA    java    Java    ПРОГРАММИРОВАНИЕ   программирование  " +
                "КОД    код    код    СТРИМ    Stream    stream    API   api  ";
        String normText = normalize.apply(longText);

        System.out.println("Текст после нормализации:");
        System.out.println(normText);
        System.out.println();

        WordCounter wc = new WordCounter(normText);

        System.out.println("Частотный словарь:");
        Map<String, Integer> freqs = wc.count();
        for (Map.Entry<String, Integer> e : freqs.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }

        System.out.println();
        System.out.println("Самое частое слово: " + wc.mostFrequent());
    }
}