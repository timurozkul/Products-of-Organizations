/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.HashMap;

/**
 *
 * @author timurozkul
 */
public class MonthsRes {
    private int resMonth;
    private HashMap<String, Integer> byCountry = new HashMap<>();

    public MonthsRes() {
    }

    public int getResMonth() {
        return resMonth;
    }

    public void setResMonth(int resMonth) {
        this.resMonth = resMonth;
    }

    public HashMap<String, Integer> getByCountry() {
        return byCountry;
    }

    public void setByCountry(int newPax, String country) {
       byCountry.put(country, newPax);
    }

    public void initHash(){
        
        byCountry.put("GB ", 0);
        byCountry.put("PT ", 0);
        byCountry.put("ES ", 0);
        byCountry.put("BE ", 0);
        byCountry.put("IE ", 0);
        byCountry.put("FR ", 0);
        byCountry.put("DE ", 0);
        byCountry.put("CH ", 0);
        byCountry.put("IR ", 0);
        byCountry.put("NL ", 0);
        byCountry.put("   ", 0);
        byCountry.put("Others", 0);
        
    }

    
}
