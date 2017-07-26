/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.helperclass;

import cabservice.jdbc;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author icebit
 */
public class Payment {
    private int idPayment;
    private double paymentAmount;
    private String paymentMethod;
    private Date date;
    private int rentid;
    private int customerid;

    public Payment(int idPayment, double paymentAmount, String paymentMethod, Date date, int rentid, int customerid) {
        this.idPayment = idPayment;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.rentid = rentid;
        this.customerid = customerid;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRentid() {
        return rentid;
    }

    public void setRentid(int rentid) {
        this.rentid = rentid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
    
    
    public void addPayment (jdbc database){
        int id = database.generateID("Payment","idPayment");
        this.setIdPayment(id);
        Date d = this.getDate();
        java.text.SimpleDateFormat sdf = 
        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(d);
        
        String query = "INSERT INTO Payment (idPayment, paymentAmount, paymentMode, date, Rent_idRent, Customer_idCustomer) VALUES ("+id+", "+this.getPaymentAmount()+", '"+this.getPaymentMethod()+"', '"+currentTime+"', "+this.getRentid()+", "+this.getCustomerid()+");";
        database.update(query);
    }
    
     public static Vector <Payment> searchPayment(String tableCol, String searchText, jdbc database){
        Vector <Payment> paymentList = new Vector<>();
        try {
            ResultSet rs = database.search("select * from Payment where "+tableCol+" like "+searchText);
            while (rs.next()){
                Payment d = new Payment(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6));
                paymentList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentList;
    }
     
     public static Vector <Payment> searchPayment(String query, jdbc database){
        Vector <Payment> paymentList = new Vector<>();
        try {
            ResultSet rs = database.search(query);
            while (rs.next()){
                Payment d = new Payment(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6));
                paymentList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentList;
    }
    
    public static Payment getPayment(int id,jdbc database){
        Payment d = null;
        try {
            ResultSet rs = database.search("select * from Payment where idPayment="+id);
            while (rs.next()){
             d = new Payment(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    public boolean deletePayment (jdbc database){
        
        database.update("delete from Payment where idPayment="+this.getIdPayment());
        
        return true;
    }
    
    public boolean updatePayment (jdbc database){
         
         Date d = this.getDate();
         java.text.SimpleDateFormat sdf = 
         new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(d);
         
         String query = "UPDATE Payment SET  paymentAmount = "+this.getPaymentAmount()+", paymentMode = '"+this.getPaymentMethod()+"', date = '"+currentTime+"', RentidRent = "+this.getRentid()+", CustomeridCustomer = "+this.getCustomerid()+" where idPayment="+this.getIdPayment()+";";
         database.update(query);
         return true;
    }
    
}
