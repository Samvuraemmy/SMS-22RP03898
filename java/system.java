/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// This section specifies the license and template used for this Java class.
package javaconnection;
import java.awt.*;
import javax.swing.*;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class system {
    // These are the connection details for the MariaDB database.
       private static final String JDBC_URL="jdbc:mariadb://localhost:3306/management";
   private static final String USERNAME="root";
   private static final String PASSWORD="";
   public static void main(String args[]) {
        //  This section creates the main JFrame window for the application.
         // We set its title, size, and closing behavior.
 
        JFrame frame1=new JFrame();
        frame1.setTitle("student management system");
        frame1.setSize(250,250);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1=new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        //Here, we create a JPanel to hold all the UI elements and arrange them using GridBagLayout.
        frame1.add(panel1);
        JLabel label1=new JLabel("Name:");
        JLabel label2=new JLabel("Reg number:");
        JTextField txtname=new JTextField(35);
        JLabel label3=new JLabel("Math marks:");
         JTextField txtmath=new JTextField(32);
        JLabel label4=new JLabel("java marks:");
         JTextField txtjava=new JTextField(30);
        JLabel label5=new JLabel("php marks:");
        JTextField txtphp=new JTextField(15);
        JButton btn1=new JButton("add student");
        btn1.setBackground(Color.GREEN);
   
       JButton btn2=new JButton("exit");
        btn2.setBackground(Color.RED);
        //Create text fields for user input. Set their editability and size.
        txtname.setEditable(true);
         JTextField txtregno=new JTextField(20);
          txtname.setEditable(true);
          gbc.insets=new Insets (6,6,6,6);
           gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label1,gbc);
         gbc.gridx = 1; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(txtname,gbc);
         gbc.gridx = 0; 
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
         panel1.add(label2,gbc);
         gbc.gridx = 1; 
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
         panel1.add(txtregno,gbc);
         gbc.gridx = 0; 
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
         panel1.add(label3,gbc);
         gbc.gridx = 1; 
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
         panel1.add(txtmath,gbc);
         gbc.gridx = 0; 
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
         panel1.add(label4,gbc);
         gbc.gridx = 1; 
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
         panel1.add(txtjava,gbc);
         gbc.gridx = 0; 
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
         panel1.add(label5,gbc);
         gbc.gridx = 1; 
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
         panel1.add(txtphp,gbc);
          gbc.gridx = 0; 
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
         panel1.add(btn1,gbc);
         gbc.gridx = 1; 
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
         panel1.add(btn2,gbc);
          DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        String[] columnNames = {"Name", "Reg Number", "Average marks"};
        tableModel.setColumnIdentifiers(columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane, gbc);
        btn1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        //JOptionPane.showMessageDialog(null,txtname.getText());
        try{
                  Connection conn=null;
                  Statement stmt=null;
           Class.forName("org.mariadb.jdbc.Driver");
    conn = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
    stmt=conn.createStatement();
    String sql="CREATE TABLE IF NOT EXISTS STUDENTSMGMNT(name varchar(10),regno varchar(20),math int,java int,php int)";
    stmt.executeUpdate(sql);  
    
    sql="INSERT INTO STUDENTSMGMNT(name,regno,math,java,php)VALUES(?,?,?,?,?)"; 
    PreparedStatement pstm=conn.prepareStatement(sql);
      String field1 = txtname.getText();
      String field2 = txtregno.getText();
      int field3 = Integer.parseInt(txtmath.getText());
      int field4 = Integer.parseInt(txtjava.getText());
      int field5 = Integer.parseInt(txtphp.getText());
      if (field3 < 0 || field3 > 100 || field4 < 0 || field4 > 100 || field5 < 0 ||field5 > 100) {
     JOptionPane.showMessageDialog(null, "Marks should be between 0 and 100", "Error", JOptionPane.ERROR_MESSAGE);
     JOptionPane.showMessageDialog(null,"please enter the valid numeric value of marks");
        txtname.setText("");
txtregno.setText("");
txtmath.setText("");
txtjava.setText("");
txtphp.setText("");     
      }
      else{
pstm.setString(1, field1);
pstm.setString(2,field2);
pstm.setInt(3,field3);
pstm.setInt(4,field4);
pstm.setInt(5,field5);
      
pstm.executeUpdate();
      }
 txtname.setText("");
txtregno.setText("");
txtmath.setText("");
txtjava.setText("");
txtphp.setText("");
ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTSMGMNT");
                    while (rs.next()) {
                        String name = rs.getString("name");
                        String regno = rs.getString("regno");
                        int math = rs.getInt("math");
                        int java=rs.getInt("java");
                        
                        int php=rs.getInt("php");
                        int avg=(math+java+php)/3;

                        // Add row to table model
                        tableModel.addRow(new Object[]{name, regno,avg});

        }}
        
        catch(SQLException d){
           d.printStackTrace();
        }
        catch(Exception d){
           d.printStackTrace();
        }
    }});
       

btn2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e){
    System.exit(0);


}});





        frame1.setVisible(true);
    
        
    }
}
