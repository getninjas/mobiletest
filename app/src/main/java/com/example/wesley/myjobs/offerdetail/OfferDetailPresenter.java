package com.example.wesley.myjobs.offerdetail;

import com.example.wesley.myjobs.api.EventService;
import com.example.wesley.myjobs.eventdetail.EventDetailContract;
import com.example.wesley.myjobs.eventdetail.EventDetailPresenter;
import com.example.wesley.myjobs.model.Lead;
import com.example.wesley.myjobs.model.Offer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import rx.Subscriber;

/**
 * Created by wesley on 9/9/16.
 */
public class OfferDetailPresenter extends EventDetailPresenter {

    private Offer offer;
    public OfferDetailPresenter(EventDetailContract.View view) {
        super(view);
    }

    @Override
    public void loadDetail(String url) {
        EventService eventService = EventService.getInstance();
        view.showLoading();
        eventService.getBaseURL(url, new Subscriber<JsonElement>() {
            @Override
            public void onCompleted() {
                view.detailReceived(offer);
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
                view.hideLoading();
            }

            @Override
            public void onNext(JsonElement jsonElement) {
                JsonObject resultObj = jsonElement.getAsJsonObject();
                offer = new Offer(resultObj, true);
            }
        });
    }
}
