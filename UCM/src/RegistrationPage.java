import javax.swing.*;
import java.awt.*;

public class RegistrationPage extends JFrame {
    private JTextField fullNameField, emailField, phoneField, addressField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton;
    private JLabel messageLabel;

    public RegistrationPage() {
        setTitle("Registration Page");
        setSize(1500, 1250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fullNameField = new JTextField(25);
        emailField = new JTextField(25);
        phoneField = new JTextField(25);
        addressField = new JTextField(25);
        passwordField = new JPasswordField(25);
        confirmPasswordField = new JPasswordField(25);
        registerButton = new JButton("Register");
        messageLabel = new JLabel("Please fill all the required fields");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        messageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        messageLabel.setVisible(true);
        messageLabel.setSize(500,400);

        setLayout(new GridLayout(8, 2, 10, 10));

        add(new JLabel("Full Name:"));
        add(fullNameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Phone Number:"));
        add(phoneField);
        add(new JLabel("Address:"));
        add(addressField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Confirm Password:"));
        add(confirmPasswordField);
        add(new JLabel());
        add(registerButton);

        registerButton.addActionListener(e -> {
            if (registrationValidation()) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Registration Successful");
            }
        });
    }

        private boolean registrationValidation(){
            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneField.getText();
            String address = addressField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

            if (fullName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || password.isEmpty()) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Please fill all the required fields");
                return false;
            }
            if (!password.equals(confirmPassword)) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Passwords do not match");
                return false;
            }
            if(password.length() < 6) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Password must be at least 6 characters");
                return false;
            }
            return true;
        }
        public static void main(String[] args) {
         SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
                 new RegistrationPage().setVisible(true);
             }
         });
        }
    }
