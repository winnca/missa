package practice_2.task_3_2;

public class GradeJournal {

    public static void main(String[] args) {
        // 1. Массив имён
        String[] names = {"Алиса", "Борис", "Вера", "Глеб"};

        // 2. Зубчатый массив с оценками
        int[][] grades = {
                {5, 4, 5, 5, 3},        // Алиса
                {3, 3, 4},              // Борис
                {5, 5, 5, 5, 5, 4},     // Вера
                {4, 3, 4, 5}            // Глеб
        };

        System.out.println("=== Журнал оценок ===");

        // Переменные для поиска лучшего студента
        double maxAverage = -1;
        String bestStudent = "";

        // 6. Вывод информации по каждому студенту
        for (int i = 0; i < names.length; i++) {
            int count = grades[i].length;
            double avg = average(grades[i]);
            int minGrade = min(grades[i]);
            int maxGrade = max(grades[i]);

            // Форматированный вывод
            System.out.printf("%-7s | Оценок: %d | Средний: %.2f | Мин: %d | Макс: %d%n",
                    names[i], count, avg, minGrade, maxGrade);

            // 7. Поиск лучшего студента
            if (avg > maxAverage) {
                maxAverage = avg;
                bestStudent = names[i];
            }
        }

        // Вывод лучшего студента
        System.out.printf("%nЛучший студент: %s (средний балл: %.2f)%n",
                bestStudent, maxAverage);
    }

    // 3. Средний балл
    public static double average(int[] grades) {
        if (grades.length == 0) return 0.0;

        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    // 4. Максимальная оценка
    public static int max(int[] grades) {
        int max = grades[0];
        for (int grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }

    // 5. Минимальная оценка
    public static int min(int[] grades) {
        int min = grades[0];
        for (int grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }
}
