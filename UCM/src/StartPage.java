import javax.swing.*;
import java.awt.*;
public class StartPage extends JFrame {
    private JLabel title;
    private JButton register, login;

    public StartPage() {
        title = new JLabel("Hello there! Would you like to register or log in?");
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setForeground(Color.WHITE);
        title.setBounds(60, 50, 400, 35);

        register = new JButton("Register");
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.setBounds(100, 115, 100, 50);
        register.setFont(new Font("SanSerif", Font.BOLD, 10));
        register.setBackground(new Color(0, 0, 0));
        register.setForeground(Color.WHITE);
        register.setRolloverEnabled(true);
        register.setFocusPainted(false);

        login = new JButton("Login");
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setBounds(250, 115, 100, 50);
        login.setFont(new Font("SanSerif", Font.BOLD, 10));
        login.setBackground(new Color(0, 0, 0));
        login.setForeground(Color.WHITE);
        login.setRolloverEnabled(true);
        login.setFocusPainted(false);

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
        frame.add(register);
        frame.add(login);
        frame.setVisible(true);

        login.addActionListener(e -> {
            LoginPage loginPage = new LoginPage();
        });

        register.addActionListener(e -> {
            RegistrationPageA registrationPage = new RegistrationPageA();
        });
    }
    public static void main(String[] args) {
        StartPage frame = new StartPage();
    }

}
