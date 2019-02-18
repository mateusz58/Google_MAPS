package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.util.Log;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Token;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.ErrorUtils;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.API_end_points;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Booking;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Car;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.car_booking;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;


public class TEST_GET_REQUEST {




    private final String Token ="Token "+"2aa1eaf23eda36526a56fe47faecf7ac9a30e909";
    private final String Token_fail ="Token "+"47a64c5cff5735dc4d215f4c3601ea5c6344625f";
    private static Error_Response_Token errorResponce;
    private static List<Car> car_list;
    private static List<car_booking> car_booking_list;
    private static List<Booking> booking_list;
    private static String result;

    @Before
    public void setUp() {

//

    }


    @Test
    public void GET_booking() {

        ///Inicjacja
        API_end_points apiEndpoints  = Parking_Service.getRetrofitInstance().create(API_end_points.class);
        // Pobranie listy
        Call<List<Booking>> call =  apiEndpoints.getBooking_token(Token);
        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<List<Booking>> response = call.execute();
            List<Booking> authResponse = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void GET_booking_asynchronous() {
        String token=Token;

        ///Inicjacja
        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
        // Pobranie listy
        Call<List<Booking>> call = apiEndpoints.getBooking_token(token);
        booking_list=new ArrayList<Booking>();
        try {
            call.enqueue(new Callback <List<Booking>>() {
                @Override
                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                    if(response.isSuccessful())
                    {
                        booking_list=response.body();
                    }
                    else
                    {
                        errorResponce = ErrorUtils.parse_Error_Token(response);
                    }
                }
                @Override
                public void onFailure(Call<List<Booking>> call, Throwable t) {
                }
            });
            Thread.sleep(1000);
            if(token==Token)
            {
                assertTrue(!car_booking_list.isEmpty());
            }
            else
            {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }
        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }
    @Test
    public void GET_car_booking_synchronous() {

        ///Inicjacja
        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
        // Pobranie listy
        Call<List<car_booking>> call = apiEndpoints.getcar_booking_token(Token);
        try {
            Response<List<car_booking>> response = call.execute();
            List<car_booking> authResponse = response.body();
            assertTrue(response.isSuccessful());
            int a=2;

        } catch (IOException e1) {
            Log.e(getClass().getSimpleName(), "Exception handled", e1);
        }
    }
    @Test
    public void GET_car_booking_asynchronous() {
        String token=Token;

        ///Inicjacja
        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
        // Pobranie listy
        Call<List<car_booking>> call = apiEndpoints.getcar_booking_token(token);
        car_booking_list=new ArrayList<car_booking>();
        try {
            call.enqueue(new Callback <List<car_booking>>() {
                @Override
                public void onResponse(Call<List<car_booking>> call, Response<List<car_booking>> response) {
                    if(response.isSuccessful())
                    {
                        car_booking_list=response.body();
                    }
                    else
                    {
                        errorResponce = ErrorUtils.parse_Error_Token(response);
                    }
                }
                @Override
                public void onFailure(Call<List<car_booking>> call, Throwable t) {

                }
            });
            Thread.sleep(1000);
            if(token==Token)
            {
                assertTrue(!car_booking_list.isEmpty());
            }
            else
            {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }

        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }


    @Test
    public void GET_car_synchronous() {

        ///Inicjacja
        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
        // Pobranie listy
        Call<List<Car>> call = apiEndpoints.getCar_token(Token);
        try {
            Response<List<Car>> response = call.execute();
            List<Car> authResponse = response.body();
            assertTrue(response.isSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
 public void GET_car_asynchronous_error_handling() {

        String token=Token_fail;
        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
        // Pobranie listy
        Call<List<Car>> call = apiEndpoints.getCar_token(token);
        car_list=new ArrayList<Car>();
        try {
        call.enqueue(new Callback <List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if(response.isSuccessful())
                {
                    car_list=response.body();
                }
                else
                {
                    errorResponce = ErrorUtils.parse_Error_Token(response);
                }
            }
            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {

            }
        });
            Thread.sleep(1000);
            if(token==Token)
            {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }
            else
            {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }

        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }


    }

    }

