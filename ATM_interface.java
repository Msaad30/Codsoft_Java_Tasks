/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author M.saad
 */
public class ATM_interface extends javax.swing.JFrame {

    /**
     * Creates new form ATM_interface
     */
    public ATM_interface() {
        initComponents();
    }
    
    int balance = 20000;
    int withdrow = 0;
    int deposit = 0;
    
    public void withdrow()
    {
        
        int withdrow = Integer.parseInt(text.getText());
        
        balance = balance - withdrow;
        
        label1.setText("After withdrow your balance is : "+balance);
    }
    
    public void deposit()
    {
        
        int deposit = Integer.parseInt(text.getText());
        
        balance = balance + deposit;
        
        label1.setText("After deposit your balance is : "+balance);
    }
    
    public void balance()
    {
        label1.setText("Total Balance is : "+balance);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        UpdateButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        text = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        ClearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UpdateButton.setBackground(new java.awt.Color(255, 0, 0));
        UpdateButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        UpdateButton.setText("Deposit");
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });
        jPanel2.add(UpdateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 170, 41));

        SaveButton.setBackground(new java.awt.Color(0, 255, 0));
        SaveButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SaveButton.setForeground(new java.awt.Color(255, 255, 255));
        SaveButton.setText("Withdrow");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });
        jPanel2.add(SaveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 170, 39));

        text.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        text.setToolTipText("");
        text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textActionPerformed(evt);
            }
        });
        jPanel2.add(text, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 400, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 450, 150));

        jLabel1.setFont(new java.awt.Font("Javanese Text", 1, 36)); // NOI18N
        jLabel1.setText("ATM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, 60));

        label1.setFont(new java.awt.Font("Javanese Text", 1, 24)); // NOI18N
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 450, 50));

        ClearButton.setBackground(new java.awt.Color(51, 51, 51));
        ClearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ClearButton.setForeground(new java.awt.Color(255, 255, 255));
        ClearButton.setText("Check Balance");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ClearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 160, 41));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        
        withdrow();

    }//GEN-LAST:event_SaveButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed

        balance();
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        deposit();
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textActionPerformed

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
            java.util.logging.Logger.getLogger(ATM_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ATM_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ATM_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ATM_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ATM_interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label1;
    private javax.swing.JTextField text;
    // End of variables declaration//GEN-END:variables
}
