package com.example.wesley.myjobs.leaddetail;

import com.example.wesley.myjobs.api.EventService;
import com.example.wesley.myjobs.eventdetail.EventDetailContract;
import com.example.wesley.myjobs.eventdetail.EventDetailPresenter;
import com.example.wesley.myjobs.model.Lead;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import rx.Subscriber;

/**
 * Created by wesley on 9/9/16.
 */
public class LeadDetailPresenter extends EventDetailPresenter {
    private Lead lead;

    public LeadDetailPresenter(EventDetailContract.View view) {
        super(view);
    }

    @Override
    public void loadDetail(String url) {
        EventService eventService = EventService.getInstance();
        view.showLoading();
        eventService.getBaseURL(url, new Subscriber<JsonElement>() {
            @Override
            public void onCompleted() {
                view.detailReceived(lead);
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
                lead = new Lead(resultObj, true);
            }
        });
    }
}
