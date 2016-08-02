package com.test.getninjas.pedidos.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateFormatter {
    private static String ISOFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static String toFormattedDate(String dateToFormat){
        SimpleDateFormat dateformat = new SimpleDateFormat(ISOFORMAT, Locale.getDefault());
        try {
            Date date = dateformat.parse(dateToFormat);

            String newFormattedDate = new SimpleDateFormat("dd 'de' MMMM").format(date);
            return newFormattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}