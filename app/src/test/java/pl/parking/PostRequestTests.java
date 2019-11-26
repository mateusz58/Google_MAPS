package pl.parking;

import org.junit.Test;

import pl.parking.controller.api.ApiController;
import pl.parking.controller.reponses.errorResponses.ErrorResponseBooking;
import pl.parking.controller.reponses.errorResponses.ErrorResponseCar;
import pl.parking.controller.reponses.errorResponses.ErrorUtils;
import pl.parking.model.Booking;
import pl.parking.model.Car;
import pl.parking.service.ParkingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertTrue;

public class PostRequestTests {

    private static ErrorResponseBooking error_response_booking;
    private static ErrorResponseCar error_response_car;
    private static Booking response_booking;
    private static Car response_car;
    private static int path_card_id;
    private static boolean active;
    private static String status;
    private final String Token = "Token " + "e2dcc0104592247e950fc7c072bc60303cef4acc";
    private final String Token2 = "Token " + "5f3f880d2c05e2dfdf34cfc9c1377e2dd7881644";
    private final String user_register = "user30@mail.com";
    private final String user_login = "user30@mail.com";
    private final int path_booking_id = 52;
    private final int path_car_id = 67;
    private final String password = "matp17954";

    private final String date_from = "2019-02-21T16:46:19Z";
    private final String date_to = "2019-02-23T16:46:19Z";
    private final String[] registration_plate_array = {"plate1425", "plzate1"};
    private final int number_of_cars = registration_plate_array.length;
    private final int user = 2;
    private final int parking = 2;
    private static String response;

    @Test
    public void postBookingAsynchronous() throws InterruptedException {

        final int[] code = new int[1];

        String registration_plate = "";
        for (int i = 0; i < registration_plate_array.length; i++) {
            if (i == 0) {
                registration_plate = registration_plate + registration_plate_array[i];
            } else {
                registration_plate = registration_plate + "," + registration_plate_array[i];
            }
        }
        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);
        Call<Booking> call =
                apiController.postBooking(Token, date_from, date_to, "defauuza", 2, 3, 1);
        call.enqueue(
                new Callback<Booking>() {
                    @Override
                    public void onResponse(Call<Booking> call, Response<Booking> response) {
                        if (response.isSuccessful()) {
                            response_booking = response.body();
                            code[0] = response.code();
                        } else {
                            error_response_booking = ErrorUtils.parse_Error_Booking(response);
                        }
                    }
                    @Override
                    public void onFailure(Call<Booking> call, Throwable t) {}
                });
        Thread.sleep(1000);
        if (error_response_booking == null) {
            assertTrue(response_booking.getDateFrom() != null);
        } else {
            assertTrue(error_response_booking.getErrorMessage() != null);
        }
    }

    @Test
    public void putBookingAsynchronous() throws InterruptedException {

        final int[] code = new int[1];

        String registration_plate = "";
        for (int i = 0; i < registration_plate_array.length; i++) {
            if (i == 0) {
                registration_plate = registration_plate + registration_plate_array[i];
            } else {
                registration_plate = registration_plate + "," + registration_plate_array[i];
            }
        }
        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);
        Call<Booking> call = apiController.putBooking(String.valueOf(path_booking_id), Token);
        call.enqueue(
                new Callback<Booking>() {
                    @Override
                    public void onResponse(Call<Booking> call, Response<Booking> response) {
                        if (response.isSuccessful()) {
                            response_booking = response.body();
                            code[0] = response.code();
                        } else {
                            error_response_booking = ErrorUtils.parse_Error_Booking(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Booking> call, Throwable t) {}
                });
     
        Thread.sleep(1000);
        if (error_response_booking == null) {
            assertTrue(response_booking.getDateFrom() != null);
        } else {
            assertTrue(error_response_booking.getErrorMessage() != null);
        }
    }

    @Test
    public void putCarAsynchronous() throws InterruptedException {

        final int[] code = new int[1];

        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);
        Call<String> call = apiController.putCar(Token, String.valueOf(path_car_id));
        call.enqueue(
                new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                          response.body();
                            code[0] = response.code();
                        } else {
                            error_response_car = ErrorUtils.parse_Error_Car(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {}
                });
            Thread.sleep(1000);
        if (error_response_car == null) {
            assertTrue(response_car.getDateFrom() != null);
        } else {
            assertTrue(error_response_car.getErrorMessage() != null);
        }
    }
}
