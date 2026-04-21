// AUTHOR: Moyo Ibikunle
// DATE: 12/04/2026
// STUDENT ID: C00309427
// PURPOSE: This class gets and displays all nutrition records stored
//          in the nutrition table. It reads the recipe ID, fat, protein,
//          and calorie values from the database and returns them as a
//           string.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadNutrition {

    public String getAllNutrition() throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        String output = "";

        String sql = "SELECT recipe_id, fat, protein, calories FROM nutrition";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql);

            resultSet = pstat.executeQuery();

            while (resultSet.next()) {
                output = output +
                        "Recipe ID: " + resultSet.getInt("recipe_id") +
                        " | Fat: " + resultSet.getDouble("fat") +
                        " | Protein: " + resultSet.getDouble("protein") +
                        " | Calories: " + resultSet.getDouble("calories") + "\n";
            }

            //if there was no row and nothing was put into output
            if (output.equals("")) {
                output = "No nutrition records found.";
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