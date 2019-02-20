package com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api;


import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Token;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Booking;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Car;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_PasswordReset;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_User_login;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_User_logout;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_User_register;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Parking;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.car_booking;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Log_out;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Login;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Password_change;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Password_reset;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_end_points {

    //// NO TOKEN GETTERS
    ///pobierz rezerwacje
    @GET("api/booking/")
    Call<List<Booking>> getBooking();
///pobierz parkingi
    @GET("api/parking/")
    Call<List<Parking>> getParking();
//pobierz samochody
    @GET("api/car/")
    Call<List<Car>> getCar();
///pobierz rezerwacje car_booking
    @GET("api/car_booking/")
    Call<List<car_booking>> getcar_booking();


///Get parkings list
    @GET("api/parking/{id}")
    Call<List<car_booking>> getParking_selected(@Path(value = "{id}", encoded = true) int id);

//reset hasla

    //// TOKEN required

    @GET("api/booking/logged")
    Call<List<Booking>> getBooking_token(@Header("Authorization")
                                                 String token);

    @GET("api/car/logged")
    Call<List<Car>> getCar_token(@Header("Authorization")
                                         String token);

    @GET("api/car_booking/logged")
    Call<List<car_booking>> getcar_booking_token(@Header("Authorization")
                                                         String token);
////////{POST booking

    //HEADER
//{'Authorization': 'Token ' + auth_token,
//        'date-from':'2019-02-16T16:46:19Z',
//        "date-to": "2019-02-17T17:56:20Z",
//        "registration-plate":"use38yy7gr,uzcb2r345"
//DATA
//    data={
//        "parking": 2,
//                "user": 3,
//                "number_of_cars":2
//    }


    ///Rezerwacja


    @FormUrlEncoded
    @POST("api/booking/")
    Call<Booking> postBooking(@Header("Authorization")
                                            String token,
                                           @Header("date-from")
                                            String date_from,
                                           @Header("date-to") String date_to,
                                           @Header("registration-plate") String registration_plate,
                                           @Field("parking") int parking,
                                           @Field("user") int id,
                                           @Field("number_of_cars") int number_of_cars);


//////////User authentication

///zmiana hasla
    @FormUrlEncoded
    @POST("api/rest-auth/password/change/")
    Call<Response_Password_change> post_password_change(@Header("Authorization")
                                                                 String token, @Field("new_password1") String new_password1, @Field("new_password2") String new_password2, @Field("old_password") String old_password);
    //rejestracja
    @FormUrlEncoded
    @POST("api/registration_custom/")
    Call<Response_Register> post_user_register(@Field("username") String username, @Field("email") String email, @Field("password1") String password1, @Field("password2") String password2);

    ///Logowanie uzytkownika
    @FormUrlEncoded
    @POST("api-token-auth/")
    Call<Response_Login> post_user_login(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    //Password reset
    @FormUrlEncoded
    @POST("api/rest-auth/password/reset/")
    Call<Response_Password_reset> post_password_reset(@Field("email") String username);

    //logout
    @POST("api/rest-auth/logout/")
    Call<Response_Log_out> post_logout(@Header("Authorization") String Token);





//////PUT REQUESTS ZMIANA rezerwacji

    ///Zmiana rezerwacji
    @FormUrlEncoded
    @PUT("api/car/logged/{id}")
    Call<Car> put_car(@Path(value = "{id}", encoded = true) int id,@Header("Authorization") String Token,@Field("status") String status);

    @FormUrlEncoded
    @PUT("api/booking/logged/{id}")
    Call<Booking> put_booking(@Path(value = "{id}", encoded = true) int id,@Header("Authorization") String Token,@Field("active") boolean active);


}








