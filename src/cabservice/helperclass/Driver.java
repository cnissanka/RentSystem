/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.helperclass;

import cabservice.jdbc;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author icebit
 */
public class Driver {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String nic;
    private String address;
    private String mobile;
    private String avaliablity;
    private double salary;

    public Driver(int id, String firstName, String lastName, String gender, String nic, String address, String mobile, String avaliablity, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nic = nic;
        this.address = address;
        this.mobile = mobile;
        this.avaliablity = avaliablity;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvaliablity() {
        return avaliablity;
    }

    public void setAvaliablity(String avaliablity) {
        this.avaliablity = avaliablity;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public boolean save(jdbc database){
        
        int id = database.generateID("Driver","idDriver");
        database.update("insert into Driver values ("+id+", '"+this.getFirstName()+"', '"+this.getLastName()+"', '"+this.getGender()+"', '"+this.getNic()+"', '"+this.getAddress()+"', '"+this.getMobile()+"', '"+this.getAvaliablity()+"', "+this.getSalary()+")");
        
        return true;
    }
    
    public static Vector <Driver> searchDriver(String tableCol, String searchText, jdbc database){
        Vector <Driver> driverList = new Vector<>();
        try {
            ResultSet rs = database.search("select * from Driver where "+tableCol+" like '"+("%"+searchText+"%")+"'");
            while (rs.next()){
                Driver d = new Driver(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9));
                driverList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driverList;
    }
    
    public static Vector <Driver> searchDriverCustomQuery(String query, jdbc database){
        Vector <Driver> driverList = new Vector<>();
        try {
            ResultSet rs = database.search(query);
            while (rs.next()){
                Driver d = new Driver(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9));
                driverList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driverList;
    }
    
    public static Driver getDriver(int id,jdbc database){
        Driver d = null;
        try {
            ResultSet rs = database.search("select * from Driver where idDriver="+id);
            while (rs.next()){
                d = new Driver(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    public boolean deleteDriver ( jdbc database){
        
        database.update("delete from Driver where idDriver="+this.getId());
        
        return true;
    }
    
    public boolean updateDriver ( jdbc database){
        
         database.update("update Driver set FirstName = '"+this.getFirstName()+"', LastName = '"+this.getLastName()+"', gender='"+this.getGender()+"', nic='"+this.getNic()+"', address='"+this.getAddress()+"', mobile='"+this.getMobile()+"', avalibility='"+this.getAvaliablity()+"', salary="+this.getSalary()+" where idDriver="+this.getId()+";");
         return true;
    }
    

}
