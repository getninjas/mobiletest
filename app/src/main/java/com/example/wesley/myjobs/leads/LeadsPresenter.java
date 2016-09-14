package com.example.wesley.myjobs.leads;

import com.example.wesley.myjobs.api.EventService;
import com.example.wesley.myjobs.eventlist.EventListContract;
import com.example.wesley.myjobs.model.Event;
import com.example.wesley.myjobs.model.Lead;
import com.example.wesley.myjobs.model.MasterLinks;
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
public class LeadsPresenter implements EventListContract.Presenter {
    private EventListContract.View view;
    private List<Event> list;

    public LeadsPresenter(EventListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadList(final boolean showLoading) {
        EventService eventService = EventService.getInstance();
        MasterLinks masterLinks = LinkStorage.getMasterLinks();
        if(showLoading) view.showLoading();
        eventService.getBaseURL(masterLinks.getLeads(), new Subscriber<JsonElement>() {
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
                JsonArray array = resultObj.get("leads").getAsJsonArray();
                list = new ArrayList<>();
                for(int i = 0; i < array.size(); i++) {
                    JsonObject json = array.get(i).getAsJsonObject();
                    Event event = new Lead(json, false);
                    list.add(event);
                }
            }
        });
    }
}
