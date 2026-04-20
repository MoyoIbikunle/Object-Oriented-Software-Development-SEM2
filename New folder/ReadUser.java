import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadUser {

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