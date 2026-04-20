import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRecipeIngredient {

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