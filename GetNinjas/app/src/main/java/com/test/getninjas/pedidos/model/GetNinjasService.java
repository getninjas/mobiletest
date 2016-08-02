package com.test.getninjas.pedidos.model;

import com.test.getninjas.pedidos.BuildConfig;
import com.test.getninjas.pedidos.utils.WebClient;


public class GetNinjasService implements WebClient.WebClientListener {

    private WebClient client;

    public GetNinjasService() { client = new WebClient(); }

    public void getLinks() {
        client.get(BuildConfig.BASE_URL, this);
    }

    @Override
    public void onSuccess(int statusCode, String response) {

    }

    @Override
    public void onFailure(int statusCode, String errorResponse) {

    }
}
