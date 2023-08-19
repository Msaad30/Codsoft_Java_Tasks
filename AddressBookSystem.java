/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author M.saad
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class AddressBookSystem extends javax.swing.JFrame {

    /**
     * Creates new form AddressBookSystem
     */
    public AddressBookSystem() {
        initComponents();
        getId();    //auto id genrator
        showTable();    //table
    }
    
    // Auto Id Genrater
    int lastId;
    public void getId()
    {
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","");  
            Statement st = con.createStatement();
            String sql = "select max(id) from clients";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
            {
                lastId = rs.getInt(1);
                lastId++ ;
                idtxt.setText(Integer.toString(lastId));
            }
            showTable();
   
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(this,e);
        }
    }
    
    // Showing Table
    private void showTable()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","");
            String sql = "select * from clients";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            t.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            
        }
    }
    
    //Validation
    public boolean validation()
    {
       String Id, Name, Address, Mobile, Mail, Age;
       
        Id = searchid.getText();
        Name = name.getText();
        Address = add.getText();
        Mobile = mobile.getText();
        Mail = mail.getText();
        Age = age.getText();
        
        if(Name.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Plase enter Name");
             return false;
        }
        if(Address.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Plase enter Address");
             return false;
        }
        if(Mobile.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Plase enter Mobile number");
         return false;
        }
          
        Pattern p = Pattern.compile("^[7-9][0-9]*$");
        Matcher m = p.matcher(Mobile);
           if(!m.matches())
        {
            JOptionPane.showMessageDialog(null,"InValid Contact Number");
            return false;
        }
          
        if((Mobile.length() == 10) == false)
        {
            JOptionPane.showMessageDialog(null,"InValid Contact Number");
            return false;
        }
        
        if(Mail.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Plase enter Email Id");
         return false;
        }
          if(Age.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Plase enter Age");
         return false;
        }
         return true;        
    }
    
    //insert function
    public void insertRecord()
    {
        String Id, Name, Address, Mobile, Mail, Age, C_Date;
        
        Id = idtxt.getText();
        Name = name.getText();
        Address = add.getText();
        Mobile = mobile.getText();
        Mail = mail.getText();
        Age = age.getText();
        
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        C_Date = d.format(cdate.getDate());
        
        getId();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","");  
            Statement st = con.createStatement();
            String sql = "insert into clients values("+Id+",'"+C_Date+"','"+Name+"','"+Address+"','"+Mobile+"','"+Mail+"',"+Age+")";
            st.execute(sql);
            showTable();
            JOptionPane.showMessageDialog(this,"Client Detail saved");
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(this,"Sorry Detail not saved because "+e);
        }
    }
    
    // Update function
    public void updateRecord()
    {
        String Id, C_Date, Name, Address, Mobile, Mail, Age;
        Id = searchid.getText();
        Name = name.getText();
        Address = add.getText();
        Mobile = mobile.getText();
        Mail = mail.getText();
        Age = age.getText();
        
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        C_Date = d.format(cdate.getDate());
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","");
            String sql = "update clients set id = ?, date = ?, name = ?, address = ?, mobile = ?, email = ?, age= ? where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(Id));
            ps.setString(2, C_Date);
            ps.setString(3, Name);
            ps.setString(4, Address);
            ps.setString(5, Mobile);
            ps.setString(6, Mail);
            ps.setInt(7, Integer.parseInt(Age));
            ps.setInt(8, Integer.parseInt(Id));
            ps.executeUpdate();  
            JOptionPane.showMessageDialog(this,"Record have updeted");
            showTable();
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Record not updeted because "+e);
        } 
    }
    
    public void search()
    {
        String Id, Name, Address, Mobile, Mail, Age;
        
        Id = searchid.getText();
        Name = name.getText();
        Address = add.getText();
        Mobile = mobile.getText();
        Mail = mail.getText();
        Age = age.getText();
        

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","");
            String sql = "select * from clients where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(Id));
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                idtxt.setText(rs.getString("id"));
                cdate.setDate(rs.getDate("Date"));
                name.setText(rs.getString("name"));
                add.setText(rs.getString("address"));
                mobile.setText(rs.getString("mobile"));
                mail.setText(rs.getString("email"));
                age.setText(rs.getString("age"));
                JOptionPane.showMessageDialog(this,"Record found");
            }
            else  JOptionPane.showMessageDialog(this,"Sorry Detail not found");
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Sorry Detail not found because ");
        }
    }
    
    // clear function
    public void clear()
    {
        cdate.setDate(null);
        searchid.setText("");
        name.setText("");
        add.setText("");
        mobile.setText("");
        mail.setText("");
        age.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        idtxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        mobile = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        age = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        add = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        cdate = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        SaveButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        UpdateButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t = new javax.swing.JTable();
        sid = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        searchid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idtxt.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        idtxt.setToolTipText("");
        idtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtxtActionPerformed(evt);
            }
        });
        jPanel2.add(idtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 80, 40));

        jLabel5.setFont(new java.awt.Font("Javanese Text", 1, 18)); // NOI18N
        jLabel5.setText("Mobile No");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 120, -1));

        jLabel6.setFont(new java.awt.Font("Javanese Text", 1, 18)); // NOI18N
        jLabel6.setText("Address");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 120, -1));

        jLabel3.setFont(new java.awt.Font("Javanese Text", 1, 18)); // NOI18N
        jLabel3.setText("Email");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 100, 40));

        name.setFont(new java.awt.Font("Javanese Text", 0, 18)); // NOI18N
        name.setToolTipText("");
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        jPanel2.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 320, 40));

        jLabel4.setFont(new java.awt.Font("Javanese Text", 1, 18)); // NOI18N
        jLabel4.setText("Name");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 110, -1));

        mobile.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        mobile.setToolTipText("");
        mobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobileActionPerformed(evt);
            }
        });
        jPanel2.add(mobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 180, 40));

        mail.setFont(new java.awt.Font("Javanese Text", 0, 18)); // NOI18N
        mail.setToolTipText("");
        mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailActionPerformed(evt);
            }
        });
        jPanel2.add(mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 180, 40));

        age.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        age.setToolTipText("");
        age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageActionPerformed(evt);
            }
        });
        jPanel2.add(age, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 90, 40));

        jLabel10.setFont(new java.awt.Font("Javanese Text", 1, 18)); // NOI18N
        jLabel10.setText("Age");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 110, -1));

        add.setColumns(20);
        add.setFont(new java.awt.Font("Javanese Text", 0, 18)); // NOI18N
        add.setRows(5);
        jScrollPane2.setViewportView(add);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 320, 120));

        jLabel13.setFont(new java.awt.Font("Javanese Text", 1, 18)); // NOI18N
        jLabel13.setText("Date");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 70, 40));
        jPanel2.add(cdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 150, 40));

        jLabel14.setFont(new java.awt.Font("Javanese Text", 1, 18)); // NOI18N
        jLabel14.setText("Id");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 470, 410));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        SaveButton.setBackground(new java.awt.Color(0, 255, 0));
        SaveButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SaveButton.setForeground(new java.awt.Color(255, 255, 255));
        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        ClearButton.setBackground(new java.awt.Color(51, 51, 51));
        ClearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ClearButton.setForeground(new java.awt.Color(255, 255, 255));
        ClearButton.setText("Clear");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        UpdateButton.setBackground(new java.awt.Color(255, 0, 0));
        UpdateButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        UpdateButton.setText("Update");
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpdateButton)
                .addGap(18, 18, 18)
                .addComponent(ClearButton)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClearButton)
                    .addComponent(SaveButton)
                    .addComponent(UpdateButton))
                .addGap(19, 19, 19))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 340, 70));

        jLabel9.setBackground(new java.awt.Color(255, 51, 102));
        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\data for project\\small Png\\—Pngtree—vector add user icon_4101360.png")); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        t.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(t);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 540, 410));

        sid.setFont(new java.awt.Font("Javanese Text", 1, 18)); // NOI18N
        sid.setText("Search ");
        sid.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sid.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                sidAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        sid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidMouseClicked(evt);
            }
        });
        sid.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                sidVetoableChange(evt);
            }
        });
        jPanel1.add(sid, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 120, -1));

        panel.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, -1, 50));

        searchid.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        searchid.setToolTipText("");
        searchid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchidActionPerformed(evt);
            }
        });
        searchid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchidKeyReleased(evt);
            }
        });
        jPanel1.add(searchid, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 120, 34));

        jLabel1.setFont(new java.awt.Font("Javanese Text", 1, 36)); // NOI18N
        jLabel1.setText("Address Booking");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtxtActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void mobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileActionPerformed

    private void mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mailActionPerformed

    private void ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed

        if (validation()==true)
        {
            insertRecord();
            clear();
            getId();
        }
        
        
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed

        clear();
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        // TODO add your handling code here:

        int response = JOptionPane.showConfirmDialog(this," Do you want Update it ", "Update" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            updateRecord();
        }
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void sidAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sidAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_sidAncestorAdded

    private void sidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidMouseClicked
        // TODO add your handling code here:

        if(sid.getText() == "Search Id")
        {
            panel.setVisible(true);
            sid.setText("Search");
        }
        else  {
            panel.setVisible(false);
            sid.setText("Enter Id");
        }
    }//GEN-LAST:event_sidMouseClicked

    private void sidVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_sidVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_sidVetoableChange

    private void searchidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchidActionPerformed

    private void searchidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchidKeyReleased
        // TODO add your handling code here:
        
        search();
        
    }//GEN-LAST:event_searchidKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressBookSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddressBookSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddressBookSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddressBookSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddressBookSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JTextArea add;
    private javax.swing.JTextField age;
    private com.toedter.calendar.JDateChooser cdate;
    private javax.swing.JTextField idtxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField mobile;
    private javax.swing.JTextField name;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField searchid;
    private javax.swing.JLabel sid;
    private javax.swing.JTable t;
    // End of variables declaration//GEN-END:variables
}
