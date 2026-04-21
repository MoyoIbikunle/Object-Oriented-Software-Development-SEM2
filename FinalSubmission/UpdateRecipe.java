// AUTHOR: Moyo Ibikunle
// DATE: 01/02/2026
// STUDENT ID: C00309427
// PURPOSE: This class updates the main details of an existing recipe
//          in the recipes table. It allows the recipe name,
//          instructions, servings, preparation time, and cooking
//          time to be changed for a selected recipe ID.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRecipe {

      // This method updates the details of an existing recipe
    // in the recipes table using the recipe ID.
    // It returns the number of rows affected by the update.
    public int updateRecipe(int recipeId, String newRecipeName, String newInstructions,
                            int newServings, int prepTime, int cookTime) throws SQLException {

        Connection connection = null;
        PreparedStatement pstat = null;
        int rowsAffected = 0;

        String sql = "UPDATE recipes SET recipe_name = ?, instructions = ?, default_servings = ?, prep_time = ?, cook_time = ? WHERE recipe_id = ?";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql);

            pstat.setString(1, newRecipeName);
            pstat.setString(2, newInstructions);
            pstat.setInt(3, newServings);
            pstat.setInt(4, prepTime);
            pstat.setInt(5, cookTime);
            pstat.setInt(6, recipeId);

            rowsAffected = pstat.executeUpdate();
            System.out.println(rowsAffected + " record(s) successfully updated.");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        } finally {
            try {
                if (pstat != null) pstat.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return rowsAffected;
    }
}