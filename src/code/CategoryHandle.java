/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package code;

import cabservice.jdbc;
import javax.swing.JTable;

/**
 *
 * @author icebit
 */
public class CategoryHandle {
    
     public static void add(String catName, String catDesc, String rentPerKM,JTable jtable, jdbc j){
     
         int catid = j.generateID("Category","");
         j.update("insert into Category values ('"+catid+"', '"+catName+"', '"+catDesc+"','"+rentPerKM+"')");
         updateTable(j, jtable);
         
     }
     
     public static void updateTable (jdbc j, JTable jtable1){
         String cols [] = {"idCategory", "CategoryName", "CategoryDescription", "RentPerKm"};
         j.fillDataToTable(jtable1, "Category", cols);
     }
    
}
