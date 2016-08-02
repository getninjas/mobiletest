package com.test.getninjas.pedidos.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.test.getninjas.pedidos.model.data.ResponseObject;
import com.test.getninjas.pedidos.model.data.links.Links;
import com.test.getninjas.pedidos.model.data.links.Self;
import com.test.getninjas.pedidos.model.data.offer.Address;
import com.test.getninjas.pedidos.model.data.offer.Offer;
import com.test.getninjas.pedidos.model.data.offer.User;
import com.test.getninjas.pedidos.utils.Key;
import com.test.getninjas.pedidos.utils.WebClient;
import com.test.getninjas.pedidos.view.OffersView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OffersPresenter implements BasePresenter<OffersView>, WebClient.WebClientListener {

    private OffersView view;
    private Self self;

    public void getOffers(String href) {
        WebClient client = new WebClient();
        client.get(href, this);
    }

    public void refresh() {

    }

    @Override
    public void attachView(OffersView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onSuccess(int statusCode, String response) {
        JSONObject o = new JSONObject();
        ResponseObject rObj = new ResponseObject();
        try {
            o = new JSONObject(response);
            rObj.links = new Gson().fromJson(o.getJSONObject(Key.LINKS).toString(), Links.class);
            rObj.offers = new ArrayList<>();
            JSONArray array = o.getJSONArray("offers");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Offer offer = new Offer();
                offer.state = obj.getString("state");
                offer.links = new Gson().fromJson(obj.getJSONObject(Key.LINKS).toString(), Links.class);
                obj = obj.getJSONObject(Key.EMBEDDED);
                obj = obj.getJSONObject("request");
                offer.createdAt = obj.getString("created_at");
                offer.title = obj.getString("title");
                JSONObject objUser = obj.getJSONObject(Key.EMBEDDED).getJSONObject("user");
                offer.user = new Gson().fromJson(objUser.toString(), User.class);
                JSONObject objAddress = obj.getJSONObject(Key.EMBEDDED).getJSONObject("address");
                offer.address =  new Gson().fromJson(objAddress.toString(), Address.class);
                rObj.offers.add(offer);
            }
            view.onOffersReceived(rObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(int statusCode, String errorResponse) {
        view.onOffersReceived(null);
    }
}
