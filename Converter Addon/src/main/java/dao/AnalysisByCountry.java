/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author timurozkul
 */
public class AnalysisByCountry {
    Input dao = new Input();
    List rows = new ArrayList();
    CBMSetter cbs = new CBMSetter();
    
    public void createHeaders(Input dao, CBMSetter cbs) {
        this.dao = dao;
        this.cbs = cbs;
        rowCreator();
       
        Row row1 = (Row)rows.get(1);
        
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
        
    }
    
    public void countryPrinter(){
        
        for(int i = 0; i < cbs.getClientsBM().size(); i++){
            int counter = 1;
            Collection<String> keys = cbs.getClientsBM().get(i).getByCountry().keySet();
            for(String k: keys){
            counter++;
            int countryPax = (int)cbs.getClientsBM().get(i).getByCountry().get(k);
            Row row = (Row)rows.get(counter);
            Cell cellHeader = row.createCell(0);
            cellHeader.setCellValue(k);
            Cell cell = row.createCell(i + 1);
            cell.setCellValue(countryPax);
            }
        }
    }
    
    public void rowCreator(){
        for(int i = 0; i < 150;  i++){
            rows.add(dao.getSheetCountry().createRow(i));
        }
    }  
}
