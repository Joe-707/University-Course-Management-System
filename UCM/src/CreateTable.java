import java.sql.Statement;
import java.sql.Connection;

public class CreateTable{
    public static void main(String[] args){
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        try{
            String query="create table course(courseId TEXT primary key default 'C' || LPAD(nextval('course_seq')::TEXT,3,'0'),coursename VARCHAR(150),credits NUMERIC(2,1),instructorid VARCHAR(4))";
            stmt=connect.createStatement();
            stmt.executeUpdate(query);
            System.out.println("NIGERUNDAYOO!!");


        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
