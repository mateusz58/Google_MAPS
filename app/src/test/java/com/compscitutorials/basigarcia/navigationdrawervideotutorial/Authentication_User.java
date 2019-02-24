//package com.compscitutorials.basigarcia.navigationdrawervideotutorial;
//
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.ErrorUtils;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Login;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Password_change;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Register_User;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Token;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.API_end_points;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_PasswordReset;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_Passwordchange;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_User_login;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_User_logout;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.POST_User_register;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Log_out;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Login;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Password_change;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Password_reset;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Register;
//
//import org.junit.Test;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.Calendar;
//import java.util.Date;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static junit.framework.Assert.assertTrue;
//
//import java.text.SimpleDateFormat;
//
//public class Authentication_User {
//    private final String Token = "Token " + "e2dcc0104592247e950fc7c072bc60303cef4acc";
//
//    private final String Token2 = "Token " + "5f3f880d2c05e2dfdf34cfc9c1377e2dd7881644";
//
//    private final String user_register = "user32@mail.com";
//
//    private final String user_login = "user30@mail.com";
//
//
//    private final String password = "matp17954";
//
//    private static Error_Response_Login error_response_login;
//    private static Error_Response_Register_User error_response_register_user;
//    private static Error_Response_Token error_response_token;
//    private static Error_Response_Password_change error_response_password_change;
//
//    private static Response_Login response_login;
//    private static Response_Register response_register;
//    private static Response_Password_reset response_password_reset;
//    private static Response_Password_change response_password_change;
//    private static Response_Log_out response_log_out;
//
//
//    private final String Time1 = "2019-02-21T16:46:19Z";
//
//    private final String Time2 = "2019-02-24T16:46:19Z";
//
//
//    @Test
//    public void POST_login_asynchronous_error_handling() {
//
//        ///Inicjacja
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Login> call = apiEndpoints.post_user_login(Token, user_login, user_login, password);
//        call.enqueue(new Callback<Response_Login>() {
//            @Override
//            public void onResponse(Call<Response_Login> call, Response<Response_Login> response) {
//                if (response.isSuccessful()) {
//                    response_login = response.body();
//                } else {
//                    error_response_login = ErrorUtils.parse_Error_Login(response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response_Login> call, Throwable t) {
//
//            }
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
//        //Magic is here at .execute() instead of .enqueue()
//        if (response_login == null) {
//            assertTrue(response_login.getEmail().contains("user2@mail.com"));
//        } else {
//            assertTrue(error_response_login.getNonFieldErrors() != null);
//        }
//
//    }
//
//    @Test
//    public void POST_login_synchronous() {
//
//        ///Inicjacja
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Login> call = apiEndpoints.post_user_login(Token, user_login, user_login, password);
//        try {
//            //Magic is here at .execute() instead of .enqueue()
//            Response<Response_Login> response = call.execute();
//            Response_Login authResponse = response.body();
//
//            assertTrue(response.isSuccessful());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void POST_register_synchronous() {
//
//        ///Inicjacja
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Register> call = apiEndpoints.post_user_register(user_register, user_register, password, password);
//        try {
//            //Magic is here at .execute() instead of .enqueue()
//            Response<Response_Register> response = call.execute();
//            Response_Register authResponse = response.body();
//
//            assertTrue(response.isSuccessful());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void POST_register_asynchronous() {
//
//        ///Inicjacja
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Register> call = apiEndpoints.post_user_register("user102@mail.com", "user102@mail.com", password, password);
//        call.enqueue(new Callback<Response_Register>() {
//            @Override
//            public void onResponse(Call<Response_Register> call, Response<Response_Register> response) {
//                if (response.isSuccessful()) {
//                    response_register = response.body();
//                } else {
//                    error_response_register_user = ErrorUtils.parse_Error_Register_User(response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response_Register> call, Throwable t) {
//
//            }
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
//        //Magic is here at .execute() instead of .enqueue()
//        if (error_response_register_user == null) {
//            assertTrue(response_register.getKey().contains("a"));
//        } else {
//            assertTrue(error_response_register_user.getEmail() != null);
//        }
//    }
//
//    @Test
//    public void POST_change_password_asynchronous() {
//
//        ///Inicjacja
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Password_change> call = apiEndpoints.post_password_change(Token, password, password, password);
//        call.enqueue(new Callback<Response_Password_change>() {
//            @Override
//            public void onResponse(Call<Response_Password_change> call, Response<Response_Password_change> response) {
//                if (response.isSuccessful()) {
//                    response_password_change = response.body();
//                } else {
//                    error_response_password_change = ErrorUtils.parse_Error_Password_change(response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response_Password_change> call, Throwable t) {
//
//            }
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
//        //Magic is here at .execute() instead of .enqueue()
//        if (error_response_password_change == null) {
//            assertTrue(!response_password_change.getDetail().isEmpty());
//        } else {
//            assertTrue(error_response_password_change.getNewPassword1() != null);
//        }
//    }
//
//
//    @Test
//    public void POST_change_password_synchronous() {
//        ///Inicjacja
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Password_change> call = apiEndpoints.post_password_change(Token, password, password, password);
//        try {
//            //Magic is here at .execute() instead of .enqueue()
//            Response<Response_Password_change> response = call.execute();
//            Response_Password_change authResponse = response.body();
//
//            assertTrue(response.isSuccessful());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void POST_reset_password_synchronous() {
//
//        ///Inicjacja
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Password_reset> call = apiEndpoints.post_password_reset(user_login);
//        try {
//            //Magic is here at .execute() instead of .enqueue()
//            Response<Response_Password_reset> response = call.execute();
//            Response_Password_reset authResponse = response.body();
//            assertTrue(response.isSuccessful());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void POST_reset_password_asynchronous() {
//
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Password_reset> call = apiEndpoints.post_password_reset(user_login);
//        call.enqueue(new Callback<Response_Password_reset>() {
//            @Override
//            public void onResponse(Call<Response_Password_reset> call, Response<Response_Password_reset> response) {
//                if (response.isSuccessful()) {
//                    response_password_reset = response.body();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response_Password_reset> call, Throwable t) {
//
//            }
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
//        //Magic is here at .execute() instead of .enqueue()
//        assertTrue(response_password_reset.getDetail() != null);
//    }
//
//
//    @Test
//    public void POST_logout_synchronous() throws ParseException {
//
//        ///Inicjacja
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Log_out> call = apiEndpoints.post_logout(Token2);
//        try {
//            //Magic is here at .execute() instead of .enqueue()
//            Response<Response_Log_out> response = call.execute();
//            Response_Log_out authResponse = response.body();
//            assertTrue(response.isSuccessful());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void POST_logout_synchronous_asynchronous() {
//
//        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
//        // Pobranie listy
//        Call<Response_Log_out> call = apiEndpoints.post_logout(Token2);
//        call.enqueue(new Callback<Response_Log_out>() {
//            @Override
//            public void onResponse(Call<Response_Log_out> call, Response<Response_Log_out> response) {
//                if (response.isSuccessful()) {
//                    response_log_out = response.body();
//                }
//                else
//                {
//                    error_response_token=ErrorUtils.parse_Error_Token(response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response_Log_out> call, Throwable t) {
//
//            }
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
//        //Magic is here at .execute() instead of .enqueue()
//        assertTrue(response_log_out.getDetail() != null);
//    }
//
//
//
//}
//
//
//
//
