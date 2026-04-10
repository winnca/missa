package practice_2.task_1_2.company.core;

public class EmployeeFixed {
    private String name;
    private int age;
    private double salary;
    private String password;

    public EmployeeFixed(String name, int age, double salary, String password) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getRole() {
        return "Employee";
    }

    public void promote(double raise) {
        this.salary += raise;
    }

    public void printSummary() {
        System.out.println(name + ", " + age + " years, " + salary + " rub.");
    }

    private boolean validatePassword(String input) {
        return password.equals(input);
    }

    public void authenticate(String input){
        validatePassword(input);
    }

}
