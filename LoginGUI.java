import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private int attempts = 0;

    // reference to the main GUI so we can tell it login was successful
    private RecipeAdminGUI mainGUI;

    public LoginGUI(RecipeAdminGUI mainGUI) {
        // we pass in the main GUI so LoginGUI can talk back to it
        this.mainGUI = mainGUI;

         // ---- FORCE CUSTOM COLOURS ----
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Admin Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // only closes this window, not the whole app
        setLayout(new GridLayout(4, 2, 5, 5));

        usernameField = new JTextField();
        passwordField = new JPasswordField(); // hides what you type
        loginButton = new JButton("Login");

        add(new JLabel("Username:"));
        add(usernameField);

        add(new JLabel("Password:"));
        add(passwordField);

        add(new JLabel(""));
        add(loginButton);

        Color coral = new Color(232, 165, 152);
        Color softWhite = new Color(255, 255, 255);
        Color darkText = new Color(44, 44, 44);
        Font buttonFont = new Font("Arial", Font.BOLD, 13);

        getContentPane().setBackground(softWhite);
        loginButton.setBackground(coral);
        loginButton.setForeground(softWhite);
        loginButton.setFont(buttonFont);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                // input validation
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Please enter username and password.");
                    return;
                }

                attempts++;

                try {
                    ReadUser readUser = new ReadUser();
                    boolean valid = readUser.isValidAdmin(username, password);

                    if (valid) {
                        // tell the main GUI to show admin buttons
                        mainGUI.showAdminControls();
                        // close the login window
                        setVisible(false);

                    } else {
                        if (attempts >= 3) {
                            // used all attempts - close login window, go back to main screen
                            JOptionPane.showMessageDialog(LoginGUI.this, "Sorry - You have used all 3 attempts.");
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(LoginGUI.this, "Incorrect credentials. " + (3 - attempts) + " attempt(s) remaining.");
                            usernameField.setText("");
                            passwordField.setText("");
                        }
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }
}