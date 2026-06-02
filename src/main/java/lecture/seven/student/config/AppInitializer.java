package lecture.seven.student.config;

import jakarta.annotation.PostConstruct;
import lecture.seven.student.model.Student;
import lecture.seven.student.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {

    private final StudentRepository repository;

    public AppInitializer(StudentRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            repository.save(new Student("Ali", "Hasan"));
            repository.save(new Student("Fatima", "Kassem"));
            repository.save(new Student("Ivan", "Petrov"));
            repository.save(new Student("Ekaterina", "Sidorova"));
            System.out.println("Созданы тестовые студенты");
        }
    }
}