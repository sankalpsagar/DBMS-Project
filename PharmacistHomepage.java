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
public class PharmacistHomepage extends javax.swing.JFrame {

    /**
     * Creates new form PharmacistHomepage
     */
    String UserName;
    public PharmacistHomepage(String u) {
        initComponents();
        this.UserName=u;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        getContentPane().setBackground(Color.black);
        this.initFrame1();
        this.initframe2();
        this.initFrame3();
        Connection conn=null;
        try {
        conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
        Statement stmt=conn.createStatement(); c6.removeAllItems();
        c7.removeAllItems();
        String sql = "select distinct fID from messages where tID = '"+(this.UserName)+"'";
        ResultSet rs=stmt.executeQuery(sql);
        
        while(rs.next()) {
            c6.addItem(rs.getString("fID"));
        }
        sql = "select distinct tID from messages where fID = '"+(this.UserName)+"'";
        rs=stmt.executeQuery(sql);
        while(rs.next()) {
            c7.addItem(rs.getString("tID"));
        }
        sql = "select userid from users where userid!='"+(this.UserName)+"'";
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            c8.addItem(rs.getString("userid"));
        }
        rs.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
    }
    
    public void initFrame3(){
        Connection conn = null;
        jTextArea2.setEditable(false);
        try{
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement(); 
            String sql;
            sql="select p.Name as Name, r.reviewText as Feedback, r.starRating as rate from reviews r, patient p where r.patientID=p.patientID and r.reviewedID='"+(this.UserName)+"';";
            ResultSet rs;
            rs=stmt.executeQuery(sql);
            String n,f;
            int r;
            while(rs.next()){
                n=rs.getString("Name");
                f=rs.getString("Feedback");
                r=rs.getInt("rate");
                String dis=n + " gave "+r+" stars out of 5 stars.\n"+"Review: "+f+" \nxx------------------------------------------------------------------xx\n\n";
                jTextArea2.append(dis);
            }
            sql="select avg(starRating) as rate from reviews where reviewedID='"+(this.UserName)+"';";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                jLabel17.setText(rs.getString("rate"));
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    
}
    public void initframe2(){
        c2.removeAllItems();
        Connection conn = null;
        t3.setEditable(false);
        t4.setEditable(false);
        DefaultTableModel tb = (DefaultTableModel) tb2.getModel();
            tb.setRowCount(0);
        try{
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement(); 
            String sql;
            sql="select roleID from userRelations where role='Pharmacist' and userID='"+ this.UserName +"';";
            ResultSet rs;
            rs=stmt.executeQuery(sql);
            int phid=0;
            while(rs.next()){
                phid=rs.getInt("RoleID");
            }
            sql="select orderID from orders where pharmaID="+phid+";";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
               // JOptionPane.showMessageDialog(this, "in");
                c2.addItem(rs.getInt("orderID")+"");
            }
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void initFrame1(){
         Connection conn = null;
        try{
             DefaultTableModel tb1 = (DefaultTableModel) jTable1.getModel();
            tb1.setRowCount(0);
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement(); 
            String sql;
            sql="select roleID from userRelations where role='Pharmacist' and userID='"+ this.UserName +"';";
            ResultSet rs;
            rs=stmt.executeQuery(sql);
            int phid=0;
            while(rs.next()){
                phid=rs.getInt("RoleID");
            }
            sql="select m.Name as n, m.Drug as d, i.Price as p, i.quantity as q from Medicine m , Inventory i where i.pharmaID = "+phid+" and m.medID = i.medID";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                String n=rs.getString("n");
                c1.addItem(n);
                int q=rs.getInt("q");
                int p=rs.getInt("p");
                String d=rs.getString("d");
               
                //JOptionPane.showMessageDialog(this, "inside");
                tb1.addRow(new Object[]{n,d,p,q});
                //jTable1.setModel(tb1);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        c1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        c2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        t3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        t4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        c6 = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        ta1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        ta2 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        c7 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        c8 = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        ta3 = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setBackground(new java.awt.Color(0, 0, 0));
        jTable1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Drug", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Current Inventory");

        jLabel2.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Update Stock");

        jLabel3.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Medicine:");

        c1.setBackground(new java.awt.Color(0, 0, 0));
        c1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Price:");

        t1.setBackground(new java.awt.Color(0, 0, 0));
        t1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity:");

        t2.setBackground(new java.awt.Color(0, 0, 0));
        t2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t2.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Update Stock");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(jButton1)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage Inventory", jPanel1);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Order ID:");

        c2.setBackground(new java.awt.Color(0, 0, 0));
        c2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        c2.setForeground(new java.awt.Color(255, 255, 255));
        c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ordered By:");

        t3.setBackground(new java.awt.Color(0, 0, 0));
        t3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t3.setForeground(new java.awt.Color(255, 255, 255));

        tb2.setBackground(new java.awt.Color(0, 0, 0));
        tb2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        tb2.setForeground(new java.awt.Color(255, 255, 255));
        tb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Drug", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb2);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Order Details:");

        jLabel9.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total Price:");

        t4.setBackground(new java.awt.Color(0, 0, 0));
        t4.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        t4.setForeground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Mark as Delivered");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(c2, 0, 242, Short.MAX_VALUE)
                            .addComponent(t3))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8)))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jButton2)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Orders", jPanel2);

        jTabbedPane2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel8FocusGained(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("From:");

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                                .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(71, 71, 71))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Inbox", jPanel8);

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel9FocusGained(evt);
            }
        });

        ta2.setBackground(new java.awt.Color(0, 0, 0));
        ta2.setColumns(20);
        ta2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        ta2.setForeground(new java.awt.Color(255, 255, 255));
        ta2.setRows(5);
        jScrollPane11.setViewportView(ta2);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("To:");

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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane11)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                                .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(71, 71, 71))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Outbox", jPanel9);

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 2, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Send To:");

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(465, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane12)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                            .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(89, 89, 89)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(440, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(27, 27, 27))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(44, 44, 44)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(114, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Send Message", jPanel10);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Messages", jPanel4);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Recent Reviews:");

        jTextArea2.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea2.setColumns(20);
        jTextArea2.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Average Rating:");

        jLabel17.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel10)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel17))
                .addContainerGap(177, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Reviews", jPanel6);

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Log Out");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Are you sure?");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel16)))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 655, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Logout", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql;
            sql="update orders set status ='Delivered' where orderID="+c2.getSelectedItem()+";";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Updated!");
            this.initframe2();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2ActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        try{

            DefaultTableModel tb1 = (DefaultTableModel) tb2.getModel();
            tb1.setRowCount(0);
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql;
            sql="select roleID from userRelations where role='Pharmacist' and userID='"+ this.UserName +"';";
            ResultSet rs;
            rs=stmt.executeQuery(sql);
            int phid=0;
            while(rs.next()){
                phid=rs.getInt("RoleID");
            }
            sql="select patientID,totalPrice from orders where orderID="+c2.getSelectedItem()+";";
            rs=stmt.executeQuery(sql);
            int pid=0;
            while(rs.next()){
                pid=rs.getInt("patientID");
                t4.setText(rs.getString("totalPrice"));
            }
            sql="select name from patient where patientID="+pid+";";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                t3.setText(rs.getString("Name"));
            }
            sql="select status from orders where orderID="+c2.getSelectedItem()+";";
            rs=stmt.executeQuery(sql);
            String stat="";
            while(rs.next()){
                stat=rs.getString("status");
            }
            if (stat.equals("Pending"))
            {
                jButton2.setEnabled(true);
            }
            else{
                jButton2.setEnabled(false);
            }
            sql="select o.quantity as quantity, m.name as name, m.drug as drug from orderItems o, medicine m where o.medId=m.medID and orderID="+c2.getSelectedItem()+";";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                // JOptionPane.showMessageDialog(this, "in1");
                //int mid=rs.getInt("medID");
                int q=rs.getInt("quantity");
                String name=rs.getString("name");
                String drug=rs.getString("drug");
                tb1.addRow(new Object[]{name,drug,q,stat});
            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_c2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Connection conn=null;
        try{
            conn = DriverManager.getConnection(MediKart.url, MediKart.user, MediKart.password);
            Statement stmt=conn.createStatement();
            String sql;
            sql="select medID from Medicine where Name='"+ c1.getSelectedItem().toString() +"';";
            ResultSet rs;
            rs=stmt.executeQuery(sql);
            int mid=0;
            while(rs.next()){
                mid=rs.getInt("medID");
            }
            sql="select roleID from userRelations where role='Pharmacist' and userID='"+ this.UserName +"';";
            rs=stmt.executeQuery(sql);
            int phid=0;
            while(rs.next()){
                phid=rs.getInt("RoleID");
            }
            sql="update inventory set price = "+ t1.getText() +" where medID="+mid+ " and pharmaId="+ phid+";";
            stmt.executeUpdate(sql);
            sql="update inventory set quantity = "+ t2.getText() +" where medID="+mid+ " and pharmaId="+ phid+";";
            stmt.executeUpdate(sql);
            this.initFrame1();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void c6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_c6ItemStateChanged

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

    private void jPanel8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel8FocusGained
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
    }//GEN-LAST:event_jPanel8FocusGained

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

    private void jPanel9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel9FocusGained
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
    }//GEN-LAST:event_jPanel9FocusGained

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
            java.util.logging.Logger.getLogger(PharmacistHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PharmacistHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PharmacistHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PharmacistHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PharmacistHomepage("SoumMed").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> c1;
    private javax.swing.JComboBox<String> c2;
    private javax.swing.JComboBox<String> c6;
    private javax.swing.JComboBox<String> c7;
    private javax.swing.JComboBox<String> c8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    private javax.swing.JTextArea ta1;
    private javax.swing.JTextArea ta2;
    private javax.swing.JTextArea ta3;
    private javax.swing.JTable tb2;
    // End of variables declaration//GEN-END:variables
}
