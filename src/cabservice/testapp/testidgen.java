/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.testapp;

import cabservice.jdbc;

/**
 *
 * @author icebit
 */
public class testidgen {
    public static void main(String[] args) {
        
        int id = new jdbc().generateID("Login", "idUser");
        System.out.println(id);
        
    }
}
