package practice_2.task_2_1;

public abstract class Employee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name=name;
        this.baseSalary=baseSalary;
    }

    abstract double calculateBonus();

    double totalCompensation(){
        return baseSalary + calculateBonus();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return name + " | " +
                "baseSalary: " + baseSalary +
                " | " + "bonus: " + calculateBonus() + " | " + "total: " + totalCompensation();
    }
}
