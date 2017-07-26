/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.helperclass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author icebit
 */
public class test {
    public static void main(String[] args) {
        String s = "2017-05-27";
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(s);
            System.out.println(d.toString());
        } catch (ParseException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
