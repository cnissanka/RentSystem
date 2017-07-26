/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package code;

import cabservice.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author icebit
 */
public class LoginHandle {
    
    public static void add (String user, String pass, jdbc j, Vector <JTextField> clc, JTable jtable){
        int id = 0;
        ResultSet rs = j.search("select count(*) as cmt from Login");
        try {
            while (rs.next()){
                id = rs.getInt("cmt") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
        j.update("insert into Login values ("+id+",'"+user+"', '"+pass+"')");
        
        for (JTextField t : clc){
            t.setText("");
        }
        updateLoginTable(jtable, j);
    }
    
    public static void updateLoginTable (JTable jtable, jdbc j){
        
        ResultSet rs = j.search("select * from Login");
        DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
        dtm.setNumRows(0);
        try{
            while (rs.next()){
                Vector v= new Vector();
                v.add(rs.getInt("idUser"));
                v.add(rs.getString("Username"));
                v.add(rs.getString("Password"));
                dtm.addRow(v);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
