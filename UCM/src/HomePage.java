import javax.swing.*;
import java.awt.*;

public class HomePage {
    private JLabel title, logoLabel, homeLabel;
    private JButton mStudentsButton, mCoursesButton, mInstructorsButton, enrollButton, analyticsButton;
    private ImageIcon logoIcon, homeIcon;

    public HomePage() {

        title = new JLabel("Welcome to the Mamas University Admin Home Page");
        title.setFont(new Font("Tahoma", Font.BOLD, 27));
        title.setForeground(Color.WHITE);
        title.setBounds(85, 0, 900, 35);

        mStudentsButton = new JButton("Manage Students");
        mStudentsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mStudentsButton.setFont(new Font( "SansSerif", Font.BOLD, 10));
        mStudentsButton.setRolloverEnabled(true);
        mStudentsButton.setFocusPainted(false);
        mStudentsButton.setBounds(10,80,130,35);
        mStudentsButton.setBackground(new Color(0, 0, 0));
        mStudentsButton.setForeground(Color.WHITE);

        mCoursesButton = new JButton("Manage Courses");
        mCoursesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mCoursesButton.setFont(new Font( "SansSerif", Font.BOLD, 10));
        mCoursesButton.setRolloverEnabled(true);
        mCoursesButton.setFocusPainted(false);
        mCoursesButton.setBounds(10,130,130,35);
        mCoursesButton.setBackground(new Color(0, 0, 0));
        mCoursesButton.setForeground(Color.WHITE);

        mInstructorsButton = new JButton("Manage Instructors");
        mInstructorsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mInstructorsButton.setFont(new Font( "SansSerif", Font.BOLD, 10));
        mInstructorsButton.setRolloverEnabled(true);
        mInstructorsButton.setFocusPainted(false);
        mInstructorsButton.setBounds(10,180,130,35);
        mInstructorsButton.setBackground(new Color(0, 0, 0));
        mInstructorsButton.setForeground(Color.WHITE);

        enrollButton = new JButton("Enroll Student");
        enrollButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        enrollButton.setFont(new Font("SanSerif", Font.BOLD, 10));
        enrollButton.setRolloverEnabled(true);
        enrollButton.setFocusPainted(false);
        enrollButton.setBounds(10,230,130,35);
        enrollButton.setBackground(new Color(0, 0, 0));
        enrollButton.setForeground(Color.WHITE);

        analyticsButton = new JButton("View Analytics");
        analyticsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        analyticsButton.setFont(new Font("SanSerif", Font.BOLD, 10));
        analyticsButton.setRolloverEnabled(true);
        analyticsButton.setFocusPainted(false);
        analyticsButton.setBounds(10,280,130,35);
        analyticsButton.setBackground(new Color(0, 0, 0));
        analyticsButton.setForeground(Color.WHITE);

        logoIcon = new ImageIcon("images/mamaslogo2small.png");
        logoLabel = new JLabel(logoIcon);
        logoLabel.setSize(125,125);
        logoLabel.setLocation(755, 355);

        homeIcon = new ImageIcon("images/MamasHome.png");
        homeLabel = new JLabel(homeIcon);
        homeLabel.setSize(900,506);
        homeLabel.setLocation(0,0);

        JFrame frame = new JFrame();

        frame.setTitle("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(175,80);
        frame.setLayout(null);
        frame.setSize(900,530);
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.add(mStudentsButton);
        frame.add(mCoursesButton);
        frame.add(mInstructorsButton);
        frame.add(enrollButton);
        frame.add(analyticsButton);
        frame.add(title);
        frame.setVisible(true);
        frame.add(logoLabel);
        frame.add(homeLabel);

        mStudentsButton.addActionListener(e -> {
            StudentsPage studentsPage = new StudentsPage();
        });

        mCoursesButton.addActionListener(e -> {
            CoursesPage coursesPage = new CoursesPage();
        });

        mInstructorsButton.addActionListener(e -> {
            InstructorsPage instructorsPage = new InstructorsPage();
        });

        enrollButton.addActionListener(e -> {
            EnrollmentPage enrollmentPage = new EnrollmentPage();
        });

        analyticsButton.addActionListener(e -> {
            AnalyticsChoicePage analyticsChoicePage = new AnalyticsChoicePage();
        });

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { new HomePage();}
        });

    }
}

