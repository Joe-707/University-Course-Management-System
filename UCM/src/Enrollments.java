import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Enrollments extends Course{
    private String enrollmentId;
    private String studentId;
    private int grade;

    public Enrollments(String courseName, String courseCode, String enrollmentId, String studentId, int grade,int credits,String instructorId) {
        super(courseName, courseCode,credits,instructorId);
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.grade = grade;
    }

    public void enrollStudent(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        System.out.println("Enrolling student: " + studentId+"to course: " + courseCode);
    }
    public void addCourse(String instructorId, String courseCode) {
        this.instructorId = instructorId;
        this.courseCode = courseCode;
        System.out.println("Adding course: " + courseCode+"with instructor: " + instructorId);
    }
    public void displayEnrollment() {
        System.out.println("Enrollment ID: " + enrollmentId);
        System.out.println("Student ID: " + studentId);
        System.out.println("Course ID: " + courseCode);
        System.out.println("Course Name: " + courseName);
        System.out.println("Grade: " + grade);
        System.out.println("Instructor ID: " + instructorId);

    }

    public static void main(String[] args) {
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        PreparedStatement pstmt=null;

    }
}

