package pl.parking.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pl.parking.model.Car;
import pl.parking.model.CarBooking;

public class ApiStaticData {
    public static String URL = "https://polar-plains-14145.herokuapp.com";
    public static String userid = "1";

    public static List<CarBooking> bookings = new ArrayList<>();

    public static List<Car> cars = new ArrayList<>();

    public static boolean is_Token = false;

    public static String token = "";
}
