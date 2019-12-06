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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Krish
 */
public class DoctorHomepage extends javax.swing.JFrame {

    /**
     * Creates new form DoctorHomepage
     */
    String UserName;
    public DoctorHomepage(String u) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        getContentPane().setBackground(Color.black);
        this.UserName = u;
        this.initTables();
        this.initFrame2(); 
        try{
            String sql;
        Connection conn = null;
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement();
         sql = "select userid from users where userid!='"+(this.UserName)+"'";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            c8.addItem(rs.getString("userid"));
        }}
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.initmsg();
    }

    public void initmsg(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql;
            ResultSet rs;
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
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    public void initFrame2(){
        Connection conn = null;
        c3.removeAllItems();
        c4.removeAllItems();
        try{
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql="select Name from patient;";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                c4.addItem(rs.getString("Name"));
            }
            sql="select Name from Medicine;";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                c3.addItem(rs.getString("Name"));
            }
           
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }
    public void initTables()
    {
        
         Connection conn = null;
        try{
            DefaultTableModel tb = (DefaultTableModel) jTable1.getModel();
            tb.setRowCount(0);
            String aid ="";
            String n = "";
            int t = -1;
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select appointments.appID as appID, p.name as name, appointments.time as time from doctor, appointments, patient p, userRelations ur where doctor.doctorID ="
                + " appointments.doctorID and ur.roleID=doctor.doctorID "
                + "and appointments.patientID = p.patientID and status ='Pending' and ur.userId = '"+(this.UserName)+"' and ur.role='Doctor' ;";
         ResultSet rs=stmt.executeQuery(sql);
         while(rs.next()){
             aid = rs.getString("appID");
             n = rs.getString("name");
             t = rs.getInt("time");
            tb.addRow(new Object[]{aid,n,t});
           

            }
         }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
        
        try{
            DefaultTableModel tb1 = (DefaultTableModel) jTable2.getModel();
            tb1.setRowCount(0);
            String aid ="";
            String n = "";
            int t = -1;
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "select appointments.appID as appID, p.name as name, appointments.time as time from doctor, appointments, patient p, userRelations ur where doctor.doctorID ="
                + " appointments.doctorID and ur.roleID=doctor.doctorID "
                + "and appointments.patientID = p.patientID and status ='Done' and ur.userId = '"+(this.UserName)+"' and ur.role='Doctor' ;";
         ResultSet rs=stmt.executeQuery(sql);
         while(rs.next()){
             aid = rs.getString("appID");
             n = rs.getString("name");
             t = rs.getInt("time");
            tb1.addRow(new Object[]{aid,n,t});
            

            }
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        c3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        c4 = new javax.swing.JComboBox<>();
        AddButton = new javax.swing.JButton();
        GiveButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        c6 = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        ta1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        ta2 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        c7 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        c8 = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        ta3 = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pending Appointments");

        jLabel2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Appointment ID");

        t1.setBackground(new java.awt.Color(0, 0, 0));
        t1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        t1.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Mark as Completed");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Past Appointments");

        jTable1.setBackground(new java.awt.Color(0, 0, 0));
        jTable1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment ID", "Patient ID", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jTable2.setBackground(new java.awt.Color(0, 0, 0));
        jTable2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment ID", "Patient ID", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 72, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(t1)
                    .addComponent(jButton1))
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(54, 54, 54)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        jTabbedPane1.addTab("Appointments", jPanel1);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Patient ID:");

        c3.setBackground(new java.awt.Color(0, 0, 0));
        c3.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Medicine:");

        c4.setBackground(new java.awt.Color(0, 0, 0));
        c4.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c4.setForeground(new java.awt.Color(255, 255, 255));

        AddButton.setBackground(new java.awt.Color(0, 0, 0));
        AddButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        AddButton.setForeground(new java.awt.Color(255, 255, 255));
        AddButton.setText("Add to Prescription");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        GiveButton.setBackground(new java.awt.Color(0, 0, 0));
        GiveButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        GiveButton.setForeground(new java.awt.Color(255, 255, 255));
        GiveButton.setText("Give Prescription");
        GiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GiveButtonActionPerformed(evt);
            }
        });

        jTable3.setBackground(new java.awt.Color(0, 0, 0));
        jTable3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        jTable3.setForeground(new java.awt.Color(255, 255, 255));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable3);

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Erase Prescription");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(GiveButton))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(85, 85, 85))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(AddButton))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(71, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddButton)
                    .addComponent(jButton2))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(GiveButton)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Prescription", jPanel4);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel6FocusGained(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("From:");

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
        jScrollPane9.setViewportView(ta1);

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
                            .addComponent(jScrollPane9)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                                .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(71, 71, 71))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(49, Short.MAX_VALUE))
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

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("To:");

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
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                                .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(71, 71, 71))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Outbox", jPanel7);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Send To:");

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
                .addContainerGap(479, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane12)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                            .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(89, 89, 89)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(452, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(27, 27, 27))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(44, 44, 44)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Send Message", jPanel8);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Messages", jPanel2);

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));

        jButton10.setBackground(new java.awt.Color(0, 0, 0));
        jButton10.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Log Out");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Are you sure?");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel18)))
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Logout", jPanel14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      Connection conn = null;
        try{
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        String sql = "update appointments set status='Done' where appID="+(t1.getText())+";";
        stmt.executeUpdate(sql);
        this.initTables();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        // TODO add your handling code here:
        c4.setEnabled(false);
        DefaultTableModel tb1 = (DefaultTableModel) jTable3.getModel();
        String m= c3.getSelectedItem().toString();
        tb1.addRow(new Object[]{m});
        c3.removeItem(m);
    }//GEN-LAST:event_AddButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        c4.setEnabled(true);
        DefaultTableModel tb1 = (DefaultTableModel) jTable3.getModel();
        tb1.setRowCount(0);
        this.initFrame2();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void GiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GiveButtonActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        try{
                   int did =0;
                int pid = 0;
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); 
        DefaultTableModel tb1 = (DefaultTableModel) jTable3.getModel();
        String sql= "select RoleID from userRelations where role='Doctor' and userID='"+(this.UserName)+"';";
        ResultSet rs= stmt.executeQuery(sql);
        while(rs.next()){
                did=rs.getInt("RoleID");
        }
        //JOptionPane.showMessageDialog(this, did+"");
        sql= "select patientID from Patient where name ='"+(c4.getSelectedItem().toString())+"';";
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                    pid=rs.getInt("patientID");
        }

                int presid=0;

       // JOptionPane.showMessageDialog(this, pid+"");
        sql="insert into prescription(patientID,doctorID,cycleEnd,Active) values (" + (pid)+","+ (did)+ ",CURDATE() + INTERVAL 15 DAY,1);";
        stmt.executeUpdate(sql);
        sql="select max(prescriptionID) as p from prescription;";
        rs=stmt.executeQuery(sql);
        while(rs.next()){
        presid=rs.getInt("p");}
        int mid = 0;
        
        for(int count = 0; count < tb1.getRowCount(); count++){
           sql="select medID from Medicine where Name='"+ (tb1.getValueAt(count, 0).toString())+"';";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
        mid=rs.getInt("medID");}
        sql="insert into PrescriptionMeds values ("+presid+","+mid+");";
        stmt.executeUpdate(sql);
        }
        c4.setEnabled(true);
        tb1.setRowCount(0);
        this.initFrame2();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }//GEN-LAST:event_GiveButtonActionPerformed

    private void c6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c6ItemStateChanged
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

    }//GEN-LAST:event_c6ItemStateChanged

    private void c6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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

    }//GEN-LAST:event_jButton4ActionPerformed

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

    private void c8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c8ActionPerformed

    private void ta3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ta3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ta3CaretUpdate

    private void ta3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ta3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ta3FocusGained

    private void ta3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ta3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ta3CaretPositionChanged

    private void ta3InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ta3InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ta3InputMethodTextChanged

    private void ta3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ta3PropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_ta3PropertyChange

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Welcome().setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(DoctorHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorHomepage("Dr.Roop").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton GiveButton;
    private javax.swing.JComboBox<String> c3;
    private javax.swing.JComboBox<String> c4;
    private javax.swing.JComboBox<String> c6;
    private javax.swing.JComboBox<String> c7;
    private javax.swing.JComboBox<String> c8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField t1;
    private javax.swing.JTextArea ta1;
    private javax.swing.JTextArea ta2;
    private javax.swing.JTextArea ta3;
    // End of variables declaration//GEN-END:variables
}
