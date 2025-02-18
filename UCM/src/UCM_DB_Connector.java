import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class UCM_DB_Connector {
    private final String url = "jdbc:postgresql://localhost:5432/ucm";
    private final String user = "postgres";
    private final String password = "Crossword25";
    Connection conn = null;

    public Connection getConnection() {
        try {
            conn =DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to Mamas!");
            } else {
                System.out.println("Failed to connect to Mamas");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return conn;
    }
    public static void main(String[] args) {
        UCM_DB_Connector connector = new UCM_DB_Connector();
        connector.getConnection();
    }

}
