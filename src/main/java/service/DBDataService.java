package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBDataService {
    private final static String DATABASE_URL = "jdbc:sqlite:C:\\Users\\Willi3\\IdeaProjects\\Nachi1\\src\\main\\resources\\database.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return conn;
        }
    }
}
