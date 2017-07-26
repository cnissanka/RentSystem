/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package code;

import cabservice.jdbc;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author icebit
 */
public class VechileHandle {
    
    public static void add(String vehicleNo, String vechileBrand, int catID, JTable jtable, jdbc j){
        int id = j.generateID("Vechicle","");
        j.update("insert into Vechicle values ("+id+","+catID+", '"+vehicleNo+"', '"+vechileBrand+"')");
        updateTable(jtable, j);
        
    }
    
    
    public static void updateTable (JTable jtable, jdbc j){
        ResultSet rs = j.search("select * from Vechicle");
        DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
        dtm.setNumRows(0);
        try{
            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getInt("idVechicle"));
                int catid = rs.getInt("idCategory");
                v.add(catid);
                v.add(rs.getString("VehicleNumber"));
                v.add(rs.getString("VehicleBrand"));
                dtm.addRow(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void updateCategorycombo(jdbc j, JComboBox combobox){
        DefaultComboBoxModel dtc = (DefaultComboBoxModel) combobox.getModel();
        dtc.removeAllElements();
        
        ResultSet rs = j.search("select * from Category");
        try{
            while (rs.next()){
                dtc.addElement(rs.getInt("idCategory"));
            }
        }catch(Exception e){
            
        }
    }
}
