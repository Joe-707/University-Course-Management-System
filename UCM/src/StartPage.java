import javax.swing.*;
import java.awt.*;
public class StartPage extends JFrame {
    private JLabel title;
    private JButton student, instructor, admin;

    public StartPage() {
        title = new JLabel("Hello there! Please click on your Mamas University status:");
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setForeground(Color.WHITE);
        title.setBounds(20, 50, 400, 35);

        student = new JButton("Student");
        student.setCursor(new Cursor(Cursor.HAND_CURSOR));
        student.setBounds(50, 100, 100, 50);
        student.setFont(new Font("SanSerif", Font.BOLD, 10));
        student.setBackground(new Color(0, 0, 0));
        student.setForeground(Color.WHITE);
        student.setRolloverEnabled(true);
        student.setFocusPainted(false);

        instructor = new JButton("Instructor");
        instructor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        instructor.setBounds(175, 100, 100, 50);
        instructor.setFont(new Font("SanSerif", Font.BOLD, 10));
        instructor.setBackground(new Color(0, 0, 0));
        instructor.setForeground(Color.WHITE);
        instructor.setRolloverEnabled(true);
        instructor.setFocusPainted(false);

        admin = new JButton("Admin");
        admin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        admin.setBounds(300, 100, 100, 50);
        admin.setFont(new Font("SanSerif", Font.BOLD, 10));
        admin.setBackground(new Color(0, 0, 0));
        admin.setForeground(Color.WHITE);
        admin.setRolloverEnabled(true);
        admin.setFocusPainted(false);

        JFrame frame = new JFrame();

        frame.setTitle("Status Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 460, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("SanSerif", Font.PLAIN, 20));
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        frame.add(title);
        frame.add(student);
        frame.add(instructor);
        frame.add(admin);
        frame.setVisible(true);

        student.addActionListener(e -> {
            RegistrationPageS registrationPage = new RegistrationPageS();
        });
        instructor.addActionListener(e -> {
            RegistrationPageI registrationPage = new RegistrationPageI();
        });
        admin.addActionListener(e -> {
            RegistrationPageA registrationPage = new RegistrationPageA();
        });
    }
    public static void main(String[] args) {
        StartPage frame = new StartPage();
    }

}
