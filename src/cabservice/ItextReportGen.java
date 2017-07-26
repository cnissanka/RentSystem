/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabservice;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author icebit
 */
public class ItextReportGen {
    
    public static void createReport(String title, String tablename, jdbc j, String [] cols) {
        try {
        Document document = new Document();
         PdfWriter.getInstance(document,new FileOutputStream("Report.pdf"));
            document.open();;
            PdfPTable table = new PdfPTable(cols.length);
            document.add(new Paragraph(title, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.RED)));
            document.add(new Paragraph("------------------------------------------------------------------------------------------------------------"));
       
            ResultSet rs = j.search("select * from "+tablename+";");
            while (rs.next()){
               for (String s : cols){
                   table.addCell(rs.getString(s));
               }
               table.completeRow();
            }
            
            document.add(table);
            document.close();;
            
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(new File("Report.pdf"));
            } else {
                System.out.println("Open is not supported");
            }
            
        } catch (DocumentException ex) {
            Logger.getLogger(ItextReportGen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItextReportGen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItextReportGen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ItextReportGen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public static void printInvoice(String date, int driver, int vech, int customer, String customername, String km, String rentcost, String method, String amt, String balance ){
        try {
            Document doc = new Document();
               PdfWriter.getInstance(doc,new FileOutputStream("invoice.pdf"));
            doc.open();;
            
            PdfPTable table = new PdfPTable(2);
            doc.add(new Paragraph("Invoice Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.RED)));
            doc.add(new Paragraph("------------------------------------------------------------------------------------------------------------"));
            
            table.addCell("Date of Transcation");
            table.addCell(date);
            
            table.completeRow();
            
            table.addCell("Customer ID ");
            table.addCell(customer+"");
            
            table.addCell("Customer Name ");
            table.addCell(customername+"");
            
            table.addCell("Driver ID");
            table.addCell(driver+"");
            
            table.addCell("Vehicle ID");
            table.addCell(vech+"");
            
            table.addCell("KM");
            table.addCell(km+"");
            
            table.addCell("Rent Cost");
            table.addCell("Rs. "+rentcost);
            
            table.addCell("Payment Method");
            table.addCell(method);
            
            table.addCell("Rent Amount");
            table.addCell("Rs. " +amt);
            
            table.addCell("Balance Due / Surplus");
            table.addCell("Rs. "+balance);
            
            doc.add(table);
            
            doc.close();;
            
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(new File("invoice.pdf"));
            } else {
                System.out.println("Open is not supported");
            }
            
        } catch (Exception e) {
        }
    }
    
   
}
