package practice_4.task_5_2;

import java.util.function.Function;

public class Result<T> {
    private final T value;
    private final Exception error;
    private Result(T value){
        this.value=value;
        this.error=null;
    }
    private Result(Exception error){
        this.value=null;
        this.error=error;
    }
    public static <T> Result<T> success(T value){ // фабричный метод для успешного результата
        return new Result<>(value);
    }
    public static <T> Result<T> failure(Exception error){ // фабричный метод для ошибки
        return new Result<>(error);
    }
    public boolean isSuccess(){ // проверка на успешность операции
        return error == null;
    }
    public T getValue(){ // получение значения
        return value;
    }
    public Exception getError(){ // получение ошибки
        return error;
    }
    public T getOrDefault(T defaultValue){ // при успехе получаем значение, при ... дефолтное
        return isSuccess() ? value:defaultValue;
    }
    public <R> Result<R> map(Function<T, R> mapper){
        if (!isSuccess()){
            return Result.failure(this.error);
        }
        try {
            return Result.success(mapper.apply(this.value));
        } catch (Exception e){
            return Result.failure(e);
        }
    }
    @Override
    public String toString(){
        if (isSuccess()){
            return "Result.success={value=" + value + "}";
        } else{
            return "Result.failure={error=" + error.getClass().getSimpleName() + "}";
        }
    }
}

class Test {
    public static Result<Integer> divide(int a, int b) {
        if (b == 0) {
            return Result.failure(new ArithmeticException("Деление на ноль невозможно"));
        }
        return Result.success(a / b);
    }

    public static void main(String[] args) {
        System.out.println("1. Проверка divide(10, 2)");
        Result<Integer> successResult = divide(10, 2);
        System.out.println("Результат операции: " + successResult);
        System.out.println("isSuccess(): " + successResult.isSuccess());
        System.out.println("getValue(): " + successResult.getValue());
        System.out.println("getOrDefault(100): " + successResult.getOrDefault(100));

        System.out.println("\n2. Проверка divide(10, 0)");
        Result<Integer> failureResult = divide(10, 0);
        System.out.println("Результат операции: " + failureResult);
        System.out.println("isSuccess(): " + failureResult.isSuccess());
        System.out.println("getError(): " + failureResult.getError());
        System.out.println("getOrDefault(100): " + failureResult.getOrDefault(100));

        System.out.println("\n3. Проверка цепочки map (Успех)");
        Result<Integer> chainSuccess = divide(10, 2).map(res -> "Результат: " + res).map(String::length);
        System.out.println("Итог цепочки map для успеха: " + chainSuccess);

        System.out.println("\n4. Проверка цепочки map (Ошибка)");
        Result<Integer> chainFailure = divide(10, 0).map(res -> "Результат: " + res).map(String::length);
        System.out.println("Итог цепочки map для ошибки: " + chainFailure);
    }
}