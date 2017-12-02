import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Starter {
    public static void main(String[] args) {

        CourseSection cs = new CourseSection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = "";

        cs.start();
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
