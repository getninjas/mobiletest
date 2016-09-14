package com.example.wesley.myjobs.api;

import com.example.wesley.myjobs.util.URLUtil;
import com.google.gson.JsonElement;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wesley on 9/7/16.
 */
public class EventService {
    private static EventService service;
    private static final String ENDPOINT = "http://testemobile.getninjas.com.br";

    public void getListURL(Subscriber<JsonElement> subscriber) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EventsApi eventsApi = retrofit.create(EventsApi.class);
        Observable<JsonElement> jsonObservable = eventsApi.getJSONDataFromURL();

        jsonObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getBaseURL(String url, Subscriber<JsonElement> subscriber) {
        String[] paths = URLUtil.getEndpointWithPath(url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(paths[0])
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EventsApi eventsApi = retrofit.create(EventsApi.class);
        Observable<JsonElement> jsonObservable = eventsApi.getBaseJSONDataFromURL(paths[1]);

        jsonObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static EventService getInstance() {
        if(service == null) {
            service = new EventService();
        }
        return service;
    }
}
