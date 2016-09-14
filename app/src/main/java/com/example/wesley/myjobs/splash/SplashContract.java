package com.example.wesley.myjobs.splash;

import com.example.wesley.myjobs.BasePresenter;
import com.example.wesley.myjobs.BaseView;
import com.example.wesley.myjobs.model.MasterLinks;

/**
 * Created by wesley on 9/9/16.
 */
public interface SplashContract {

    interface View extends BaseView<Presenter> {
        void urlsReceived(MasterLinks masterLinks);
    }

    interface Presenter extends BasePresenter {
        void buildListUrls();
    }
}
