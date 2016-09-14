package com.example.wesley.myjobs.splash;

import com.example.wesley.myjobs.api.EventService;
import com.example.wesley.myjobs.model.MasterLinks;
import com.google.gson.JsonElement;

import rx.Subscriber;

/**
 * Created by wesley on 9/9/16.
 */
public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private MasterLinks masterLinks;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void buildListUrls() {
        EventService eventService = EventService.getInstance();
        eventService.getListURL(new Subscriber<JsonElement>() {
            @Override
            public void onCompleted() {
                view.urlsReceived(masterLinks);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
            }

            @Override
            public void onNext(JsonElement jsonElement) {
                try {
                    masterLinks = new MasterLinks(jsonElement.getAsJsonObject());
                } catch (Exception e) {
                    view.showError(e);
                }
            }
        });
    }
}

