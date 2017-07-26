/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice.helperclass;

import cabservice.jdbc;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 * @author icebit
 */
public class ReportGenerate {

    public static void genearateBooking(String filename, Driver driver, SpecificVehicle specVehicle, Customer customer, Rent rent) {
        try {
            

            Document document = new Document();
            OutputStream outputStream = new FileOutputStream(new File(filename + ".pdf"));
            PdfWriter.getInstance(document, outputStream);
            document.open();

            //add logo
            Image logoImage = Image.getInstance("logo.png");
            document.add(logoImage);
            
            Font font = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            document.add(new Paragraph("Booking Sheet", font));
            document.add(new Paragraph("\n"));
            Font textfont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
            Font attributefont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            document.add(new Paragraph("SmartRental (TM) offers the best car rental services, and ensures your safety, please protect this document and show it for your rental cab. This document lists out all the relevant details that you would be needing.", textfont));
            
            //get customer
            String customerfullname = "";
            if (customer.getGender().equals("Male")) {
                customerfullname += "Mr.";
            } else {
                customerfullname += "Mrs.";
            }
            customerfullname += customer.getFirstname();
            customerfullname += " " + customer.getLastname();
            
            document.add(new Paragraph("Customer : "+customerfullname,new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD)));
            
            Font subtopicfont = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL | Font.UNDERLINE | Font.BOLD);
            document.add(new Paragraph("Driver Details", subtopicfont));
            
            //driver details 
            String driverfullname = "";
            if (driver.getGender().equals("Male")) {
                driverfullname += "Mr.";
            } else {
                driverfullname += "Mrs.";
            }

            driverfullname += driver.getFirstName();
            driverfullname += driver.getLastName();

            document.add(new Paragraph("Driver Name : \t" + driverfullname, attributefont));
            document.add(new Paragraph("Driver Mobile : \t" + driver.getMobile(), attributefont));

            document.add(new Paragraph("Vehicle Details", subtopicfont));
            document.add(new Paragraph("Dash No : \t"+specVehicle.getDashno(), attributefont));
            document.add(new Paragraph("Description : \t"+specVehicle.getDescription(), attributefont));
            
            jdbc database = new jdbc();
            Vehicle veh = Vehicle.getVehicle(specVehicle.getVehicle_id(), database);
            Category cat = Category.getCategory(veh.getIdCategory(), database);
            
            document.add(new Paragraph("Vehicle Type : \t"+cat.getCategoryName(), attributefont));
            document.add(new Paragraph("Rent Per First 10 KM : \t Rs."+cat.getRentPerFirst10Km(), attributefont));
            document.add(new Paragraph("Rent Per Next KM : \t Rs."+cat.getRentPerNextKm(), attributefont));
            
            document.add(new Paragraph("Rent Details", subtopicfont));
            document.add(new Paragraph("Booking Date : \t"+(rent.getBookingDate().getDay()+"/"+rent.getBookingDate().getMonth()+"/"+rent.getBookingDate().getYear()), attributefont));
            document.add(new Paragraph("Booking START DATE : \t"+(rent.getBookingToDate().getDay()+"/"+rent.getBookingToDate().getMonth()+"/"+rent.getBookingToDate().getYear()), attributefont));
            document.add(new Paragraph("Booking END DATE : \t"+(rent.getBookingFromDate().getDay()+"/"+rent.getBookingFromDate().getMonth()+"/"+rent.getBookingFromDate().getYear()), attributefont));
            
            document.add(new Paragraph("If you have any questions or suggestions please feel free to call (+94)7774534433. Thank you.", textfont));
            
            document.close();
            outputStream.close();

            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(new File(filename + ".pdf"));
            } else {
                System.out.println("Open is not supported");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
      
    }

 /*  public static void generatePayment(String reportname, Customer customer, Payment payment, Rent rent) {
        try{
        
            Document document = new Document();
            OutputStream outputStream = new FileOutputStream(new File(filename + ".pdf"));
            PdfWriter.getInstance(document, outputStream);
            document.open();

            //add logo
            Image logoImage = Image.getInstance("logo.png");
            document.add(logoImage);
            
            Font font = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            document.add(new Paragraph("Booking Sheet", font));
            document.add(new Paragraph("\n"));
            Font textfont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
            Font attributefont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            document.add(new Paragraph("SmartRental (TM) offers the best car rental services, and ensures your safety, please protect this document and show it for your rental cab. This document lists out all the relevant details that you would be needing.", textfont));
            
            //get customer
            String customerfullname = "";
            if (customer.getGender().equals("Male")) {
                customerfullname += "Mr.";
            } else {
                customerfullname += "Mrs.";
            }
            customerfullname += customer.getFirstname();
            customerfullname += " " + customer.getLastname();
            
            document.add(new Paragraph("Customer : "+customerfullname,new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD)));
            
            
            
            
    }catch(Exception e){
    
    }
*/
}
