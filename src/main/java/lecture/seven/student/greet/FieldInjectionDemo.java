package lecture.seven.student.greet;

import org.springframework.beans.factory.annotation.Autowired;

public class FieldInjectionDemo {
    @Autowired
    private GreetingService service;
}
