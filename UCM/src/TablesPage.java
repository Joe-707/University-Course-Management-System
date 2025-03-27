import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TablesPage {
    private JTable table;
    private JLabel title, pageLabel;
    private ImageIcon pageIcon;
    private JButton backButton, nextButton;
    private Object[][] fetchCourseData() {
        try {
            //Connection to db
            Connection connect= new UCM_DB_Connector().getConnection();

            String query = "select courseid,round(avg(grade),2) as averageGrade from exammarks group by courseid order by averageGrade desc ";
            PreparedStatement pstmt = connect.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstmt.executeQuery();

            //Helps set number of rows
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            Object[][] data = new Object[rowCount][2];
            int row = 0;

            while (rs.next()) {
                data[row][0] = rs.getString("courseid");
                data[row][1] = rs.getString("averageGrade");
                row++;
            }
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    public TablesPage() {
        title = new JLabel("Table:");
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

        nextButton = new JButton("Student Marks");
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.setBounds(680, 400, 150, 30);
        nextButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        nextButton.setBackground(new Color(0, 0, 0));
        nextButton.setForeground(Color.WHITE);
        nextButton.setRolloverEnabled(true);
        nextButton.setFocusPainted(false);

        JFrame frame = new JFrame();

        frame.setTitle("Tables");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(175, 80);
        frame.setLayout(null);
        frame.setSize(900, 530);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        //table
        String[] columnNames = {"Course ID","Average Grade"};
        Object[][] data = fetchCourseData();

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50,50,800,260);

        //styling table
        scrollPane.getViewport().setBackground(Color.BLACK);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setShowGrid(true);
        table.getTableHeader().setBackground(Color.DARK_GRAY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));

        //centering values in the cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply to all columns
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        frame.add(scrollPane);
        frame.add(title);
        frame.add(pageLabel);
        pageLabel.add(backButton);
        pageLabel.add(nextButton);
        frame.setVisible(true);

        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                AnalyticsChoicePage back = new AnalyticsChoicePage();
            }
        });
        nextButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                StudentMarksPage next = new StudentMarksPage();
            }
        });

    }

    public static void main (String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new TablesPage();}
        });
    }
}
