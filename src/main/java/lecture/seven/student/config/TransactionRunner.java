package lecture.seven.student.config;

import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TransactionRunner implements CommandLineRunner {
    private final StudentService studentService;

    public TransactionRunner(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) {
        Student good = new Student("Valid", "Student");
        Student bad = new Student("", "Invalid"); // Пустое имя для вызова ошибки

        try {
            System.out.println("Запуск saveTwoOneBroken");
            studentService.saveTwoOneBroken(good, bad);
        } catch (IllegalStateException e) {
            System.out.println("Исключение перехвачено: " + e.getMessage());
        }
    }
}
