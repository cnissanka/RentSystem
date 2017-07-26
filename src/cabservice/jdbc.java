/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cabservice;

import java.sql.Statement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author icebit
 */
public class jdbc {
    
    String url = "jdbc:mysql://localhost/cab";
    String user = "root";
    String pass = "1234";
    
    Connection conn = null;
    Statement stm = null;
    
    public jdbc(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            stm = conn.createStatement();
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
    public void update (String sql){
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet search (String sql){
        System.out.println(sql);
        ResultSet rs = null;
        try{
            rs = stm.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    
    public void clearAll (Vector <JTextField> txt){
        for (JTextField t : txt){
            t.setText("");
        }
    }
    
    private boolean isDuplicatedID (int id, String tablename, String idcol){
        boolean val = false;
        try {
            String res = null;
            ResultSet rs = this.search("select * from "+tablename+" where "+idcol+"="+id+";");
            while (rs.next()){
                res = rs.getString(idcol);
                System.out.println(res);
            }
            
            if (res!=null){
                val = true;
            } 
        } catch (Exception e) {
        }
        
        return val;
    }
    
    public int generateID (String tablename,String colname){
        ResultSet rs = this.search("select count(*) as cmt from "+tablename);
        int id= 0;
        
        try{
            while (rs.next()){
                id = rs.getInt("cmt") + 1; 
                System.out.println("id in beg " +id);
            }
            
         //check to see if duplicate
         boolean isduplicated = isDuplicatedID(id, tablename, colname);
            System.out.println(isduplicated);
         while (isduplicated == true){
             id++;
             isduplicated = isDuplicatedID(id, tablename, colname);
         }
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
    
    public void fillDataToTable (JTable jtable, String tableName, String colArr []){
        DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
        dtm.setNumRows(0);
        ResultSet rs = this.search("select * from "+tableName);
        try{
            while (rs.next()){
                Vector v = new Vector();
                for (String s : colArr){
                    v.add(rs.getObject(s));
                }
                dtm.addRow(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void generateDateList (JComboBox cmbDate, JComboBox cmbMonth, JComboBox cmbYear){
        
        DefaultComboBoxModel cmbDate_dcm = (DefaultComboBoxModel) cmbDate.getModel();
        //DefaultComboBoxModel cmbMonth_dcm = (DefaultComboBoxModel) cmbMonth.getModel();
        DefaultComboBoxModel cmbYear_dcm = (DefaultComboBoxModel) cmbYear.getModel();
        
        for (int i = 1; i <= 31; i++){
            cmbDate_dcm.addElement(new Integer(i));
        }
              
        
        for (int i = 1900; i <= 3000; i++){
            cmbYear_dcm.addElement(new Integer(i));
        }
        
        //Tue May 02 16:52:36 IST 2017
        
        String date []= new Date().toString().split(" ");
        
        //get day 
        String day = date[2];
        
        //get Month
        String month = date[1];
        
        //get Year
        String year = date[5];
        
        cmbDate.setSelectedItem(Integer.parseInt(day));
        cmbMonth.setSelectedItem(month);
        cmbYear.setSelectedItem(Integer.parseInt(year));
       
        
    }
    
    public void loadColsForComboBox (String tblname, JComboBox cmbbox){
        try{
            ResultSet rs = this.search("describe "+tblname+";");
       
             DefaultComboBoxModel dtc = (DefaultComboBoxModel) cmbbox.getModel();
             dtc.removeAllElements();
            while(rs.next()){
             //   vecString.add(rs.getString("Field"));
                  dtc.addElement(rs.getString("Field"));
                  System.out.println(rs.getString("Field"));
            }
           
          
        }catch(Exception e){
            
        }
        
        
    }
    
    public boolean isDuplicated (String tablename, String colname, String currentname){
        
        ResultSet rs = this.search("select * from "+tablename+" where "+colname+" = "+currentname);
        String res = "";
        try {
            while (rs.next()){
                res = rs.getString(colname);
            }
        } catch (SQLException ex) {
            Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (!res.equals(""));
        
    }
    
    
}
