import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadRecipe {

    public String getAllRecipes() throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        String output = "";

        String sql = "SELECT recipe_id, recipe_name, default_servings, prep_time, cook_time FROM recipes";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql);

            resultSet = pstat.executeQuery();

            while (resultSet.next()) {
                output = output +
                        "ID: " + resultSet.getInt("recipe_id") +
                        " | Name: " + resultSet.getString("recipe_name") +
                        " | Servings: " + resultSet.getInt("default_servings") +
                        " | Prep Time: " + resultSet.getInt("prep_time") +
                        " | Cook Time: " + resultSet.getInt("cook_time") + "\n";
            }

            if (output.equals("")) {
                output = "No recipes found.";
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

    public String searchRecipes(String searchTerm) throws SQLException {
    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet resultSet = null;

    String output = "";

    String sql = "SELECT DISTINCT recipes.recipe_id, recipes.recipe_name, recipes.default_servings, recipes.prep_time, recipes.cook_time " +
                 "FROM recipes " +
                 "LEFT JOIN recipe_ingredients ON recipes.recipe_id = recipe_ingredients.recipe_id " +
                 "LEFT JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id " +
                 "WHERE recipes.recipe_name LIKE ? " +
                 "OR ingredients.name LIKE ?";

    try {
        connection = DBConnection.getConnection();
        pstat = connection.prepareStatement(sql);
        // same search term passed in twice, once for recipe name, once for ingredient name
        pstat.setString(1, "%" + searchTerm + "%");
        pstat.setString(2, "%" + searchTerm + "%");
        resultSet = pstat.executeQuery();

        while (resultSet.next()) {
            output = output +
                    "ID: " + resultSet.getInt("recipe_id") +
                    " | Name: " + resultSet.getString("recipe_name") +
                    " | Servings: " + resultSet.getInt("default_servings") +
                    " | Prep Time: " + resultSet.getInt("prep_time") +
                    " | Cook Time: " + resultSet.getInt("cook_time") + "\n";
        }

        if (output.equals("")) {
            output = "No recipes found matching: " + searchTerm;
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