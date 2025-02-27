import java.sql.Statement;
import java.sql.Connection;

public class CreateTable{
    public static void main(String[] args){
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        try{
            String query="create table student(Id VARCHAR(4) primary key,unitId VARCHAR(4),instructorName VARCHAR(100),instructorEmail VARCHAR(100) ,instructorTelephone VARCHAR(4)) ";
            stmt=connect.createStatement();
            stmt.executeUpdate(query);
            System.out.println("NIGERUNDAYOO!!");


        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
