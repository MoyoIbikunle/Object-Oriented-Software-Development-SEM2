// AUTHOR: Moyo Ibikunle
// DATE: 01/02/2026
// STUDENT ID: C00309427
// PURPOSE: This class updates an existing record in the recipe_ingredients
//          table. It allows the quantity and unit of a specific ingredient
//          linked to a specific recipe to be changed.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRecipeIngredient {

        // This method updates the quantity and unit of an ingredient
    // linked to a specific recipe in the recipe_ingredients table.
    // It returns the number of rows affected by the update.
    public int updateRecipeIngredient(int recipe_id, int ingredient_id, double newQuantity, String newUnit) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;

        int i = 0;

        String sql = "UPDATE recipe_ingredients " +
                     "SET quantity = ?, unit = ? " +
                     "WHERE recipe_id = ? AND ingredient_id = ?";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql);

            pstat.setDouble(1, newQuantity);
            pstat.setString(2, newUnit);
            pstat.setInt(3, recipe_id);
            pstat.setInt(4, ingredient_id);

            i = pstat.executeUpdate();

            System.out.println(i + " record successfully updated in the table.");

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

        return i;
    }
}