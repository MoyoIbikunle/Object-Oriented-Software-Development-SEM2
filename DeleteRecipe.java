import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRecipe {

    public int deleteRecipe(int recipeId) throws SQLException {
        Connection connection = null;
        PreparedStatement deleteNutritionSt = null;
        PreparedStatement deleteRecipeIngredientsSt = null;
        PreparedStatement deleteRecipeSt = null;

        int rowsAffected = 0;

        String deleteNutritionSql = "DELETE FROM nutrition WHERE recipe_id = ?";
        String deleteRecipeIngredientsSql = "DELETE FROM recipe_ingredients WHERE recipe_id = ?";
        String deleteRecipeSql = "DELETE FROM recipes WHERE recipe_id = ?";
        
        try {
            connection = DBConnection.getConnection();

            deleteNutritionSt = connection.prepareStatement(deleteNutritionSql);
            deleteNutritionSt.setInt(1, recipeId);
            deleteNutritionSt.executeUpdate();

            deleteRecipeIngredientsSt = connection.prepareStatement(deleteRecipeIngredientsSql);
            deleteRecipeIngredientsSt.setInt(1, recipeId);
            deleteRecipeIngredientsSt.executeUpdate();

            deleteRecipeSt = connection.prepareStatement(deleteRecipeSql);
            deleteRecipeSt.setInt(1, recipeId);
            rowsAffected = deleteRecipeSt.executeUpdate();

            System.out.println(rowsAffected + " recipe deleted successfully.");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        } finally {
            try {
                if (deleteNutritionSt != null) deleteNutritionSt.close();
                if (deleteRecipeIngredientsSt != null) deleteRecipeIngredientsSt.close();
                if (deleteRecipeSt != null) deleteRecipeSt.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return rowsAffected;
    }
}