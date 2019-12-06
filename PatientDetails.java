/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medikart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Sankalp
 */
public class PatientDetails extends javax.swing.JFrame {
    String UserName;
    /**
     * Creates new form PatientDetails
     */
    public PatientDetails(String u) {
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        c1 = new javax.swing.JComboBox<>();
        t2 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        t3 = new javax.swing.JTextField();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel3.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sex");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Age");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 183, -1, -1));

        jLabel5.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Height");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 226, -1, -1));

        jLabel6.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Weight");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 262, -1, -1));

        jLabel7.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Insurance");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 307, -1, -1));

        t1.setBackground(new java.awt.Color(0, 0, 0));
        t1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(t1, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 92, 259, -1));

        c1.setBackground(new java.awt.Color(0, 0, 0));
        c1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c1.setForeground(new java.awt.Color(255, 255, 255));
        c1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        jPanel1.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 135, 108, -1));

        t2.setBackground(new java.awt.Color(0, 0, 0));
        t2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(t2, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 178, 259, -1));

        t4.setBackground(new java.awt.Color(0, 0, 0));
        t4.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(t4, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 257, 259, -1));

        t3.setBackground(new java.awt.Color(0, 0, 0));
        t3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(t3, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 221, 259, -1));

        rb1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        rb1.setForeground(new java.awt.Color(255, 255, 255));
        rb1.setText("Yes");
        jPanel1.add(rb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 300, -1, -1));

        rb2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        rb2.setForeground(new java.awt.Color(255, 255, 255));
        rb2.setText("No");
        jPanel1.add(rb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 300, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Submit Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 346, 148, -1));

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 418, 404));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String name = t1.getText();
        int insurance = 0;
        String sex = c1.getSelectedItem().toString();
        int age = Integer.parseInt(t2.getText());
        int height = Integer.parseInt(t3.getText());
        int weight = Integer.parseInt(t4.getText());
        Connection conn = null;

        try{
                    if (name.equals("")){
                        JOptionPane.showMessageDialog(this, "Name can't be blank!");
                        return;
                    }
                            if (rb1.isSelected())
        {
            insurance = 1;
        }
        else if (rb2.isSelected())
        {
            insurance = 0;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please clarify your insurance status.");
            return;
        }
                    int maxID = 0;
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql = "insert into patient(Name, Sex, Age, Height, Weight, Insurance) values"
                    + "('"+(name)+"', '"+(sex)+"', "+(age)+", "+(height)+", "+(weight)+", "+(insurance)+")";
            stmt.executeUpdate(sql);
            sql = "select max(patientID) from patient";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                maxID = rs.getInt("max(patientID)");
            }
            /*String role = "Patient";
            sql = "insert into userrelations values ('"+(this.UserName)+"', '"+(role)+"', "+(maxID)+")";
            stmt.executeUpdate(sql);*/
            sql="CALL AddUR('"+this.UserName+"','Patient');";
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
            java.util.logging.Logger.getLogger(PatientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientDetails("maithili").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> c1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    // End of variables declaration//GEN-END:variables
}