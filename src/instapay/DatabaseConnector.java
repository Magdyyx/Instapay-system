package instapay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection;

    private DatabaseConnector() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:sqlserver://DESKTOP-5G3H0IA:1433;databaseName=instapay;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
                String user = "admin";
                String password = "12345";
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to establish a database connection.");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
