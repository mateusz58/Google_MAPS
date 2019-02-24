package com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Parking_Service  {


////TESTS
    private API_end_points api;

    //TESTS
    private static Retrofit retrofit;


    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
   private static OkHttpClient client_json =
           new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                   .addInterceptor(new LogJsonInterceptor())
           .build();

    private static OkHttpClient client =
            new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();







    public static final String BASE_URL = "http://192.168.8.106:8000/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
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