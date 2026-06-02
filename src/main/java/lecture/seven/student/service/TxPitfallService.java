package lecture.seven.student.service;

import lecture.seven.student.model.Student;
import lecture.seven.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TxPitfallService {

    private final StudentRepository repository;
    public TxPitfallService(StudentRepository r) { this.repository = r; }

    public void outerWithoutAnnotation(Student good, Student bad) {
        // ВНИМАНИЕ: this.innerWithTransaction(...) не создаст транзакцию,
        // потому что вызов идёт через this, минуя Spring-прокси
        this.innerWithTransaction(good, bad);
    }

    @Transactional
    public void innerWithTransaction(Student good, Student bad) {
        repository.save(good);
        if (bad.getName().isBlank()) {
            throw new IllegalStateException("Bad student");
        }
        repository.save(bad);
    }
}