package pl.parking;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.parking.controller.api.ApiController;
import pl.parking.controller.reponses.errorResponses.ErrorResponseToken;
import pl.parking.controller.reponses.errorResponses.ErrorUtils;
import pl.parking.model.Booking;
import pl.parking.model.Car;
import pl.parking.model.CarBooking;
import pl.parking.service.ParkingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertTrue;

public class GetRequestsTests {

    private static ErrorResponseToken errorResponce;
    private static List<Car> car_list;
    private static List<CarBooking> CarBooking_list;
    private static List<Booking> booking_list;
    private static String result;
    private final String Token = "Token " + "2aa1eaf23eda36526a56fe47faecf7ac9a30e909";
    private final String Token_fail = "Token " + "47a64c5cff5735dc4d215f4c3601ea5c6344625f";

    @Before
    public void setUp() {}

    @Test
    public void GET_booking() {

        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<Booking>> call = apiEndpoints.getBookingToken(Token);
        try {

            Response<List<Booking>> response = call.execute();
            List<Booking> authResponse = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GET_booking_asynchronous() {
        String token = Token;

        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<Booking>> call = apiEndpoints.getBookingToken(token);
        booking_list = new ArrayList<Booking>();
        try {
            call.enqueue(
                    new Callback<List<Booking>>() {
                        @Override
                        public void onResponse(
                                Call<List<Booking>> call, Response<List<Booking>> response) {
                            if (response.isSuccessful()) {
                                booking_list = response.body();
                            } else {
                                errorResponce = ErrorUtils.parse_Error_Token(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Booking>> call, Throwable t) {}
                    });
            Thread.sleep(1000);
            if (token == Token) {
                assertTrue(!CarBooking_list.isEmpty());
            } else {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }
        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }

    @Test
    public void GET_CarBooking_synchronous() {

        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<CarBooking>> call = apiEndpoints.getCarBookingToken(Token);
        try {
            Response<List<CarBooking>> response = call.execute();
            List<CarBooking> authResponse = response.body();
            assertTrue(response.isSuccessful());
            int a = 2;

        } catch (IOException e1) {
            Log.e(getClass().getSimpleName(), "Exception handled", e1);
        }
    }

    @Test
    public void GET_CarBooking_asynchronous() {
        String token = Token;

        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<CarBooking>> call = apiEndpoints.getCarBookingToken(token);
        CarBooking_list = new ArrayList<CarBooking>();
        try {
            call.enqueue(
                    new Callback<List<CarBooking>>() {
                        @Override
                        public void onResponse(
                                Call<List<CarBooking>> call,
                                Response<List<CarBooking>> response) {
                            if (response.isSuccessful()) {
                                CarBooking_list = response.body();
                            } else {
                                errorResponce = ErrorUtils.parse_Error_Token(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CarBooking>> call, Throwable t) {}
                    });
            Thread.sleep(1000);
            if (token == Token) {
                assertTrue(!CarBooking_list.isEmpty());
            } else {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }

        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }

    @Test
    public void GET_car_synchronous() {

        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<Car>> call = apiEndpoints.getCarToken(Token);
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

        String token = Token_fail;
        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<Car>> call = apiEndpoints.getCarToken(token);
        car_list = new ArrayList<Car>();
        try {
            call.enqueue(
                    new Callback<List<Car>>() {
                        @Override
                        public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                            if (response.isSuccessful()) {
                                car_list = response.body();
                            } else {
                                errorResponce = ErrorUtils.parse_Error_Token(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Car>> call, Throwable t) {}
                    });
            Thread.sleep(1000);
            if (token == Token) {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            } else {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }

        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }
}
