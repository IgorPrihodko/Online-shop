package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final Logger logger = Logger.getLogger(DBConnector.class);
    private static String dbUrl = "jdbc:mysql://localhost:3306/online_shop";
    private static String login = "root";
    private static String password = "1111";

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
