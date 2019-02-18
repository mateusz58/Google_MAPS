package com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Login;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Parking_Service  {

////TESTS
    private API_end_points api;

    //TESTS
    private static Retrofit retrofit;


    private static OkHttpClient httpClient = new OkHttpClient();

    public static final String BASE_URL = "http://192.168.8.106:8000/";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    public static Response_Login parse_Success_Login(Response<?> response){
//        Converter<ResponseBody,Error_Response_Login> converter =getRetrofitInstance().responseBodyConverter(Error_Response_Login.class , new Annotation[0]);
//        Error_Response_Login errorResponseLogin;
//        try{
//            errorResponseLogin = converter.convert(response.errorBody());
//        }catch (IOException e){
//            return new Response_Login();
//        }
//        return ResponseLogin;
//    }

}