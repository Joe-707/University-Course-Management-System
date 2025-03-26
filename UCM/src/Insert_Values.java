import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Insert_Values extends Person{
    private int age=0;
    public Insert_Values(String name, String email, int birthYear, int birthMonth, int birthDay) {
        super(name, email, birthYear, birthMonth, birthDay);
        age=0;
    }


    public static void main(String[] args) {


        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        PreparedStatement pstmt=null;




        try{
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter student name: ");
            String std_name=sc.nextLine();
            System.out.println("Enter student email: ");
            String std_email=sc.nextLine();
            System.out.println("Enter course id: ");
            String course_id=sc.nextLine();
            System.out.println("Enter student birth year: ");
            int std_birthYear=sc.nextInt();
            System.out.println("Enter student birth month: ");
            int std_birthMonth=sc.nextInt();
            System.out.println("Enter student birth day: ");
            int std_birthDay=sc.nextInt();
            Person std=new Person(std_name,std_email,std_birthYear,std_birthMonth,std_birthDay) {
                @Override
                public int calculateAge() {
                    return super.calculateAge();
                }
                @Override
                public void display() {
                    super.display();
                }
                public String getDOB() {
                    return super.getDOB(birthYear,birthMonth,birthDay);
                }
            };
            String query="insert into student(stdname,stdemail,courseid,dob,age,grade) values (?,?,?,?,?,?)";
            pstmt=connect.prepareStatement(query);
            pstmt.setString(1,std_name);
            pstmt.setString(2,std_email);
            pstmt.setString(3,course_id);
            pstmt.setInt(5,std.calculateAge());


            pstmt.executeUpdate();

            System.out.println("Profile table updated");
            std.display();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
