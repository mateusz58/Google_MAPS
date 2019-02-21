package com.compscitutorials.basigarcia.navigationdrawervideotutorial.Recycler_List_Car;

import java.util.ArrayList;

public class Car {

    private String title;

    private String message;


    public Car(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public Car() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
