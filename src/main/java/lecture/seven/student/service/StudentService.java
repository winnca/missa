// StudentService.java
package lecture.seven.student.service;

import lecture.seven.student.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student save(Student student);
    Student findById(Long id);
    void deleteById(Long id);
}