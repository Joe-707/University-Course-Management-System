import java.sql.Statement;
import java.sql.Connection;

public class CreateTable{
    public static void main(String[] args){
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        try{
            String query="create table ExamMarks(stdId TEXT primary key,courseid VARCHAR(4),grade NUMERIC(4,2),band VARCHAR(2))";
            stmt=connect.createStatement();
            stmt.executeUpdate(query);
            System.out.println("NIGERUNDAYOO!!");


        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
