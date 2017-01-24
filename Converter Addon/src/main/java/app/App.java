/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import dao.AnalysisByCountry;
import dao.AnalysisByGroups;
import dao.AnalysisByReservation;
import dao.CBMSetter;
import dao.Input;
import dao.RawDataOutput;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 *
 * @author timurozkul
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, ParseException{
        Input dao = new Input();
        RawDataOutput rwo = new RawDataOutput();
        CBMSetter cbm = new CBMSetter();
        AnalysisByGroups abg = new AnalysisByGroups();
        AnalysisByCountry abc = new AnalysisByCountry();
        AnalysisByReservation abr = new AnalysisByReservation();
        
        dao.loadClients();
        cbm.clientIterator(dao);
        rwo.createHeaders(dao);
        rwo.createRawData();
        abg.createHeaders(dao, cbm);
        abg.groupPrinter();
        abc.createHeaders(dao, cbm);
        abc.countryPrinter();
        abr.createHeaders(dao, cbm);
        abr.resPrinter();
        
        dao.finish();
    }
    
}
