// AUTHOR: Moyo Ibikunle
// DATE: 1/03/2026
// Student ID: C00309427
// PURPOSE: This class inserts a recipe ingredient record into the recipe_ingredients table.
//          It links a recipe to an ingredient with a quantity and unit, then gets
//          and returns the generated recipe ingredient ID from the database.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRecipeIngredient {

    public int insertRecipeIngredient(int recipeId, int ingredientId, double quantity, String unit) throws SQLException {
        int recipeIngredientId = -1;

        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

         // SQL statement to insert a new row into the recipe_ingredients table
        String sql = "INSERT INTO recipe_ingredients(recipe_id, ingredient_id, quantity, unit) VALUES (?, ?, ?, ?)";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setInt(1, recipeId);
            pstat.setInt(2, ingredientId);
            pstat.setDouble(3, quantity);
            pstat.setString(4, unit);

            //execute insert
            pstat.executeUpdate();

            //retrieve ID
            rs = pstat.getGeneratedKeys();
            if (rs.next()) {
                recipeIngredientId = rs.getInt(1);
            }

            System.out.println("RecipeIngredient successfully added with ID = " + recipeIngredientId);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstat != null) pstat.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return recipeIngredientId;
    }
}