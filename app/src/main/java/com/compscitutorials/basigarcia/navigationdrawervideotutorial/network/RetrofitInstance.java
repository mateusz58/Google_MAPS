package com.compscitutorials.basigarcia.navigationdrawervideotutorial.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
//    private static final String BASE_URL = "https://gist.githubusercontent.com/mateusz58/13f9124ef5cf972695414bdd0e2aa88a/raw/c5c087c6ba7d35ebb02ed80bb35b95794cda9147/";
      private static final String BASE_URL = "http://192.168.8.104:8000/api/";
//    private static final String BASE_URL = "http://navjacinth9.000webhostapp.com/json/"; //TESTING
//    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/"; //TESTING
//    private static final String BASE_URL = "https://api.myjson.com/bins/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}