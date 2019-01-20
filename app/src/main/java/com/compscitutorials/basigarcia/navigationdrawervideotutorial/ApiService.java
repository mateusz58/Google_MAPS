package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Parking_models.Parking;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/parking_wsp/") // deklarujemy endpoint oraz metodę
    void getData(Callback<Parking> pResponse);

//    @POST("/wsexample/") // deklarujemy endpoint, metodę oraz dane do wysłania
//    void postData(@Body DataBody pBody, Callback<DataBody> pResponse);
}
