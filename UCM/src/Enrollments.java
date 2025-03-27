import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Enrollments extends JFrame {
    private JTextField stdIdField, courseIdField,gradeField,adminIdField;
    private JButton registerButton,backButton;
    private JLabel title, messageLabel,gradeLabel, stdIdLabel, courseIdLabel, adminIdLabel,logoLabel;
    private ImageIcon logoIcon;

    public Enrollments() {

        title = new JLabel("Enroll Student");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(330, 50, 300, 35);

        stdIdLabel = new JLabel("Student ID:");
        stdIdLabel.setForeground(Color.WHITE);
        stdIdLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        stdIdLabel.setBounds(75, 150, 150, 35);

        stdIdField = new JTextField(50);
        stdIdField.setBounds(225, 150, 250, 35);
        stdIdField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        gradeLabel = new JLabel("Grade:");
        gradeLabel.setForeground(Color.WHITE);
        gradeLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        gradeLabel.setBounds(75, 200, 100, 35);

        gradeField = new JTextField(50);
        gradeField.setBounds(225, 200, 250, 35);
        gradeField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));


        courseIdLabel = new JLabel("Course ID:");
        courseIdLabel.setForeground(Color.WHITE);
        courseIdLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        courseIdLabel.setBounds(75, 250, 100, 35);

        courseIdField = new JTextField(50);
        courseIdField.setBounds(225, 250, 250, 35);
        courseIdField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        adminIdLabel = new JLabel("Admin Id:");
        adminIdLabel.setForeground(Color.WHITE);
        adminIdLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        adminIdLabel.setBounds(75, 300, 100, 35);

        adminIdField = new JTextField(50);
        adminIdField.setBounds(225, 300, 250, 35);
        adminIdField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));


        registerButton = new JButton("Enroll");
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

        frame.setTitle("Enroll Student");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0, 0, 900, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("SanSerif", Font.PLAIN, 20));
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        frame.add(stdIdLabel);
        frame.add(stdIdField);
        frame.add(courseIdLabel);
        frame.add(courseIdField);
        frame.add(gradeLabel);
        frame.add(gradeField);
        frame.add(adminIdLabel);
        frame.add(adminIdField);
        frame.add(registerButton);
        frame.add(backButton);
        frame.add(title);
        frame.setVisible(true);
        frame.add(logoLabel);

        registerButton.addActionListener(e -> {
            if (registrationValidation()) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Student Enrolled");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
            }
        });
        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                frame.dispose();
            }
        });
    }

    protected boolean registrationValidation(){
        String stdId = stdIdField.getText();
        String grade = gradeField.getText();
        String courseId = courseIdField.getText();
        String adminId = adminIdField.getText();

        //Connection to db
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        PreparedStatement pstmt=null;
        try{
            if(!stdId.isEmpty()&&!grade.isEmpty()&&!courseId.isEmpty()&&!adminId.isEmpty()){
                double dGrade = Double.parseDouble(grade);

                //Does Course Exist?
                String courseQuery="select courseid from course where courseid=?";
                PreparedStatement courseCheck=connect.prepareStatement(courseQuery);
                courseCheck.setString(1, courseId);
                ResultSet c_rs=courseCheck.executeQuery();

                if(!c_rs.next()){
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Course Not Found");
                    messageLabel.setVisible(true);
                    JOptionPane.showMessageDialog(null, messageLabel);
                    System.out.println("Course ID does not exist");
                    return false;
                }

                //StudentCheck
                String stdQuery="select stdid from student where stdid=?";
                PreparedStatement stdCheck=connect.prepareStatement(stdQuery);
                stdCheck.setString(1, stdId);
                ResultSet s_rs=stdCheck.executeQuery();

                if(!s_rs.next()){
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Student Not Found");
                    messageLabel.setVisible(true);
                    JOptionPane.showMessageDialog(null, messageLabel);
                    System.out.println("Student ID does not exist");
                    return false;
                }


                //Admin Check
                String adminQuery="select adminid from admin where adminid=?";
                PreparedStatement adminCheck=connect.prepareStatement(adminQuery);
                adminCheck.setString(1, adminId);
                ResultSet a_rs=adminCheck.executeQuery();

                if(!a_rs.next()){
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Admin Not Found");
                    messageLabel.setVisible(true);
                    JOptionPane.showMessageDialog(null, messageLabel);
                    System.out.println("Admin ID does not exist");
                    return false;
                }

                String insertQuery="insert into enrollments(stdid,courseid,grade,adminid) values(?,?,?,?)";
                pstmt=connect.prepareStatement(insertQuery);
                pstmt.setString(1,stdId);
                pstmt.setDouble(3,dGrade);
                pstmt.setString(2,courseId);
                pstmt.setString(4,adminId);
                pstmt.executeUpdate();

                System.out.println("Enrollments table updated");
            }else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Please fill all the required fields");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
                System.out.println("Enrollments table not updated");
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
                new Enrollments();
            }
        });
    }
}


