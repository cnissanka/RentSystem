/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.helperclass;

import cabservice.jdbc;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author icebit
 */
public class TransactionHistory {

    private int idTransaction;
    private Date TransactionDate;
    private double TransactionIncome;
    private String TransactionMethod;

    public TransactionHistory(int idTransaction, Date TransactionDate, double TransactionIncome, String TransactionMethod) {
        this.idTransaction = idTransaction;
        this.TransactionDate = TransactionDate;
        this.TransactionIncome = TransactionIncome;
        this.TransactionMethod = TransactionMethod;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Date getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(Date TransactionDate) {
        this.TransactionDate = TransactionDate;
    }

    public double getTransactionIncome() {
        return TransactionIncome;
    }

    public void setTransactionIncome(double TransactionIncome) {
        this.TransactionIncome = TransactionIncome;
    }

    public String getTransactionMethod() {
        return TransactionMethod;
    }

    public void setTransactionMethod(String TransactionMethod) {
        this.TransactionMethod = TransactionMethod;
    }

    public void save(jdbc database) {
        int id = database.generateID("TransactionHistory", "idTransaction");
        this.setIdTransaction(id);
        database.update("insert into TransactionHistory values (" + id + ", '" + new SimpleDateFormat("yyyy-MM-dd").format(this.getTransactionDate()) + "', " + this.getTransactionIncome() + ", '" + this.getTransactionMethod() + "')");
    }

    public static Vector<TransactionHistory> searchTransactions(String query, jdbc database) {
        Vector<TransactionHistory> v = new Vector<>();
        try {
            ResultSet rs = database.search(query);
            while (rs.next()) {
                TransactionHistory transaction = new TransactionHistory(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4));
                v.add(transaction);
            }
        } catch (Exception e) {
        }
        return v;
    }

}
