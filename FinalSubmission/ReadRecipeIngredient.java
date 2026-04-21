// AUTHOR: Moyo Ibikunle
// DATE: 13/03/2026
// STUDENT ID: C00309427
// PURPOSE: This class retrieves all ingredients linked to a specific recipe.
//          It joins the recipes, recipe_ingredients, and ingredients tables
//          to display the recipe name, ingredient name, quantity, and unit
//          for the selected recipe.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadRecipeIngredient {

    // This method retrieves all ingredients associated with a specific recipe.
    // It returns the recipe name, ingredient name, quantity, and unit
    // as a formatted string.
    public String readRecipeIngredient(int recipeId) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        String output = "";

        String sql = "SELECT recipes.recipe_name, ingredients.name, " +
                     "recipe_ingredients.quantity, recipe_ingredients.unit " +
                     "FROM recipes " +
                     "INNER JOIN recipe_ingredients ON recipes.recipe_id = recipe_ingredients.recipe_id " +
                     "INNER JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id " +
                     "WHERE recipes.recipe_id = ?";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql);
            pstat.setInt(1, recipeId);

            resultSet = pstat.executeQuery();

            //loop through each row and add ingredient details to the output
            while (resultSet.next()) {
                output = output +
                        resultSet.getString("recipe_name") + " | " +
                        resultSet.getString("name") + " | " +
                        resultSet.getDouble("quantity") + " | " +
                        resultSet.getString("unit") + "\n";
            }

            // If no ingredients were found for the recipe, return a message instead
            if (output.equals("")) {
                output = "No ingredients found for selected recipe.";
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