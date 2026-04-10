package practice_2.task_2_1;

public class EmployeeBonus {
    public static void main(String[] args) {
        Employee[] team = {
                new Manager("Olga", 120000, 5),
                new Developer("Andrey", 95000, "Java"),
                new Developer("Maria", 100000, "Python"),
                new Intern("Intern Petr", 30000)
        };

        System.out.println("=== calculate bonus ===");
        double totalBudget = 0;
        for (Employee e : team) {
            System.out.println(e);
            totalBudget += e.totalCompensation();
        }
        System.out.printf("\ntotal budget: %.0f rub.%n", totalBudget);
    }
}
