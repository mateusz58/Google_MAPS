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
    private static List<CarBooking> carBookingList;
    private static List<Booking> bookingList;
    private static List<Car> carList;
    private final String Token = "Token " + "2aa1eaf23eda36526a56fe47faecf7ac9a30e909";
    private final String Token_fail = "Token " + "47a64c5cff5735dc4d215f4c3601ea5c6344625f";

    @Before
    public void setUp() {
        bookingList = new ArrayList<>();
    }

    @Test
    public void getBookingTokenMethodShouldReturnAllBookingOfGivenUser() {
        String token = Token;

        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<Booking>> call = apiEndpoints.getBookingToken(token);
        try {
            call.enqueue(
                    new Callback<List<Booking>>() {
                        @Override
                        public void onResponse(
                                Call<List<Booking>> call, Response<List<Booking>> response) {
                            if (response.isSuccessful()) {
                                bookingList = response.body();
                            } else {
                                errorResponce = ErrorUtils.parse_Error_Token(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Booking>> call, Throwable t) {}
                    });
            Thread.sleep(1000);
            if (token == Token) {
                assertTrue(!carBookingList.isEmpty());
            } else {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }
        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }

    @Test
    public void getCarBookingTokenMethodShouldReturnShouldReturnAllCars() {
        String token = Token;

        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<CarBooking>> call = apiEndpoints.getCarBookingToken(token);
        carBookingList = new ArrayList<CarBooking>();
        try {
            call.enqueue(
                    new Callback<List<CarBooking>>() {
                        @Override
                        public void onResponse(
                                Call<List<CarBooking>> call,
                                Response<List<CarBooking>> response) {
                            if (response.isSuccessful()) {
                                carBookingList = response.body();
                            } else {
                                errorResponce = ErrorUtils.parse_Error_Token(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CarBooking>> call, Throwable t) {}
                    });
            Thread.sleep(1000);
            if (token == Token) {
                assertTrue(!carBookingList.isEmpty());
            } else {
                assertTrue(errorResponce.getDetail().contains("Invalid token."));
            }

        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }

    @Test
    public void getCarTokenShouldReturnAllCarsOfGivenUser() {

        String token = Token_fail;
        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<List<Car>> call = apiEndpoints.getCarToken(token);
        try {
            call.enqueue(
                    new Callback<List<Car>>() {
                        @Override
                        public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                            if (response.isSuccessful()) {
                                carList = response.body();
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
