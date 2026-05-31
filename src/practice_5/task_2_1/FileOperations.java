package practice_5.task_2_1;

import java.io.*;
import java.util.*;

public class FileOperations {
    public static void writeLines(String filename, List<String> lines) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }
    public static List<String> readLines(String filename) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }
    public static void printFileStats(String filename) throws IOException {
        int lines = 0;
        int words = 0;
        int chars = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                chars += line.length();
                String cleanLine = line.trim();
                if (!cleanLine.isEmpty()) {
                    String[] wordArray = cleanLine.split("\\s+");
                    words += wordArray.length;
                }
            }
        }
        System.out.println("Файл: " + filename);
        System.out.println("Строк: " + lines);
        System.out.println("Слов: " + words);
        System.out.println("Символов: " + chars);
    }
    public static List<String> grep(String filename, String keyword) throws IOException {
        List<String> result = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) return result;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(keyword)) {
                    result.add(line);
                }
            }
        }
        return result;
    }
    public static void copyFile(String source, String destination) throws IOException {
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[128];
            int count;

            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        String file = "rome.txt";
        String copyOfFile = "rome_copy.txt";
        List<String> text = new ArrayList<>();
        text.add("Сегодня гуляем с собакой.");
        text.add("Занимаемся спортом вместе с собакой");
        text.add("Учимся плавать.");
        text.add("Не пропускаем занятия по СТП.");
        text.add("Финал сессии уже рядом.");
        try {
            FileOperations.writeLines(file, text);
            System.out.println("Файл создан и записан.\n");

            System.out.println("Чтение файла");
            List<String> linesFromFile = FileOperations.readLines(file);
            for (String s : linesFromFile) {
                System.out.println(s);
            }
            System.out.println();

            System.out.println("Статистика");
            FileOperations.printFileStats(file);
            System.out.println();

            System.out.println("Результаты поиска по слову");
            List<String> foundLines = FileOperations.grep(file, "собакой");
            for (String s : foundLines) {
                System.out.println(s);
            }

            System.out.println();
            FileOperations.copyFile(file, copyOfFile);
            System.out.println("Копирование файла: " + copyOfFile);
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}

