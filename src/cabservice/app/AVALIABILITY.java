/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.app;

import cabservice.helperclass.Driver;
import cabservice.helperclass.SpecificVehicle;
import cabservice.jdbc;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author icebit
 */
public class AVALIABILITY extends javax.swing.JFrame {

    /**
     * Creates new form AVALIABILITY
     */
    public AVALIABILITY() {
        initComponents();
    }
    jdbc database;

    public AVALIABILITY(int index) {
        initComponents();
        jTabbedPane1.setSelectedIndex(index);
        database = new jdbc();
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        Vector<Driver> driverlist = Driver.searchDriver("idDriver", "", database);
        for (Driver dv : driverlist) {

            Vector<String> list = new Vector<>();
            list.add(dv.getId() + "");
            list.add(dv.getFirstName());
            list.add(dv.getLastName());
            list.add(dv.getGender());
            list.add(dv.getNic());
            list.add(dv.getAddress());
            list.add(dv.getMobile());
            list.add(dv.getAvaliablity());
            list.add(dv.getSalary()
                    + "");

            dtm.addRow(list);
            loadVehicles();

        }

        database.loadColsForComboBox("Driver", jComboBox1);
        database.loadColsForComboBox("SpecificVehicle", jComboBox2);
    }

    public void loadVehicles() {
        Vector<SpecificVehicle> vlist = SpecificVehicle.searchSpecificVehicle("DashNo", "", database);
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        dtm.setNumRows(0);
        for (SpecificVehicle veh : vlist) {
            Vector<String> data = new Vector<>();
            data.add(veh.getId() + "");
            data.add(veh.getDashno());
            data.add(veh.getDescription());
            data.add(veh.getVehicle_id() + "");
            data.add(veh.getAvaliability());
            dtm.addRow(data);
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtsearchveh = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FIRST NAME", "LAST NAME", "GENDER", "NIC", "ADDRESS", "MOBILE", "AVALIABILITY", "SALARY"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRSTNAME", "LASTNAME", "GENDER", "NIC", "MOBILE", "ADDRESS", "AVALIABILITY" }));
        jComboBox1.setToolTipText("");

        jLabel12.setText("BASED ON ");

        jLabel11.setText("SEARCH ");

        jButton2.setText("SEARCH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("TOGGLE AVALIABILITY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(36, 36, 36)
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DRIVER AVALIABILITY", jPanel1);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRSTNAME", "LASTNAME", "GENDER", "NIC", "MOBILE", "ADDRESS", "AVALIABILITY" }));
        jComboBox2.setToolTipText("");

        jButton3.setText("SEARCH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel13.setText("SEARCH ");

        jLabel14.setText("BASED ON ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DashNo", "Description", "Vehicle ID", "Avaliablity"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton4.setText("Toggle Avaliability");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(36, 36, 36)
                                .addComponent(txtsearchveh, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtsearchveh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("VEHICLE AVALIABILITY", jPanel2);

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
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() != -1) {
            int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            Driver d = Driver.getDriver(id, database);
            String avaliable = "";
            if (d.getAvaliablity().equals("YES")) {
                avaliable = "NO";
            } else {
                avaliable = "YES";
            }
            d.setAvaliablity(avaliable);
            d.updateDriver(database);
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            Vector<Driver> driverlist = Driver.searchDriver("idDriver", "", database);
            for (Driver dv : driverlist) {

                Vector<String> list = new Vector<>();
                list.add(dv.getId() + "");
                list.add(dv.getFirstName());
                list.add(dv.getLastName());
                list.add(dv.getGender());
                list.add(dv.getNic());
                list.add(dv.getMobile());
                list.add(dv.getMobile());
                list.add(dv.getAvaliablity());
                list.add(dv.getSalary()
                        + "");

                dtm.addRow(list);

            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (jTable2.getSelectedRow() != -1) {
            int id = Integer.parseInt(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 0)));
            SpecificVehicle sp = SpecificVehicle.getSpecificVehicle(id, database);
            // JOptionPane.showMessageDialog(null,sp.getAvaliability().equals("Yes") );
            if (sp.getAvaliability().equals("Yes")) {
                sp.setAvaliability("No");
            } else {
                sp.setAvaliability("Yes");
            }
            sp.updateSpecificVehicle(database);
            loadVehicles();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String searchCol = jComboBox2.getSelectedItem().toString();
            Vector<SpecificVehicle> vehList = SpecificVehicle.searchSpecificVehicle(searchCol, txtsearchveh.getText(), database);
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setNumRows(0);
            for (SpecificVehicle veh : vehList) {
                Vector<String> data = new Vector<>();
                data.add(veh.getId() + "");
                data.add(veh.getDashno());
                data.add(veh.getDescription());
                data.add(veh.getVehicle_id() + "");
                data.add(veh.getAvaliability());
                dtm.addRow(data);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {

            String searchCol = jComboBox1.getSelectedItem().toString();

            Vector<Driver> driverList = Driver.searchDriver(searchCol, txtsearch.getText(), database);

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            //  Vector<Driver> driverlist = Driver.searchDriver("idDriver", "", database);
            for (Driver dv : driverList) {

                Vector<String> list = new Vector<>();
                list.add(dv.getId() + "");
                list.add(dv.getFirstName());
                list.add(dv.getLastName());
                list.add(dv.getGender());
                list.add(dv.getNic());
                list.add(dv.getAddress());
                list.add(dv.getMobile());
                list.add(dv.getAvaliablity());
                list.add(dv.getSalary()
                        + "");

                dtm.addRow(list);

            }

            // Vector <Driver> driverList = Driver.searchDriverCustomQuery("select * from Driver where "+txtsearch.getText()+" like ", database);
        } catch (Exception e) {
            e.printStackTrace();
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
            java.util.logging.Logger.getLogger(AVALIABILITY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AVALIABILITY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AVALIABILITY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AVALIABILITY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AVALIABILITY(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsearchveh;
    // End of variables declaration//GEN-END:variables
}
