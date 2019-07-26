package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DBConnector {

    private static final Logger logger = Logger.getLogger(DBConnector.class);
    private static final String dbUrl = "jdbc:mysql://localhost:3306/online_shop?serverTimezone=" +
            TimeZone.getDefault().getID();
    private static final String login = "root";
    private static final String password = "1111";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Wrong attemp to connnect DB", e);
        }
        return null;
    }
}
