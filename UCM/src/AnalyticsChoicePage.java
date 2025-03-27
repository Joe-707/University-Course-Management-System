import javax.swing.*;
import java.awt.*;
public class AnalyticsChoicePage {
    private JLabel title;
    private JButton graph, chart, table,backButton;

    public AnalyticsChoicePage() {
        title = new JLabel("Please click on the form of analytics you want to view:");
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setForeground(Color.WHITE);
        title.setBounds(20, 50, 400, 35);

        graph = new JButton("Graph");
        graph.setCursor(new Cursor(Cursor.HAND_CURSOR));
        graph.setBounds(50, 100, 100, 50);
        graph.setFont(new Font("SanSerif", Font.BOLD, 10));
        graph.setBackground(new Color(0, 0, 0));
        graph.setForeground(Color.WHITE);
        graph.setRolloverEnabled(true);
        graph.setFocusPainted(false);

        chart = new JButton("Chart");
        chart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chart.setBounds(175, 100, 100, 50);
        chart.setFont(new Font("SanSerif", Font.BOLD, 10));
        chart.setBackground(new Color(0, 0, 0));
        chart.setForeground(Color.WHITE);
        chart.setRolloverEnabled(true);
        chart.setFocusPainted(false);

        table = new JButton("Table");
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table.setBounds(300, 100, 100, 50);
        table.setFont(new Font("SanSerif", Font.BOLD, 10));
        table.setBackground(new Color(0, 0, 0));
        table.setForeground(Color.WHITE);
        table.setRolloverEnabled(true);
        table.setFocusPainted(false);

        backButton = new JButton("Back");
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(360, 200, 80, 30);
        backButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setRolloverEnabled(true);
        backButton.setFocusPainted(false);

        JFrame frame = new JFrame();

        frame.setTitle("Status Selection");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0, 0, 460, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("SanSerif", Font.PLAIN, 20));
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        frame.add(title);
        frame.add(graph);
        frame.add(chart);
        frame.add(table);
        frame.add(backButton);
        frame.setVisible(true);

        chart.addActionListener(e ->{
            ChartsPage chartsPage = new ChartsPage();
        });
        graph.addActionListener(e -> {
            GraphsPage graphsPage = new GraphsPage();
        });
        table.addActionListener(e -> {
            TablesPage tablesPage = new TablesPage();
        });
        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                frame.dispose();
            }
        });
    }
    public static void main (String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new AnalyticsChoicePage();}
        });
    }
}
