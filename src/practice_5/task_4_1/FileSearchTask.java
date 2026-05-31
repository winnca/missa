package practice_5.task_4_1;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class FileSearchTask implements Callable<List<String>> {
    private String filename;
    private String keyword;

    public FileSearchTask(String filename, String keyword) {
        this.filename = filename;
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public List<String> call() {
        List<String> result = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            result.add(filename + ": файл не найден");
            return result;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(keyword)) {
                    result.add(filename + ":" + currentLine + ": " + line);
                }
                currentLine++;
            }
        } catch (IOException e) {
            result.add(filename + ": ошибка чтения файла");
        }

        return result;
    }
}

class ParallelSearch {
    public static void searchInFiles(List<String> files, String keyword) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<List<String>>> futures = new ArrayList<>();
        for (String f : files) {
            FileSearchTask task = new FileSearchTask(f, keyword);
            futures.add(executor.submit(task));
        }
        for (Future<List<String>> future : futures) {
            try {
                List<String> lines = future.get();
                for (String s : lines) {
                    System.out.println(s);
                }
            } catch (Exception e) {
                System.out.println("Ошибка потока: " + e.getMessage());
            }
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        String file1 = "Vinni.txt";
        String file2 = "Rud.txt";
        String file3 = "Push.txt";
        String file4 = "Unknown.txt";

        try {
            PrintWriter p1 = new PrintWriter(new FileWriter(file1));
            p1.println("Winni");
            p1.println("Rud");
            p1.println("Push");
            p1.close();

            PrintWriter p2 = new PrintWriter(new FileWriter(file2));
            p2.println("Java");
            p2.println("Python");
            p2.println("JS");
            p2.close();

            PrintWriter p3 = new PrintWriter(new FileWriter(file3));
            p3.println("Backend.");
            p3.println("numpy+pandas");
            p3.println("frontend");
            p3.close();

            List<String> allFiles = new ArrayList<>();
            allFiles.add(file1);
            allFiles.add(file2);
            allFiles.add(file3);
            allFiles.add(file4);

            searchInFiles(allFiles, "java");

        } catch (IOException e) {
            System.out.println("Ошибка при создании файлов: " + e.getMessage());
        } finally {
            new File(file1).delete();
            new File(file2).delete();
            new File(file3).delete();
            System.out.println("\nТестовые файлы удалены.");
        }
    }
}