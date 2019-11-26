package pl.parking.controller.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder =
                original.newBuilder().header("Authorization:", "Token" + authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
