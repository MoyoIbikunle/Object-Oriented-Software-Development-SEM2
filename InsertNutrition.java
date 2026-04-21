// AUTHOR: Moyo Ibikunle
// DATE: 3/04/2026
//Student ID: C00309427
// PURPOSE: This class inserts nutrition information into the nutrition table.
//          It stores the fat, protein, and calorie values linked to a
//          specific recipe, then retrieves and returns the generated
//          nutrition ID from the database.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertNutrition {

      // This method inserts a nutrition record for a specific recipe.
    // It stores the recipe ID along with fat, protein, and calorie values.
    // After insertion, it retrieves and returns the auto-generated
    // nutrition ID from the database.
    public int insertNutrition(int recipeId, double fat, double protein, double calories) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int nutritionId = -1;

        String sql = "INSERT INTO nutrition (recipe_id, fat, protein, calories) VALUES (?, ?, ?, ?)";

        try {
            connection = DBConnection.getConnection();

            //preparing the sql statement and requesting the generated key
            pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setInt(1, recipeId);
            pstat.setDouble(2, fat);
            pstat.setDouble(3, protein);
            pstat.setDouble(4, calories);

            pstat.executeUpdate();

            rs = pstat.getGeneratedKeys();

            //gets result of first column id and puts it into id
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