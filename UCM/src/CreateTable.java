import java.sql.Statement;
import java.sql.Connection;

public class CreateTable{
    public static void main(String[] args){
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        try{
            String query="create table course(courseId VARCHAR(4) primary key,course_name varchar(255),credits DECIMAL(1,2),coordinator varchar(100)) ";
            stmt=connect.createStatement();
            stmt.executeUpdate(query);
            System.out.println("NIGERENDAYOO!!");


        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
