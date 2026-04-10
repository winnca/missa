package practice_2.task_5_1;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class GradeSystem {
    public static void main(String[] args) {
        Student student1 = new Student("Alex", 1);
        Student student2 = new Student("Bon", 2);
        Student student3 = new Student("Caroline", 3);
        Student student4 = new Student("Dean", 4);
        Student student5 = new Student("Egg", 5);
        Student student6 = new Student("Fionna", 6);
        Student student7 = new Student("German", 7);

        Grade grade1 = Grade.fromScore(97);
        Grade grade2 = Grade.fromScore(72);
        Grade grade3 = Grade.fromScore(56);
        Grade grade4 = Grade.fromScore(57);
        Grade grade5 = Grade.fromScore(87);
        Grade grade6 = Grade.fromScore(65);
        Grade grade7 = Grade.fromScore(67);

        EnumMap<Grade, List<Student>> map = new EnumMap<>(Grade.class);

        for (Grade g : Grade.values()){
            map.put(g, new ArrayList<>());
        }

        map.get(grade1).add(student1); // список студентов получивших соответствующую оценку
        map.get(grade2).add(student2);
        map.get(grade3).add(student3);
        map.get(grade4).add(student4);
        map.get(grade5).add(student5);
        map.get(grade6).add(student6);
        map.get(grade7).add(student7);

        EnumSet<Grade> passingGrades = EnumSet.noneOf(Grade.class); // пустой множество для указанного типа перечисления
        for (Grade g : Grade.values()){
            if (g.isPassing()){
                passingGrades.add(g);
            }
        }

        double totalGpa = 0.0;
        int totalStudents = 0;

        for (Grade grade : Grade.values()) {
            List<Student> studentsInGrade = map.get(grade);
            int count = studentsInGrade.size();

            System.out.println(grade + " (" + grade.getDescription() + "): " + count + " student/students");

            if (count > 0) {
                System.out.print("  Students: ");
                for (Student s : studentsInGrade) {
                    System.out.print(s.name() + " ");
                }
                System.out.println();
            }

            totalGpa += grade.getGpaValue() * count;
            totalStudents += count;
        }

        System.out.println("PassingGrades: " + passingGrades);
        System.out.print("Students : ");
        for (Grade g : passingGrades) {
            for (Student s : map.get(g)) {
                System.out.print(s.name() + " ");
            }
        }
        System.out.println();
        double averageGpa = totalStudents > 0 ? totalGpa / totalStudents : 0.0;
        System.out.printf("%nAvg GPA all students: %.2f%n", averageGpa);
    }
}
