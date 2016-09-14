package com.example.wesley.myjobs.util;

import java.util.List;

/**
 * Created by wesley on 9/9/16.
 */
public class StringUtil {
    public static String clearDateNoTimezone(String dateStr) {
        int timezoneIndex = dateStr.indexOf('+');
        if(timezoneIndex != -1) {
            return dateStr.substring(0, timezoneIndex);
        }
        return dateStr;
    }

    public static String getStringFromArray(String[] values) {
        StringBuilder value = new StringBuilder();

        for(int i = 0; i < values.length; i++) {
            value.append(values[i]);
            if(i < values.length - 1) {
                value.append(", ");
            }
        }

        return value.toString();
    }

    public static String getStringFromList(List<String> values) {
        StringBuilder value = new StringBuilder();

        for(int i = 0; i < values.size(); i++) {
            value.append(values.get(i));
            if(i < values.size() - 1) {
                value.append(", ");
            }
        }

        return value.toString();
    }
}
