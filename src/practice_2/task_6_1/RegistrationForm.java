package practice_2.task_6_1;

public class RegistrationForm {
    @NotEmpty(message = "Name must")
    String name;

    @NotEmpty
    String email;

    @Range(min = 18, max = 120, message = "Year: 18 - 120")
    int age;

    public RegistrationForm(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
