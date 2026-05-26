package practice_4.task_4_2;

public class BankException extends Exception{
    private String operationId;

    public BankException(String message, String operationId) {
        super(message);
        this.operationId = operationId;
    }

    public String getOperationId() { return operationId; }
}
