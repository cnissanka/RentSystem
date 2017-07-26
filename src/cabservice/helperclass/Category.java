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
public class Category {
    
    private int id;
    private String categoryName;
    private String categoryDescription;
    private double rentPerFirst10Km;
    private double rentPerNextKm; 

    public Category(int id, String categoryName, String categoryDescription, double rentPerFirst10Km, double rentPerNextKm) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.rentPerFirst10Km = rentPerFirst10Km;
        this.rentPerNextKm = rentPerNextKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public double getRentPerFirst10Km() {
        return rentPerFirst10Km;
    }

    public void setRentPerFirst10Km(double rentPerFirst10Km) {
        this.rentPerFirst10Km = rentPerFirst10Km;
    }

    public double getRentPerNextKm() {
        return rentPerNextKm;
    }

    public void setRentPerNextKm(double rentPerNextKm) {
        this.rentPerNextKm = rentPerNextKm;
    }
    
    public boolean add(jdbc database){
        int id = database.generateID("Category","idCategory");
        this.setId(id);
        database.update("insert into Category values ("+id+", '"+this.getCategoryName()+"', '"+this.getCategoryDescription()+"'"+", "+this.getRentPerFirst10Km()+","+this.getRentPerNextKm()+");");
        
        return true;
    }
    
    public static Vector <Category> searchCategory(String tableCol, String searchText, jdbc database){
        Vector <Category> categoryList = new Vector<>();
        try {
            ResultSet rs = database.search("select * from Category where "+tableCol+" like '"+("%"+searchText+"%")+"'");
            while (rs.next()){
                Category d = new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
                categoryList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }
    
    public static Category getCategory(int id,jdbc database){
        Category d = null;
        try {
            ResultSet rs = database.search("select * from Category where idCategory="+id);
            while (rs.next()){
              d = new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    public boolean deleteCategory (jdbc database){
        
        database.update("delete from Category where idCategory="+this.getId());
        
        return true;
    }
    
    public boolean updateCategory (jdbc database){
        
         database.update("update Category set CategoryName='"+this.getCategoryName()+"', CategoryDescription='"+this.getCategoryDescription()+"', RentPerFirst10="+this.getRentPerFirst10Km()+", RentPerNextKM="+this.getRentPerNextKm() + " where idCategory="+this.getId());
         return true;
    }
    
    
    
}
