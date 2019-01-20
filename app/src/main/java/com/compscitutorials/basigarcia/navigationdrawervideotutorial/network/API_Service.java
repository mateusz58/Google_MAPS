package com.compscitutorials.basigarcia.navigationdrawervideotutorial.network;


import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models.Booking;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models.Parking_builder;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models.Users;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models.parking_wsp;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API_Service {

    @GET("api/booking/")
    Call<List<Booking>> getBooking();


    @GET("api/parking/")
    Call<List<Parking_builder>> getParking();

    @GET("api/parking_wsp/")
    Call<List<parking_wsp>> getparking_wsp();


//    @POST("api-token-auth/")
//    Call<Users> basicLogin();
//
//

    @FormUrlEncoded
    @POST("api-token-auth/")
    Call<Users> login(@Field("username") String username, @Field("password") String password);


}








