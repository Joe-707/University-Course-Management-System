import javax.swing.*;
import java.awt.*;

public class ChartsPage {
    private JTable table;
    private JLabel title, pageLabel;
    private ImageIcon pageIcon;

    public ChartsPage() {
        title = new JLabel("Chart:");
        title.setFont(new Font("Tahoma", Font.BOLD, 23));
        title.setForeground(Color.WHITE);
        title.setBounds(380, 0, 300, 35);

        pageIcon = new ImageIcon("images/mamasPage.png");
        pageLabel = new JLabel(pageIcon);
        pageLabel.setSize(900, 506);
        pageLabel.setLocation(0, 0);

        JFrame frame = new JFrame();

        frame.setTitle("Charts");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(175, 80);
        frame.setLayout(null);
        frame.setSize(900, 530);
        frame.getContentPane().setBackground(new Color(0, 0, 0));


        frame.add(title);
        frame.add(pageLabel);
        frame.setVisible(true);

    }

    public static void main (String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new ChartsPage();}
        });
    }
}
