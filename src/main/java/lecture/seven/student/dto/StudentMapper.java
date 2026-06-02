package lecture.seven.student.dto;

import lecture.seven.student.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        Student s = new Student();
        s.setName(request.name());
        s.setSurname(request.surname());
        return s;
    }

    public StudentResponse toResponse(Student s) {
        return new StudentResponse(s.getId(), s.getName(), s.getSurname());
    }
}

