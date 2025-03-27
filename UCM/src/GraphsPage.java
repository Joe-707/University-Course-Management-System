import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;//This is for Bar Graph

public class GraphsPage {
    private JTable table;
    private JLabel title, pageLabel;
    private ImageIcon pageIcon;
    private JButton backButton;

    public GraphsPage() {
        title = new JLabel("Graph:");
        title.setFont(new Font("Tahoma", Font.BOLD, 23));
        title.setForeground(Color.WHITE);
        title.setBounds(380, 0, 300, 35);

        pageIcon = new ImageIcon("images/mamasPage.png");
        pageLabel = new JLabel(pageIcon);
        pageLabel.setSize(900, 506);
        pageLabel.setLocation(0, 0);

        backButton = new JButton("Back");
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(765, 10, 80, 30);
        backButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setRolloverEnabled(true);
        backButton.setFocusPainted(false);

        JFrame frame = new JFrame();

        frame.setTitle("Graphs");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(175, 80);
        frame.setLayout(null);
        frame.setSize(900, 530);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        JFreeChart chart = createBarGraphFromDB();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(150, 60, 600, 400);


        frame.add(chartPanel);
        frame.add(title);
        frame.add(pageLabel);
        frame.add(backButton);
        frame.setVisible(true);

        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                frame.dispose();
            }
        });

    }
    private JFreeChart createBarGraphFromDB() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try{
            Connection conn= new UCM_DB_Connector().getConnection();
            String query="select courseid,count(distinct  stdid) as totalStd from enrollments group by courseid order by totalStd desc";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                String courseid = rs.getString("courseid");
                int count = rs.getInt("totalStd");
                dataset.setValue(count, "Students", courseid);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ChartFactory.createBarChart("Number of students","Courses","Number of Students",dataset);
    }

    public static void main (String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new GraphsPage();}
        });
    }
}
