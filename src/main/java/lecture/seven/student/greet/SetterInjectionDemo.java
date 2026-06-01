package lecture.seven.student.greet;

import org.springframework.beans.factory.annotation.Autowired;

public class SetterInjectionDemo {
    private GreetingService service;

    @Autowired
    public void setService(GreetingService service) {
        this.service = service;
    }
}
