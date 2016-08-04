package com.example.getninjastest.util;

import android.content.Context;
import android.content.pm.PackageManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AsifMoinul on 8/2/2016.
 */
public class Utils {

    public static String getFormattedDate(String originalDate){

        String formattedDate = "";
        try {
            DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            DateFormat targetFormat = new SimpleDateFormat("dd MMM");
            Date date = originalFormat.parse(originalDate);
            formattedDate = targetFormat.format(date);
        } catch (ParseException e) {
        }

        return formattedDate;
    }

    public static boolean isApplicationWithPackageNameInstalled(String packageName, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static String getMaskedPhoneNumber(String phone){
        if(phone == null || phone.equals("")){
            return "";
        }
        return phone.replaceAll("(?<=..).(?=...*-)", "*");
    }

    public static String getMaskedEmail(String email){
        if(email == null || email.equals("")){
            return "";
        }
        return email.replaceAll("(?<=..).(?=...*@)", "*");
    }


}
