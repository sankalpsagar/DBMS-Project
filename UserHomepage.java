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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sankalp
 */
public class UserHomepage extends javax.swing.JFrame {

    /**
     * Creates new form UserHomepage
     */
            String role = "";
    String UserName;
    public UserHomepage(String u) {
        this.UserName = u;
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        getContentPane().setBackground(Color.black);
        l2.setVisible(false);

        Connection conn = null;
        int s = 0;
        String sex ="";
        t4.setEditable(false);
        
        try {
        
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select * from users, userrelations, patient where users.userID = userrelations.userId"
                + " and userrelations.roleID = patient.patientID and users.userid = '"+(this.UserName)+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            l5.setText("Name: "+rs.getString("Name"));
            sex =rs.getString("Sex");
            l7.setText("Age: "+rs.getString("Age"));
            l8.setText("Height: "+rs.getString("Height")+"cm");
            l9.setText("Weight: "+rs.getString("Weight")+"kg");
             s = rs.getInt("Insurance");}
            if (s == 0)
            {
                l10.setText("Insurance Status: No Insurance");
            }
            else
            {
                l10.setText("Insurance Status: Insurance Availale");
            }
            if (sex.equals("M"))
            {
                jLabel3.setVisible(false);
                l6.setText("Sex: Male");
            }
            else
            {
                jLabel5.setVisible(false);
                l6.setText("Sex: Female");
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
        conn = null;
        try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select distinct symptom1 from symptoms";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            c1.addItem(rs.getString("symptom1"));
        }
        sql = "select distinct symptom2 from symptoms";
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            c2.addItem(rs.getString("symptom2"));
        }
        sql = "select distinct symptom3 from symptoms";
        rs = stmt.executeQuery(sql);
        while (rs.next()){
            c3.addItem(rs.getString("symptom3"));
        }
        c4.removeAllItems();
        sql = "select distinct name from pharmacist";
        rs = stmt.executeQuery(sql);
        while (rs.next()){
            c4.addItem(rs.getString("name"));
        }
        sql = "select userid from users where userid!='"+(this.UserName)+"'";
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            c8.addItem(rs.getString("userid"));
        }
        sql = "select * from prescription where patientID = "
                + "(Select patientID from patient, userrelations, users where users.userid = userrelations.userID"
                + " and userrelations.roleID = patient.patientID and userrelations.role = 'Patient' and users.userid ="
                + " '"+(this.UserName)+"')"
                + " and cycleEnd < sysdate() and "
                + "active='1';";
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            JOptionPane.showMessageDialog(this, "Medicine Cycle ended! For prescription ID: "+rs.getString("PrescriptionID"));
        }
        sql = "update prescription set active=0 where patientID in "
                + "(Select patientID from patient, userrelations, users where users.userid = userrelations.userID"
                + " and userrelations.roleID = patient.patientID and userrelations.role = 'Patient' and users.userid ="
                + " '"+(this.UserName)+"')"
                + " and cycleEnd < sysdate() and "
                + "active='1';";
        stmt.executeUpdate(sql);
        sql = "select distinct prescription.prescriptionID from prescription, prescriptionmeds, medicine, doctor,"
                + " patient where prescription.prescriptionID = prescriptionmeds.prescriptionID and prescriptionmeds.medID "
                + "= medicine.medID and doctor.doctorID = prescription.doctorID and "
                + "patient.patientID = (select patientID from patient, userrelations, users where patient.patientID = "
                + "userrelations.roleID and userrelations.role = 'Patient' and userrelations.userid = users.userid "
                + "and users.userid='"+(this.UserName)+"')";
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            c9.addItem(rs.getInt("PrescriptionID")+"");
        }
        sql = "select * from appointments, doctor where appointments.patientID = "
                + "(Select patientID from patient, userrelations, users where users.userid = userrelations.userID"
                + " and userrelations.roleID = patient.patientID and userrelations.role = 'Patient' and users.userid ="
                + " '"+(this.UserName)+"')"
                + " and appointments.doctorID = doctor.doctorID";
        rs = stmt.executeQuery(sql);
        DefaultListModel lb = (DefaultListModel) jList3.getModel();
        lb.removeAllElements();
        if(rs.next()) {
            lb.addElement(rs.getString("doctor.Name"));
            jList3.setModel(lb);
        }
        rs.close();
        DefaultListModel lb1 = (DefaultListModel) jList1.getModel();
        lb1.removeAllElements();
        sql = "Select * from doctor";
        rs = stmt.executeQuery(sql);
        while(rs.next()) {

            lb1.addElement(rs.getString("doctor.Name"));
            jList1.setModel(lb1);
        }
        c6.removeAllItems();
        c7.removeAllItems();
        sql = "select distinct fID from messages where tID = '"+(this.UserName)+"'";
        rs=stmt.executeQuery(sql);
        
        while(rs.next()) {
            c6.addItem(rs.getString("fID"));
        }
        sql = "select distinct tID from messages where fID = '"+(this.UserName)+"'";
        rs=stmt.executeQuery(sql);
        while(rs.next()) {
            c7.addItem(rs.getString("tID"));
        }
        rs.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        l10 = new javax.swing.JLabel();
        l9 = new javax.swing.JLabel();
        l8 = new javax.swing.JLabel();
        l7 = new javax.swing.JLabel();
        l6 = new javax.swing.JLabel();
        l5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelx = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        c1 = new javax.swing.JComboBox<>();
        c2 = new javax.swing.JComboBox<>();
        c3 = new javax.swing.JComboBox<>();
        l1 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        c4 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        c5 = new javax.swing.JComboBox<>();
        AddCart = new javax.swing.JButton();
        qtyT = new javax.swing.JTextField();
        RemoveCart = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        t4 = new javax.swing.JTextField();
        AddPrescription = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Price = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        t5 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        c6 = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        ta1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        ta2 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        c7 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        c8 = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        ta3 = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        c9 = new javax.swing.JComboBox<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        c11 = new javax.swing.JComboBox<>();
        c12 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        c13 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton10 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resc/male.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -40, 270, 260));

        l10.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        l10.setForeground(new java.awt.Color(0, 0, 0));
        l10.setText("Insurance Status:");
        jPanel1.add(l10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 340, 16));

        l9.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        l9.setForeground(new java.awt.Color(0, 0, 0));
        l9.setText("Weight:");
        jPanel1.add(l9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 360, 16));

        l8.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        l8.setForeground(new java.awt.Color(0, 0, 0));
        l8.setText("Height:");
        jPanel1.add(l8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 370, 16));

        l7.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        l7.setForeground(new java.awt.Color(0, 0, 0));
        l7.setText("Age:");
        jPanel1.add(l7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 370, 16));

        l6.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        l6.setForeground(new java.awt.Color(0, 0, 0));
        l6.setText("Sex:");
        jPanel1.add(l6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 340, 16));

        l5.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        l5.setForeground(new java.awt.Color(0, 0, 0));
        l5.setText("Name:");
        jPanel1.add(l5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 360, 16));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Your Profile");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 350, 100));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resc/female.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 160));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resc/Profile.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 520));

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane1.addTab("Profile", jScrollPane1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(null);

        jList1.setBackground(new java.awt.Color(0, 0, 0));
        jList1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jList1.setForeground(new java.awt.Color(255, 255, 255));
        jList1.setModel(new DefaultListModel());
        jScrollPane3.setViewportView(jList1);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(6, 220, 596, 177);

        jList3.setBackground(new java.awt.Color(0, 0, 0));
        jList3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jList3.setForeground(new java.awt.Color(255, 255, 255));
        jList3.setModel(new DefaultListModel());
        jScrollPane5.setViewportView(jList3);

        jPanel2.add(jScrollPane5);
        jScrollPane5.setBounds(6, 44, 596, 115);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Past Bookings");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(6, 21, 102, 17);

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Book Again");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(499, 165, 103, 33);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Book");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(506, 403, 96, 33);

        jScrollPane2.setViewportView(jPanel2);

        jTabbedPane1.addTab("Appointments", jScrollPane2);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel3FocusGained(evt);
            }
        });
        jPanel3.setLayout(null);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("What Symptoms are you showing?");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(44, 23, 250, 17);

        jLabel12.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Symptom 1:");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(161, 61, 80, 17);

        labelx.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        labelx.setForeground(new java.awt.Color(255, 255, 255));
        labelx.setText("Symptom 2:");
        jPanel3.add(labelx);
        labelx.setBounds(161, 115, 80, 17);

        l3.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        l3.setForeground(new java.awt.Color(255, 255, 255));
        l3.setText("Symptom 3:");
        jPanel3.add(l3);
        l3.setBounds(164, 168, 80, 17);

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Check Diagnosis");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);
        jButton5.setBounds(305, 207, 140, 33);

        c1.setBackground(new java.awt.Color(0, 0, 0));
        c1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(c1);
        c1.setBounds(337, 57, 108, 25);

        c2.setBackground(new java.awt.Color(0, 0, 0));
        c2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(c2);
        c2.setBounds(337, 111, 108, 25);

        c3.setBackground(new java.awt.Color(0, 0, 0));
        c3.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(c3);
        c3.setBounds(337, 164, 108, 25);

        l1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        l1.setForeground(new java.awt.Color(255, 255, 255));
        l1.setText("Disease Name");
        jPanel3.add(l1);
        l1.setBounds(210, 270, 200, 17);

        l2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        l2.setForeground(new java.awt.Color(255, 255, 255));
        l2.setText("Recommended Medicine:");
        jPanel3.add(l2);
        l2.setBounds(130, 360, 450, 17);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Check Medicine");
        jButton1.setPreferredSize(new java.awt.Dimension(125, 32));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(310, 310, 140, 32);

        jScrollPane4.setViewportView(jPanel3);

        jTabbedPane1.addTab("Diagnose", jScrollPane4);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(null);

        jLabel16.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Pharmacist:");
        jPanel5.add(jLabel16);
        jLabel16.setBounds(137, 75, 80, 17);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Order Form");
        jPanel5.add(jLabel17);
        jLabel17.setBounds(229, 21, 160, 29);

        c4.setBackground(new java.awt.Color(0, 0, 0));
        c4.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c4.setForeground(new java.awt.Color(255, 255, 255));
        c4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4ActionPerformed(evt);
            }
        });
        jPanel5.add(c4);
        c4.setBounds(261, 71, 131, 25);

        jLabel18.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Medicine:");
        jPanel5.add(jLabel18);
        jLabel18.setBounds(136, 130, 70, 17);

        c5.setBackground(new java.awt.Color(0, 0, 0));
        c5.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c5.setForeground(new java.awt.Color(255, 255, 255));
        c5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5ActionPerformed(evt);
            }
        });
        jPanel5.add(c5);
        c5.setBounds(261, 121, 131, 25);

        AddCart.setBackground(new java.awt.Color(0, 0, 0));
        AddCart.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        AddCart.setForeground(new java.awt.Color(255, 255, 255));
        AddCart.setText("Add to Cart");
        AddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCartActionPerformed(evt);
            }
        });
        jPanel5.add(AddCart);
        AddCart.setBounds(330, 164, 110, 33);
        jPanel5.add(qtyT);
        qtyT.setBounds(441, 121, 31, 24);

        RemoveCart.setBackground(new java.awt.Color(0, 0, 0));
        RemoveCart.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        RemoveCart.setForeground(new java.awt.Color(255, 255, 255));
        RemoveCart.setText("Remove from Cart");
        RemoveCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveCartActionPerformed(evt);
            }
        });
        jPanel5.add(RemoveCart);
        RemoveCart.setBounds(135, 164, 150, 33);

        jLabel19.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Qty");
        jPanel5.add(jLabel19);
        jLabel19.setBounds(404, 125, 30, 17);

        jLabel20.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Prescription ID:");
        jPanel5.add(jLabel20);
        jLabel20.setBounds(120, 218, 100, 17);

        t4.setBackground(new java.awt.Color(0, 0, 0));
        t4.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(t4);
        t4.setBounds(210, 480, 125, 25);

        AddPrescription.setBackground(new java.awt.Color(0, 0, 0));
        AddPrescription.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        AddPrescription.setForeground(new java.awt.Color(255, 255, 255));
        AddPrescription.setText("Show Medicines in Prescription");
        AddPrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPrescriptionActionPerformed(evt);
            }
        });
        jPanel5.add(AddPrescription);
        AddPrescription.setBounds(173, 256, 250, 33);

        jButton8.setBackground(new java.awt.Color(0, 0, 0));
        jButton8.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Checkout");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);
        jButton8.setBounds(388, 476, 100, 33);

        Price.setBackground(new java.awt.Color(0, 0, 0));
        Price.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        Price.setForeground(new java.awt.Color(255, 255, 255));
        Price.setText("Total Price:");
        jPanel5.add(Price);
        Price.setBounds(120, 484, 100, 17);

        tb1.setBackground(new java.awt.Color(0, 0, 0));
        tb1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        tb1.setForeground(new java.awt.Color(255, 255, 255));
        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Quantity", "Drug"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tb1);

        jPanel5.add(jScrollPane6);
        jScrollPane6.setBounds(100, 300, 410, 150);

        t5.setBackground(new java.awt.Color(0, 0, 0));
        t5.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(t5);
        t5.setBounds(264, 214, 125, 25);

        jScrollPane9.setViewportView(jPanel5);

        jTabbedPane1.addTab("Medicines", jScrollPane9);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel4FocusGained(evt);
            }
        });
        jPanel4.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel6FocusGained(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("From:");

        c6.setBackground(new java.awt.Color(0, 0, 0));
        c6.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        c6.setForeground(new java.awt.Color(255, 255, 255));
        c6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                c6ItemStateChanged(evt);
            }
        });
        c6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c6ActionPerformed(evt);
            }
        });

        ta1.setBackground(new java.awt.Color(0, 0, 0));
        ta1.setColumns(20);
        ta1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        ta1.setForeground(new java.awt.Color(255, 255, 255));
        ta1.setRows(5);
        jScrollPane8.setViewportView(ta1);

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                                .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(71, 71, 71))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Inbox", jPanel6);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel7FocusGained(evt);
            }
        });

        ta2.setBackground(new java.awt.Color(0, 0, 0));
        ta2.setColumns(20);
        ta2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        ta2.setForeground(new java.awt.Color(255, 255, 255));
        ta2.setRows(5);
        jScrollPane11.setViewportView(ta2);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("To:");

        c7.setBackground(new java.awt.Color(0, 0, 0));
        c7.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        c7.setForeground(new java.awt.Color(255, 255, 255));
        c7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c7ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Refresh");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane11)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                                .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(71, 71, 71))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Outbox", jPanel7);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Send To:");

        c8.setBackground(new java.awt.Color(0, 0, 0));
        c8.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        c8.setForeground(new java.awt.Color(255, 255, 255));
        c8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c8ActionPerformed(evt);
            }
        });

        ta3.setBackground(new java.awt.Color(0, 0, 0));
        ta3.setColumns(20);
        ta3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        ta3.setForeground(new java.awt.Color(255, 255, 255));
        ta3.setRows(5);
        ta3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ta3CaretUpdate(evt);
            }
        });
        ta3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ta3FocusGained(evt);
            }
        });
        ta3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ta3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                ta3InputMethodTextChanged(evt);
            }
        });
        ta3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ta3PropertyChange(evt);
            }
        });
        jScrollPane12.setViewportView(ta3);

        jButton9.setBackground(new java.awt.Color(0, 0, 0));
        jButton9.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Send");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(454, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane12)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                            .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(89, 89, 89)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(434, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(27, 27, 27))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(44, 44, 44)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(108, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Send Message", jPanel8);

        jPanel4.add(jTabbedPane2);
        jTabbedPane2.setBounds(0, 0, 620, 520);

        jScrollPane7.setViewportView(jPanel4);

        jTabbedPane1.addTab("Messages", jScrollPane7);

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Choose Prescription ID:");

        c9.setBackground(new java.awt.Color(0, 0, 0));
        c9.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        c9.setForeground(new java.awt.Color(255, 255, 255));
        c9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c9ActionPerformed(evt);
            }
        });

        tb2.setBackground(new java.awt.Color(0, 0, 0));
        tb2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        tb2.setForeground(new java.awt.Color(255, 255, 255));
        tb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name", "Medicine Drug", "Cycle End", "DoctorName"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tb2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(c9, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(c9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Prescription", jPanel9);

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setLayout(null);

        c11.setBackground(new java.awt.Color(0, 0, 0));
        c11.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        c11.setForeground(new java.awt.Color(255, 255, 255));
        c11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "4", "3", "2", "1" }));
        c11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c11ActionPerformed(evt);
            }
        });
        jPanel10.add(c11);
        c11.setBounds(550, 190, 55, 27);

        c12.setBackground(new java.awt.Color(0, 0, 0));
        c12.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        c12.setForeground(new java.awt.Color(255, 255, 255));
        c12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor", "Pharmacist", "Hospital" }));
        c12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c12ActionPerformed(evt);
            }
        });
        jPanel10.add(c12);
        c12.setBounds(350, 80, 258, 27);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Select category:");
        jPanel10.add(jLabel13);
        jLabel13.setBounds(50, 70, 210, 50);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Select name:");
        jPanel10.add(jLabel14);
        jLabel14.setBounds(50, 120, 220, 40);

        c13.setBackground(new java.awt.Color(0, 0, 0));
        c13.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        c13.setForeground(new java.awt.Color(255, 255, 255));
        c13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c13ActionPerformed(evt);
            }
        });
        jPanel10.add(c13);
        c13.setBounds(350, 130, 258, 27);

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Rating:");
        jPanel10.add(jLabel15);
        jLabel15.setBounds(480, 180, 60, 40);

        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jScrollPane13.setViewportView(jTextArea1);

        jPanel10.add(jScrollPane13);
        jScrollPane13.setBounds(60, 240, 530, 209);

        jButton10.setBackground(new java.awt.Color(0, 0, 0));
        jButton10.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Submit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton10);
        jButton10.setBounds(441, 470, 120, 33);

        jTabbedPane1.addTab("Reviews", jPanel10);

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Log Out");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Are you sure?");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel10)))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Logout", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       l2.setVisible(true);
       String dis = l1.getText();
        Connection conn = null;
        try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select medicine.Name from symptoms, disease, medicine where symptoms.diseaseID = disease.diseaseID"
                + " and symptoms.medRecommended = medicine.medID and disease.Name = '"+(dis)+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        if(rs.next()) {
            l2.setText("Recommended Medicine: "+rs.getString("Name"));
        }
        else{
            l2.setText ("No suitable medicine found. Please contact the nearest doctor.");
        }
        
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4FocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        c6.removeAllItems();
        try {
        
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select distinct fID from messages where tID = '"+(this.UserName)+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            c6.addItem(rs.getString("fID"));
        }}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }         
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void c6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c6ActionPerformed
        // TODO add your handling code here:
        ta1.setEditable(false);
                        Connection conn = null;
                ta1.setText("");
        try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select timeStamp, Text from messages where tID = '"+(this.UserName)+"' && fID = "
                + "'"+(c6.getSelectedItem().toString())+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            String time = rs.getString("timeStamp");
            String text = rs.getString("Text");
            ta1.append("\nTime: "+ time);
            ta1.append(" "+text);
        }}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }         
    }//GEN-LAST:event_c6ActionPerformed

    private void c6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c6ItemStateChanged
        // TODO add your handling code here:
          
    }//GEN-LAST:event_c6ItemStateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here: 
        Connection conn = null;

        try {
        
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        c7.removeAllItems();
        String sql = "select distinct tID from messages where fID = '"+(this.UserName)+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            c7.addItem(rs.getString("tID"));
        }}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }   
    }//GEN-LAST:event_jButton7ActionPerformed

    private void c7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c7ActionPerformed
        // TODO add your handling code here:
                                Connection conn = null;
                ta2.setText("");
                ta2.setEditable(false);
        try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select timeStamp, Text from messages where fID = '"+(this.UserName)+"' && tID = "
                + "'"+(c7.getSelectedItem().toString())+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            String time = rs.getString("timeStamp");
            String text = rs.getString("Text");
            ta2.append("\nTime: "+ time);
            ta2.append(" "+text);
        }}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
    }//GEN-LAST:event_c7ActionPerformed

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel3FocusGained

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String sym1 = c1.getSelectedItem().toString();
        String sym2 = c2.getSelectedItem().toString();
        String sym3 = c3.getSelectedItem().toString();
        Connection conn = null;
        try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select disease.Name from symptoms, disease where symptoms.diseaseID = disease.diseaseID and "
                + "symptom1 = '"+(sym1)+"' and symptom2 = '"+(sym2)+"' and symptom3 = '"+(sym3)+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        if(rs.next()) {
            l1.setText(rs.getString("Name"));
        }
        else{
            l1.setText ("No disease found.");
        }
        
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tb = (DefaultTableModel) tb1.getModel();
        int n = tb1.getRowCount();
        int orderid = 0;
        int total;
        try {
            Connection conn = null;
            if (t4.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Cart Empty.");
                return;
            }
            else{
                total = Integer.parseInt(t4.getText());
            }
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql = "insert into orders (patientID, pharmaID, totalPrice, ordDate, arrDate, status) values ("
            + " (select patientID from patient, userrelations where userrelations.userID = '"+(this.UserName)+"' and role="
            + " 'patient' and patient.patientID = userrelations.roleID), "
            + "(Select pharmaID from pharmacist where Name= '"+(c4.getSelectedItem().toString())+"'), "
            + " "+(total)+", date(sysdate()), curdate() + INTERVAL 7 DAY, 'Pending')";
            stmt.executeUpdate(sql);
            sql = "select max(orderid) from orders";
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                            orderid = rs.getInt("max(orderid)");
                        }
            for(int i = 0; i < n; i++){
                
                     
                
                String Name = tb1.getValueAt(i, 0).toString();
                int price = Integer.parseInt(tb1.getValueAt(i, 1).toString());
                int qty = Integer.parseInt(tb1.getValueAt(i, 2).toString());
                sql="select medID from medicine where name='"+Name+"';";
                rs=stmt.executeQuery(sql);
                int mid=0;
                while(rs.next()){
                    mid = rs.getInt("medID");
                }
                sql="select pharmaID from pharmacist where Name = '"+(c4.getSelectedItem().toString())+"';";
              rs=stmt.executeQuery(sql);
                int phid=0;
                while(rs.next()){
                    phid = rs.getInt("pharmaID");
                }
              
                 
                    
                        
                        sql = "update inventory set quantity = quantity - "+(qty)+" where pharmaID = "
                        + phid+ " and"
                        + " medID = "+mid+";";
                        stmt.executeUpdate(sql);
                        sql = "insert into orderitems values ("+(orderid)+","+ mid+", "+(qty)+");";
                        stmt.executeUpdate(sql);

                        
                   
                    

                    
              
                

            }
            tb.setRowCount(0);
                        tb1.setModel(tb);
                        t5.setText("");
                        qtyT.setText("");
                        c4.setEnabled(true);
                                    JOptionPane.showMessageDialog(this, "Your order is placed! Your orderid is: "+orderid);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void AddPrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPrescriptionActionPerformed
        // TODO add your handling code here:
        
        if(t5.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nothing entered!");
        }
        else{
            int pid = Integer.parseInt(t5.getText());
        Connection conn = null;
        try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "delete from cursorTable";
        stmt.executeUpdate(sql);
        sql = "call InsMed('"+(pid)+"')";
        stmt.executeUpdate(sql);
        sql = "select * from cursorTable";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            JOptionPane.showMessageDialog(this, "Medicine: "+rs.getString("Name")+ "\n Drug: "+rs.getString("Drug"));
        }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
        
        } 
    }//GEN-LAST:event_AddPrescriptionActionPerformed

    private void RemoveCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveCartActionPerformed
        // TODO add your handling code here:
        if (tb1.getRowCount() == 1){
            c4.setEnabled(true);
        }
        int total = Integer.parseInt(t4.getText());
        DefaultTableModel tb = (DefaultTableModel) tb1.getModel();
        int i = tb1.getSelectedRow();
        int col = 1;
        int qty = Integer.parseInt(tb.getValueAt(i, 2).toString());
        int price = Integer.parseInt(tb.getValueAt(i, col).toString());
        total -= qty*price;
        t4.setText(total+"");
        tb.removeRow(i);
        tb1.setModel(tb);
    }//GEN-LAST:event_RemoveCartActionPerformed

    private void AddCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCartActionPerformed
        // TODO add your handling code here:
            c4.setEnabled(false);

        Connection conn = null;
        try {
            DefaultTableModel tb = (DefaultTableModel) tb1.getModel();
            int total;
            if (t4.getText().equals(""))
            {
                total=0;
            }
            else{
                total = Integer.parseInt(t4.getText());}
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql = "select medicine.Name, inventory.Price, medicine.Drug, inventory.quantity from pharmacist, inventory, medicine where pharmacist.pharmaID = inventory.pharmaID"
            + " and inventory.medID = medicine.medID and pharmacist.name = '"+(c4.getSelectedItem().toString())+"'"
            + "and medicine.Name = '"+(c5.getSelectedItem().toString())+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                int qty = Integer.parseInt(qtyT.getText());
                int qtyTot = rs.getInt("quantity");
                if (qty > qtyTot){
                    JOptionPane.showMessageDialog(this, "We ran out of medicines! Max order allowed: "+qtyTot);
                    return;
                }
                String name = rs.getString("name");
                int price = Integer.parseInt(rs.getString("Price"));
                String drug = rs.getString("Drug");
                total += qty * price;
                t4.setText (total+"");
                tb.addRow(new Object[]{name, price, qty, drug});
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_AddCartActionPerformed

    private void c5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c5ActionPerformed

    private void c4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4ActionPerformed
        // TODO add your handling code here:
        c5.removeAllItems();
        String pharma = c4.getSelectedItem().toString();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql = "select medicine.Name from pharmacist, inventory, medicine where pharmacist.pharmaID = inventory.pharmaID"
            + " and inventory.medID = medicine.medID and pharmacist.name = '"+(pharma)+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                c5.addItem(rs.getString("Name"));
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_c4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if ((ta3.getText().length()) > 140){
            JOptionPane.showMessageDialog(this, "Message can't exceed 140 characters!");
        }
        else if (ta3.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Message can't be empty!");
        }
        else {
            Connection conn= null;
             try {
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql = "insert into messages (FID, TID, Text, timeStamp) values ('"+(this.UserName)+"', "
                    + "'"+(c8.getSelectedItem().toString())+"', '"+(ta3.getText())+"', current_timestamp())";
           stmt.executeUpdate(sql);
           JOptionPane.showMessageDialog(this, "Message sent!");
           ta3.setText("");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        }
        
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void c8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c8ActionPerformed

    private void ta3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ta3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ta3CaretUpdate

    private void ta3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ta3CaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_ta3CaretPositionChanged

    private void ta3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ta3FocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_ta3FocusGained

    private void ta3InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ta3InputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_ta3InputMethodTextChanged

    private void ta3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ta3PropertyChange
        // TODO add your handling code here:                                
        
    }//GEN-LAST:event_ta3PropertyChange

    private void jPanel7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel7FocusGained
        // TODO add your handling code here:
        Connection conn = null;
        try {
        
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select distinct tID from messages where fID = '"+(this.UserName)+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            c7.addItem(rs.getString("tID"));
        }}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }   
    }//GEN-LAST:event_jPanel7FocusGained

    private void jPanel6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel6FocusGained
        // TODO add your handling code here:
        Connection conn = null;
        try {
        
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select distinct fID from messages where tID = '"+(this.UserName)+"'";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            c6.addItem(rs.getString("fID"));
        }}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }         
    }//GEN-LAST:event_jPanel6FocusGained

    private void c9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c9ActionPerformed
        // TODO add your handling code here:
        int pid = Integer.parseInt(c9.getSelectedItem().toString());
        Connection conn = null;
        try {
        DefaultTableModel tb = (DefaultTableModel) tb2.getModel();
        tb.setRowCount(0);
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select * from prescription, prescriptionmeds, medicine, doctor where prescription.prescriptionID"
                + " = prescriptionmeds.prescriptionID and prescriptionmeds.medID = medicine.medID and "
                + "doctor.doctorID = prescription.doctorID and prescription.prescriptionID="+(pid)+"";
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            String name = rs.getString("medicine.Name");
            String drug = rs.getString("medicine.Drug");
            String dateend = rs.getString("prescription.cycleEnd");
            String docN = rs.getString("doctor.Name");
            tb.addRow(new Object[]{name, drug, dateend, docN});
        }}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }         
    }//GEN-LAST:event_c9ActionPerformed

    private void c11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c11ActionPerformed

    private void c12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c12ActionPerformed
        // TODO add your handling code here:

        String sql = "";
        switch (c12.getSelectedItem().toString()){
            case "Doctor":
                sql="select distinct doctor.name from patient, prescription, doctor where patient.patientID = prescription.patientID "
                        + "and prescription.doctorID = doctor.doctorID  and patient.patientID = (select patient.patientID "
                        + "from patient, userrelations, users where patient.patientID =userrelations.roleID and "
                        + "userrelations.role = 'Patient' and userrelations.userid = users.userid and "
                        + "users.userid = '"+(this.UserName)+"');";
                role="Doctor";
                break;
            case "Hospital":
                sql="select distinct hospital.name from patient, prescription, doctor, employment, hospital where "
                        + "doctor.doctorID = employment.doctorID and employment.hospitalID = hospital.hospitalID and"
                        + " patient.patientID = prescription.patientID and prescription.doctorID = doctor.doctorID  and "
                        + "patient.patientID = (select patient.patientID from patient, userrelations, users "
                        + "where patient.patientID =userrelations.roleID and userrelations.role = 'Patient' and "
                        + "userrelations.userid = users.userid and users.userid = '"+(this.UserName)+"');";
                role="Hospital";
                break;
            case "Pharmacist":
                sql="select distinct pharmacist.name from patient, orders, pharmacist where patient.patientID = orders.patientID and "
                        + "orders.pharmaID = pharmacist.pharmaID and patient.patientID = (select patient.patientID from "
                        + "patient, userrelations, users where patient.patientID =userrelations.roleID and userrelations.role = "
                        + "'Patient' and userrelations.userid = users.userid and users.userid = "
                        + "'"+(this.UserName)+"');";
                role="Pharmacist";
                break;
        }
        Connection conn=null;
        c13.removeAllItems();
        try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        ResultSet rs=stmt.executeQuery(sql);
            
        while(rs.next()) {
            switch(role){
                case "Doctor":
                    c13.addItem(rs.getString("doctor.Name"));
                    break;
                case "Hospital":
                    c13.addItem(rs.getString("hospital.Name"));
                            break;
                case "Pharmacist":
                    c13.addItem(rs.getString("Pharmacist.Name"));
            }
        }}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }        
    }//GEN-LAST:event_c12ActionPerformed

    private void c13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c13ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_c13ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code 
        String sql="";
       switch(this.role){
           case "Doctor":
                sql="insert into reviews (patientID, reviewedID, reviewText, starRating) values "
                        + "((select patient.patientID from patient, userrelations, users where patient.patientID "
                        + "=userrelations.roleID and userrelations.role = 'Patient' and userrelations.userid = "
                        + "users.userid and users.userid = '"+(this.UserName)+"'), (select users.userID from users, userrelations, doctor where users"
                        + ".userID = userrelations.userID and userrelations.roleID=doctor.doctorID and"
                        + " userrelations.role='Doctor' and doctor.name="
                        + " '"+(c13.getSelectedItem().toString())+"'), "
                        + "'"+(jTextArea1.getText())+"', '"+(Integer.parseInt(c11.getSelectedItem().toString()))+"');";
                break;
            case "Hospital":
                sql="insert into reviews (patientID, reviewedID, reviewText, starRating) values "
                        + "((select patient.patientID from patient, userrelations, users where patient.patientID "
                        + "=userrelations.roleID and userrelations.role = 'Patient' and userrelations.userid = "
                        + "users.userid and users.userid = '"+(this.UserName)+"'), (select users.userID from users, userrelations, "
                        + "hospital where users"
                        + ".userID = userrelations.userID and userrelations.roleID=hospital.hospitalID and"
                        + " userrelations.role='Hospital' and hospital.name="
                        + " '"+(c13.getSelectedItem().toString())+"'), "
                        + "'"+(jTextArea1.getText())+"', '"+(Integer.parseInt(c11.getSelectedItem().toString()))+"');";
                role="Hospital";
                break;
            case "Pharmacist":
                sql="insert into reviews (patientID, reviewedID, reviewText, starRating) values "
                        + "((select patient.patientID from patient, userrelations, users where patient.patientID "
                        + "=userrelations.roleID and userrelations.role = 'Patient' and userrelations.userid = "
                        + "users.userid and users.userid = '"+(this.UserName)+"'), (select users.userID from users, userrelations, pharmacist where users"
                        + ".userID = userrelations.userID and userrelations.roleID=pharmacist.pharmaID and"
                        + " userrelations.role='Pharmacist' and pharmacist.name="
                        + " '"+(c13.getSelectedItem().toString())+"'), "
                        + "'"+(jTextArea1.getText())+"', '"+(Integer.parseInt(c11.getSelectedItem().toString()))+"');";
                role="Pharmacist";
                break;
       }
       Connection conn=null;
       try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        stmt.executeUpdate(sql);
        JOptionPane.showMessageDialog(this, "Review Submitted!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
       
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String doc = jList3.getSelectedValue();
        Connection conn=null;
       try {
           String sql = "select users.userID from users, userrelations, doctor "
                   + "where users.userID = userrelations.userid and userrelations.roleid = doctor.doctorID "
                   + "and userrelations.role = 'Doctor' and doctor.name = '"+(doc)+"';";
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            doc = rs.getString("users.userid");
        }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
        new Booking(doc, this.UserName).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         String doc = jList1.getSelectedValue();
        Connection conn=null;
       try {
           String sql = "select users.userID from users, userrelations, doctor "
                   + "where users.userID = userrelations.userid and userrelations.roleid = doctor.doctorID "
                   + "and userrelations.role = 'Doctor' and doctor.name = '"+(doc)+"';";
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            doc = rs.getString("users.userid");
        }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
        new Booking(doc, this.UserName).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Welcome().setVisible(true);
        
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(UserHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserHomepage("rutanjit").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCart;
    private javax.swing.JButton AddPrescription;
    private javax.swing.JLabel Price;
    private javax.swing.JButton RemoveCart;
    private javax.swing.JComboBox<String> c1;
    private javax.swing.JComboBox<String> c11;
    private javax.swing.JComboBox<String> c12;
    private javax.swing.JComboBox<String> c13;
    private javax.swing.JComboBox<String> c2;
    private javax.swing.JComboBox<String> c3;
    private javax.swing.JComboBox<String> c4;
    private javax.swing.JComboBox<String> c5;
    private javax.swing.JComboBox<String> c6;
    private javax.swing.JComboBox<String> c7;
    private javax.swing.JComboBox<String> c8;
    private javax.swing.JComboBox<String> c9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l10;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JLabel labelx;
    private javax.swing.JTextField qtyT;
    private javax.swing.JTextField t4;
    private javax.swing.JTextField t5;
    private javax.swing.JTextArea ta1;
    private javax.swing.JTextArea ta2;
    private javax.swing.JTextArea ta3;
    private javax.swing.JTable tb1;
    private javax.swing.JTable tb2;
    // End of variables declaration//GEN-END:variables
}
