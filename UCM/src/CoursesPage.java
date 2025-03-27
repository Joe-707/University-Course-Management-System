import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CoursesPage {
    private JTable table;
    private JLabel title, pageLabel;
    private ImageIcon pageIcon;
    private JButton backButton,addButton,deleteButton;
    private Object[][] fetchCourseData(){
        try{
            //Connection to db
            Connection connect=null;
            Statement stmt=null;
            UCM_DB_Connector stdConn = new UCM_DB_Connector();
            connect=stdConn.getConnection();
            PreparedStatement pstmt=null;

            String query="select * from course";
            pstmt=connect.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=pstmt.executeQuery();

            //Helps set number of rows
            rs.last();
            int rowCount=rs.getRow();
            rs.beforeFirst();

            Object[][] data=new Object[rowCount][4];
            int row=0;

            while(rs.next()){
                data[row][0]=rs.getString("courseid");
                data[row][1]=rs.getString("coursename");
                data[row][2]=rs.getDouble("credits");
                data[row][3]=rs.getString("instructorid");
                row++;
            }
            return data;

        }
        catch(Exception e){
            e.printStackTrace();
            return new Object[0][0];
        }

    }

    public CoursesPage() {

        title = new JLabel("Courses:");
        title.setFont(new Font("Tahoma", Font.BOLD, 23));
        title.setForeground(Color.WHITE);
        title.setBounds(380, 0, 300, 35);

        pageIcon = new ImageIcon("images/mamasPage.png");
        pageLabel = new JLabel(pageIcon);
        pageLabel.setSize(900, 506);
        pageLabel.setLocation(0, 0);

        addButton = new JButton("Add Course");
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setBounds(700, 330, 150, 30);
        addButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        addButton.setBackground(new Color(0, 0, 0));
        addButton.setForeground(Color.WHITE);
        addButton.setRolloverEnabled(true);
        addButton.setFocusPainted(false);

        backButton = new JButton("Back");
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(765, 10, 80, 30);
        backButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setRolloverEnabled(true);
        backButton.setFocusPainted(false);

        deleteButton = new JButton("Delete Course");
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setBounds(700, 390, 150, 30);
        deleteButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        deleteButton.setBackground(new Color(0, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setRolloverEnabled(true);
        deleteButton.setFocusPainted(false);

        JFrame frame = new JFrame();

        frame.setTitle("Courses");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(175, 80);
        frame.setLayout(null);
        frame.setSize(900, 530);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        //table
        String[] columnNames = {"Course Code","Course Name","Credits","Instructor Id"};
        Object[][] data = fetchCourseData();

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50,50,800,260);
        scrollPane.getViewport().setBackground(Color.BLACK);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setShowGrid(false);

        table.getTableHeader().setBackground(Color.DARK_GRAY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        frame.add(scrollPane);
        frame.add(title);
        frame.add(pageLabel);
        pageLabel.add(addButton);
        pageLabel.add(backButton);
        pageLabel.add(deleteButton);
        frame.setVisible(true);

        addButton.addActionListener(e -> {
            if (addButton.getText().equals("Add Course")) {
                AddCourse add = new AddCourse();
                add.setVisible(true);
            }
        });
        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                frame.dispose();
            }
        });
        deleteButton.addActionListener(e -> {
            if (deleteButton.getText().equals("Delete Course")) {
                DeletePage delete=new DeletePage("Delete Course","Course ID","Delete Course","Deleted Course","course","courseid","Back.");
            }
        });

    }

    public static void main (String[]args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {new CoursesPage();}
        });
    }
}
