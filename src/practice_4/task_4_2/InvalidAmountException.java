package practice_4.task_4_2;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(double amount) {
        super("Сумма должна быть положительной, получено: " + amount);
    }
}
