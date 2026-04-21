// AUTHOR: Moyo Ibikunle
// DATE: 1/02/2026
// STUDENT ID: C00309427
// PURPOSE: This class gets and displays all ingredients stored in the
//          ingredients table. It reads the ingredient ID and name from
//          the database and returns them as a string.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadIngredients {

    // This method gets all ingredients from the ingredients table.
    // It reads each ingredient's ID and name, formats the results into
    // a string, and returns that string to be displayed in the GUI.
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