import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;//This is for Pie Chart


public class ChartsPage {
    private JLabel title, pageLabel;
    private ImageIcon pageIcon;
    private JButton backButton;



    public ChartsPage() {
        title = new JLabel("Band Distribution");
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

        frame.setTitle("Charts");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(175, 80);
        frame.setLayout(null);
        frame.setSize(900, 530);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        //Pie Chart
        JFreeChart chart = createChartFromDatabase();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(0, 0, 0));
        chartPanel.setBounds(150, 60, 600, 400);

        frame.add(chartPanel);
        frame.add(title);
        frame.add(pageLabel);
        pageLabel.add(backButton);
        frame.setVisible(true);

        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                frame.dispose();
            }
        });

    }
    private JFreeChart createChartFromDatabase(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        try{
            Connection conn=new UCM_DB_Connector().getConnection();
            String query="select band, count(*) as total from exammarks group by band";
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                String band=rs.getString("band");
                int count=rs.getInt("total");
                dataset.setValue(band, count);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return ChartFactory.createPieChart("Band Distribution",dataset,true,true,false);
    }
    public static void main (String[]args){
        SwingUtilities.invokeLater(ChartsPage::new);
    }

}

