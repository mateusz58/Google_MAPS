/**
 * File generated by Magnet rest2mobile 1.1 - May 28, 2016 12:00:19 AM
 * @see {@link http://developer.magnet.com}
 */

package com.compscitutorials.basigarcia.navigationdrawervideotutorial.TEMP;


/**
 * Generated from json example
{
  "Miasto" : "jakiesmiasto",
  "Ulica" : "jakasulica",
  "ID" : 1,
  "Nr_ulicy" : 12,
  "Wspolrzedna_X" : 25.4,
  "Wspolrzedna_Y" : 12.8,
  "Iloscmiejsc" : 20,
  "Nazwa" : "jakisparkname"
}

 */

public class _ParkingsResult {

  
@com.google.gson.annotations.SerializedName("ID")
  private Integer iD;

  
@com.google.gson.annotations.SerializedName("Ilosc_miejsc")
  private Integer iloscmiejsc;

  
@com.google.gson.annotations.SerializedName("Miasto")
  private String miasto;

  
@com.google.gson.annotations.SerializedName("Nazwa")
  private String nazwa;

  
@com.google.gson.annotations.SerializedName("Nr_ulicy")
  private Integer nr_ulicy;

  
@com.google.gson.annotations.SerializedName("Ulica")
  private String ulica;

  
@com.google.gson.annotations.SerializedName("Wspolrzedna_X")
  private Double X;

  
@com.google.gson.annotations.SerializedName("Wspolrzedna_Y")
  private Double Y;

  public Integer getID() {
    return iD;
  }
  public Integer getIloscmiejsc() {
    return iloscmiejsc;
  }
  public String getMiasto() {
    return miasto;
  }
  public String getNazwa() {
    return nazwa;
  }
  public Integer getNr_ulicy() {
    return nr_ulicy;
  }
  public String getUlica() {
    return ulica;
  }
  public Double getX() {
    return X;
  }
  public Double getY() {
    return Y;
  }

  /**
  * Builder for _ParkingsResult
  **/
  public static class _ParkingsResultBuilder {
    private _ParkingsResult toBuild = new _ParkingsResult();

    public _ParkingsResultBuilder() {
    }

    public _ParkingsResult build() {
      return toBuild;
    }

    public _ParkingsResultBuilder iD(Integer value) {
      toBuild.iD = value;
      return this;
    }
    public _ParkingsResultBuilder iloscmiejsc(Integer value) {
      toBuild.iloscmiejsc = value;
      return this;
    }
    public _ParkingsResultBuilder miasto(String value) {
      toBuild.miasto = value;
      return this;
    }
    public _ParkingsResultBuilder nazwa(String value) {
      toBuild.nazwa = value;
      return this;
    }
    public _ParkingsResultBuilder nr_ulicy(Integer value) {
      toBuild.nr_ulicy = value;
      return this;
    }
    public _ParkingsResultBuilder ulica(String value) {
      toBuild.ulica = value;
      return this;
    }
    public _ParkingsResultBuilder wspolrzedna_X(Double value) {
      toBuild.X = value;
      return this;
    }
    public _ParkingsResultBuilder wspolrzedna_Y(Double value) {
      toBuild.Y = value;
      return this;
    }
  }
}