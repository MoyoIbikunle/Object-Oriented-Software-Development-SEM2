import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadIngredients {

    public String getAllIngredients() throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        String output = "";

        String sql = "SELECT ingredient_id, name FROM ingredients";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql);

            resultSet = pstat.executeQuery();

            //while theres still another row form the result of the sql
            while (resultSet.next()) {
                output = output +
                //column names
                        "ID: " + resultSet.getInt("ingredient_id") +
                        " | Name: " + resultSet.getString("name") + "\n";
            }

            if (output.equals("")) {
                output = "No ingredients found.";
            }

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

        return output;
    }
}