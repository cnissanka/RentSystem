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
public class Vehicle {
    
    private int id;
    private int idCategory;
    private String brandName;
    private int qty;

    public Vehicle(int id, int idCategory, String brandName, int qty) {
        this.id = id;
        this.idCategory = idCategory;
        this.brandName = brandName;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    public boolean save (jdbc database){
        
        int id = database.generateID("Vechicle","idVechicle");
        this.setId(id);
        database.update("insert into Vechicle values ("+id+", '"+this.getIdCategory()+"', '"+this.getBrandName()+"', "+this.getQty()+")");
        
        
        return true;
    }
    
    
    public static Vector <Vehicle> searchVehicle(String tableCol, String searchText, jdbc database){
        Vector <Vehicle> vehicleList = new Vector<>();
        try {
            ResultSet rs = database.search("select * from Vechicle where "+tableCol+" like '"+("%"+searchText+"%")+"'");
            while (rs.next()){
                Vehicle d = new Vehicle(rs.getInt(1), rs.getInt(2), rs.getString("VehicleBrand"), rs.getInt(4));
                
                vehicleList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleList;
    }
    
    public static Vehicle getVehicle(int id,jdbc database){
        Vehicle d = null;
        try {
            ResultSet rs = database.search("select * from Vechicle where idVechicle="+id);
            while (rs.next()){
               d = new Vehicle(rs.getInt(1), rs.getInt(2), rs.getString("VehicleBrand"), rs.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    public boolean deleteVehicle (jdbc database){
        
        database.update("delete from Vechicle where idVechicle="+this.getId());
        
        return true;
    }
    
    public boolean updateCustomer (jdbc database){
        
         database.update("update Vechicle set idCategory = "+this.getIdCategory() + ", VehicleBrand='"+this.getBrandName()+"', VehicleQty="+this.getQty() + " where idVechicle="+this.getId()+";");
         
         return true;
    } 
    
}
