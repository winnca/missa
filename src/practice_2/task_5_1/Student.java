package practice_2.task_5_1;

public record Student(String name, int id) {
    public Student{
        if (name == null || name.isEmpty() ){
            throw new IllegalArgumentException("Нарушено правило валидации строк");
        }
        if (id <= 0){
            throw new IllegalArgumentException("Нарушено правило валидации идентификаторов");
        }
    }
}
