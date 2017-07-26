/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.helperclass;

import cabservice.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author icebit
 */
public class Login {
    private int id;
    private String username;
    private String password;

    public Login(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
     public boolean add(jdbc database){
         
         int id = database.generateID("Login","idUser");
         this.setId(id);
         database.update("insert into Login values ("+id+", '"+this.getUsername()+"', '"+this.getPassword()+"');");
         
         return true;
    }
    
    public static Vector <Login> searchLogin(String tableCol, String searchText, jdbc database){
       
        Vector<Login> loginList = new Vector<>();
        
         ResultSet rs = database.search("select * from Login where "+tableCol+" like '"+(searchText+"%")+"'");
        try {
            while (rs.next()){
                Login d = new Login(rs.getInt(1), rs.getString(2), rs.getString(3));
                loginList.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loginList;
        
    }
    
    public static Login getLogin(int id,jdbc database){
        Login d = null;
        try {
            ResultSet rs = database.search("select * from Login where idUser="+id);
            while (rs.next()){
              d = new Login(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    public boolean deleteLogin (jdbc database){
        database.update("delete from Login where idUser="+this.getId());
        
        return true;
    }
    
    public boolean updateLogin (jdbc database){
     
         database.update("update Login set Username='"+this.getUsername()+"', Password='"+this.getPassword()+"' where idUser="+this.getId());
         return true;
    }
    
    
}
