import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadRecipe {

    public String getAllRecipes() throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        String output = "";

        String sql = "SELECT recipe_id, recipe_name, default_servings, prep_time, cook_time FROM recipes";

        try {
            connection = DBConnection.getConnection();

            pstat = connection.prepareStatement(sql);

            resultSet = pstat.executeQuery();

            while (resultSet.next()) {
                output = output +
                        "ID: " + resultSet.getInt("recipe_id") +
                        " | Name: " + resultSet.getString("recipe_name") +
                        " | Servings: " + resultSet.getInt("default_servings") +
                        " | Prep Time: " + resultSet.getInt("prep_time") +
                        " | Cook Time: " + resultSet.getInt("cook_time") + "\n";
            }

            if (output.equals("")) {
                output = "No recipes found.";
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstat != null) pstat.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return output;
    }

    public String searchRecipes(String searchTerm) throws SQLException {
    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet resultSet = null;
    PreparedStatement ingredientStat = null;
    PreparedStatement nutritionStat = null;
    ResultSet ingredientSet = null;
    ResultSet nutritionSet = null;

    String output = "";

    //DISTINCT: helps to not have duplicate recipes in result, e.g. if pasta  was searched up
    //a recipe called pasta with both pasta as an ingredient would show up twice cus it contains pasta twice in its records
    //distinct helps avoid this
     String sql = "SELECT DISTINCT recipes.recipe_id, recipes.recipe_name, recipes.default_servings, recipes.prep_time, recipes.cook_time, recipes.instructions " +
                 "FROM recipes " +
                 "LEFT JOIN recipe_ingredients ON recipes.recipe_id = recipe_ingredients.recipe_id " +
                 "LEFT JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id " +
                 "WHERE recipes.recipe_name LIKE ? " +
                 "OR ingredients.name LIKE ?";

    String ingredientSql = "SELECT ingredients.name, recipe_ingredients.quantity, recipe_ingredients.unit " +
                           "FROM recipe_ingredients " +
                           "JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id " +
                           "WHERE recipe_ingredients.recipe_id = ?";

    String nutritionSql = "SELECT calories, protein, fat FROM nutrition WHERE recipe_id = ?";

    try {
        connection = DBConnection.getConnection();
        pstat = connection.prepareStatement(sql);
        pstat.setString(1, "%" + searchTerm + "%");
        pstat.setString(2, "%" + searchTerm + "%");
        resultSet = pstat.executeQuery();

        while (resultSet.next()) {
            int recipeId = resultSet.getInt("recipe_id");
            String recipeName = resultSet.getString("recipe_name");
            int servings = resultSet.getInt("default_servings");
            int prepTime = resultSet.getInt("prep_time");
            int cookTime = resultSet.getInt("cook_time");
            String instructions = resultSet.getString("instructions");

            // recipe header
            output += "========================\n";
            output += "ID: " + recipeId + "\n";
            output += "Recipe: " + recipeName + "\n";
            output += "Servings: " + servings + " | Prep: " + prepTime + " mins | Cook: " + cookTime + " mins\n";

            // instructions
            output += "\nINSTRUCTIONS:\n";
            output += instructions + "\n";

            // ingredients - second query using this recipe's ID
            output += "\nINGREDIENTS:\n";
            ingredientStat = connection.prepareStatement(ingredientSql);
            ingredientStat.setInt(1, recipeId);
            ingredientSet = ingredientStat.executeQuery();

            while (ingredientSet.next()) {
                String ingredientName = ingredientSet.getString("name");
                double quantity = ingredientSet.getDouble("quantity");
                String unit = ingredientSet.getString("unit");
                output += "- " + ingredientName + ": " + quantity + " " + unit + "\n";
            }

            ingredientSet.close();
            ingredientStat.close();

            // nutrition - third query using this recipe's ID
            output += "\nNUTRITION:\n";
            nutritionStat = connection.prepareStatement(nutritionSql);
            nutritionStat.setInt(1, recipeId);
            nutritionSet = nutritionStat.executeQuery();

            if (nutritionSet.next()) {
                output += "Calories: " + nutritionSet.getDouble("calories") + "\n";
                output += "Protein: " + nutritionSet.getDouble("protein") + "g\n";
                output += "Fat: " + nutritionSet.getDouble("fat") + "g\n";
            }

            nutritionSet.close();
            nutritionStat.close();

            output += "\n";
        }

        if (output.equals("")) {
            output = "No recipes found matching: " + searchTerm;
        }

    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        throw sqlException;
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (pstat != null) pstat.close();
            if (connection != null) connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    return output;
}
public String adjustServings(int recipeId, int desiredServings) throws SQLException {
    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet resultSet = null;

    String output = "";

    try {
        connection = DBConnection.getConnection();

        // Step 1 - get recipe name and default servings
        String recipeSql = "SELECT recipe_name, default_servings, prep_time, cook_time FROM recipes WHERE recipe_id = ?";
        pstat = connection.prepareStatement(recipeSql);
        pstat.setInt(1, recipeId);
        resultSet = pstat.executeQuery();

        if (!resultSet.next()) {
            return "No recipe found with ID: " + recipeId;
        }

        String recipeName = resultSet.getString("recipe_name");
        int defaultServings = resultSet.getInt("default_servings");
        int prepTime = resultSet.getInt("prep_time");
        int cookTime = resultSet.getInt("cook_time");

        resultSet.close();
        pstat.close();

        // scaling ratio
        double ratio = (double) desiredServings / defaultServings;

        // adjusted times
        int adjustedPrep = (int) Math.round(prepTime * (1 + (ratio - 1) * 0.6));
        int adjustedCook = (int) Math.round(cookTime * (1 + (ratio - 1) * 0.2));

        output += "Recipe: " + recipeName + "\n";
        output += "Adjusted for " + desiredServings + " servings (default: " + defaultServings + ")\n\n";
        output += "INGREDIENTS:\n";

        // Step 2 - get ingredients with smart scaling
        String ingredientSql = "SELECT ingredients.name, recipe_ingredients.quantity, recipe_ingredients.unit " +
                               "FROM recipe_ingredients " +
                               "JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id " +
                               "WHERE recipe_ingredients.recipe_id = ?";
        pstat = connection.prepareStatement(ingredientSql);
        pstat.setInt(1, recipeId);
        resultSet = pstat.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            double quantity = resultSet.getDouble("quantity");
            String unit = resultSet.getString("unit");

            double adjustedQuantity;

            // smart scaling based on unit type
            if (unit.equals("grams") || unit.equals("ml")) {
                // liquids and solids scale fully
                adjustedQuantity = quantity * ratio;

            } else if (unit.equals("tsp") || unit.equals("tbsp")) {
                // spices and seasonings scale at 75%
                adjustedQuantity = quantity * (1 + (ratio - 1) * 0.75);

            } else if (unit.equals("cloves") || unit.equals("unit")) {
                // whole items round to nearest whole number
                adjustedQuantity = Math.round(quantity * ratio);

            } else {
                // anything else scales fully
                adjustedQuantity = quantity * ratio;
            }

            // round to 1 decimal place for readability
            adjustedQuantity = Math.round(adjustedQuantity * 10.0) / 10.0;

            output += name + ": " + adjustedQuantity + " " + unit + "\n";
        }

        resultSet.close();
        pstat.close();

        // Step 3 - get nutrition and scale linearly
        String nutritionSql = "SELECT calories, protein, fat FROM nutrition WHERE recipe_id = ?";
        pstat = connection.prepareStatement(nutritionSql);
        pstat.setInt(1, recipeId);
        resultSet = pstat.executeQuery();

        if (resultSet.next()) {
            double calories = resultSet.getDouble("calories");
            double protein = resultSet.getDouble("protein");
            double fat = resultSet.getDouble("fat");

            double adjustedCalories = Math.round((calories * ratio) * 10.0) / 10.0;
            double adjustedProtein = Math.round((protein * ratio) * 10.0) / 10.0;
            double adjustedFat = Math.round((fat * ratio) * 10.0) / 10.0;

            output += "\nNUTRITION (for " + desiredServings + " servings):\n";
            output += "Calories: " + adjustedCalories + "\n";
            output += "Protein: " + adjustedProtein + "g\n";
            output += "Fat: " + adjustedFat + "g\n";
        }

        output += "\nTIME:\n";
        output += "Prep Time: " + adjustedPrep + " mins\n";
        output += "Cook Time: " + adjustedCook + " mins\n";

    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        throw sqlException;
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (pstat != null) pstat.close();
            if (connection != null) connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    return output;
}

public RecipeData getRecipeForEdit(int recipeId) throws SQLException {
    Connection connection = null;
    PreparedStatement recipeStat = null;
    PreparedStatement ingredientStat = null;
    PreparedStatement nutritionStat = null;
    ResultSet recipeSet = null;
    ResultSet ingredientSet = null;
    ResultSet nutritionSet = null;

    String recipeSql = "SELECT recipe_id, recipe_name, instructions, default_servings, prep_time, cook_time " +
                       "FROM recipes WHERE recipe_id = ?";

    String ingredientSql = "SELECT ingredients.name, recipe_ingredients.quantity, recipe_ingredients.unit " +
                           "FROM recipe_ingredients " +
                           "JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id " +
                           "WHERE recipe_ingredients.recipe_id = ?";

    String nutritionSql = "SELECT calories, protein, fat FROM nutrition WHERE recipe_id = ?";

    try {
        connection = DBConnection.getConnection();

        recipeStat = connection.prepareStatement(recipeSql);
        recipeStat.setInt(1, recipeId);
        recipeSet = recipeStat.executeQuery();

        if (!recipeSet.next()) {
            return null;
        }

        String recipeName = recipeSet.getString("recipe_name");
        String instructions = recipeSet.getString("instructions");
        int servings = recipeSet.getInt("default_servings");
        int prepTime = recipeSet.getInt("prep_time");
        int cookTime = recipeSet.getInt("cook_time");

        String ingredients = "";
        ingredientStat = connection.prepareStatement(ingredientSql);
        ingredientStat.setInt(1, recipeId);
        ingredientSet = ingredientStat.executeQuery();

        while (ingredientSet.next()) {
            ingredients += ingredientSet.getString("name") + ": " +
                           ingredientSet.getDouble("quantity") + " " +
                           ingredientSet.getString("unit") + "\n";
        }

        double calories = 0;
        double protein = 0;
        double fat = 0;

        nutritionStat = connection.prepareStatement(nutritionSql);
        nutritionStat.setInt(1, recipeId);
        nutritionSet = nutritionStat.executeQuery();

        if (nutritionSet.next()) {
            calories = nutritionSet.getDouble("calories");
            protein = nutritionSet.getDouble("protein");
            fat = nutritionSet.getDouble("fat");
        }

        return new RecipeData(
            recipeId, recipeName, instructions, ingredients,
            servings, prepTime, cookTime, calories, protein, fat);

    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        throw sqlException;
    } finally {
        try {
            if (nutritionSet != null) nutritionSet.close();
            if (ingredientSet != null) ingredientSet.close();
            if (recipeSet != null) recipeSet.close();
            if (nutritionStat != null) nutritionStat.close();
            if (ingredientStat != null) ingredientStat.close();
            if (recipeStat != null) recipeStat.close();
            if (connection != null) connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}














}