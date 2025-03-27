import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EnrollmentPage {
    private JTable table;
    private JLabel title, pageLabel;
    private ImageIcon pageIcon;
    private Object[][] fetchCourseData(){
        try{
            //Connection to db
            Connection connect=null;
            Statement stmt=null;
            UCM_DB_Connector stdConn = new UCM_DB_Connector();
            connect=stdConn.getConnection();
            PreparedStatement pstmt=null;

            String query="select stdid,courseid,grade,adminid from enrollments";
            pstmt=connect.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=pstmt.executeQuery();

            //Helps set number of rows
            rs.last();
            int rowCount=rs.getRow();
            rs.beforeFirst();

            Object[][] data=new Object[rowCount][4];
            int row=0;

            while(rs.next()){
                data[row][0]=rs.getString("stdid");
                data[row][1]=rs.getString("courseid");
                data[row][2]=rs.getDouble("grade");
                data[row][3]=rs.getString("adminid");
                row++;
            }
            return data;

        }
        catch(Exception e){
            e.printStackTrace();
            return new Object[0][0];
        }

    }

    public EnrollmentPage() {
        title = new JLabel("Enrollment:");
        title.setFont(new Font("Tahoma", Font.BOLD, 23));
        title.setForeground(Color.WHITE);
        title.setBounds(380, 0, 300, 35);

        pageIcon = new ImageIcon("images/mamasPage.png");
        pageLabel = new JLabel(pageIcon);
        pageLabel.setSize(900, 506);
        pageLabel.setLocation(0, 0);

        JFrame frame = new JFrame();

        frame.setTitle("Courses");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(175, 80);
        frame.setLayout(null);
        frame.setSize(900, 530);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        //table
        String[] columnNames = {"Student ID","Course Code","Grade","Admin Id"};
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
        table.setShowGrid(false);

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
        frame.setVisible(true);


    }

    public static void main (String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new EnrollmentPage();}
        });
    }
}
