package pl.parking;

import org.junit.Test;

import java.io.IOException;

import pl.parking.controller.api.ApiController;
import pl.parking.controller.reponses.errorResponses.ErrorResponseLogin;
import pl.parking.controller.reponses.errorResponses.ErrorResponsePasswordChange;
import pl.parking.controller.reponses.errorResponses.ErrorResponseRegisterUser;
import pl.parking.controller.reponses.errorResponses.ErrorResponseToken;
import pl.parking.controller.reponses.errorResponses.ErrorUtils;
import pl.parking.controller.reponses.successfulResponses.ResponseLogOut;
import pl.parking.controller.reponses.successfulResponses.ResponseLogin;
import pl.parking.controller.reponses.successfulResponses.ResponsePasswordChange;
import pl.parking.controller.reponses.successfulResponses.ResponsePasswordReset;
import pl.parking.controller.reponses.successfulResponses.ResponseRegister;
import pl.parking.service.ParkingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertTrue;

public class AuthenticationUser {
    private static ErrorResponseLogin error_response_login;
    private static ErrorResponseRegisterUser error_response_register_user;
    private static ErrorResponseToken error_response_token;
    private static ErrorResponsePasswordChange error_response_password_change;
    private static ResponseLogin response_login;
    private static ResponseRegister response_register;
    private static ResponsePasswordReset response_password_reset;
    private static ResponsePasswordChange response_password_change;
    private static ResponseLogOut response_log_out;
    private final String Token = "Token " + "e2dcc0104592247e950fc7c072bc60303cef4acc";
    private final String Token2 = "Token " + "5f3f880d2c05e2dfdf34cfc9c1377e2dd7881644";
    private final String user_register = "user32@mail.com";
    private final String userName = "user30@mail.com";
    private final String password = "password";
    private final String Time1 = "2019-02-21T16:46:19Z";
    private final String Time2 = "2019-02-24T16:46:19Z";

    @Test
    public void postUserLoginRequestShouldReturn200() throws InterruptedException {

        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<ResponseLogin> call = apiController.postUserLogin(userName, userName, password);
        call.enqueue(
                new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(
                            Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if (response.isSuccessful()) {
                            response_login = response.body();
                        } else {
                            error_response_login = ErrorUtils.parse_Error_Login(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {}
                });

        Thread.sleep(1000);
        if (response_login == null) {
            assertTrue(response_login.getEmail().contains("user2@mail.com"));
        } else {
            assertTrue(error_response_login.getNonFieldErrors() != null);
        }
    }

    @Test
    public void postRequestShouldReturnSuccessfulMessage() throws IOException {

        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<ResponseLogin> call = apiController.postUserLogin(userName, userName, password);

        Response<ResponseLogin> response = call.execute();
        ResponseLogin authResponse = response.body();

        assertTrue(response.isSuccessful());
    }

    @Test
    public void postRegisterSynchronous() throws IOException {

        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<ResponseRegister> call =
                apiController.postUserRegister(user_register, user_register, password, password);
        Response<ResponseRegister> response = call.execute();
        ResponseRegister authResponse = response.body();

        assertTrue(response.isSuccessful());
    }

    @Test
    public void postUserRegisterShouldAddUser() throws InterruptedException {

        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<ResponseRegister> call =
                apiController.postUserRegister(
                        "user102@mail.com", "user102@mail.com", password, password);
        call.enqueue(
                new Callback<ResponseRegister>() {
                    @Override
                    public void onResponse(
                            Call<ResponseRegister> call, Response<ResponseRegister> response) {
                        if (response.isSuccessful()) {
                            response_register = response.body();
                        } else {
                            error_response_register_user =
                                    ErrorUtils.parse_Error_Register_User(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegister> call, Throwable throwable) {}
                });

        Thread.sleep(1000);
        if (error_response_register_user == null) {
            assertTrue(response_register.getKey().contains("a"));
        } else {
            assertTrue(error_response_register_user.getEmail() != null);
        }
    }

    @Test
    public void postPasswordChangeRequestShouldChangePassword() throws InterruptedException {

        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<ResponsePasswordChange> call =
                apiController.postPasswordChange(Token, password, password, password);
        call.enqueue(
                new Callback<ResponsePasswordChange>() {
                    @Override
                    public void onResponse(
                            Call<ResponsePasswordChange> call,
                            Response<ResponsePasswordChange> response) {
                        if (response.isSuccessful()) {
                            response_password_change = response.body();
                        } else {
                            error_response_password_change =
                                    ErrorUtils.parse_Error_Password_change(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePasswordChange> call, Throwable t) {}
                });

        Thread.sleep(1000);
        if (error_response_password_change == null) {
            assertTrue(!response_password_change.getDetail().isEmpty());
        } else {
            assertTrue(error_response_password_change.getNewPassword1() != null);
        }
    }

    @Test
    public void PostResetPasswordAsynchronous() throws InterruptedException {

        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<ResponsePasswordReset> call = apiController.postPasswordReset(userName);
        call.enqueue(
                new Callback<ResponsePasswordReset>() {
                    @Override
                    public void onResponse(
                            Call<ResponsePasswordReset> call,
                            Response<ResponsePasswordReset> response) {
                        if (response.isSuccessful()) {
                            response_password_reset = response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePasswordReset> call, Throwable t) {}
                });

        Thread.sleep(1000);
        assertTrue(response_password_reset.getDetail() != null);
    }

    @Test
    public void PostLogOutAsynchronous() throws InterruptedException {

        ApiController apiController =
                ParkingService.getRetrofitInstance().create(ApiController.class);
        Call<ResponseLogOut> call = apiController.postLogout(Token2);
        call.enqueue(
                new Callback<ResponseLogOut>() {
                    @Override
                    public void onResponse(
                            Call<ResponseLogOut> call, Response<ResponseLogOut> response) {
                        if (response.isSuccessful()) {
                            response_log_out = response.body();
                        } else {
                            error_response_token = ErrorUtils.parse_Error_Token(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogOut> call, Throwable t) {}
                });
        Thread.sleep(1000);
        assertTrue(response_log_out.getDetail() != null);
    }
}
