import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddCourse extends JFrame {
    private JTextField courseNameField, creditsField,instructorIdField;
    private JButton registerButton,backButton;
    private JLabel title, messageLabel, courseNameLabel, creditsLabel, instructorIdLabel,logoLabel;
    private ImageIcon logoIcon;

    public AddCourse() {

        title = new JLabel("Add Course");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(330, 50, 300, 35);

        courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setForeground(Color.WHITE);
        courseNameLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        courseNameLabel.setBounds(75, 150, 150, 35);

        courseNameField = new JTextField(50);
        courseNameField.setBounds(225, 150, 250, 35);
        courseNameField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        creditsLabel = new JLabel("Credits:");
        creditsLabel.setForeground(Color.WHITE);
        creditsLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        creditsLabel.setBounds(75, 200, 100, 35);

        creditsField = new JTextField(50);
        creditsField.setBounds(225, 200, 250, 35);
        creditsField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));


        instructorIdLabel = new JLabel("Instructor Id:");
        instructorIdLabel.setForeground(Color.WHITE);
        instructorIdLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        instructorIdLabel.setBounds(75, 300, 100, 35);

        instructorIdField = new JTextField(50);
        instructorIdField.setBounds(225, 300, 250, 35);
        instructorIdField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        registerButton = new JButton("Add Course");
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setBounds(400, 500, 150, 50);
        registerButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 0, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setRolloverEnabled(true);
        registerButton.setFocusPainted(false);

        backButton = new JButton("Back");
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

        frame.setTitle("Add Course");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0, 0, 900, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("SanSerif", Font.PLAIN, 20));
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        frame.add(courseNameLabel);
        frame.add(courseNameField);
        frame.add(creditsLabel);
        frame.add(creditsField);
        frame.add(instructorIdLabel);
        frame.add(instructorIdField);
        frame.add(registerButton);
        frame.add(backButton);
        frame.add(title);
        frame.setVisible(true);
        frame.add(logoLabel);

        registerButton.addActionListener(e -> {
            if (registrationValidation()) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Course added");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
            }
        });
        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                CoursesPage back = new CoursesPage();
            }
        });
    }

    protected boolean registrationValidation(){
        String courseName = courseNameField.getText();
        String credits = creditsField.getText();
        String instructorId = instructorIdField.getText();
        //Connection to db
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        PreparedStatement pstmt=null;
        try{
            if(!courseName.isEmpty()&&!credits.isEmpty()&&!instructorId.isEmpty()){
                double dCredits = Double.parseDouble(credits);

                //InstructorCheck
                String iQuery="select instructorid from instructor where instructorid=?";
                PreparedStatement iCheck=connect.prepareStatement(iQuery);
                iCheck.setString(1, instructorId);
                ResultSet i_rs=iCheck.executeQuery();

                if(!i_rs.next()){
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Instructor Not Found");
                    messageLabel.setVisible(true);
                    JOptionPane.showMessageDialog(null, messageLabel);
                    System.out.println("Instructor ID does not exist");
                    return false;
                }
                String query="insert into course(coursename,credits,instructorid) values(?,?,?)";
                pstmt=connect.prepareStatement(query);
                pstmt.setString(1,courseName);
                pstmt.setDouble(2,dCredits);
                pstmt.setString(3,instructorId);
                pstmt.executeUpdate();

                System.out.println("Course table updated");
            }else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Please fill all the required fields");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
                System.out.println("Course table not updated");
                return false;
            }


        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddCourse();
            }
        });
    }
}


