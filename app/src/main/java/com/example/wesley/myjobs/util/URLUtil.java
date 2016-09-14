package com.example.wesley.myjobs.util;

/**
 * Created by wesley on 9/7/16.
 */
public class URLUtil {
    public static String correctUrl(String baseUrl) {
        if(baseUrl.lastIndexOf('/') != baseUrl.length() - 1) {
            baseUrl = new StringBuilder().append(baseUrl).append("/").toString();
        }
        return baseUrl;
    }

    public static String[] getEndpointWithPath(String baseUrl) {
        String[] paths = new String[2];
        paths[0] = baseUrl.substring(0, baseUrl.lastIndexOf("/"));
        paths[1] = baseUrl.substring(baseUrl.lastIndexOf("/") + 1, baseUrl.length());
        return paths;
    }
}
