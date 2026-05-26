package practice_4.task_4_2;

public class InsufficientFundsException extends BankException{
    private double required;
    private double available;

    public InsufficientFundsException(double required, double available, String operationId) {
        super(String.format("Недостаточно средств. Требуется: %.2f, доступно: %.2f",
                required, available), operationId);
        this.required = required;
        this.available = available;
    }

    public double getRequired() { return required; }
    public double getAvailable() { return available; }
}
