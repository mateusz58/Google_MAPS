package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;





import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.API_end_points;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Car;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;


public class TEST_PUT_REQUEST {

    private static HttpResponse result;

    @Test
    public void doInBackground() {
        InputStream inputStream = null;
        Car car = new Car();
        car.setStatus("CANCELLED");
        try {
            API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
            Call<String> call = apiEndpoints.put_car("76", "Token f4398f50af0af87c6cf460cd354ef834517ebdd4", car);
            Response response = call.execute();

            Thread.sleep(1000);


            assertTrue(response != null);
        } catch (IOException | InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);

        }
    }


    @Test
    public void doInBackground2() throws IOException {
        try {
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 3000;
            HttpConnectionParams.setConnectionTimeout(httpParameters,
                    timeoutConnection);
            int timeoutSocket = 3000;
            HttpConnectionParams
                    .setSoTimeout(httpParameters, timeoutSocket);

            JSONObject object = new JSONObject();
            try {
                object.accumulate("status", "CANCELLED");
            } catch (JSONException e1) {
                Log.e(getClass().getSimpleName(), "Exception handled", e1);
            }


//            String BASE_URL = "http://192.168.8.106:8000/";

            DefaultHttpClient httpclient = new DefaultHttpClient(
                    httpParameters);
            HttpPut httpPut = new HttpPut("http://192.168.8.106:8000/api/car/logged/76");
            StringEntity se = new StringEntity(object.toString(), "UTF-8");
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            httpPut.setEntity(se);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");
            httpPut.setHeader("Authorization", "Token " + "f4398f50af0af87c6cf460cd354ef834517ebdd4");
            HttpResponse result1 = httpclient.execute(httpPut);

        } catch (Exception e) {

            int a = 2;
        }
        assertTrue(result!=null);

    }
}


















