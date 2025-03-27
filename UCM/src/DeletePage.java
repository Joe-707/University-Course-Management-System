import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class DeletePage extends JFrame {
    private JTextField stdIdField;
    private JButton deleteButton,backButton;
    private JLabel title, messageLabel, stdIdLabel;
    private String tableName;
    private String idColumnName;

    public DeletePage(String titleText,String idText,String deleteText,String messageText,String tableName,String idColumnName,String backText) {
        this.tableName = tableName;
        this.idColumnName = idColumnName;
        title = new JLabel(titleText);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(150, 10, 300, 35);

        stdIdLabel = new JLabel(idText);
        stdIdLabel.setForeground(Color.WHITE);
        stdIdLabel.setFont(new Font("SanSerif", Font.PLAIN, 16));
        stdIdLabel.setBounds(75, 80, 100, 35);

        stdIdField = new JTextField(50);
        stdIdField.setBounds(170, 80, 250, 35);
        stdIdField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        deleteButton = new JButton(deleteText);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setBounds(200, 190, 150, 50);
        deleteButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        deleteButton.setBackground(new Color(0, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setRolloverEnabled(true);
        deleteButton.setFocusPainted(false);

        backButton = new JButton(backText);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(400, 10, 80, 30);
        backButton.setFont(new Font("SanSerif", Font.BOLD, 16));
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setRolloverEnabled(true);
        backButton.setFocusPainted(false);

//        logoIcon = new ImageIcon("images/mamaslogo2.png");
//        logoLabel = new JLabel(logoIcon);
//        logoLabel.setBounds(540, 140, 300, 300);

        messageLabel = new JLabel("Please fill all the required fields");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 15));
        messageLabel.setSize(300, 200);
        messageLabel.setVisible(true);

        JFrame frame = new JFrame();

        frame.setTitle(titleText);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 500, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("SanSerif", Font.PLAIN, 20));
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        frame.add(stdIdLabel);
        frame.add(stdIdField);
        frame.add(deleteButton);
        frame.add(backButton);
        frame.add(title);
        frame.setVisible(true);
//        frame.add(logoLabel);

        deleteButton.addActionListener(e -> {
            if (deletionValidation()) {
                messageLabel= new JLabel(messageText);
                messageLabel.setForeground(Color.WHITE);
                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                messageLabel.setForeground(Color.BLACK);
                messageLabel.setFont(new Font("Arial", Font.BOLD, 15));
                messageLabel.setSize(200, 300);
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
            }
        });
        backButton.addActionListener(e -> {
            if (backButton.getText().equals("Back")) {
                StudentsPage back = new StudentsPage();
            } else if (backButton.getText().equals("Back.")) {
                CoursesPage cback = new CoursesPage();
            }
        });
    }

    protected boolean deletionValidation(){
        String stdId = stdIdField.getText();
        //Connection to db
        Connection connect=null;
        Statement stmt=null;
        UCM_DB_Connector stdConn = new UCM_DB_Connector();
        connect=stdConn.getConnection();
        PreparedStatement pstmt=null;
        try{
            if(!stdId.isEmpty()){
                String query = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";
                pstmt=connect.prepareStatement(query);
                pstmt.setString(1,stdId);
                int rowsAffected=pstmt.executeUpdate();
                if(rowsAffected>0){
                    System.out.println("Student ID: "+stdId+" is deleted");
                    return true;
                }
                else{
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Wrong ID");
                    messageLabel.setVisible(true);
                    JOptionPane.showMessageDialog(null, messageLabel);
                    System.out.println("ID: "+stdId+" is not deleted");
                    return false;
                }

            }else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Please fill all the required fields");
                messageLabel.setVisible(true);
                JOptionPane.showMessageDialog(null, messageLabel);
                System.out.println("Student table not updated(Deleted)");
                return false;
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DeletePage("Delete Student","Student ID","Delete Student","Deleted Student","student","stdid","Back");
            }
        });
    }
}


