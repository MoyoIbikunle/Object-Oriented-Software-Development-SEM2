import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadRecipeIngredient {

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

            while (resultSet.next()) {
                output = output +
                        resultSet.getString("recipe_name") + " | " +
                        resultSet.getString("name") + " | " +
                        resultSet.getDouble("quantity") + " | " +
                        resultSet.getString("unit") + "\n";
            }

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