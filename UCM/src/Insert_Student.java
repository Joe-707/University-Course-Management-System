import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Insert_Student extends Person {
    private int age=0;
    public Insert_Student(String name, String email, int birthYear, int birthMonth, int birthDay) {
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
            };
            String query="insert into profile(name,birthYear,birthMonth,birthDay,age,email) values (?,?,?,?,?,?)";
            pstmt=connect.prepareStatement(query);
            pstmt.setString(1,std_name);
            pstmt.setInt(2,std_birthYear);
            pstmt.setInt(3,std_birthMonth);
            pstmt.setInt(4,std_birthDay);
            pstmt.setInt(5,std.calculateAge());
            pstmt.setString(6,std_email);

            pstmt.executeUpdate();

            System.out.println("Profile table updated");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
