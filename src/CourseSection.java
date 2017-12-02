import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CourseSection extends Thread {

    private Course course;
    private List<Registration> registrationList;
    private boolean open, closedOrCanceled;
    private Queue<String> events;
    private String lastEvent;

    public CourseSection() {
        this.course = new Course("Geometry");
        this.registrationList = new ArrayList<Registration>();
        this.open = false;
        this.closedOrCanceled = false;  // state = Planned
        this.events = new ConcurrentLinkedQueue<String>();
        this.lastEvent = "";
    }

    private Registration createRegistration(Student s, CourseSection c) {
        return new Registration(s, c);
    }

    public Course getCourse() {
        return course;
    }

    public void requestToRegister(Student aStudent) throws IncompatibleOperationException {
        boolean cond1 = (open == true) && (registrationList.size() < course.getMinimum());
        boolean cond2 = (open == true) && (registrationList.size() >= course.getMinimum());

        if(!(cond1 || cond2)) {
            throw new IncompatibleOperationException();
        }

        Registration reg = createRegistration(aStudent, this);
        addToRegistrationList(reg);


        if(registrationList.size() >= course.getMaximum()) {
            addEvent("close_registration");    // automatically send a close event
        }

    }

    public void addToRegistrationList(Registration reg) {
        registrationList.add(reg);
        reg.getStudent().addToSchedule(reg.getSection().getCourse());
    }

    private void openRegistration() throws IncompatibleOperationException {
        boolean cond = (open == false) && (closedOrCanceled == false);
        if(!cond) {
            throw new IncompatibleOperationException();
        }

        // change state to Open
        open = true;
    }

    private void closeRegistration() throws IncompatibleOperationException {
        boolean cond1 = (open == true) && (registrationList.size() < course.getMinimum());
        boolean cond2 = (open == true) && (registrationList.size() >= course.getMinimum());

        if(!(cond1 || cond2)) {
            throw new IncompatibleOperationException();
        }

        closedOrCanceled = true;
        open = false;
        if(cond1) {
            registrationList.clear();
        }
    }

    private void cancel() throws IncompatibleOperationException {
        boolean cond1 = (open == true) && (registrationList.size() < course.getMinimum());
        boolean cond2 = (open == true) && (registrationList.size() >= course.getMinimum());

        if(!(cond1 || cond2)) {
            throw new IncompatibleOperationException();
        }

        closedOrCanceled = true;
        open = false;
        registrationList.clear();
    }

    private void handleEvent(String event) {
        lastEvent = event;
        try {
            switch(event) {
                case "open_registration" : openRegistration(); break;
                case "close_registration" : closeRegistration(); break;
                case "cancel" : cancel(); break;
                case "request_to_register" : requestToRegister(new Student()); break;
                default : System.out.println("Another message delivered!");
            }
        } catch(IncompatibleOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEvent(String e) {
        events.add(e);
    }

    private String getCurrentState() {
        if(open == false && closedOrCanceled == false) {
            return "PLANNED";
        }

        if(open == true && registrationList.size() < course.getMinimum()) {
            return "OpenNotEnoughStudents";
        }

        if(open == true && registrationList.size() >= course.getMinimum()) {
            return "OpenEnoughStudents";
        }

        if(closedOrCanceled == true && registrationList.size() == 0) {
            return "Canceled";
        }

        if(closedOrCanceled == true && registrationList.size() > 0) {
            return "Closed";
        }

        return "Other";
    }

    public void run() {
        // it should be able to run even in a multithreaded environment
        System.out.println("Initial status:");
        System.out.println(this);

        while(true) {
            String ev = events.poll();
            if(ev != null) {
                handleEvent(ev);
                System.out.println(this);

                if(getCurrentState().equals("Closed") || getCurrentState().equals("Canceled")) {
                    break;
                }
            }
        }
    }

    public String toString() {
        String stat = "";
        stat += "Course section status:\n";
        stat += ("Number of students enrolled: " + registrationList.size() + "\n");
        stat += ("Current state: " + getCurrentState() + "\n");
        stat += ("Last event processed: " + lastEvent + "\n");
        stat += "\n";

        return stat;
    }
}
