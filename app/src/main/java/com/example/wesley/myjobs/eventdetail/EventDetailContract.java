package com.example.wesley.myjobs.eventdetail;

import com.example.wesley.myjobs.BasePresenter;
import com.example.wesley.myjobs.BaseView;
import com.example.wesley.myjobs.model.Event;

/**
 * Created by wesley on 9/9/16.
 */
public interface EventDetailContract {

    interface View extends BaseView<Presenter> {
        void detailReceived(Event event);
    }

    interface Presenter extends BasePresenter {
        void loadDetail(String url);
    }
}
