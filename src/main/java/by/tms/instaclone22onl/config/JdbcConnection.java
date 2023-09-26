package by.tms.instaclone22onl.config;

/*
    @author Ilya Moiseenko on 20.09.23
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {
        Connection connection;

        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
