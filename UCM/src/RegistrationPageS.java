import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationPageS extends JFrame {
    private JTextField fullNameField, emailField, dobField, courseIdField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton;
    private JLabel title, messageLabel, fullNameLabel, emailLabel, dobLabel, courseIdLabel, passwordLabel, confirmPasswordLabel, logoLabel;
    private ImageIcon logoIcon;
    private Person person;

    public RegistrationPageS() {

        title = new JLabel("Student Registration Page");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(330, 50, 300, 35);

        fullNameLabel = new JLabel("Full name:");
        fullNameLabel.setForeground(Color.WHITE);
        fullNameLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        fullNameLabel.setBounds(75, 150, 100, 35);

        fullNameField = new JTextField(50);
        fullNameField.setBounds(225, 150, 250, 35);
        fullNameField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        emailLabel.setBounds(75, 200, 100, 35);

        emailField = new JTextField(50);
        emailField.setBounds(225, 200, 250, 35);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setForeground(Color.WHITE);
        dobLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        dobLabel.setBounds(75, 250, 100, 35);

        dobField = new JTextField(50);
        dobField.setBounds(225, 250, 250, 35);
        dobField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        dobField.setToolTipText("yyyy/mm/dd"); //Need to figure out how to get text in date format

        courseIdLabel = new JLabel("Course Id:");
        courseIdLabel.setForeground(Color.WHITE);
        courseIdLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        courseIdLabel.setBounds(75, 300, 100, 35);

        courseIdField = new JTextField(50);
        courseIdField.setBounds(225, 300, 250, 35);
        courseIdField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        passwordLabel.setBounds(75, 350, 150, 35);

        passwordField = new JPasswordField(50);
        passwordField.setBounds(225, 350, 250, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        passwordField.setToolTipText("Must have more than 6 characters");

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        confirmPasswordLabel.setBounds(75, 400, 150, 35);

        confirmPasswordField = new JPasswordField(50);
        confirmPasswordField.setBounds(225, 400, 250, 35);
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        confirmPasswordField.setToolTipText("Must match password");

        registerButton = new JButton("Register");
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setBounds(400, 500, 150, 50);
        registerButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 0, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setRolloverEnabled(true);
        registerButton.setFocusPainted(false);

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

        frame.setTitle("Student Registration Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 900, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("SanSerif", Font.PLAIN, 20));
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        frame.add(fullNameLabel);
        frame.add(fullNameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(dobLabel);
        frame.add(dobField);
        frame.add(courseIdLabel);
        frame.add(courseIdField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(confirmPasswordLabel);
        frame.add(confirmPasswordField);
        frame.add(registerButton);
        frame.add(title);
        frame.setVisible(true);
        frame.add(logoLabel);

        registerButton.addActionListener(e -> {
            if (registrationValidation()) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Registration Successful");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
            }
        });

    }

    protected boolean registrationValidation(){
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String courseId = courseIdField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

        //Connection to db
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        PreparedStatement pstmt=null;
        try{
            if(!fullName.isEmpty()&&!email.isEmpty()&&!password.isEmpty()&&!confirmPassword.isEmpty()&&password.equals(confirmPassword)&&password.length()>=6){
                String query="insert into student(stdname,stdemail,password,dob,courseid,age) values(?,?,?,?,?,?)";
                pstmt=connect.prepareStatement(query);
                pstmt.setString(1,fullNameField.getText());
                pstmt.setString(2,emailField.getText());
                pstmt.setString(3,passwordField.getText());
                String dobText = dobField.getText();
                DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                java.util.Date utilDate = formatter.parse(dobText);
                pstmt.setDate(4, new java.sql.Date(utilDate.getTime()));
                pstmt.setString(5,courseIdField.getText());
                person=new Person(fullNameField.getText(),emailField.getText(),utilDate) {
                    @Override
                    public int getAge(Date birthDate) {
                        return super.getAge(birthDate);
                    }
                };
                pstmt.setInt(6,person.getAge(utilDate));

                pstmt.executeUpdate();
                System.out.println("Student table updated");
            }else {
                System.out.println("Student table not updated");
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        if ( fullName.isEmpty() || email.isEmpty() || courseId.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ) {
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
                new RegistrationPageS();
            }
        });
    }
}
