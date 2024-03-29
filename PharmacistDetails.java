/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medikart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Sankalp
 */
public class PharmacistDetails extends javax.swing.JFrame {

    /**
     * Creates new form PharmacistDetails
     */
    String UserName;
    public PharmacistDetails(String u) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        getContentPane().setBackground(Color.black);
        this.UserName = u;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        t3 = new javax.swing.JTextField();
        cb1 = new javax.swing.JCheckBox();
        t1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Please fill in your registration details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 21, -1, -1));

        jLabel2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 97, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Submit Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 374, -1, -1));

        jLabel3.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Address");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Opening Time ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 251, -1, -1));

        t2.setBackground(new java.awt.Color(0, 0, 0));
        t2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(t2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 240, 100));

        t4.setBackground(new java.awt.Color(0, 0, 0));
        t4.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(t4, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 289, 240, -1));

        jLabel5.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Closing Time");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 299, -1, -1));

        t3.setBackground(new java.awt.Color(0, 0, 0));
        t3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(t3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 240, -1));

        cb1.setBackground(new java.awt.Color(0, 0, 0));
        cb1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        cb1.setForeground(new java.awt.Color(255, 255, 255));
        cb1.setText("COD");
        jPanel1.add(cb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 332, -1, -1));

        t1.setBackground(new java.awt.Color(0, 0, 0));
        t1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(t1, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 97, 240, -1));

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                String name = t1.getText();
        String addr = t2.getText();
        int ot = Integer.parseInt(t3.getText());
        int ct = Integer.parseInt(t4.getText());
        int cod=0;
        if (cb1.isSelected())
        {
            cod=1;
        }
        Connection conn = null;

        try{
                    if (name.equals("")){
                        JOptionPane.showMessageDialog(this, "Name can't be blank!");
                        return;
                    }
                    int maxID = 0;
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql = "insert into pharmacist(Name, Address, openingTime, closingTime, COD) values"
                    + "('"+(name)+"', '"+(addr)+"', "+(ot)+", "+(ct)+", "+(cod)+")";
            stmt.executeUpdate(sql);
            /*sql = "select max(pharmaID) from pharmacist";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                maxID = rs.getInt("max(pharmaID)");
            }
            String role = "Pharmacist";
            sql = "insert into userrelations values ('"+(this.UserName)+"', '"+(role)+"', "+(maxID)+")";
            stmt.executeUpdate(sql);*/
            sql="CALL AddUR('"+this.UserName+"','Pharmacist');";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Registration Completed!");
            this.dispose();
            new Welcome().setVisible(true);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(PharmacistDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PharmacistDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PharmacistDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PharmacistDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PharmacistDetails("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cb1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    // End of variables declaration//GEN-END:variables
}
