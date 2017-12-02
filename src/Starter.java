import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Starter {
    public static void main(String[] args) {

        CourseSection cs = new CourseSection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = "";

        cs.start();

        // for testing purposes, add 18 students, so we can test what happens in case the maximum
        // number of students is reached

        // without this event, the course section will continuously report:
        // Operation incompatible with current state!
        cs.addEvent("open_registration");
        for(int i=0; i<18; i++) {
            cs.addEvent("request_to_register");
        }
        try {
            while(true) {
                command = reader.readLine();
                if(command.equals("exit")) {
                    break;
                }
                cs.addEvent(command);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
