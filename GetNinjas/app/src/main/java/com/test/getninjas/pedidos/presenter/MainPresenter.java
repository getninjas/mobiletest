package com.test.getninjas.pedidos.presenter;

import com.google.gson.Gson;
import com.test.getninjas.pedidos.BuildConfig;
import com.test.getninjas.pedidos.model.data.ResponseObject;
import com.test.getninjas.pedidos.model.data.links.Links;
import com.test.getninjas.pedidos.utils.WebClient;
import com.test.getninjas.pedidos.view.MainView;

import org.json.JSONException;
import org.json.JSONObject;


public class MainPresenter implements BasePresenter<MainView>, WebClient.WebClientListener{

    private MainView view;

    public void getLinks() {
        WebClient client = new WebClient();
        //The entrypoint is on Gradle.
        client.get(BuildConfig.BASE_URL, this);
    }

    @Override
    public void attachView(MainView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onSuccess(int statusCode, String response) {
        response = response.replace("_", "");
        ResponseObject responseObject = new Gson().fromJson(response, ResponseObject.class);
        view.onLinksReceived(responseObject.links);
    }

    @Override
    public void onFailure(int statusCode, String errorResponse) {
        view.onLinksReceived(null);
    }
}
