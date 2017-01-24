/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author timurozkul
 */
public class Country {

/**
 *
 * @author timurozkul
 */ 
    private int gb;
    private int pt;
    private int es;
    private int be;  
    private int ie;
    private int fr;
    private int de;
    private int ch;
    private int ir;
    private int nl;
    private int others;
    private int unkown;
    ArrayList<String> othersList = new ArrayList();

    public Country() {
    }

    public Country(int gb, int pt, int es, int be, int ie, int fr, int de, int ch, int ir, int nl, int others, int unknown, ArrayList othersList) {
        this.gb = gb;
        this.pt = pt;
        this.es = es;
        this.be = be;
        this.ie = ie;
        this.fr = fr;
        this.de = de;
        this.ch = ch;
        this.ir = ir;
        this.nl = nl;
        this.others = others;
        this.unkown = unknown;
        this.othersList = othersList;
    }

    public int getUnkown() {
        return unkown;
    }

    public void setUnkown(int unkown) {
        this.unkown = unkown;
    }

    public int getGb() {
        return gb;
    }

    public void setGb(int gb) {
        this.gb = gb;
    }

    public int getPt() {
        return pt;
    }

    public void setPt(int pt) {
        this.pt = pt;
    }

    public int getEs() {
        return es;
    }

    public void setEs(int es) {
        this.es = es;
    }

    public int getBe() {
        return be;
    }

    public void setBe(int be) {
        this.be = be;
    }

    public int getIe() {
        return ie;
    }

    public void setIe(int ie) {
        this.ie = ie;
    }

    public int getFr() {
        return fr;
    }

    public void setFr(int fr) {
        this.fr = fr;
    }

    public int getDe() {
        return de;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public int getCh() {
        return ch;
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    public int getIr() {
        return ir;
    }

    public void setIr(int ir) {
        this.ir = ir;
    }

    public int getNl() {
        return nl;
    }

    public void setNl(int nl) {
        this.nl = nl;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public  ArrayList getOthersList() {
        return othersList;
    }

    public void setOthersList(String country) {
        this.othersList.add(country);
    }
}

