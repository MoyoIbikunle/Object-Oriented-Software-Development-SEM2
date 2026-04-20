import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRecipeIngredient {

    public int deleteRecipeIngredient(int recipe_id, int ingredient_id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;

        int i = 0;

        String sql = "DELETE FROM recipe_ingredients WHERE recipe_id = ? AND ingredient_id = ?";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql);

            pstat.setInt(1, recipe_id);
            pstat.setInt(2, ingredient_id);

            i = pstat.executeUpdate();

            System.out.println(i + " record successfully deleted from the table.");

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