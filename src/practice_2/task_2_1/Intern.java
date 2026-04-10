package practice_2.task_2_1;

public class Intern extends Employee{
    public Intern(String name, double balance) {
        super(name, balance);
    }

    @Override
    double calculateBonus() {
        return 10000;
    }
}
