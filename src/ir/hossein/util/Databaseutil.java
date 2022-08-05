package ir.hossein.util;
import java.sql.DriverManager;
import java.sql.Connection;

public class Databaseutil {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "Hossein9090");

            if (conn == null){
                System.out.println("Failed to connect.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

