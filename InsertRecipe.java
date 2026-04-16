import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRecipe {

    //throws SQL Exception: warning label
    public int insertRecipe(String recipeName, String instructions, int defaultServings, int prepTime, int cookTime) throws SQLException  {
        int recipeId = -1;

        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        //run sql statement and reruen any auto-generated keys
        String sql = "INSERT INTO recipes(recipe_name, instructions, default_servings, prep_time, cook_time) VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setString(1, recipeName);
            pstat.setString(2, instructions);
            pstat.setInt(3, defaultServings);
            pstat.setInt(4, prepTime);
            pstat.setInt(5, cookTime);

            pstat.executeUpdate();

            //gets automatically generated ids
            rs = pstat.getGeneratedKeys();

            if (rs.next()) {
                //get int from first column, ids form first column
                recipeId = rs.getInt(1);
            }

            System.out.println("Recipe successfully added with recipe_id = " + recipeId);

            
        } 
        //catches error thrown 
        catch (SQLException sqlException) {
            sqlException.printStackTrace(); // prints error
            throw sqlException; //throws error again to whatever called the method insertRecipe()
        } finally {
            try {
                //to check if we actually assigned a value to these objects and then closes them
                //cant close whats still null
                if (rs != null) rs.close();
                if (pstat != null) pstat.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return recipeId;
    }
}