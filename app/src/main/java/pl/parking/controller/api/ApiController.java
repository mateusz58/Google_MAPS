package pl.parking.controller.api;

import java.util.ArrayList;
import java.util.List;

import pl.parking.controller.reponses.successfulResponses.ResponseLogOut;
import pl.parking.controller.reponses.successfulResponses.ResponseLogin;
import pl.parking.controller.reponses.successfulResponses.ResponsePasswordChange;
import pl.parking.controller.reponses.successfulResponses.ResponsePasswordReset;
import pl.parking.controller.reponses.successfulResponses.ResponseRegister;
import pl.parking.model.Booking;
import pl.parking.model.Car;
import pl.parking.model.CarBooking;
import pl.parking.model.Parking;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiController {

    @GET("api/booking/")
    Call<List<Booking>> getAllBookings();

    @GET("api/parking/")
    Call<List<Parking>> getAllParkings();

    @GET("api/car/")
    Call<List<Car>> getAllCars();

    @GET("api/CarBooking/")
    Call<List<CarBooking>> getAllCarBookings();

    @GET("api/parking/{id}")
    Call<List<CarBooking>> getParkingById(@Path(value = "{id}", encoded = true) int id);

    @GET("api/booking/logged")
    Call<List<Booking>> getBookingToken(@Header("Authorization") String token);

    @GET("api/car/logged")
    Call<List<Car>> getCarToken(@Header("Authorization") String token);

    @GET("api/CarBooking/logged")
    Call<List<CarBooking>> getCarBookingToken(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/booking/")
    Call<Booking> postBooking(
            @Header("Authorization") String token,
            @Header("date-from") String date_from,
            @Header("date-to") String date_to,
            @Header("registration-plate") String registration_plate,
            @Field("parking") int parking,
            @Field("user") int id,
            @Field("number_of_cars") int number_of_cars);

    @FormUrlEncoded
    @POST("api/rest-auth/password/change/")
    Call<ResponsePasswordChange> postPasswordChange(
            @Header("Authorization") String token,
            @Field("new_password1") String new_password1,
            @Field("new_password2") String new_password2,
            @Field("old_password") String old_password);

    @FormUrlEncoded
    @POST("api/registration_custom/")
    Call<ResponseRegister> postUserRegister(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password1") String password1,
            @Field("password2") String password2);

    @FormUrlEncoded
    @POST("api-token-auth/")
    Call<ResponseLogin> postUserLogin(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("api/rest-auth/password/reset/")
    Call<ResponsePasswordReset> postPasswordReset(@Field("email") String username);

    @POST("api/rest-auth/logout/")
    Call<ResponseLogOut> postLogout(@Header("Authorization") String Token);

    @GET("api/car/logged/{id}")
    Call<String> putCar(@Path(value = "id") String id, @Header("Authorization") String Token);

    @FormUrlEncoded
    @PUT("api/booking/logged/{id}")
    Call<Booking> putBooking(
            @Path(value = "{id}", encoded = true) String id, @Header("Authorization") String Token);

    @GET("api/car/logged")
    Call<List<Car>> putCarTokenPut(
            @Header("Authorization") String Token,
            @Header("car-id") String car_id,
            @Header("booking-id") String booking_id);
}
