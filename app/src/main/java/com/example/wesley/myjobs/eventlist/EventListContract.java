package com.example.wesley.myjobs.eventlist;

import com.example.wesley.myjobs.BasePresenter;
import com.example.wesley.myjobs.BaseView;
import com.example.wesley.myjobs.model.Event;
import com.example.wesley.myjobs.model.Lead;

import java.util.List;

/**
 * Created by wesley on 9/9/16.
 */
public interface EventListContract {

    interface View extends BaseView<Presenter> {
        void listReceived(List<Event> leadList);
    }

    interface Presenter extends BasePresenter {
        void loadList(boolean showLoading);
    }
}
