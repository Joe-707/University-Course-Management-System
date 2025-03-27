import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Enrollments extends Course implements Statistics{
    private String enrollmentId;
    private String studentId;
    private double grade;

    public Enrollments(String courseName, String courseCode, String enrollmentId, String studentId, double grade,int credits,String instructorId) {
        super(courseName, courseCode,credits,instructorId);
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.grade = grade;
    }
    public boolean checkGrade(double grade){
        this.grade = grade;
        if(grade>=60){
            System.out.println("Welcome to Mamas University");
            return true;
        }
        else{
            System.out.println("You have not met the required mark.See admin for further information");
            return false;
        }
    }
    public double average(double [] data,double grade){
        this.grade = grade;
        double sum = 0;
        for(int j=0;j<data.length;j++){
            data[j]=data[j]+grade;
            sum+=data[j];
        }
        return sum/data.length;
    }

//    public String gradeConverter(double grade){
//
//    }


    public void displayEnrollment() {
        System.out.println("Enrollment ID: " + enrollmentId);
        System.out.println("Student ID: " + studentId);
        System.out.println("Course ID: " + courseCode);
        System.out.println("Course Name: " + courseName);
        System.out.println("Grade: " + grade);
        System.out.println("Instructor ID: " + instructorId);

    }

    public static void main(String[] args) {
        try{
            Connection connect=null;
            Statement stmt=null;
            UCM_DB_Connector stdConn = new UCM_DB_Connector();
            connect=stdConn.getConnection();
            PreparedStatement pstmt=null;

            String query="insert into enrollments values()";
            pstmt=connect.prepareStatement(query);


        }
        catch(Exception e){
            e.printStackTrace();
        }




    }
}

