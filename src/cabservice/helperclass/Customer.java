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
public class Customer {
    
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String nic;
    private String mobile;
    private String land;
    private String email;
    private String address;

    public Customer(int id, String firstname, String lastname, String gender, String nic, String mobile, String land, String email, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.nic = nic;
        this.mobile = mobile;
        this.land = land;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
     public boolean save(jdbc database){
        
        int id = database.generateID("Customer","idCustomer");
        database.update("insert into Customer values ("+id+", '"+this.getFirstname()+"', '"+this.getLastname()+"', '"+this.getGender()+"', '"+this.getNic()+"', '"+this.getMobile()+"', '"+this.getLand()+"', '"+this.getEmail()+"', '"+this.getAddress()+"')");
        
        return true;
    }
     
    public static Vector <Customer> getAllCustomers (jdbc database){
          Vector <Customer> customerlist = new Vector <>();
        try {
          
            ResultSet rs = database.search("select * from Customer");
            while (rs.next()){
                 Customer d = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                customerlist.add(d);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerlist;
    } 
    
    public static Vector <Customer> searchCustomer(String tableCol, String searchText, jdbc database){
        Vector <Customer> customerList = new Vector<>();
        try {
            ResultSet rs = database.search("select * from Customer where "+tableCol+" like '"+("%"+searchText+"%")+"'");
            while (rs.next()){
                Customer d = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                customerList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }
    
    public static Customer getCustomer(int id,jdbc database){
        Customer d = null;
        try {
            ResultSet rs = database.search("select * from Customer where idCustomer="+id);
            while (rs.next()){
                  d = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    public boolean deleteCustomer (jdbc database){
        
        database.update("delete from Customer where idCustomer="+this.getId());
        
        return true;
    }
    
    public boolean updateCustomer (jdbc database){
        
         database.update("update Customer set firstname = '"+this.getFirstname()+"', lastname = '"+this.getLastname()+"', gender='"+this.getGender()+"', nic='"+this.getNic()+"', address='"+this.getAddress()+"', mobile='"+this.getMobile()+"', land='"+this.getLand()+"', email='"+this.getEmail()+"' where idCustomer="+this.getId()+";");
         return true;
    } 
    
     
    
}
