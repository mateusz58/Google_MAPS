/**
 * File generated by Magnet rest2mobile 1.1 - May 28, 2016 12:00:19 AM
 * @see {@link http://developer.magnet.com}
 */

package com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

public class Parking_builder {


    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("free_places")
    private Integer free_places;

    @Expose
    @SerializedName("parking_City")
    private String parking_City;


    @Expose
    @SerializedName("parking_name")
    private String parking_name;

//    @Expose
//    @SerializedName("Nr_ulicy")
//    private Integer nr_ulicy;


    @Expose
    @SerializedName("parking_Street")
    private String parking_Street;

    @Expose
    @SerializedName("x")
    private Double x;


    @Expose
    @SerializedName("y")
    private Double y;

    //    public Integer getNr_ulicy() {
//        return nr_ulicy;
//    }

    public Integer getid() {
        return id;
    }
    public Integer getfree_places() {
        return free_places;
    }
    public String getparking_City() {
        return parking_City;
    }
    public String getparking_name() {
        return parking_name;
    }

    public String getparking_Street() {
        return parking_Street;
    }
    public Double getX() {
        return x;
    }
    public Double getY() {
        return y;
    }

    /**
     * Builder for _ParkingsResult
     **/
    public static class ParkingsResultBuilder {
        private Parking_builder toBuild = new Parking_builder();

        public ParkingsResultBuilder() {
        }

        public Parking_builder build() {
            return toBuild;
        }

        public ParkingsResultBuilder id(Integer value) {
            toBuild.id = value;
            return this;
        }
        public ParkingsResultBuilder free_places(Integer value) {
            toBuild.free_places = value;
            return this;
        }
        public ParkingsResultBuilder parking_City(String value) {
            toBuild.parking_City = value;
            return this;
        }
        public ParkingsResultBuilder parking_name(String value) {
            toBuild.parking_name = value;
            return this;
        }
//        public ParkingsResultBuilder nr_ulicy(Integer value) {
//            toBuild.nr_ulicy = value;
//            return this;
//        }
        public ParkingsResultBuilder parking_Street(String value) {
            toBuild.parking_Street = value;
            return this;
        }
        public ParkingsResultBuilder x(Double value) {
            toBuild.x = value;
            return this;
        }
        public ParkingsResultBuilder y(Double value) {
            toBuild.y = value;
            return this;
        }
    }
}
