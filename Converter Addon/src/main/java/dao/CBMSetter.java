/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Clients;
import dto.ClientsByMonth;
import dto.MonthsRes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author timurozkul
 */
public class CBMSetter {
    /*
        Class objective: The data that is in one HashMap (clients) is set to another 
        one (clientsBM).
        
        The clientsBM structure is so that guest are categorized by the months they 
        Check In. The aim is to find the characterisitcs of guest who Check In in
        a certain time frame. 
    
        Example: When do the guest who Check in January start booking the hotel 
        that are from England.
    
        *Please look at this page in conjuction with the ClientsByMonth
    */
    private HashMap<Integer, Clients> clients = new HashMap<>();
    private HashMap<Integer, ClientsByMonth> clientsBM = new HashMap<>();
        
    
    //Below we are iterating from one HashMap to set the other one.
    public void clientIterator(Input input) throws ParseException{
        clients = input.getClientData();
        preSetterList();// Sets the list
        Collection<Integer> keys = clients.keySet();
        for(Integer i: keys){
            monthDivider(clients.get(i).getMonth(), clients.get(i).getCountry(), clients.get(i).getPax(), clients.get(i).getCheckIn(), clients.get(i).getCheckOut(), clients.get(i).getGroupName(), clients.get(i).getReservationDate());
        }
    }
    
    public void monthDivider(String month, String country, int pax, String checkIn, String checkOut, String groupName, String resDate) throws ParseException{
    
        switch(month){
            case "01"://January
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "02"://February
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "03"://March
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "04"://April
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "05"://May
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "06"://June
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "07"://July
                paxSetter(month, pax);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                reservationSetter(month, pax, country, resDate);
                break;
            case "08"://August
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "09"://September
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "10"://October
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                break;
            case "11"://November
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                
                break;
            case "12"://December
                paxSetter(month, pax);
                reservationSetter(month, pax, country, resDate);
                avStaySetter(month, checkIn, checkOut);
                countrySetter(month, country, pax);      
                groupSetter(month, pax, groupName);
                
                break;
            default: 
            System.out.println("Error on switch case printPaxByMonth method.");
        }      
    }

    
    public void preSetterList(){
        // Pre setting HashMap because of ordered iteration required for printing on excel
        // some keys will not have any value.
        
        //COUNTRY
        HashMap<String, Integer>[] byCountry = new HashMap[12];
        String[] countrys = {"GB ", "PT ", "ES ", "BE ", "IE ", "FR ", "DE ", "CH ", "IR ", "NL ", "   ", "Others"};
        
        for(int i = 0; i < 12; i++){
            byCountry[i] = new HashMap<>();
            for(int k = 0; k < 12; k++){
               byCountry[i].put(countrys[k], 0);
            }
        }
        // GROUPS
        Collection<Integer> keys = clients.keySet();
        HashMap<String, Integer> groupNames = new HashMap();
         
        for(Integer i: keys){
            String groupName = clients.get(i).getGroupName();
            groupNames.put(groupName, 0);
        }       
        
         //  ------------ DTO SETTER -------------         
       for(int i = 0; i < 12; i++){
            HashMap<String, Integer> groupNamesC = new HashMap(groupNames);
            clientsBM.put(i, new ClientsByMonth());
            
            clientsBM.get(i).setByCountryHash(byCountry[i]);
            clientsBM.get(i).preSetGroups(groupNamesC);
            for(int k = 0; k < 13; k++){
            clientsBM.get(i).getRes2015().put(k, new MonthsRes());
            clientsBM.get(i).getRes2015().get(k).initHash();
            clientsBM.get(i).getResUnknown().put(k, new MonthsRes());
            clientsBM.get(i).getResUnknown().get(k).initHash();
            clientsBM.get(i).getRes2016().put(k, new MonthsRes());
            clientsBM.get(i).getRes2016().get(k).initHash();
            }
        }
    }

    public void paxSetter(String month, int pax){
        int monthInt = Integer.parseInt(month) - 1;//month starts with 1 - Jan on original data and in the hashmap starts with 0 - Jan
        int newPax = pax + (int)clientsBM.get(monthInt).getPax();
        clientsBM.get(monthInt).setPax(newPax);
        clientsBM.get(monthInt).setFolios( clientsBM.get(monthInt).getFolios() + 1);
    }
    
    //Below is where the average stay of guest are calculated and set on the HashMap
    public void avStaySetter(String month, String checkIn, String checkOut) throws ParseException{
        
        int monthInt = Integer.parseInt(month);
        SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
        
        long d1=formater.parse(checkIn).getTime();
        long d2=formater.parse(checkOut).getTime();

        int stayTime = (int)Math.abs((d1-d2)/(1000*60*60*24));
        clientsBM.get(monthInt-1).setTotalStays(clientsBM.get(monthInt-1).getTotalStays() + stayTime);     
    }
    
    public void countrySetter(String month, String country, int pax){
        int monthInt = Integer.parseInt(month) -1;
        clientsBM.get(monthInt).setByCountry(monthInt, country, pax);       
    }    
    
    public void groupSetter(String month, int pax, String groupName){
        int monthInt = Integer.parseInt(month) - 1;
        
        clientsBM.get(monthInt).setGroups(pax, groupName);
    }
    
    public void reservationSetter(String month, int pax, String country, String resDate){
        int monthInt = Integer.parseInt(month) -1;
        int resMonth = Integer.parseInt(resDate.substring(0, 2));
        int resYear = Integer.parseInt(resDate.substring(6, resDate.length()));
        
        switch(resYear){
            case 2015:
            clientsBM.get(monthInt).setRes2015pax(resMonth, country, pax);
            break;
            case 2016:
            clientsBM.get(monthInt).setRes2016pax(resMonth, country, pax);
            default:
            clientsBM.get(monthInt).setResUnknownpax(resMonth, country, pax);
        }
    }      

    public HashMap<Integer, ClientsByMonth> getClientsBM() {
        return clientsBM;
    }
}

