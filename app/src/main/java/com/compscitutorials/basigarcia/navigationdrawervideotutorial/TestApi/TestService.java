package com.compscitutorials.basigarcia.navigationdrawervideotutorial.TestApi;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Testmodel.Topics;

import java.util.List;

import rx.Observable;
import retrofit2.http.GET;

public interface TestService {

    String URL_BASE = "https://guessthebeach.herokuapp.com/api/";

    @GET("topics/")
    Observable<List<Topics>> getTopicsRx();

}
