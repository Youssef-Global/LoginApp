package ClientSide;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The LoginGUI class represents a simple GUI for a login page.
 * It allows users to enter their username and password and sends them to the server for validation.
 */
public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JTextArea messageArea;
    private LoginClient client;

    /**
     * Constructs a new LoginGUI object.
     * Sets up the GUI components and establishes connection to the server.
     */
    public LoginGUI() {
        setTitle("Login Page");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        usernameField = new JTextField(30);
        passwordField = new JPasswordField(30);
        loginButton = new JButton("Login");
        messageArea = new JTextArea(5, 20);

        // Create panel to hold username and password fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        // Create panel to hold login button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout
        buttonPanel.add(loginButton); // Add login button to the panel

        // Add components to the content pane
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
        container.add(new JScrollPane(messageArea), BorderLayout.SOUTH);

        // Create a LoginClient instance
        client = new LoginClient();

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    /**
     * Sends the entered username and password to the server for validation.
     * Receives and displays the server's response.
     */
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Send login request to the server
        String response = client.login(username, password);

        // Display server's response
        messageArea.append(response + "\n");

        // If response indicates success, close the client
        if (response.equals("You have successfully logged in.")) {
            client.close();
            JOptionPane.showMessageDialog(this, "Login successful! Welcome " + username + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the GUI
        }
        else {
            if (response.equals("Maximum login attempts exceeded. Connection closed.")) {
                JOptionPane.showMessageDialog(this, "Maximum login attempts exceeded. Exiting application.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose(); // Close the GUI
            }
            // Set focus to the username field for user convenience
            passwordField.requestFocus();
        }
    }

    /**
     * The main method creates an instance of LoginGUI and makes it visible.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }
}
