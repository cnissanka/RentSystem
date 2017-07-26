/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.testapp;

import cabservice.helperclass.ReportGenerate;
import java.util.regex.*;

/**
 *
 * @author icebit
 */
public class regextest {

    public static void main(String[] args) {
        String test = "abcdef";

        //Word that is 2 to 20 characters in length
        //[A-Za-z]{2,20} \\w{2,20}
       // regexChecker("\\s[A-Za-z]{2,20}\\s", " chirath nissanka 23424324 asb tyrtytryty ");
     /*  
       //zip code is 5 digits long
       regexChecker("\\d{5}\\s", "554545 54545 54333");
       //chirath.abc@gmail.com
       regexChecker("[A-Za-z0-9._%-]+@[A-Za-z0-9._-]+\\.[A-Za-z]{2,4}", "@.com");
       System.out.println(validateForm("[A-Za-z0-9._%-]+@[A-Za-z0-9._-]+\\.[A-Za-z]{2,4}", "077853220"));
       System.out.println(validateForm("[0-9]{9}[Vv]{1}","9826140sss40v"));*/
     
     //   ReportGenerate.genearateBooking("abc");
System.out.println(     validateForm("[0-9]*","4000"));
    }

    public static void regexChecker(String theRegex, String str2check) {
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexmatcher = checkRegex.matcher(str2check);

        while (regexmatcher.find()) {
            if (regexmatcher.group().length() != 0) {
                System.out.println(regexmatcher.group().trim());
            }
            System.out.println("Start index: " + regexmatcher.start());
            System.out.println("End Index; " + regexmatcher.end());
        }

    }
    
    public static boolean validateForm (String theRegex, String str2check){
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexmatcher = checkRegex.matcher(str2check);

        while (regexmatcher.find()) {
            if (regexmatcher.group().length() != 0) {
                if (regexmatcher.group().trim().equals(str2check))
                {
                    return true;
                }
            }
           
        }
        return false;
    }
    
    
}
