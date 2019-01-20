package com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Booking_List {
    @SerializedName("Booking_List")
    private ArrayList<Booking> booking_List;

    public ArrayList<Booking> get_Booking_List() {
        return booking_List;
    }

    public void set_Booking_List(ArrayList<Booking> employeeArrayList) {
        this.booking_List = employeeArrayList;
    }
}
