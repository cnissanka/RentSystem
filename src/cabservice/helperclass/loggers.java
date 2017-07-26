/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.helperclass;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 *
 * @author icebit
 */
public class loggers {
    
    public static void initLogger(){
    try{
        String path="mylog.txt";
        PatternLayout layout=new PatternLayout("%-3p %-10d %m %L %n");
        
        RollingFileAppender apender=new RollingFileAppender(layout, path);
        apender.setName("MyLog");
        apender.setMaxFileSize("1MB");
        apender.activateOptions();
        
        Logger.getRootLogger().addAppender(apender);
    }catch (Exception e){
    }
    }
    
}
