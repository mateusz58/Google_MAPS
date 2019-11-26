package pl.parking.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {

    public static String convertDateTimeToString(Date input) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String folderName = formatter.format(input);
        return folderName;
    }

    public static Date convertStringToDateTime(String input) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = formatter.parse(input);
        return date;
    }

    public static String convertString(String input) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = formatter.parse(input);
        String folderName = formatter.format(input);
        return folderName;
    }
}
