package com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static Error_Response_Token parse_Error_Token(Response<?> response){
        Converter<ResponseBody,Error_Response_Token> converter =Parking_Service.getRetrofitInstance().responseBodyConverter(Error_Response_Token.class , new Annotation[0]);
        Error_Response_Token errorResponse;
        try{
            errorResponse = converter.convert(response.errorBody());
        }catch (IOException e){
            return new Error_Response_Token();
        }
        return errorResponse;
    }

    public static Error_Response_Login parse_Error_Login(Response<?> response){
        Converter<ResponseBody,Error_Response_Login> converter =Parking_Service.getRetrofitInstance().responseBodyConverter(Error_Response_Login.class , new Annotation[0]);
        Error_Response_Login errorResponseLogin;
        try{
            errorResponseLogin = converter.convert(response.errorBody());
        }catch (IOException e){
            return new Error_Response_Login();
        }
        return errorResponseLogin;
    }

    public static Error_Response_Booking parse_Error_Booking(Response<?> response){
        Converter<ResponseBody,Error_Response_Booking> converter =Parking_Service.getRetrofitInstance().responseBodyConverter(Error_Response_Booking.class , new Annotation[0]);
        Error_Response_Booking errorResponseBooking;
        try{
            errorResponseBooking = converter.convert(response.errorBody());
        }catch (IOException e){
            return new Error_Response_Booking();
        }
        return errorResponseBooking;
    }

    public static Error_Response_Car parse_Error_Car(Response<?> response){
        Converter<ResponseBody,Error_Response_Car> converter =Parking_Service.getRetrofitInstance().responseBodyConverter(Error_Response_Car.class , new Annotation[0]);
        Error_Response_Car error_Response_car;
        try{
            error_Response_car = converter.convert(response.errorBody());
        }catch (IOException e){
            return new Error_Response_Car();
        }
        return error_Response_car;
    }

    public static Error_Response_Register_User parse_Error_Register_User(Response<?> response){
        Converter<ResponseBody,Error_Response_Register_User> converter =Parking_Service.getRetrofitInstance().responseBodyConverter(Error_Response_Register_User.class , new Annotation[0]);
        Error_Response_Register_User errorResponseRegister_User;
        try{
            errorResponseRegister_User = converter.convert(response.errorBody());
        }catch (IOException e){
            return new Error_Response_Register_User();
        }
        return errorResponseRegister_User;
    }

    public static Error_Response_Password_change parse_Error_Password_change(Response<?> response){
        Converter<ResponseBody,Error_Response_Password_change> converter =Parking_Service.getRetrofitInstance().responseBodyConverter(Error_Response_Password_change.class , new Annotation[0]);
        Error_Response_Password_change error_response_password_change;
        try{
            error_response_password_change = converter.convert(response.errorBody());
        }catch (IOException e){
            return new Error_Response_Password_change();
        }
        return error_response_password_change;
    }



}