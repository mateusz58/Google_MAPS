package com.compscitutorials.basigarcia.navigationdrawervideotutorial.network;


import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models.Booking;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models.Parking_builder;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Test.EmployeeList;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Test.parking_wsp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("retrofit-demo.php")
    Call<EmployeeList> getEmployeeData();

    @GET("posts")
    Call<List<Parking_builder>> getPosts();

    @GET("booking")
    Call<List<Booking>> getBooking();


    @GET("parking/")
    Call<List<Parking_builder>> getParking();

    @GET("12j5xc")
    Call<List<parking_wsp>> getparking_wsp();

}
