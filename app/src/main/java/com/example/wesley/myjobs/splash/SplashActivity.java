package com.example.wesley.myjobs.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.customview.DialogSpinner;
import com.example.wesley.myjobs.eventlist.EventListActivity;
import com.example.wesley.myjobs.model.MasterLinks;
import com.example.wesley.myjobs.storage.LinkStorage;
import com.example.wesley.myjobs.util.DialogUtil;

/**
 * Created by wesley on 9/9/16.
 */
public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        setPresenter(new SplashPresenter(this));
        presenter.buildListUrls();
    }

    @Override
    public void urlsReceived(MasterLinks masterLinks) {
        LinkStorage.setMasterLinks(masterLinks);
        Intent intent = new Intent(SplashActivity.this, EventListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(Throwable e) {
        DialogUtil.showError(SplashActivity.this, e.getLocalizedMessage());
    }
}
