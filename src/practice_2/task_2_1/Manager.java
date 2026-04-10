package practice_2.task_2_1;

public class Manager extends Employee{
    int teamSize;

    public Manager(String name, double baseSalary, int teamSize) {
        super(name, baseSalary);
        this.teamSize = teamSize;
    }

    @Override
    double calculateBonus() {
        return baseSalary * 0.15 + teamSize * 5000;
    }
}
