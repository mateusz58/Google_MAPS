package com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans;

import java.util.Date;

/**
 * Created by zlp on 6/3/16.
 */
public class Parking_reservationsResult {

    String ID_uzytkownika;
    Date    Od;
    String  getNumer_rejestracji;
    Date  TheDo;
    Date Data_reserwacji;
    String  Adres;
    String  Numer_rejestracji;
    String  Kod_dostepu;


    public String getID_uzytkownika() {
        return ID_uzytkownika;
    }

    public void setiD_uzytkownika(String iD_uzytkownika) {
        this.ID_uzytkownika = iD_uzytkownika;
    }

    public Date getData_reserwacji() {
        return Data_reserwacji;
    }

    public void setData_reserwacji(Date data_reserwacji) {
        Data_reserwacji = data_reserwacji;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String adres) {
        Adres = adres;
    }

    public String getNumer_rejestracji() {
        return Numer_rejestracji;
    }

    public void setNumer_rejestracji(String numer_rejestracji) {
        Numer_rejestracji = numer_rejestracji;
    }

    public String getKod_dostepu() {
        return Kod_dostepu;
    }

    public void setKod_dostepu(String kod_dostepu) {
        Kod_dostepu = kod_dostepu;
    }

    public String getGetNumer_rejestracji() {
        return getNumer_rejestracji;
    }

    public void setGetNumer_rejestracji(String getNumer_rejestracji) {
        this.getNumer_rejestracji = getNumer_rejestracji;
    }

    public Date getOd() {
        return Od;
    }

    public void setOd(Date od) {
        Od = od;
    }
    public Date getTheDo() {
        return TheDo;
    }
    public void setTheDo(Date theDo) {
        TheDo = theDo;
    }
}







