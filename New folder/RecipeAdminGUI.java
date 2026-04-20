import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RecipeAdminGUI extends JFrame{

    private boolean recipeLoadedForEdit = false;

    //Text Fields
    private JTextField idField;
    private JTextField nameField;
    private JTextField servingsField;
    private JTextField prepTimeField;
    private JTextField cookTimeField;
    private JTextField caloriesField;
    private JTextField proteinField;
    private JTextField fatField;
    private JTextField searchField;
    private JTextField adjustIdField;
    private JTextField adjustServingsField;
    private JTextField convertAmountField;

    private JPanel adjustPanel;
    private JPanel inputPanel;
    private JPanel converterPanel;

    private JComboBox<String> fromUnitBox;
    private JComboBox<String> toUnitBox;

    private JLabel convertResultLabel;

    //Text Area: allows for multi-line input
    private JTextArea ingredientsArea;
    private JTextArea instructionsArea;
    private JTextArea outputArea;   // when the user wants to view recipes, displays information back to the user

    //Buttons
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton adminLoginButton;
    private JButton adminLogoutButton;
    private JButton adjustButton;
    private JButton editButton;
    private JButton convertButton;
    private JButton newRecipeButton;

    public RecipeAdminGUI(){

          // ---- FORCE CUSTOM COLOURS ----
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        setTitle("Recipe Admin");
        setSize(1000, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //INPUT PANEL
        inputPanel = new JPanel(new GridLayout (10, 2, 5, 5));
      
        
        idField = new JTextField();
        nameField = new JTextField();
        //making areas big enough to see text input
        ingredientsArea = new JTextArea(3, 20);
        instructionsArea = new JTextArea(3, 20);
        servingsField = new JTextField();
        prepTimeField = new JTextField();
        cookTimeField = new JTextField();
        caloriesField = new JTextField();
        proteinField = new JTextField();
        fatField = new JTextField();
        searchField = new JTextField();

        //label pairs
        inputPanel.add(new JLabel("Recipe ID:"));
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        //adding scrollpane so users can scroll if text goes past 3 rows/lines
        inputPanel.add(new JLabel("Ingredients:"));
        inputPanel.add(new JScrollPane(ingredientsArea));

        inputPanel.add(new JLabel("Instructions:"));
        inputPanel.add(new JScrollPane(instructionsArea));

        inputPanel.add(new JLabel("Servings:"));
        inputPanel.add(servingsField);

        inputPanel.add(new JLabel("Prep Time:"));
        inputPanel.add(prepTimeField);

        inputPanel.add(new JLabel("Cook Time:"));
        inputPanel.add(cookTimeField);

        inputPanel.add(new JLabel("Calories:"));
        inputPanel.add(caloriesField);

        inputPanel.add(new JLabel("Protein:"));
        inputPanel.add(proteinField);

        inputPanel.add(new JLabel("Fat:"));
        inputPanel.add(fatField);

        inputPanel.setVisible(false);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);


        adjustPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        adjustPanel.add(new JLabel("Recipe ID to Adjust:"));
        adjustIdField = new JTextField(5);
        adjustPanel.add(adjustIdField);

        adjustPanel.add(new JLabel("New Servings:"));
        adjustServingsField = new JTextField(5);
        adjustPanel.add(adjustServingsField);

            converterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            converterPanel.add(new JLabel("Amount:"));
            convertAmountField = new JTextField(6);
            converterPanel.add(convertAmountField);

            converterPanel.add(new JLabel("From:"));
            fromUnitBox = new JComboBox<String>(new String[]{"grams", "ounces", "ml", "cups"});
            converterPanel.add(fromUnitBox);

            converterPanel.add(new JLabel("To:"));
            toUnitBox = new JComboBox<String>(new String[]{"grams", "ounces", "ml", "cups"});
            converterPanel.add(toUnitBox);

            convertButton = new JButton("Convert");
            converterPanel.add(convertButton);

            convertResultLabel = new JLabel("Result: ");
            converterPanel.add(convertResultLabel);

        JPanel utilityPanel = new JPanel(new GridLayout(2, 1));
        utilityPanel.add(adjustPanel);
        utilityPanel.add(converterPanel);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(utilityPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

          //          OUTPUT AREA 
        outputArea = new JTextArea(18, 90);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        add(outputScrollPane, BorderLayout.CENTER);

        //            BUTTON PANEL 
        JPanel buttonPanel = new JPanel(new FlowLayout());
        newRecipeButton = new JButton("New Recipe");
        addButton = new JButton("Add Recipe");
        editButton = new JButton("Edit Recipe");
        updateButton = new JButton("Save Updated Recipe");
        deleteButton = new JButton("Delete Recipe");
        viewButton = new JButton("View Recipes");
        searchButton = new JButton("Search Recipes");
        adjustButton = new JButton("Adjust Servings");
        adminLoginButton = new JButton ("Admin Login");
        adminLogoutButton = new JButton("Admin Logout");
        
        

        // on startup - only view, search and admin login are visible
        newRecipeButton.setVisible(false);
        addButton.setVisible(false);
        updateButton.setVisible(false);
        deleteButton.setVisible(false);
        adminLogoutButton.setVisible(false);
        editButton.setVisible(false);
        updateButton.setVisible(false);

        buttonPanel.add(newRecipeButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(adminLoginButton);
        buttonPanel.add(adminLogoutButton);
        buttonPanel.add(adjustButton);
      
        add (buttonPanel, BorderLayout.SOUTH);

        // colours
        Color coral = new Color(232, 165, 152);
        Color sage = new Color(122, 158, 126);
        Color darkText = new Color(44, 44, 44);
        Color softWhite = new Color(255, 255, 255);

        // fonts
        Font buttonFont = new Font("Arial", Font.BOLD, 13);

        // backgrounds
        getContentPane().setBackground(softWhite);
        inputPanel.setBackground(softWhite);
        buttonPanel.setBackground(softWhite);

        // view and search - coral
        viewButton.setBackground(coral);
        viewButton.setForeground(softWhite);
        viewButton.setFont(buttonFont);

        searchButton.setBackground(coral);
        searchButton.setForeground(softWhite);
        searchButton.setFont(buttonFont);

        // add and update - sage green
        addButton.setBackground(sage);
        addButton.setForeground(softWhite);
        addButton.setFont(buttonFont);

        updateButton.setBackground(sage);
        updateButton.setForeground(softWhite);
        updateButton.setFont(buttonFont);

        // delete - darker red
        deleteButton.setBackground(new Color(180, 80, 70));
        deleteButton.setForeground(softWhite);
        deleteButton.setFont(buttonFont);

        // admin buttons - dark charcoal
        adminLoginButton.setBackground(darkText);
        adminLoginButton.setForeground(softWhite);
        adminLoginButton.setFont(buttonFont);

        adminLogoutButton.setBackground(darkText);
        adminLogoutButton.setForeground(softWhite);
        adminLogoutButton.setFont(buttonFont);

        // output area
        outputArea.setBackground(new Color(252, 248, 245));
        outputArea.setForeground(darkText);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 13));


        searchPanel.setBackground(softWhite);
        adjustPanel.setBackground(softWhite);
        topPanel.setBackground(softWhite);

        adjustButton.setBackground(coral);
        adjustButton.setForeground(softWhite);
        adjustButton.setFont(buttonFont);


        converterPanel.setBackground(softWhite);
        convertButton.setBackground(coral);
        convertButton.setForeground(softWhite);
        convertButton.setFont(buttonFont);

         // ACTION LISTENERS
        setupActionListeners();
        setVisible(true);
    }

    // called by LoginGUI when admin logs in successfully
    public void showAdminControls() {
    clearFields();
    searchField.setText("");
    inputPanel.setVisible(false);
    converterPanel.setVisible(false);

    newRecipeButton.setVisible(true);
    addButton.setVisible(true);
    updateButton.setVisible(false);
    deleteButton.setVisible(true);
    adminLoginButton.setVisible(false);
    adminLogoutButton.setVisible(true);
    editButton.setVisible(true);
    updateButton.setVisible(true);
    revalidate();
    repaint();

    }

    // called when admin logs out
    private void hideAdminControls() {
        clearFields();
        searchField.setText("");
        
        inputPanel.setVisible(false);
        converterPanel.setVisible(true);

        newRecipeButton.setVisible(false);
        addButton.setVisible(false);
        updateButton.setVisible(false);
        deleteButton.setVisible(false);
        adminLoginButton.setVisible(true);
        adminLogoutButton.setVisible(false);
        editButton.setVisible(false);
        updateButton.setVisible(false);
        recipeLoadedForEdit = false;
        revalidate();
        repaint();
       }
    
       private void setupActionListeners(){


        //  ADMIN LOGIN BUTTON
        //add a listener to this button for when its clicked
        ////ActionListener is the listener
        adminLoginButton.addActionListener(new ActionListener() {
            //what runs when the button is clicked
            //ActionEvent e gives information about the click, what was clicked, when....
            public void actionPerformed(ActionEvent e) {
                // open the login window, pass this GUI as a reference
                new LoginGUI(RecipeAdminGUI.this);
            }
        });

        // ADMIN LOGOUT BUTTON
        adminLogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideAdminControls();
                //RecipeAdminGUI.this lets it know what this is in reference to, so where the dialog box should go
                JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Logged out successfully.");
            }
        });

        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

        try{

               //  READ ADMIN INPUT 
        String name = nameField.getText().trim();
        String instructions = instructionsArea.getText().trim();
        String ingredients = ingredientsArea.getText().trim();

         // CONVERTING THE STRINGS TO DOUBLE
        double calories = Double.parseDouble(caloriesField.getText().trim());
        double protein = Double.parseDouble(proteinField.getText().trim());
        double fat = Double.parseDouble(fatField.getText().trim());

        //      CONVERTING THE STRINGS TO INT
        int servings = Integer.parseInt(servingsField.getText().trim());
        int prepTime = Integer.parseInt(prepTimeField.getText().trim());
        int cookTime = Integer.parseInt(cookTimeField.getText().trim());

        //      INPUT VALIDATION
        if(name.isEmpty() || instructions.isEmpty() || ingredients.isEmpty()) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "All fields must be filled.");
            return;
        }
        

         if(calories < 0 || protein < 0 || fat < 0) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Nutrition values must be positive numbers.");
            return;
        }

        //          INSERT RECIPE
        //          create object 
        //          use method from insertRecipe to insertRecipe which reurns recipeId
        InsertRecipe recipeInserter = new InsertRecipe();
        //recipeId needed to run methods like insertRecipeIngredient, etc...
        int recipeId = recipeInserter.insertRecipe(name, instructions, servings, prepTime, cookTime);
    
         //      INSERT INGREDIENTS 
        InsertIngredient ingredientInserter = new InsertIngredient();
        InsertRecipeIngredient recipeIngredientInserter = new InsertRecipeIngredient();
        //splits the text from ingredient area into an array of strings, one per line
        //e.g. ingredientLines[0] = "Chicken"
        String[] ingredientLines = ingredients.split("\\n"); 
        //for each element ingrdLine in the array ingredientLines, do:
        for(String ingrdLine : ingredientLines) {
            //skips empty lines so nothing invalid gets inserted
         if(!ingrdLine.trim().isEmpty()) {
                int ingredientId = ingredientInserter.insertIngredient(ingrdLine.trim());
                recipeIngredientInserter.insertRecipeIngredient(recipeId, ingredientId, 1, "item");
            }
        }

        //       INSERT NUTRITION 
        InsertNutrition nutritionInserter = new InsertNutrition();
        nutritionInserter.insertNutrition(recipeId, fat, protein, calories);

        //       SUCCESS MESSAGE 
        JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Recipe added successfully!");
        clearFields();

    } 
    //catches a specific error, if servings, prep time, etc are inputted as letters the system will sned a message that they should be numbers
    catch(NumberFormatException nfe) {
    JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Servings, Prep Time, Cook Time, and Nutrition values must be numbers.");
    } 
    //catches any other error that wwasnt caught before wither SQL error, connection failure, etc...
    catch(Exception ex) {
    JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Error adding recipe: " + ex.getMessage());
    ex.printStackTrace();
    }
}
});

newRecipeButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        inputPanel.setVisible(true);
        clearAdminForm();
        updateButton.setVisible(false);
        revalidate();
        repaint();
    }
});

// ----- UPDATE BUTTON -----
updateButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        try {

             if (!recipeLoadedForEdit) {
                JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Please enter a Recipe ID and click Edit Recipe first.");
                return;
            }
            //reading in the values again incase they were changed
            int recipeId = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String instructions = instructionsArea.getText().trim();
            String ingredients = ingredientsArea.getText().trim();
            int servings = Integer.parseInt(servingsField.getText().trim());
            int prepTime = Integer.parseInt(prepTimeField.getText().trim());
            int cookTime = Integer.parseInt(cookTimeField.getText().trim());

            // Input validation
            if(name.isEmpty() || instructions.isEmpty() || ingredients.isEmpty()) {
                JOptionPane.showMessageDialog(RecipeAdminGUI.this, "All fields must be filled.");
                return;
            }

            // Call UpdateRecipe class
            UpdateRecipe updater = new UpdateRecipe();
            int rows = updater.updateRecipe(recipeId, name, instructions, servings, prepTime, cookTime);

            JOptionPane.showMessageDialog(RecipeAdminGUI.this, rows + " recipe updated successfully!");
            recipeLoadedForEdit = false;
            updateButton.setVisible(false);
            clearFields();
            inputPanel.setVisible(false);
            revalidate();
            repaint();

        } 
        //when java cant parse a String into a numeric type cause the text wasnt a valid number
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Recipe ID, Servings, Prep Time, and Cook Time must be numbers.");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Error updating recipe: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
});



// ----- DELETE BUTTON -----
deleteButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        try {
            inputPanel.setVisible(false);
            revalidate();
            repaint();

            String idText = JOptionPane.showInputDialog(RecipeAdminGUI.this, "Enter Recipe ID to delete:");

            if (idText == null || idText.trim().isEmpty()) {
                return;
            }

            int recipeId = Integer.parseInt(idText.trim());

            int confirm = JOptionPane.showConfirmDialog(
                RecipeAdminGUI.this,
                "Are you sure you want to delete recipe ID " + recipeId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            DeleteRecipe deleter = new DeleteRecipe();
            int rows = deleter.deleteRecipe(recipeId);

            JOptionPane.showMessageDialog(RecipeAdminGUI.this, rows + " recipe deleted successfully!");
            clearFields();

        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Recipe ID must be a number.");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Error deleting recipe: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
});

// ----- VIEW BUTTON -----
viewButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        try {

            inputPanel.setVisible(false);
        clearFields();
        revalidate();
        repaint();

            ReadRecipe reader = new ReadRecipe();
            // get all recipes as a String
            String allRecipes = reader.getAllRecipes();
            
        outputArea.setText(allRecipes);
        //moves cursor to the top of the text area, so user sees the beginning first
           outputArea.setCaretPosition(0);
        
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Error viewing recipes: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
});


        // ----- SEARCH BUTTON -----
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {

                    inputPanel.setVisible(false);
                    clearFields();
                    revalidate();
                    repaint();
                    
                    String searchTerm = searchField.getText().trim();

                    // Input validation
                    if(searchTerm.isEmpty()) {
                        JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Please enter a search term.");
                        return;
                    }

                    ReadRecipe reader = new ReadRecipe();
                    String results = reader.searchRecipes(searchTerm);

                    outputArea.setText(results);
                    outputArea.setCaretPosition(0);

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Error searching recipes: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        adjustButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        try {
            int recipeId = Integer.parseInt(adjustIdField.getText().trim());
            int desiredServings = Integer.parseInt(adjustServingsField.getText().trim());

            if (desiredServings <= 0) {
                JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Servings must be greater than 0.");
                return;
            }

            ReadRecipe reader = new ReadRecipe();
            String adjustedRecipe = reader.adjustServings(recipeId, desiredServings);

            outputArea.setText(adjustedRecipe);
            outputArea.setCaretPosition(0);

        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Recipe ID and New Servings must be numbers.");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Error adjusting recipe: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
});

    editButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            String idText = JOptionPane.showInputDialog(RecipeAdminGUI.this, "Enter Recipe ID to edit:");

            if (idText == null || idText.trim().isEmpty()) {
                return;
            }

            int recipeId = Integer.parseInt(idText.trim());

            ReadRecipe reader = new ReadRecipe();
            RecipeData recipe = reader.getRecipeForEdit(recipeId);

            if (recipe == null) {
                JOptionPane.showMessageDialog(RecipeAdminGUI.this, "No recipe found with that ID.");
                recipeLoadedForEdit = false;
                return;
            }

            inputPanel.setVisible(true);

            idField.setText(String.valueOf(recipe.getRecipeId()));
            nameField.setText(recipe.getRecipeName());
            instructionsArea.setText(recipe.getInstructions());
            ingredientsArea.setText(recipe.getIngredients());
            servingsField.setText(String.valueOf(recipe.getServings()));
            prepTimeField.setText(String.valueOf(recipe.getPrepTime()));
            cookTimeField.setText(String.valueOf(recipe.getCookTime()));
            caloriesField.setText(String.valueOf(recipe.getCalories()));
            proteinField.setText(String.valueOf(recipe.getProtein()));
            fatField.setText(String.valueOf(recipe.getFat()));

            recipeLoadedForEdit = true;
            updateButton.setVisible(true);

            revalidate();
            repaint();

            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Recipe loaded. You can now edit and save changes.");

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Recipe ID must be a number.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Error loading recipe: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
});

    convertButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(convertAmountField.getText().trim());
            String fromUnit = fromUnitBox.getSelectedItem().toString();
            String toUnit = toUnitBox.getSelectedItem().toString();

            double result = 0;

            if (fromUnit.equals("grams") && toUnit.equals("ounces")) {
                result = amount / 28.35;
            } else if (fromUnit.equals("ounces") && toUnit.equals("grams")) {
                result = amount * 28.35;
            } else if (fromUnit.equals("ml") && toUnit.equals("cups")) {
                result = amount / 236.59;
            } else if (fromUnit.equals("cups") && toUnit.equals("ml")) {
                result = amount * 236.59;
            } else if (fromUnit.equals(toUnit)) {
                result = amount;
            } else {
                JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Please choose a valid conversion pair.");
                return;
            }

            convertResultLabel.setText("Result: " + String.format("%.2f", result) + " " + toUnit);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Please enter a valid amount.");
        }
    }
});
    }



    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        servingsField.setText("");
        prepTimeField.setText("");
        cookTimeField.setText("");
        caloriesField.setText("");
        proteinField.setText("");
        fatField.setText("");
        ingredientsArea.setText("");
        instructionsArea.setText("");
        outputArea.setText("");
        convertAmountField.setText("");
        convertResultLabel.setText("Result: ");
        adjustIdField.setText("");
        adjustServingsField.setText("");

        recipeLoadedForEdit = false;
    }

    private void clearAdminForm() {
    idField.setText("");
    nameField.setText("");
    servingsField.setText("");
    prepTimeField.setText("");
    cookTimeField.setText("");
    caloriesField.setText("");
    proteinField.setText("");
    fatField.setText("");
    ingredientsArea.setText("");
    instructionsArea.setText("");
    recipeLoadedForEdit = false;
}
    
} 