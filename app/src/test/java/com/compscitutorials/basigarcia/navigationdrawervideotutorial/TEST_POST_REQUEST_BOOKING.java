//package com.compscitutorials.basigarcia.navigationdrawervideotutorial;
//
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.ErrorUtils;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Booking;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Car;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Token;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.API_end_points;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Booking;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Car;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Password_change;
//
//import org.junit.Test;
//
//import java.io.IOException;
//
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static junit.framework.Assert.assertTrue;
//
//import static junit.framework.Assert.fail;
//import static org.assertj.core.api.Assertions.assertThat;
//public class TEST_POST_REQUEST_BOOKING {
//
//    private final String Token = "Token " + "e2dcc0104592247e950fc7c072bc60303cef4acc";
//
//    private final String Token2 = "Token " + "5f3f880d2c05e2dfdf34cfc9c1377e2dd7881644";
//
//    private final String user_register = "user30@mail.com";
//
//    private final String user_login = "user30@mail.com";
//
//    private static  Error_Response_Booking error_response_booking;
//
//    private static Error_Response_Car error_response_car;
//
//    private static Booking response_booking;
//
//    private static Car response_car;
//
//    private static int path_card_id;
//
//    private final int path_booking_id=52;
//
//    private final int path_car_id=67;
//
//    private static boolean active;
//
//    private static String status;
//
//    private final String password = "matp17954";
//
//    private final String date_from = "2019-02-21T16:46:19Z";
//    private final String date_to = "2019-02-23T16:46:19Z";
//    private final String[] registration_plate_array = {"plate1425", "plzate1"};
//    private final int number_of_cars = registration_plate_array.length;
//    private final int user = 2;
//    private final int parking = 2;
//
//
//    @Test
//    public void POST_booking_synchronous() throws IOException {
//        String registration_plate = "";
//        for (int i = 0; i < registration_plate_array.length; i++) {
//            if (i == 0) {
//                registration_plate = registration_plate + registration_plate_array[i];
//            } else {
//                registration_plate = registration_plate + "," + registration_plate_array[i];
//            }
//        }
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        Call<Booking> call = apiEndpoints.postBooking(Token2, date_from, date_to, registration_plate, parking, 11, registration_plate_array.length);
//        //Magic is here at .execute() instead of .enqueue()
//        //Synchronous call
//        Response<Booking> response = call.execute();
//        ResponseBody authResponse = response.errorBody();
//        assertTrue(response.isSuccessful());
//}
//    @Test
//    public void POST_booking_asynchronous() {
//
//
//        final int[] code = new int[1];
//
//        ///Inicjacja
//        String registration_plate = "";
//        for (int i = 0; i < registration_plate_array.length; i++) {
//            if (i == 0) {
//                registration_plate = registration_plate + registration_plate_array[i];
//            } else {
//                registration_plate = registration_plate + "," + registration_plate_array[i];
//            }
//        }
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        Call<Booking> call = apiEndpoints.postBooking(Token, date_from, date_to, "defauuza", 2, 3, 1);
//        call.enqueue(new Callback<Booking>() {
//            @Override
//            public void onResponse(Call<Booking> call, Response<Booking> response) {
//                if (response.isSuccessful()) {
//                    response_booking = response.body();
//                    code[0] =response.code();
//                } else {
//                    error_response_booking = ErrorUtils.parse_Error_Booking(response);
//                }
//            }
//            @Override
//            public void onFailure(Call<Booking> call, Throwable t) {
//            }
//        });
//        try {
//            Thread.sleep(1000);
//
//        } catch (InterruptedException e) {
//        }
//        //Magic is here at .execute() instead of .enqueue()
//        if (error_response_booking == null) {
//            assertTrue(response_booking.getDateFrom()!=null);
//        } else {
//            assertTrue(error_response_booking.getErrorMessage() != null);
//        }
//    }
//    @Test
//    public void PUT_booking_asynchronous() {
//
//
//        final int[] code = new int[1];
//
//        ///Inicjacja
//        String registration_plate = "";
//        for (int i = 0; i < registration_plate_array.length; i++) {
//            if (i == 0) {
//                registration_plate = registration_plate + registration_plate_array[i];
//            } else {
//                registration_plate = registration_plate + "," + registration_plate_array[i];
//            }
//        }
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        Call<Booking> call = apiEndpoints.put_booking(path_booking_id,Token,false);
//        call.enqueue(new Callback<Booking>() {
//            @Override
//            public void onResponse(Call<Booking> call, Response<Booking> response) {
//                if (response.isSuccessful()) {
//                    response_booking = response.body();
//                    code[0] =response.code();
//                } else {
//                    error_response_booking = ErrorUtils.parse_Error_Booking(response);
//                }
//            }
//            @Override
//            public void onFailure(Call<Booking> call, Throwable t) {
//            }
//        });
//        try {
//            Thread.sleep(1000);
//
//        } catch (InterruptedException e) {
//        }
//        //Magic is here at .execute() instead of .enqueue()
//        if (error_response_booking == null) {
//            assertTrue(response_booking.getDateFrom()!=null);
//        } else {
//            assertTrue(error_response_booking.getErrorMessage() != null);
//        }
//    }
//
////    public void PUT_car_asynchronous() {
////
////
////        final int[] code = new int[1];
////
////
////        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
////        Call<Car> call = apiEndpoints.put_car(String.valueOf(path_car_id),Token,"CANCELLED");
////        call.enqueue(new Callback<Car>() {
////            @Override
////            public void onResponse(Call<Car> call, Response<Car> response) {
////                if (response.isSuccessful()) {
////                    response_car = response.body();
////                    code[0] =response.code();
////                } else {
////                    error_response_car = ErrorUtils.parse_Error_Car(response);
////                }
////            }
////            @Override
////            public void onFailure(Call<Car> call, Throwable t) {
////            }
////        });
////        try {
////            Thread.sleep(1000);
////
////        } catch (InterruptedException e) {
////        }
////        //Magic is here at .execute() instead of .enqueue()
////        if (error_response_car == null) {
////            assertTrue(response_car.getDateFrom()!=null);
////        } else {
////            assertTrue(error_response_car.getErrorMessage() != null);
////        }
////    }
//
//
//
//
//
//
//
//
//
//}
//
//
//
