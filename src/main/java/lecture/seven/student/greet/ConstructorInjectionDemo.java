package lecture.seven.student.greet;

public class ConstructorInjectionDemo {
    private final GreetingService service;

    public ConstructorInjectionDemo(GreetingService service) {
        this.service = service;
    }
}
