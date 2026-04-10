package practice_2.task_2_1;

public class Developer extends Employee{
    String language;

    public Developer(String name, double baseSalary, String language) {
        super(name, baseSalary);
        this.language = language;
    }

    @Override
    double calculateBonus() {
        return baseSalary * 0.12;
    }
}
