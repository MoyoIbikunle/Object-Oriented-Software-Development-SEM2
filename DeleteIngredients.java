import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteIngredients {

    public int deleteIngredient(int ingredientId) throws SQLException {
        Connection connection = null;
        PreparedStatement checkSt = null;
        PreparedStatement deleteSt = null;
        ResultSet resultSet = null;

        int rowsAffected = 0;

        String checkSql = "SELECT COUNT(*) FROM recipe_ingredients WHERE ingredient_id = ?";
        String deleteSql = "DELETE FROM ingredients WHERE ingredient_id = ?";

        try {
            connection = DBConnection.getConnection();

            // Check if ingredient is used in recipe_ingredients, if its used in a recipe then it shouldnt be deleted
            checkSt = connection.prepareStatement(checkSql);
            checkSt.setInt(1, ingredientId);

            resultSet = checkSt.executeQuery();
            resultSet.next();

            //taking the first column from result of sql which is count
            int usageCount = resultSet.getInt(1);

            if (usageCount > 0) {
                System.out.println("Cannot delete ingredient. It is used in one or more recipes.");
            } else {
                deleteSt = connection.prepareStatement(deleteSql);
                deleteSt.setInt(1, ingredientId);

                rowsAffected = deleteSt.executeUpdate();
                System.out.println(rowsAffected + " ingredient successfully deleted.");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (checkSt != null) checkSt.close();
                if (deleteSt != null) deleteSt.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return rowsAffected;
    }
}