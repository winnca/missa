package lecture.seven.student.controller;

import jakarta.validation.Valid;
import lecture.seven.student.dto.StudentMapper;
import lecture.seven.student.dto.StudentRequest;
import lecture.seven.student.dto.StudentResponse;
import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
//
//@RestController
//@RequestMapping("/api/students")
//public class StudentRestController {
//
//    private final StudentService studentService;
//
//    public StudentRestController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @GetMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public List<Student> getAll() {
//        return studentService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Student> getById(@PathVariable Long id) {
//        Student s = studentService.findById(id);
//        return s != null ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
//    }
//
////    @PostMapping
////    public ResponseEntity<Student> create(@RequestBody Student student) {
////        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
////    }
////
////    @PutMapping("/{id}")
////    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
////        if (studentService.findById(id) == null) {
////            return ResponseEntity.notFound().build();
////        }
////        student.setId(id);
////        return ResponseEntity.ok(studentService.save(student));
////    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
//        return ResponseEntity.ok(studentService.update(id, request));
//    }
//    @PostMapping
//    public ResponseEntity<Student> create(@Valid @RequestBody StudentRequest request) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(request));
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (studentService.findById(id) == null) {
//            return ResponseEntity.notFound().build();
//        }
//        studentService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//    // Поиск по имени (содержит подстроку, игнорируя регистр)
//    @GetMapping("/search")
//    public List<Student> searchByName(@RequestParam String q) {
//        return studentService.findByNameContainingIgnoreCase(q);
//    }
//
//    // Поиск по фамилии
//    @GetMapping("/search/surname")
//    public List<Student> searchBySurname(@RequestParam String q) {
//        return studentService.findBySurnameContainingIgnoreCase(q);
//    }
//
//    // Подсчёт студентов с определённым именем
//    @GetMapping("/count")
//    public ResponseEntity<Map<String, Object>> countByName(@RequestParam String name) {
//        long count = studentService.countByName(name);
//        Map<String, Object> result = new HashMap<>();
//        result.put("name", name);
//        result.put("count", count);
//        return ResponseEntity.ok(result);
//    }
//}

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService service;
    private final StudentMapper mapper;

    public StudentRestController(StudentService service, StudentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return service.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        Student s = service.findById(id);
        return s != null
                ? ResponseEntity.ok(mapper.toResponse(s))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(
            @Valid @RequestBody StudentRequest request) {
        Student saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Student entity = mapper.toEntity(request);
        entity.setId(id);
        return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}