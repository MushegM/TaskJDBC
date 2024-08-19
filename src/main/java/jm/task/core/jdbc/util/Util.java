package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER ="com.mysql.jdbc.Driver";
    private static final String DB_URL ="jdbc:mysql://localhost:3306/";
    private static final String DB_USER ="root";
    private static final String DB_PASSWORD ="Veter2793";


    public static Connection getConnection() {
        Connection connection=null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException("Failed to connect to the database", e);
        }
        return connection;
    }
}
