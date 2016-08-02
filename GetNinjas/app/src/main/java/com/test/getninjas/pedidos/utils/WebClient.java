package com.test.getninjas.pedidos.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WebClient {
    private static WebClient instance;
    public static WebClient getInstance(){
        if(instance == null){
            instance = new WebClient();
        }
        return instance;
    }

    public void get(String url, final WebClientListener listener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        listener.onFailure(0, e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        String res = response.body().string();
                        listener.onSuccess(response.code(), res);
                    }
                });


    }

    public interface WebClientListener {
        void onSuccess(int statusCode, String response);
        void onFailure(int statusCode, String errorResponse);
    }
}
