/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author timurozkul
 */
public class ClientsByMonth {
    
    private int pax;
    private int folios;
    private int totalStays;
    private HashMap<String, Integer> byCountry = new HashMap<>();  
    private HashMap<String, Integer> groups = new HashMap<>();
    private HashMap<Integer, MonthsRes> res2015 = new HashMap<>();
    private HashMap<Integer, MonthsRes> res2016 = new HashMap<>();
    private HashMap<Integer, MonthsRes> resUnknown = new HashMap<>();

    public HashMap<Integer, MonthsRes> getRes2016() {
        return res2016;
    }

    public void setRes2016(HashMap<Integer, MonthsRes> res2016) {
        this.res2016 = res2016;
    }

    public HashMap<Integer, MonthsRes> getResUnknown() {
        return resUnknown;
    }

    public void setResUnknown(HashMap<Integer, MonthsRes> resUnknown) {
        this.resUnknown = resUnknown;
    }
 
    public void setResUnknownpax(int resMonth, String country, int pax) {

        
        if(resUnknown.get(resMonth).getByCountry().get(country) == null){
            country = "Others";                            
        }
        int newPax = resUnknown.get(resMonth).getByCountry().get(country) + pax;
        resUnknown.get(resMonth).setByCountry(newPax, country);
    }
    
    public void setRes2016pax(int resMonth, String country, int pax) {

        
        if(res2016.get(resMonth).getByCountry().get(country) == null){
            country = "Others";                            
        }
        int newPax = res2016.get(resMonth).getByCountry().get(country) + pax;
        res2016.get(resMonth).setByCountry(newPax, country);
    }
    
    
    public void setRes2015pax(int resMonth, String country, int pax) {

        
        if(res2015.get(resMonth).getByCountry().get(country) == null){
            country = "Others";                            
        }
        int newPax = res2015.get(resMonth).getByCountry().get(country) + pax;
        res2015.get(resMonth).setByCountry(newPax, country);
    }
    
    public HashMap<Integer, MonthsRes> getRes2015() {
        return res2015;
    }

    public void setRes2015(HashMap<Integer, MonthsRes> res2015) {
        this.res2015 = res2015;
    }
       
    
    public ClientsByMonth() {
    }
     
    public ClientsByMonth(int pax, int totalStays) {
        this.pax = pax;
        this.totalStays = totalStays;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public int getFolios() {
        return folios;
    }

    public void setFolios(int folios) {
        this.folios = folios;
    }

    public int getTotalStays() {
        return totalStays;
    }

    public void setTotalStays(int totalStays) {
        this.totalStays = totalStays;
    }

    public HashMap<String, Integer> getByCountry() {
        return byCountry;
    }
    public void setByCountryHash(HashMap<String, Integer> byCountry){
        this.byCountry = byCountry;
    }
    
    public void setByCountry(int month, String country, int pax) {
      
       
        if(byCountry.get(country) == null){
            country = "Others";
            byCountry.put(country, 0);                    
        }
     
        int newPax = byCountry.get(country) + pax;
        byCountry.put(country, newPax);
    }

    public HashMap getGroups() {
        return groups;
    }
    
    public void preSetGroups( HashMap<String, Integer> groupNames){
        this.groups = groupNames;
    }

    public void setGroups(int pax, String groupName) {
        
        int newPax = this.groups.get(groupName) + pax;
        this.groups.put(groupName, newPax);
        
    }
}
