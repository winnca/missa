package lecture.seven.student.dto;

import jakarta.validation.constraints.*;

public record StudentRequest(
        @NotBlank(message = "Имя обязательно")
        @Size(min = 2, max = 100, message = "Имя 2–100 символов")
        String name,
        @NotBlank(message = "Фамилия обязательна")
        @Size(min = 2, max = 100)
        String surname
) {}

