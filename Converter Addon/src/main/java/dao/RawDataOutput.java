/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/**
 *
 * @author timurozkul
 */
public class RawDataOutput {
    
    Input dao = new Input();
    List rows = new ArrayList();
     
    public void createHeaders(Input dao) throws FileNotFoundException{
        this.dao = dao;
        rowCreator();
       
        Row row1 = (Row)rows.get(1);
        
        Cell cFirstNameTitle = row1.createCell(1);
        cFirstNameTitle.setCellValue("First Name");
        
        Cell cLastNameTitle = row1.createCell(2);
        cLastNameTitle.setCellValue("Last Name");

        Cell cInDate = row1.createCell(3);
        cInDate.setCellValue("Check In Date");

        Cell cOutDate = row1.createCell(4);
        cOutDate.setCellValue("Check Out Date");

        Cell cFolioV = row1.createCell(5);
        cFolioV.setCellValue("PAX");

        Cell cReservationDate = row1.createCell(6);
        cReservationDate.setCellValue("Reservation Date");

        Cell cCountry = row1.createCell(7);
        cCountry.setCellValue("Country");

        Cell cBookingType = row1.createCell(8);
        cBookingType.setCellValue("Booking Type");

        Cell cGroup = row1.createCell(9);
        cGroup.setCellValue("Group Name"); 
    }
    
    public void createRawData(){
       
        Collection<Integer> keys = dao.getClientData().keySet();
         
        int counter = 2;
        for(Integer i: keys){

           Cell cell = dao.getSheetData().createRow(counter).createCell(1);
           Cell cell2 = dao.getSheetData().createRow(counter).createCell(2);
           Cell cell3 = dao.getSheetData().createRow(counter).createCell(3);
           Cell cell4 = dao.getSheetData().createRow(counter).createCell(4);
           Cell cell5 = dao.getSheetData().createRow(counter).createCell(5);
           Cell cell6 = dao.getSheetData().createRow(counter).createCell(6);
           Cell cell7 = dao.getSheetData().createRow(counter).createCell(7);
           Cell cell8 = dao.getSheetData().createRow(counter).createCell(8);
           Cell cell9 = dao.getSheetData().createRow(counter).createCell(9);

           cell.setCellValue(dao.getClientData().get(i).getFirstName());
           cell2.setCellValue(dao.getClientData().get(i).getLastName());
           cell3.setCellValue(dao.getClientData().get(i).getCheckIn());
           cell4.setCellValue(dao.getClientData().get(i).getCheckOut());
           cell5.setCellValue(dao.getClientData().get(i).getPax());
           cell6.setCellValue(dao.getClientData().get(i).getReservationDate());
           cell7.setCellValue(dao.getClientData().get(i).getCountry());
           cell8.setCellValue(dao.getClientData().get(i).getBookingType());
           cell9.setCellValue(dao.getClientData().get(i).getGroupName());

           counter ++;
        }
    }
    
    public void rowCreator(){
        for(int i = 0; i < 150;  i++){
            rows.add(dao.getSheetData().createRow(i));
        }
    }   
}
