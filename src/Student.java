import java.util.Map;
import java.util.TreeMap;

public class Student {

    private Map<Course, Boolean> schedule;

    public Student() {
        this.schedule = new TreeMap<Course, Boolean>();
    }

    public void addToSchedule(Course c) {
        schedule.put(c, false);
    }

    public boolean hasPassedCourse(Course c) {
        return schedule.get(c);
    }
}
