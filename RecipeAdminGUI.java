import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RecipeAdminGUI extends JFrame{
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
   
   
    private JPanel inputPanel;

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

    public RecipeAdminGUI(){

          // ---- FORCE CUSTOM COLOURS ----
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        setTitle("Recipe Admin");
        setSize(900, 900);
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

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

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
        addButton = new JButton("Add Recipe");
        updateButton = new JButton("Update Recipe");
        deleteButton = new JButton("Delete Recipe");
        viewButton = new JButton("View Recipes");
        searchButton = new JButton("Search Recipes");
        adjustButton = new JButton("Adjust Servings");
        adminLoginButton = new JButton ("Admin Login");
        adminLogoutButton = new JButton("Admin Logout");

        // on startup - only view, search and admin login are visible
        addButton.setVisible(false);
        updateButton.setVisible(false);
        deleteButton.setVisible(false);
        adminLogoutButton.setVisible(false);


        buttonPanel.add(addButton);
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




         // ACTION LISTENERS
        setupActionListeners();
        setVisible(true);
    }

    // called by LoginGUI when admin logs in successfully
    public void showAdminControls() {
        inputPanel.setVisible(true);
        addButton.setVisible(true);
        updateButton.setVisible(true);
        deleteButton.setVisible(true);
        adminLoginButton.setVisible(false);
        adminLogoutButton.setVisible(true);
        revalidate();
        repaint();
    }

    // called when admin logs out
    private void hideAdminControls() {
        inputPanel.setVisible(false);
        addButton.setVisible(false);
        updateButton.setVisible(false);
        deleteButton.setVisible(false);
        adminLoginButton.setVisible(true);
       adminLogoutButton.setVisible(false);
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


// ----- UPDATE BUTTON -----
updateButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        try {
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
            clearFields();

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
            int recipeId = Integer.parseInt(idField.getText().trim());

            // Call DeleteRecipe class
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
            int recipeId = Integer.parseInt(idField.getText().trim());
            int desiredServings = Integer.parseInt(servingsField.getText().trim());

            if (desiredServings <= 0) {
                JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Servings must be greater than 0.");
                return;
            }

            ReadRecipe reader = new ReadRecipe();
            String adjustedRecipe = reader.adjustServings(recipeId, desiredServings);

            outputArea.setText(adjustedRecipe);
            outputArea.setCaretPosition(0);

        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Recipe ID and Servings must be numbers.");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(RecipeAdminGUI.this, "Error adjusting recipe: " + ex.getMessage());
            ex.printStackTrace();
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

    }
} 