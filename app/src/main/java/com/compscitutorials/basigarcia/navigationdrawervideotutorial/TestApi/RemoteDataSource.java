package com.compscitutorials.basigarcia.navigationdrawervideotutorial.TestApi;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Testmodel.Topics;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

public class RemoteDataSource implements TestService {

    private TestService api;

    public RemoteDataSource(Retrofit retrofit) {

        this.api = retrofit.create(TestService.class);
    }
    @Override
    public Observable<List<Topics>> getTopicsRx() {
        return api.getTopicsRx();
    }
}
