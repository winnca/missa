// StudentService.java
package lecture.seven.student.service;

import lecture.seven.student.dto.StudentRequest;
import lecture.seven.student.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student save(Student student);
    Student findById(Long id);
    void deleteById(Long id);
    List<Student> findByNameContainingIgnoreCase(String namePart);
    List<Student> findBySurnameContainingIgnoreCase(String surnamePart);
    long countByName(String name);
    Student update(Long id, StudentRequest request);
    Student create(StudentRequest request);
    void saveTwoOneBroken(Student good, Student bad);
}