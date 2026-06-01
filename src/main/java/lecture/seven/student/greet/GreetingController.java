// GreetingController.java — REST-контроллер
package lecture.seven.student.greet;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/greet")
public class GreetingController {

    private final GreetingService greetingService;

    // Конструкторное внедрение — Spring сам передаст бин EnglishGreetingService
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/{name}")
    public String greet(@PathVariable String name) {
        return greetingService.greet(name);
    }
}

