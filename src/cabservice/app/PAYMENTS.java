/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.app;

import cabservice.helperclass.Customer;
import cabservice.helperclass.Payment;
import cabservice.helperclass.Rent;
import cabservice.helperclass.ReportGenerate;
import cabservice.helperclass.TransactionHistory;
import cabservice.jdbc;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author icebit
 */
public class PAYMENTS extends javax.swing.JFrame {

    /**
     * Creates new form PAYMENTS
     */
    public PAYMENTS() {
        initComponents();
    }
    jdbc database;

    public PAYMENTS(int index) {
        initComponents();
        jTabbedPane1.setSelectedIndex(index);
        database = new jdbc();
        loadCustomer(jTable2);
        loadCustomer(jTable3);
        loadBookings(jTable1);
        loadBookings();
        database.loadColsForComboBox("Rent", jComboBox3);
        database.loadColsForComboBox("Customer", jComboBox2);
        loadPayments();
    }

    public void getAllPaymentsRelatedToCustomerandBooking(int customerid, int bookingid) {
        jTabbedPane1.setSelectedIndex(1);
        String query = "select * from Payment where Customer_idCustomer=" + customerid + " and Rent_idRent=" + bookingid + ";";
        Vector<Payment> paymentlist = Payment.searchPayment(query, database);
        DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
        dtm.setNumRows(0);
        for (Payment p : paymentlist) {
            Vector v = new Vector();
            v.add(p.getIdPayment());
            v.add(p.getPaymentAmount());
            v.add(p.getPaymentMethod());
            v.add(p.getDate());
            v.add(p.getRentid());
            v.add(p.getCustomerid());

            dtm.addRow(v);
        }
    }
    

    public double checkFullyPaid(int rentid) {

        Vector<Payment> paymentList = Payment.searchPayment("Rent_idRent", rentid + "", database);
        double price = 0;
        for (Payment p : paymentList) {
            price += p.getPaymentAmount();
        }
        double balance = 0;
        Rent rent = Rent.getRent(rentid, database);
        if (price >= rent.getBooking_cost()) {
            balance = price - rent.getBooking_cost();
            rent.setStatus("PAID");
            rent.update(database);
            loadBookings(jTable1);
            loadPayments();
        } else {
            balance = 0;
        }
        return balance;
    }

    public void loadBookings(JTable tbl) {
        Vector<Rent> rent = Rent.searchRent("status", "'" + "DUE" + "'", database);
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        dtm.setNumRows(0);
        for (Rent r : rent) {
            Vector<String> v = new Vector<>();
            v.add(r.getVechicle_idVechicle() + "");
            v.add(r.getCustomer_idCustomer() + "");
            v.add(r.getIdRent() + "");
            v.add(r.getDriver_idDriver() + "");
            v.add("Rs." + r.getBooking_cost() + "");
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingDate()));
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingFromDate()));
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingToDate()));
            v.add(r.getStatus());
            dtm.addRow(v);
        }
    }
    
    public void loadBookings(){
         Vector<Rent> rent = Rent.searchRent("status", "'" + "DUE" + "' or status='PAID'", database);
        DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
        dtm.setNumRows(0);
        for (Rent r : rent) {
            Vector<String> v = new Vector<>();
            v.add(r.getVechicle_idVechicle() + "");
            v.add(r.getCustomer_idCustomer() + "");
            v.add(r.getIdRent() + "");
            v.add(r.getDriver_idDriver() + "");
            v.add("Rs." + r.getBooking_cost() + "");
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingDate()));
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingFromDate()));
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingToDate()));
            v.add(r.getStatus());
            dtm.addRow(v);
        }
    }

    public void loadPayments() {
        try {
            Vector<Payment> paymentlist = Payment.searchPayment("paymentMode", "'" + "CASH" + "'", database);
            DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
            dtm.setNumRows(0);
            for (Payment p : paymentlist) {
                Vector v = new Vector();
                v.add(p.getIdPayment());
                v.add(p.getPaymentAmount());
                v.add(p.getPaymentMethod());
                v.add(p.getDate());
                v.add(p.getRentid());
                v.add(p.getCustomerid());

                dtm.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    public void loadCustomer(JTable tbl) {
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        dtm.setNumRows(0);
        Vector<Customer> customerlist = Customer.getAllCustomers(database);
        for (Customer cl : customerlist) {
            Vector<String> row = new Vector<>();
            row.add(cl.getId() + "");
            row.add(cl.getFirstname());
            row.add(cl.getLastname());
            row.add(cl.getGender());
            row.add(cl.getNic());
            row.add(cl.getMobile());
            row.add(cl.getLand());
            row.add(cl.getEmail());
            row.add(cl.getAddress());
            dtm.addRow(row);
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

        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jTextField34 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtPaymentAmount = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtRemainingBalance = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txtDatePayment = new datechooser.beans.DateChooserCombo();
        cmbPaymentMode = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jTextField36 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        jButton4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("PAYMENT AMOUNT ; ");

        jLabel2.setText("PAYMENT MODE      :");

        jLabel3.setText("DATE OF PAYMENT  :");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Select"));

        jButton7.setText("SEARCH");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRSTNAME", "LASTNAME", "GENDER", "NIC", "MOBILE", "LAND", "EMAIL", "ADDRESS" }));

        jLabel38.setText("BASED ON ");

        jLabel39.setText("SEARCH ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FIRSTNAME", "LASTNAME", "GENDER", "NIC", "MOBILE", "LAND", "EMAIL", "ADDRESS"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(36, 36, 36)
                                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Booking Select"));

        jLabel40.setText("SEARCH ");

        jLabel41.setText("BASED ON ");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRSTNAME", "LASTNAME", "GENDER", "NIC", "MOBILE", "LAND", "EMAIL", "ADDRESS" }));

        jButton8.setText("SEARCH");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vehicle ID", "Customer ID", "RENT ID", "Driver ID", "Booking Cost", "Booking Date", "Booking Start Date", "Booking End Date", "Status"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(36, 36, 36)
                        .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("View Payments Already Made ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Remaining Balance : ");

        txtRemainingBalance.setEditable(false);

        jButton3.setText("Place Payment ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cmbPaymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASH" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPaymentAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(txtDatePayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPaymentMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRemainingBalance))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(448, 448, 448)
                                .addComponent(jButton3)
                                .addGap(14, 14, 14)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel4, jPanel5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbPaymentMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtDatePayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4)
                    .addComponent(txtRemainingBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PLACE PAYMENT", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Select"));

        jButton9.setText("SEARCH");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRSTNAME", "LASTNAME", "GENDER", "NIC", "MOBILE", "LAND", "EMAIL", "ADDRESS" }));

        jLabel42.setText("BASED ON ");

        jLabel43.setText("SEARCH ");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FIRSTNAME", "LASTNAME", "GENDER", "NIC", "MOBILE", "LAND", "EMAIL", "ADDRESS"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton5.setText("RESET");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(36, 36, 36)
                                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Booking Select"));

        jLabel44.setText("SEARCH ");

        jLabel45.setText("BASED ON ");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIRSTNAME", "LASTNAME", "GENDER", "NIC", "MOBILE", "LAND", "EMAIL", "ADDRESS" }));

        jButton10.setText("SEARCH");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vehicle ID", "Customer ID", "RENT ID", "Driver ID", "Booking Cost", "Booking Date", "Booking Start Date", "Booking End Date", "Status"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(36, 36, 36)
                                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel5.setText("Enter Start Time Period : ");

        jLabel6.setText("Enter End Time Period    :");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Payment Amount", "Payment Mode", "Date", "Rent ID", "Customer ID"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jButton4.setText("FIND");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton11.setText("RESET");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(111, 111, 111))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton4)
                                .addComponent(jButton11)))
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane5)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("VIEW PAYMENTS", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (jTable2.getSelectedRow() != -1) {
            int id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
            Vector<Rent> rentlist = Rent.searchRent("Customer_idCustomer", id + " and status LIKE '" + "DUE" + "'", database);
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setNumRows(0);
            for (Rent r : rentlist) {
                Vector<String> v = new Vector<>();
                v.add(r.getVechicle_idVechicle() + "");
                v.add(r.getCustomer_idCustomer() + "");
                v.add(r.getIdRent() + "");
                v.add(r.getDriver_idDriver() + "");
                v.add("Rs." + r.getBooking_cost() + "");
                v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingDate()));
                v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingFromDate()));
                v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingToDate()));
                v.add(r.getStatus());
                dtm.addRow(v);
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            if (jTable2.getSelectedRow() != -1) {
                if (jTable1.getSelectedRow() != -1) {
                    int customer_id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                    int rentid = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                    Date paymentDate = txtDatePayment.getDateFormat().parse(txtDatePayment.getText());
                    
                    Rent rent = Rent.getRent(rentid, database);
                    if (!paymentDate.after(rent.getBookingToDate())){
                        JOptionPane.showMessageDialog(null, "Payment date is invalid it must be on "+rent.getBookingToDate().toString());
                        return;
                    }
                    
                    String paymentmode = cmbPaymentMode.getSelectedItem().toString();
                    double payment_value = Double.parseDouble(txtPaymentAmount.getText());

                    Payment payment = new Payment(0, payment_value, paymentmode, paymentDate, rentid, customer_id);
                    payment.addPayment(database);

                    TransactionHistory transhistory = new TransactionHistory(0, payment.getDate(), payment.getPaymentAmount(), payment.getPaymentMethod());
                    transhistory.save(database);

                    JOptionPane.showMessageDialog(null, "Payment added :) ");

                    double balance = checkFullyPaid(rentid);
                    JOptionPane.showMessageDialog(null, "There was a balance of " + balance);
                    
                  //  ReportGenerate.generatePayment("abc", Customer.getCustomer(customer_id, database), payment, rent);
                    
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (jTable1.getSelectedRow() != -1) {
            int rentid = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            Rent r = Rent.getRent(rentid, database);
            Vector<Payment> paymentList = Payment.searchPayment("Rent_idRent", rentid + "", database);
            double price = 0;
            for (Payment p : paymentList) {
                price += p.getPaymentAmount();
            }
            txtRemainingBalance.setText("Rs." + ( r.getBooking_cost() - price) + "");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (jTable2.getSelectedRow() != -1) {
            if (jTable1.getSelectedRow() != -1) {
                
                int customer_id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                int rentid = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                getAllPaymentsRelatedToCustomerandBooking(customer_id, rentid);
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        if (jTable3.getSelectedRow() != -1) {
            int id = Integer.parseInt(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
            Vector<Rent> rentlist = Rent.searchRent("Customer_idCustomer", id + "", database);
            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
            dtm.setNumRows(0);
            for (Rent r : rentlist) {
                Vector<String> v = new Vector<>();
                v.add(r.getVechicle_idVechicle() + "");
                v.add(r.getCustomer_idCustomer() + "");
                v.add(r.getIdRent() + "");
                v.add(r.getDriver_idDriver() + "");
                v.add("Rs." + r.getBooking_cost() + "");
                v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingDate()));
                v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingFromDate()));
                v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingToDate()));
                v.add(r.getStatus());
                dtm.addRow(v);
            }
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        try {
            if (jTable4.getSelectedRow() != -1) {
                int id = Integer.parseInt(jTable4.getValueAt(jTable4.getSelectedRow(), 2).toString());
                Vector<Payment> paymentlist = Payment.searchPayment("select * from Payment where Rent_idRent="+id, database);
                DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
                dtm.setNumRows(0);
                for (Payment p : paymentlist) {
                    Vector v = new Vector();
                    v.add(p.getIdPayment());
                    v.add(p.getPaymentAmount());
                    v.add(p.getPaymentMethod());
                    v.add(p.getDate());
                    v.add(p.getRentid());
                    v.add(p.getCustomerid());

                    dtm.addRow(v);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        loadCustomer(jTable3);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        loadPayments();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String searchCol = jComboBox2.getSelectedItem().toString();
            Vector<Customer> customerList = Customer.searchCustomer(searchCol, jTextField34.getText(), database);

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setNumRows(0);
            Vector<Customer> customerlist = Customer.getAllCustomers(database);
            for (Customer cl : customerList) {
                Vector<String> row = new Vector<>();
                row.add(cl.getId() + "");
                row.add(cl.getFirstname());
                row.add(cl.getLastname());
                row.add(cl.getGender());
                row.add(cl.getNic());
                row.add(cl.getMobile());
                row.add(cl.getLand());
                row.add(cl.getEmail());
                row.add(cl.getAddress());
                dtm.addRow(row);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Vector<Rent> rent = Rent.searchRent(jComboBox3.getSelectedItem().toString(), "'" + jTextField35.toString() + "'", database);
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setNumRows(0);
        for (Rent r : rent) {
            Vector<String> v = new Vector<>();
            v.add(r.getVechicle_idVechicle() + "");
            v.add(r.getCustomer_idCustomer() + "");
            v.add(r.getIdRent() + "");
            v.add(r.getDriver_idDriver() + "");
            v.add("Rs." + r.getBooking_cost() + "");
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingDate()));
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingFromDate()));
            v.add(new SimpleDateFormat("yyyy-MM-dd").format(r.getBookingToDate()));
            v.add(r.getStatus());
            dtm.addRow(v);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Date startdate = dateChooserCombo2.getDateFormat().parse(dateChooserCombo2.getText());
            Date enddate = dateChooserCombo3.getDateFormat().parse(dateChooserCombo3.getText());

            String startdate_str = new SimpleDateFormat("yyyy-MM-dd").format(startdate);
            String enddate_str = new SimpleDateFormat("yyyy-MM-dd").format(enddate);

            String query = "select * from Payment where date > '" + startdate_str + "' and date < '" + enddate_str + "';";
            Vector<Payment> paymentlist = Payment.searchPayment(query, database);

            DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
            dtm.setNumRows(0);
            for (Payment p : paymentlist) {
                Vector v = new Vector();
                v.add(p.getIdPayment());
                v.add(p.getPaymentAmount());
                v.add(p.getPaymentMethod());
                v.add(p.getDate());
                v.add(p.getRentid());
                v.add(p.getCustomerid());

                dtm.addRow(v);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(PAYMENTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PAYMENTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PAYMENTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PAYMENTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PAYMENTS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbPaymentMode;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private datechooser.beans.DateChooserCombo txtDatePayment;
    private javax.swing.JTextField txtPaymentAmount;
    private javax.swing.JTextField txtRemainingBalance;
    // End of variables declaration//GEN-END:variables
}
