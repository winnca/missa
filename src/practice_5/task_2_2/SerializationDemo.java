package practice_5.task_2_2;

import java.io.*;
import java.util.*;

public class SerializationDemo {

    // Класс должен реализовывать Serializable для сериализации
    static class StudentRecord implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private int grade;
        private String faculty;
        private transient String password; // transient — не сериализуется

        public StudentRecord(String name, int grade, String faculty, String password) {
            this.name = name;
            this.grade = grade;
            this.faculty = faculty;
            this.password = password;
        }

        // Геттеры
        public String getName() { return name; }
        public int getGrade() { return grade; }
        public String getFaculty() { return faculty; }
        public String getPassword() { return password; } // null после десериализации

        @Override
        public String toString() {
            return String.format("StudentRecord{name='%s', grade=%d, faculty='%s', password=%s}",
                    name, grade, faculty, password);
        }
    }

    // Сохранить список объектов в файл
    public static void saveStudents(String filename, List<StudentRecord> students) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        }
    }

    // Загрузить список объектов из файла
    @SuppressWarnings("unchecked")
    public static List<StudentRecord> loadStudents(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<StudentRecord>) ois.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<StudentRecord> students = new ArrayList<>();
        students.add(new StudentRecord("Анна", 95, "ИТ", "secret123"));
        students.add(new StudentRecord("Борис", 78, "Математика", "pass456"));

        System.out.println("До сериализации:");
        students.forEach(System.out::println);

        String filename = "students.ser";
        saveStudents(filename, students);
        System.out.println("\nСохранено в " + filename);

        List<StudentRecord> loaded = loadStudents(filename);
        System.out.println("\nПосле десериализации:");
        loaded.forEach(System.out::println);
        // Обратите внимание: password == null (transient поле!)

        new File(filename).delete();
    }
}

