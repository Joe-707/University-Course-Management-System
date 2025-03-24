public class Course {
    protected String courseName;
    protected String courseCode;
    protected int credits;
    protected String instructorId;

    public Course(String courseName, String courseCode, int credits, String instructorId) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.instructorId = instructor;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseCode() {
        return courseCode;
    }

}
