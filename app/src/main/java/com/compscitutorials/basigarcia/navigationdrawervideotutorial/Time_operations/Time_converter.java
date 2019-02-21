package com.compscitutorials.basigarcia.navigationdrawervideotutorial.Time_operations;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Time_converter {

    public static String convert_date_time_to_string(Date input)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // (3) create a new String using the date format we want
        String folderName = formatter.format(input);
        // (4) this prints "Folder Name = 2009-09-06-08.23.23"
        return folderName;
    }
    public static Date convert_string_to_date_time(String input) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = formatter.parse(input);
        System.out.println(date);
        return date;
    }
    public static String convert_string(String input) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = formatter.parse(input);
        String folderName = formatter.format(input);

        System.out.println(date);
        return folderName;
    }



}
