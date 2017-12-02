public class Course implements Comparable<Course> {

    private int minimum;
    private int maximum;
    private String name;

    public Course(String name) {
        this.name = name;
        this.minimum = 5;
        this.maximum = 20;
    }

    public Course(String name, int minimum, int maximum) {
        this.name = name;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public String getName() {
        return name;
    }

    public void getPrerequisities() {
        // TODO
    }

    public int compareTo(Course o) {
        return this.getName().compareTo(o.getName());
    }
}
