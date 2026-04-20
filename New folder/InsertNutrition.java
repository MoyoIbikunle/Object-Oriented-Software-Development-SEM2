import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertNutrition {

    public int insertNutrition(int recipeId, double fat, double protein, double calories) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int nutritionId = -1;

        String sql = "INSERT INTO nutrition (recipe_id, fat, protein, calories) VALUES (?, ?, ?, ?)";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setInt(1, recipeId);
            pstat.setDouble(2, fat);
            pstat.setDouble(3, protein);
            pstat.setDouble(4, calories);

            pstat.executeUpdate();

            rs = pstat.getGeneratedKeys();

            if (rs.next()) {
                nutritionId = rs.getInt(1);
            }

            System.out.println("Nutrition added with ID = " + nutritionId + " for recipe_id = " + recipeId);

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

        return nutritionId;
    }
}