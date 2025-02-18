import java.sql.Statement;
import java.sql.Connection;

public class Student{
    private int age;
    public static void main(String[] args){
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        try{
            String query="create table profile(stdId SERIAL primary key,name varchar(255),birthYear int,birthMonth int,birthDay int,age int,email varchar(255)) ";
            stmt=connect.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Profile table created");


        } catch (Exception e){
            e.printStackTrace();
        }



    }

}
