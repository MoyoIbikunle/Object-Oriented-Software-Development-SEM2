import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIngredient {

    public int insertIngredient(String name) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int ingredientId = -1;

        String sql = "INSERT INTO ingredients(name) VALUES (?)";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setString(1, name);
            pstat.executeUpdate();

            rs = pstat.getGeneratedKeys();

            if (rs.next()) {
                ingredientId = rs.getInt(1);
            }

            System.out.println("Ingredient successfully added with ingredient_id = " + ingredientId);

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

        return ingredientId;
    }
}