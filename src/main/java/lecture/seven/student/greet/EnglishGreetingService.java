// EnglishGreetingService.java — реализация
package lecture.seven.student.greet;

import org.springframework.stereotype.Service;

@Service
public class EnglishGreetingService implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}