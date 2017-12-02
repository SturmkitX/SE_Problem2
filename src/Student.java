import java.util.Map;
import java.util.TreeMap;

public class Student {

    private Map<Course, Boolean> schedule;
    private String name;

    public Student() {
        this.name = "Random student";
        this.schedule = new TreeMap<Course, Boolean>();
    }

    public Student(String name) {
        this.name = name;
        this.schedule = new TreeMap<Course, Boolean>();
    }

    public void addToSchedule(Course c) {
        schedule.put(c, false);
    }

    public boolean hasPassedCourse(Course c) {
        return schedule.get(c);
    }
}
