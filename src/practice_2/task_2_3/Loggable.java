package practice_2.task_2_3;

public interface Loggable {
    abstract String getComponentName(); // возвращает имя компонента
    static String getLogLevel(){
        return "INFO";
    }
    default void log(String message){
        System.out.println(formatTimestamp() + " [" + getComponentName() + "] " + message);
    }
    default void logError(String message){
        System.out.println(formatTimestamp()+ " [" + getComponentName() + "] " + "Error: " + message);
    }
    private String formatTimestamp(){
        return java.time.format.DateTimeFormatter.ofPattern("'['HH:mm:ss']'").format(java.time.LocalTime.now());
    }
}
