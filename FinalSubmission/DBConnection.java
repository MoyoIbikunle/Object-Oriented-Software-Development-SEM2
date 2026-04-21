// AUTHOR: Moyo Ibikunle
//Student ID: C00309427
// DATE: 1/04/2026
// PURPOSE: This class is responsible for creating and returning a connection
//          to the MySQL database for the Recipe Management System.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost/recipe_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Adunni1509$$";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
}

