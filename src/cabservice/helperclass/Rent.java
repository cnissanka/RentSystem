/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.helperclass;

import cabservice.jdbc;
import datechooser.beans.DateChooserCombo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author icebit
 */
public class Rent {

    private int Vechicle_idVechicle;
    private int Customer_idCustomer;
    private int idRent;
    private int Driver_idDriver;
    private double booking_cost;
    private Date bookingDate;
    private String status;
    private Date bookingFromDate;
    private Date bookingToDate;

    public Rent(int Vechicle_idVechicle, int Customer_idCustomer, int idRent, int Driver_idDriver, double booking_cost, Date bookingDate, String status, Date bookingFromDate, Date bookingToDate) {
        this.Vechicle_idVechicle = Vechicle_idVechicle;
        this.Customer_idCustomer = Customer_idCustomer;
        this.idRent = idRent;
        this.Driver_idDriver = Driver_idDriver;
        this.booking_cost = booking_cost;
        this.bookingDate = bookingDate;
        this.status = status;
        this.bookingFromDate = bookingFromDate;
        this.bookingToDate = bookingToDate;
    }

    public int getVechicle_idVechicle() {
        return Vechicle_idVechicle;
    }

    public void setVechicle_idVechicle(int Vechicle_idVechicle) {
        this.Vechicle_idVechicle = Vechicle_idVechicle;
    }

    public int getCustomer_idCustomer() {
        return Customer_idCustomer;
    }

    public void setCustomer_idCustomer(int Customer_idCustomer) {
        this.Customer_idCustomer = Customer_idCustomer;
    }

    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
    }

    public int getDriver_idDriver() {
        return Driver_idDriver;
    }

    public void setDriver_idDriver(int Driver_idDriver) {
        this.Driver_idDriver = Driver_idDriver;
    }

    public double getBooking_cost() {
        return booking_cost;
    }

    public void setBooking_cost(double booking_cost) {
        this.booking_cost = booking_cost;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookingFromDate() {
        return bookingFromDate;
    }

    public void setBookingFromDate(Date bookingFromDate) {
        this.bookingFromDate = bookingFromDate;
    }

    public Date getBookingToDate() {
        return bookingToDate;
    }

    public void setBookingToDate(Date bookingToDate) {
        this.bookingToDate = bookingToDate;
    }

    public void save(jdbc database) {
        int id = database.generateID("Rent", "idRent");
        this.setIdRent(id);
        String bookeddate = new SimpleDateFormat("yyyy-MM-dd").format(bookingDate);
        String bookedFrom = new SimpleDateFormat("yyyy-MM-dd").format(bookingFromDate);
        String bookedTo = new SimpleDateFormat("yyyy-MM-dd").format(bookingToDate);
        String query = "insert into Rent values (" + id + "," + this.getVechicle_idVechicle() + "," + this.getCustomer_idCustomer() + ", " + this.getDriver_idDriver() + ", " + this.getBooking_cost() + ",'" + bookeddate + "', '" + this.getStatus() + "', '" + bookedFrom + "', '" + bookedTo + "' )";
        database.update(query);
    }

    public static Vector<Rent> searchRent(String tableCol, String searchText, jdbc database) {

        Vector<Rent> rentList = new Vector<>();

        ResultSet rs = database.search("select * from Rent where " + tableCol + " like "+searchText);
        try {
            while (rs.next()) {
                Rent r = new Rent(rs.getInt(2), rs.getInt(3), rs.getInt(1), rs.getInt(4), rs.getDouble(5), rs.getDate(6), rs.getString(7), rs.getDate(8), rs.getDate(9));
                rentList.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rentList;

    }
    
    public static Vector<Rent> searchRent(String query,jdbc database) {

        Vector<Rent> rentList = new Vector<>();

        ResultSet rs = database.search(query);
        try {
            while (rs.next()) {
                Rent r = new Rent(rs.getInt(2), rs.getInt(3), rs.getInt(1), rs.getInt(4), rs.getDouble(5), rs.getDate(6), rs.getString(7), rs.getDate(8), rs.getDate(9));
                rentList.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rentList;

    }

    public static Rent getRent(int id, jdbc database) {
        Rent d = null;
        try {
            ResultSet rs = database.search("select * from Rent where idRent=" + id);
            while (rs.next()) {

                d = new Rent(rs.getInt(2), rs.getInt(3), rs.getInt(1), rs.getInt(4), rs.getDouble(5), rs.getDate(6), rs.getString(7), rs.getDate(8), rs.getDate(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public void update(jdbc database) {
        
            Date bookingdate_1 =this.getBookingDate();
            Date bookingfrom_1 = this.getBookingFromDate();
            Date bookingto_1 = this.getBookingToDate();
             database.update("update Rent set Vechicle_idVechicle=" + this.getVechicle_idVechicle() + ", Customer_idCustomer=" + this.getCustomer_idCustomer() + " , Driver_idDriver=" + this.getDriver_idDriver() + ", booking_cost=" + this.getBooking_cost() + ", bookingdate='" + (new SimpleDateFormat("yyyy-MM-dd").format(bookingdate_1)) + "', status='" + this.getStatus() + "', bookingFromDate='" + (new SimpleDateFormat("yyyy-MM-dd").format(bookingfrom_1)) + "', bookingToDate='" + (new SimpleDateFormat("yyyy-MM-dd").format(bookingto_1)) + "' where idRent=" + this.getIdRent());
        
       

    }
    
    public void delete(jdbc database){
        database.update("delete from Rent where idRent="+this.getIdRent());
    }
}
