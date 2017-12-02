public class Registration {
    private Student student;
    private CourseSection section;

    public Registration(Student s, CourseSection c) {
        this.student = s;
        this.section = c;
    }

    public Student getStudent() {
        return student;
    }

    public CourseSection getSection() {
        return section;
    }
}
