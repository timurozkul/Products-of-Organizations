/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Clients;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author timurozkul
 */
public class Input {
    
    public static final String CLIENTINFO_FILE = "clientInfo.txt";
    public static final String DELIMITER = "::";
 
    Workbook workbook = new HSSFWorkbook();
    Sheet sheetData = workbook.createSheet("Data");
    Sheet sheetAnalysisByGroup = workbook.createSheet("AnalysisByGroup");
    Sheet sheetAnalysisByCountry = workbook.createSheet("AnalysisByCountry");
    Sheet sheetAnalysisByReservation = workbook.createSheet("AnalysisByReservation");
    
    private HashMap<Integer, Clients> clients = new HashMap<>();
    
    
       public void loadClients() throws FileNotFoundException{
        
        Scanner sc = new Scanner(new BufferedReader(new FileReader(CLIENTINFO_FILE)));
        int counterId = 0; 
        
        while (sc.hasNext()) {
            
            String recordLine = sc.nextLine();
            String[] recordProperties = recordLine.split(DELIMITER);
            counterId ++;
            
            if(recordProperties.length == 10){
                Clients client = new Clients();
                
                client.setFirstName(recordProperties[0]);
                client.setLastName(recordProperties[1]);
                client.setCheckIn(DateFormating(recordProperties[2]));
                
                client.setMonth(recordProperties[2].substring(4, 6));
                client.setCheckOut(DateFormating(recordProperties[3]));
                client.setPax(Integer.parseInt(recordProperties[4]));
                client.setReservationDate(DateFormating(recordProperties[5]));
                client.setCountry(recordProperties[6]);
                //skipping 7 age.
                client.setBookingType(recordProperties[8]);
                client.setGroupName(recordProperties[9]);
                
                client.setId(counterId);
                clients.put(counterId, client);
            }
        }
    }
       
    public HashMap<Integer, Clients> getClientData(){
        return clients;
    }
    
    public Sheet getSheetData(){
        return sheetData;
    }
    
    public Sheet getSheetGroup(){
        return sheetAnalysisByGroup;
    }
    
    public Sheet getSheetRes(){
        return sheetAnalysisByReservation;
    }
    
    public Sheet getSheetCountry(){
        return sheetAnalysisByCountry;
    }
    
    
    public String DateFormating(String unformatedDate){

        String formatedDate = unformatedDate.substring(4, 6) + "/" + unformatedDate.substring(6, unformatedDate.length()) + "/"+ unformatedDate.substring(0, 4);
        return formatedDate;
    } 
    
     public void finish(){
     
        try{
            FileOutputStream output = new FileOutputStream("marketStudy2.xls");
            workbook.write(output);
            output.close();
        } catch(Exception e){
            //               e.printStackTrace();
        }
    }
}
