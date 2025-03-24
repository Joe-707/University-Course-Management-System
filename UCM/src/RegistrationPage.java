import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        messageLabel.setSize(900,800);
        JOptionPane.showMessageDialog(null, messageLabel);

        setLayout(new GridLayout(8, 2, 10, 10));





        add(new JLabel("Full Name:"));
        add(fullNameField);
        add(new JLabel("Email:"));
        add(emailField);
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
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
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

            //Connection to db
            Connection connect=null;
            Statement stmt=null;
            UCM_DB_Connector stdConn = new UCM_DB_Connector();
            connect=stdConn.getConnection();
            PreparedStatement pstmt=null;
            try{
                if(!fullName.isEmpty()&&!email.isEmpty()&&!address.isEmpty()&&!password.isEmpty()&&!confirmPassword.isEmpty()&&password.equals(confirmPassword)&&password.length()>=6){
                    String query="insert into register(name,email,address,password) values(?,?,?,?)";
                    pstmt=connect.prepareStatement(query);
                    pstmt.setString(1,fullNameField.getText());
                    pstmt.setString(2,emailField.getText());
                    pstmt.setString(3,addressField.getText());
                    pstmt.setString(4,passwordField.getText());

                    pstmt.executeUpdate();
                    System.out.println("Register table updated");
                }else {
                    System.out.println("Register table not updated");
                }


            } catch (Exception e){
                e.printStackTrace();
            }

            if (fullName.isEmpty() || email.isEmpty() ||  address.isEmpty() || password.isEmpty()) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Please fill all the required fields");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
                return false;
            }
            if (!password.equals(confirmPassword)) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Passwords do not match");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
                return false;
            }
            if(password.length() < 6) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Password must be at least 6 characters");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
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
