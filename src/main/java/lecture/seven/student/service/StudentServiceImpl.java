package lecture.seven.student.service;

import jakarta.persistence.EntityNotFoundException;
import lecture.seven.student.dto.StudentRequest;
import lecture.seven.student.model.Student;
import lecture.seven.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

//    @Override
//    public Student findById(Long id) { return repository.findById(id).orElse(null);}
    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) { return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));}

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    // Демонстрация: метод, бросающий исключение посередине
    //@Transactional
    @Override
    public void saveTwoOneBroken(Student good, Student bad) {
        repository.save(good);
        if (bad.getName() == null || bad.getName().isBlank()) {
            throw new IllegalStateException("Имя не может быть пустым");
        }
        repository.save(bad);
    }

    @Override
    public List<Student> findByNameContainingIgnoreCase(String namePart) {
        return repository.findByNameContainingIgnoreCase(namePart);
    }

    @Override
    public List<Student> findBySurnameContainingIgnoreCase(String surnamePart) {
        return repository.findBySurnameContainingIgnoreCase(surnamePart);
    }

    @Override
    public long countByName(String name) {
        return repository.countByName(name);
    }
    @Override
    public Student update(Long id, StudentRequest request) {
        Student student = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        student.setName(request.name());
        student.setSurname(request.surname());
        return repository.save(student);
    }
    @Override
    public Student create(StudentRequest request) {
        Student student = new Student(request.name(), request.surname());
        return repository.save(student);
    }
}