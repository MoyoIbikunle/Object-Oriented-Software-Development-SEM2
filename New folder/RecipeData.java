public class RecipeData {
    private int recipeId;
    private String recipeName;
    private String instructions;
    private String ingredients;
    private int servings;
    private int prepTime;
    private int cookTime;
    private double calories;
    private double protein;
    private double fat;

    public RecipeData(int recipeId, String recipeName, String instructions, String ingredients,
                      int servings, int prepTime, int cookTime,
                      double calories, double protein, double fat) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getServings() {
        return servings;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }
}