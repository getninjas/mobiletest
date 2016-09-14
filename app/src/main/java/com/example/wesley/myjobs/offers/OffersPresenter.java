package com.example.wesley.myjobs.offers;

import com.example.wesley.myjobs.api.EventService;
import com.example.wesley.myjobs.eventlist.EventListContract;
import com.example.wesley.myjobs.model.Event;
import com.example.wesley.myjobs.model.MasterLinks;
import com.example.wesley.myjobs.model.Offer;
import com.example.wesley.myjobs.storage.LinkStorage;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by wesley on 9/9/16.
 */
public class OffersPresenter implements EventListContract.Presenter {

    private EventListContract.View view;
    private List<Event> list;

    public OffersPresenter(EventListContract.View view) {
        this.view = view;
    }


    @Override
    public void loadList(final boolean showLoading) {
        EventService eventService = EventService.getInstance();
        MasterLinks masterLinks = LinkStorage.getMasterLinks();
        if(showLoading) view.showLoading();
        eventService.getBaseURL(masterLinks.getOffers(), new Subscriber<JsonElement>() {
            @Override
            public void onCompleted() {
                view.listReceived(list);
                if(showLoading) view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
                if(showLoading) view.hideLoading();
            }

            @Override
            public void onNext(JsonElement jsonElement) {
                JsonObject resultObj = jsonElement.getAsJsonObject();
                JsonArray leadsArray = resultObj.get("offers").getAsJsonArray();
                list = new ArrayList<>();
                for(int i = 0; i < leadsArray.size(); i++) {
                    JsonObject json = leadsArray.get(i).getAsJsonObject();
                    Offer offer = new Offer(json, false);
                    list.add(offer);
                }
            }
        });
    }
}
