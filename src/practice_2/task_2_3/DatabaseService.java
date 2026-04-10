package practice_2.task_2_3;

public class DatabaseService implements Loggable{

    @Override
    public String getComponentName() {
        return getClass().getSimpleName();
    }

    void connect(String url){
        log("Connect: " + url);
        System.out.println("Connect set");
    }
}
