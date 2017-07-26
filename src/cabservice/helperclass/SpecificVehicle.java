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
public class SpecificVehicle {

    private int id;
    private String dashno;
    private String description;
    private int vehicle_id;
    private String avaliability;
    
    
    public SpecificVehicle(int id, String dashno, String description, int vehicle_id, String avaliability) {
        this.id = id;
        this.dashno = dashno;
        this.description = description;
        this.vehicle_id = vehicle_id;
        this.avaliability = avaliability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDashno() {
        return dashno;
    }

    public void setDashno(String dashno) {
        this.dashno = dashno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getAvaliability() {
        return avaliability;
    }

    public void setAvaliability(String avaliability) {
        this.avaliability = avaliability;
    }
    
    
    public boolean isDashNumberDuplicated (jdbc database){
       String res = null;
        try {
            
            ResultSet rs = database.search("select * from SpecificVehicle where DashNo='"+this.getDashno()+"'");
            while (rs.next()){
                res = rs.getString("DashNo");
            }
            
            
        } catch (Exception e) {
        }
        if (res == null){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean add(jdbc database){
        int id = database.generateID("SpecificVehicle","idSpecificVehicle");
        this.setId(id);
        
        Vehicle v = Vehicle.getVehicle(this.getVehicle_id(), database);
        v.setQty((v.getQty()+1));
        
        v.updateCustomer(database);
        
        database.update("insert into SpecificVehicle values ("+id+", '"+this.getDashno()+"', '"+this.getDescription()+"'"+", "+this.getVehicle_id()+", '"+this.getAvaliability()+"')");
        
        return true;
    }
    
    public static Vector <SpecificVehicle> searchSpecificVehicle(String tableCol, String searchText, jdbc database){
        Vector <SpecificVehicle> specificVehicleList = new Vector<>();
        try {
            ResultSet rs = database.search("select * from SpecificVehicle where "+tableCol+" like '"+("%"+searchText+"%")+"'");
            while (rs.next()){
                SpecificVehicle d = new SpecificVehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                specificVehicleList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specificVehicleList;
    }
    
    public static SpecificVehicle getSpecificVehicle(int id,jdbc database){
        SpecificVehicle d = null;
        try {
            ResultSet rs = database.search("select * from SpecificVehicle where idSpecificVehicle="+id);
            while (rs.next()){
             d = new SpecificVehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    public boolean deleteSpecificVehicle (jdbc database){
        
        database.update("delete from SpecificVehicle where idSpecificVehicle="+this.getId());
        int id = this.getVehicle_id();
        Vehicle v = Vehicle.getVehicle(id, database);
        v.setQty((v.getQty() - 1));
        v.updateCustomer(database);
        return true;
    }
    
    public boolean updateSpecificVehicle (jdbc database){
        
         String query = "UPDATE SpecificVehicle SET DashNo = '"+this.getDashno()+"', Description = '"+this.getDescription()+"', Vechicle_idVechicle = "+this.getVehicle_id()+", avaliablity = '"+this.getAvaliability()+"' where idSpecificVehicle= " +this.getId()+ ";";
         database.update(query);
         return true;
    }
    
    
    
}
