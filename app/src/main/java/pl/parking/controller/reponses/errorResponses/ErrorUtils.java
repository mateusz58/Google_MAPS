package pl.parking.controller.reponses.errorResponses;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import pl.parking.service.ParkingService;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static ErrorResponseToken parse_Error_Token(Response<?> response) {
        Converter<ResponseBody, ErrorResponseToken> converter =
                ParkingService.getRetrofitInstance()
                        .responseBodyConverter(ErrorResponseToken.class, new Annotation[0]);
        ErrorResponseToken errorResponse;
        try {
            errorResponse = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponseToken();
        }
        return errorResponse;
    }

    public static ErrorResponseLogin parse_Error_Login(Response<?> response) {
        Converter<ResponseBody, ErrorResponseLogin> converter =
                ParkingService.getRetrofitInstance()
                        .responseBodyConverter(ErrorResponseLogin.class, new Annotation[0]);
        ErrorResponseLogin errorResponseLogin;
        try {
            errorResponseLogin = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponseLogin();
        }
        return errorResponseLogin;
    }

    public static ErrorResponseBooking parse_Error_Booking(Response<?> response) {
        Converter<ResponseBody, ErrorResponseBooking> converter =
                ParkingService.getRetrofitInstance()
                        .responseBodyConverter(ErrorResponseBooking.class, new Annotation[0]);
        ErrorResponseBooking errorResponseBooking;
        try {
            errorResponseBooking = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponseBooking();
        }
        return errorResponseBooking;
    }

    public static ErrorResponseCar parse_Error_Car(Response<?> response) {
        Converter<ResponseBody, ErrorResponseCar> converter =
                ParkingService.getRetrofitInstance()
                        .responseBodyConverter(ErrorResponseCar.class, new Annotation[0]);
        ErrorResponseCar error_Response_car;
        try {
            error_Response_car = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponseCar();
        }
        return error_Response_car;
    }

    public static ErrorResponseRegisterUser parse_Error_Register_User(Response<?> response) {
        Converter<ResponseBody, ErrorResponseRegisterUser> converter =
                ParkingService.getRetrofitInstance()
                        .responseBodyConverter(ErrorResponseRegisterUser.class, new Annotation[0]);
        ErrorResponseRegisterUser errorResponseRegister_User;
        try {
            errorResponseRegister_User = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponseRegisterUser();
        }
        return errorResponseRegister_User;
    }

    public static ErrorResponsePasswordChange parse_Error_Password_change(Response<?> response) {
        Converter<ResponseBody, ErrorResponsePasswordChange> converter =
                ParkingService.getRetrofitInstance()
                        .responseBodyConverter(
                                ErrorResponsePasswordChange.class, new Annotation[0]);
        ErrorResponsePasswordChange error_response_password_change;
        try {
            error_response_password_change = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponsePasswordChange();
        }
        return error_response_password_change;
    }
}
