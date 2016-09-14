package com.example.wesley.myjobs.eventdetail;

/**
 * Created by wesley on 9/9/16.
 */
public abstract class EventDetailPresenter implements EventDetailContract.Presenter {

    protected EventDetailContract.View view;

    public EventDetailPresenter(EventDetailContract.View view) {
        this.view = view;
    }

    @Override
    public abstract void loadDetail(String url);
}
