// AUTHOR: Moyo Ibikunle
// DATE: 10/04/2026
// STUDENT ID: C00309427
// PURPOSE: This class checks whether the username and password entered
//          by the user match an admin record stored in the database.
//          It is used to validate admin login details for the system.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadUser {

    // This method checks whether the entered username and password
    // match a record in the admin table.
    // It returns true if a matching admin account is found,
    // otherwise it returns false.
    public boolean isValidAdmin(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try {
            connection = DBConnection.getConnection();
            pstat = connection.prepareStatement(sql);
            pstat.setString(1, username);
            pstat.setString(2, password);
            resultSet = pstat.executeQuery();

            // if a row is returned, credentials are valid
            return resultSet.next();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstat != null) pstat.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}