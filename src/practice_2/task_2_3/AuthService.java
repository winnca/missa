package practice_2.task_2_3;

public class AuthService implements Loggable{
    @Override
    public String getComponentName() {
        return getClass().getSimpleName();
    }
    void login(String username, boolean success){
        if (!success) {
            logError("entrance username: " + username + " - denied");
        } else{
            log("entrance username: " + username + " - permitted");
        }
    }
}
