/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MonthsRes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author timurozkul
 */
public class AnalysisByReservation {
    Input dao = new Input();
    List rows = new ArrayList();
    CBMSetter cbs = new CBMSetter();
    
    public void createHeaders(Input dao, CBMSetter cbs) {
        this.dao = dao;
        this.cbs = cbs;
        rowCreator();
       
        Row row1 = (Row)rows.get(2);
        
         Row rowT = (Row)rows.get(1);
        Cell cT1 = rowT.createCell(1);
        cT1.setCellValue("Check In 2016 vs 2015 Reservation");
        
        Cell cJan = row1.createCell(1);
        cJan.setCellValue("January");
        
        Cell cFeb = row1.createCell(2);
        cFeb.setCellValue("February");

        Cell cMar = row1.createCell(3);
        cMar.setCellValue("March");

        Cell cApr = row1.createCell(4);
        cApr.setCellValue("April");

        Cell cMay = row1.createCell(5);
        cMay.setCellValue("May");

        Cell cJun = row1.createCell(6);
        cJun.setCellValue("June");

        Cell cJul = row1.createCell(7);
        cJul.setCellValue("July");

        Cell cAug = row1.createCell(8);
        cAug.setCellValue("August");

        Cell cSep = row1.createCell(9);
        cSep.setCellValue("September"); 
        
        Cell cOct = row1.createCell(10);
        cOct.setCellValue("October"); 
        
        Cell cNov = row1.createCell(11);
        cNov.setCellValue("November"); 
        
        Cell cDec = row1.createCell(12);
        cDec.setCellValue("December"); 
        
        //vertical months list
 
        
        Row row2 = (Row)rows.get(3);
        Cell cJanV = row2.createCell(0);
        cJanV.setCellValue("January");
        
        Row row3 = (Row)rows.get(4);
        Cell cFebV = row3.createCell(0);
        cFebV.setCellValue("February");
        
        Row row4 = (Row)rows.get(5);
        Cell cMarV = row4.createCell(0);
        cMarV.setCellValue("March");
        
        Row row5 = (Row)rows.get(6);
        Cell cAprV = row5.createCell(0);
        cAprV.setCellValue("April");
        
        Row row6 = (Row)rows.get(7);
        Cell cMayV = row6.createCell(0);
        cMayV.setCellValue("May");
        
        Row row7 = (Row)rows.get(8);
        Cell cJunV = row7.createCell(0);
        cJunV.setCellValue("June");
        
        Row row8 = (Row)rows.get(9);
        Cell cJulV = row8.createCell(0);
        cJulV.setCellValue("July");
        
        Row row9 = (Row)rows.get(10);
        Cell cAugV = row9.createCell(0);
        cAugV.setCellValue("August");
        
        Row row10 = (Row)rows.get(11);
        Cell cSepV = row10.createCell(0);
        cSepV.setCellValue("September");
       
        Row row11 = (Row)rows.get(12);
        Cell cOctV = row11.createCell(0);
        cOctV.setCellValue("October");
        
        Row row12 = (Row)rows.get(13);
        Cell cNovV = row12.createCell(0);
        cNovV.setCellValue("November");
        
        Row row13 = (Row)rows.get(14);
        Cell cDecV = row13.createCell(0);
        cDecV.setCellValue("December");
        
        // ------------- 2016 ---------------
        Row row2ndT = (Row)rows.get(18);
        Cell cT = row2ndT.createCell(1);
        cT.setCellValue("Check In 2016 vs 2016 Reservation");
        
        Row row2nd = (Row)rows.get(19);
         Cell cJan1 = row2nd.createCell(1);
        cJan1.setCellValue("January");
        
        Cell cFeb2 = row2nd.createCell(2);
        cFeb2.setCellValue("February");

        Cell cMar3 = row2nd.createCell(3);
        cMar3.setCellValue("March");

        Cell cApr4 = row2nd.createCell(4);
        cApr4.setCellValue("April");

        Cell cMay5 = row2nd.createCell(5);
        cMay5.setCellValue("May");

        Cell cJun6 = row2nd.createCell(6);
        cJun6.setCellValue("June");

        Cell cJul7 = row2nd.createCell(7);
        cJul7.setCellValue("July");

        Cell cAug8 = row2nd.createCell(8);
        cAug8.setCellValue("August");

        Cell cSep9 = row2nd.createCell(9);
        cSep9.setCellValue("September"); 
        
        Cell cOct10 = row2nd.createCell(10);
        cOct10.setCellValue("October"); 
        
        Cell cNov11 = row2nd.createCell(11);
        cNov11.setCellValue("November"); 
        
        Cell cDec12 = row2nd.createCell(12);
        cDec12.setCellValue("December"); 
        
        //vertical months list
        
        
        Row row18 = (Row)rows.get(20);
        Cell cJanV2 = row18.createCell(0);
        cJanV2.setCellValue("January");
        
        Row row19 = (Row)rows.get(21);
        Cell cFebV3 = row19.createCell(0);
        cFebV3.setCellValue("February");
        
        Row row20 = (Row)rows.get(22);
        Cell cMarV4 = row20.createCell(0);
        cMarV4.setCellValue("March");
        
        Row row21 = (Row)rows.get(23);
        Cell cAprV5 = row21.createCell(0);
        cAprV5.setCellValue("April");
        
        Row row22 = (Row)rows.get(24);
        Cell cMayV6 = row22.createCell(0);
        cMayV6.setCellValue("May");
        
        Row row23 = (Row)rows.get(25);
        Cell cJunV112 = row23.createCell(0);
        cJunV112.setCellValue("June");
        
        Row row24 = (Row)rows.get(26);
        Cell cJulV7 = row24.createCell(0);
        cJulV7.setCellValue("July");
        
        Row row25 = (Row)rows.get(27);
        Cell cAugV8 = row25.createCell(0);
        cAugV8.setCellValue("August");
        
        Row row26 = (Row)rows.get(28);
        Cell cSepV9 = row26.createCell(0);
        cSepV9.setCellValue("September");
       
        Row row27 = (Row)rows.get(29);
        Cell cOctV10 = row27.createCell(0);
        cOctV10.setCellValue("October");
        
        Row row28 = (Row)rows.get(30);
        Cell cNovV11 = row28.createCell(0);
        cNovV11.setCellValue("November");
        
        Row row29 = (Row)rows.get(31);
        Cell cDecV12 = row29.createCell(0);
        cDecV12.setCellValue("December");
    }
    
    public void resPrinter(){
        // ----------------- 2015 Reservations
        for(int i = 0; i < cbs.getClientsBM().size(); i++){//checkIn month 
            for(int k = 1; k < cbs.getClientsBM().get(i).getRes2015().size(); k++){//reservation month
                Collection<String> keys = cbs.getClientsBM().get(i).getRes2015().get(k).getByCountry().keySet();
                int totalPax = 0;
                for(String x: keys){//adding the total people
                  totalPax = cbs.getClientsBM().get(i).getRes2015().get(k).getByCountry().get(x) + totalPax;   
                }
                
                Row row = (Row)rows.get(k+2);
                Cell cell = row.createCell(i+1);
                cell.setCellValue(totalPax);
            }                   
        }
        
        // ----------------- 2016 Reservations
        
        for(int i = 0; i < cbs.getClientsBM().size(); i++){//checkIn month 
            for(int k = 1; k < cbs.getClientsBM().get(i).getRes2016().size(); k++){//reservation month
                Collection<String> keys = cbs.getClientsBM().get(i).getRes2016().get(k).getByCountry().keySet();
                int totalPax = 0;
                for(String x: keys){//adding the total people
                  totalPax = cbs.getClientsBM().get(i).getRes2016().get(k).getByCountry().get(x) + totalPax;   
                }
                
                Row row16 = (Row)rows.get(k+19);
                Cell cell16 = row16.createCell(i+1);
                cell16.setCellValue(totalPax);
            }                   
        }   
        
        
         for(int i = 0; i < cbs.getClientsBM().size(); i++){//checkIn month 
            int totalPax = 0;
            for(int k = 0; k < cbs.getClientsBM().get(i).getResUnknown().size(); k++){//reservation month
                Collection<String> keys = cbs.getClientsBM().get(i).getResUnknown().get(k).getByCountry().keySet();
                for(String x: keys){//adding the total people
                  totalPax = cbs.getClientsBM().get(i).getResUnknown().get(k).getByCountry().get(x) + totalPax;   
                }               
            } 
            Row rowUknown = (Row)rows.get(34);
            Cell cellUknown = rowUknown.createCell(i+1);
            cellUknown.setCellValue(totalPax); 
        }        
        
        
    }
    
    public void rowCreator(){
        for(int i = 0; i < 150;  i++){
            rows.add(dao.getSheetRes().createRow(i));
        }
    }  
}
