import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddStudent extends JFrame {
    private JTextField fullNameField, emailField, dobField, courseIdField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton,backButton;
    private JLabel title, messageLabel, fullNameLabel, emailLabel, dobLabel, courseIdLabel, passwordLabel, confirmPasswordLabel, logoLabel;
    private ImageIcon logoIcon;
    private Person person;
    private String tableName;
    private String column1, column2, column3, column4, column5, column6;

    public AddStudent(String frameText, String titleText,String addText,String messageText,String backText,String tableName,String column1,String column2,String column3,String column4,String column5) {
        this.tableName = tableName;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;

        title = new JLabel(titleText);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(330, 50, 300, 35);

        fullNameLabel = new JLabel("Full Name");
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


        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        passwordLabel.setBounds(75, 300, 150, 35);

        passwordField = new JPasswordField(50);
        passwordField.setBounds(225, 300, 250, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        passwordField.setToolTipText("Must have more than 6 characters");

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        confirmPasswordLabel.setBounds(75, 350, 150, 35);

        confirmPasswordField = new JPasswordField(50);
        confirmPasswordField.setBounds(225, 350, 250, 35);
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        confirmPasswordField.setToolTipText("Must match password");

        registerButton = new JButton(addText);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setBounds(400, 500, 150, 50);
        registerButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 0, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setRolloverEnabled(true);
        registerButton.setFocusPainted(false);

        backButton = new JButton(backText);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(765, 10, 80, 30);
        backButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setRolloverEnabled(true);
        backButton.setFocusPainted(false);

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

        frame.setTitle(frameText);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(confirmPasswordLabel);
        frame.add(confirmPasswordField);
        frame.add(registerButton);
        frame.add(backButton);
        frame.add(title);
        frame.setVisible(true);
        frame.add(logoLabel);

        registerButton.addActionListener(e -> {
            if (registrationValidation()) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText(messageText);
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
            }
        });
        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                StudentsPage back = new StudentsPage();
                frame.dispose();
            }
            else if (backButton.getText().equals("Back.")) {
                InstructorsPage back = new InstructorsPage();
                frame.dispose();
            }
        });
    }

        protected boolean registrationValidation(){
            String fullName = fullNameField.getText();
            String email = emailField.getText();
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
                    String query=" insert into "+ tableName +"("+ column1 +","+ column2 +","+ column3 +","+ column4 +","+ column5 +") values(?,?,?,?,?)";
                    pstmt=connect.prepareStatement(query);
                    pstmt.setString(1,fullNameField.getText());
                    pstmt.setString(2,emailField.getText());
                    pstmt.setString(3,passwordField.getText());
                    String dobText = dobField.getText();
                    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                    java.util.Date utilDate = formatter.parse(dobText);
                    pstmt.setDate(4, new java.sql.Date(utilDate.getTime()));
                    person=new Person(fullNameField.getText(),emailField.getText(),utilDate) {
                        @Override
                        public int getAge(Date birthDate) {
                            return super.getAge(birthDate);
                        }

                        @Override
                        public void display() {
                            super.display();
                        }
                    };
                    pstmt.setInt(5,person.getAge(utilDate));

                    pstmt.executeUpdate();
                    System.out.println("Student table updated");
                    person.display();
                }else {
                    System.out.println("Student table not updated");
                }


            } catch (Exception e){
                e.printStackTrace();
            }

            if ( fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ) {
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
                new AddStudent("Add Student", "Add Student","Add Student","Student Added","Back","student","stdname","stdemail","password","dob","age");
            }
        });
    }
}

