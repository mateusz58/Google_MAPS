package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.annotation.TargetApi;
import android.os.Build;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans._ParkingsResult;

import java.util.Vector;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


//http://polar-plains-14145.herokuapp.com/parks_wsp/1

/**
 * Created by zlo on 02.05.16.
 */
public class API {
     //public static ArrayList<ArrayList<String>> Parkinghistory;
     public static Vector<String> Parkinghistory=new Vector<String>();
    public static String URL="https://polar-plains-14145.herokuapp.com";
    public static String userid="1";


}
