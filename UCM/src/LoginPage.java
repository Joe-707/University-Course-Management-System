import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//Changed Layout a bit, changed button names and message display to log in

public class LoginPage extends JFrame {
    private JTextField  emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel title, messageLabel, emailLabel, passwordLabel, logoLabel;
    private ImageIcon logoIcon;

    public LoginPage() {

        title = new JLabel("Login Page");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(330, 50, 300, 35);

        emailLabel = new JLabel("Account:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        emailLabel.setBounds(75, 250, 100, 35);

        emailField = new JTextField(50);
        emailField.setBounds(225, 250, 250, 35);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        passwordLabel.setBounds(75, 300, 150, 35);

        passwordField = new JPasswordField(50);
        passwordField.setBounds(225, 300, 250, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        passwordField.setToolTipText("Must have more than 6 characters");

        loginButton = new JButton("Login");
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(375, 450, 150, 50);
        loginButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 0, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setRolloverEnabled(true);
        loginButton.setFocusPainted(false);

        logoIcon = new ImageIcon("images/mamaslogo2.png");
        logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(540, 140, 300, 300);

        messageLabel = new JLabel("Please fill all the required fields");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 25));
        messageLabel.setSize(900, 800);
        messageLabel.setVisible(true);

        JFrame frame = new JFrame();

        frame.setTitle("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 900, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("SanSerif", Font.PLAIN, 20));
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(title);
        frame.setVisible(true);
        frame.add(logoLabel);

        loginButton.addActionListener(e -> {
            if (loginValidation()) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Successfully Logged In! Welcome.");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);

                HomePage homePage = new HomePage();
            }
        });

    }

    private boolean loginValidation(){
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());

        //Connection to db
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        PreparedStatement pstmt=null;
        try{
            if(!email.isEmpty()&&!password.isEmpty()&&password.length()>=6) {
                String adminQuery = "select adminemail,password from admin where adminemail= ? and password= ?";
                pstmt = connect.prepareStatement(adminQuery);
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String adminEmail = rs.getString("adminemail");
                    System.out.println("Admin Email: " + adminEmail);
                    System.out.println("Correct Login (Admin)");
                    return true;
                }

                String instructorQuery="select instructoremail,password from instructor where instructoremail= ? and password= ?";
                pstmt=connect.prepareStatement(instructorQuery);
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    String adminEmail = rs.getString("instructoremail");
                    System.out.println("Instructor Email: " + adminEmail);
                    System.out.println("Correct Login (Instructor)");
                    return true;
                }

                else{
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Incorrect Email or Password.Please try again");
                    messageLabel.setVisible(true);
                    JOptionPane.showMessageDialog(null, messageLabel);
                    System.out.println("Email does not exist");
                    return false;
                }

            }
            else {
                System.out.println("Incorrect Login details");
                return false;
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        if (  email.isEmpty()  || password.isEmpty() ) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Please fill all the required fields");
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
        if(password.length() > 25) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Password cannot exceed 25 characters");
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
                new LoginPage();
            }
        });
    }
}
