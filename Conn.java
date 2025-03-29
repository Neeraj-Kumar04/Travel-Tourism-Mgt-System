package travel.management.system;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            // ✅ Load the correct MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Correct JDBC URL (added timezone setting)
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tms?useSSL=false&serverTimezone=UTC", "root", "Neerajcoder@123");

            s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace(); // ✅ Use e.printStackTrace() instead of System.out.println()
        }
    }
}  
