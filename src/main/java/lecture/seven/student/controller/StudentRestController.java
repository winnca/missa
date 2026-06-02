package lecture.seven.student.controller;

import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student s = studentService.findById(id);
        return s != null ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        if (studentService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        student.setId(id);
        return ResponseEntity.ok(studentService.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (studentService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

