package com.test.getninjas.pedidos.presenter;

import com.google.gson.Gson;
import com.test.getninjas.pedidos.model.data.ResponseObject;
import com.test.getninjas.pedidos.model.data.links.Links;
import com.test.getninjas.pedidos.model.data.offer.Address;
import com.test.getninjas.pedidos.model.data.offer.Info;
import com.test.getninjas.pedidos.model.data.offer.Offer;
import com.test.getninjas.pedidos.model.data.offer.User;
import com.test.getninjas.pedidos.utils.Key;
import com.test.getninjas.pedidos.utils.WebClient;
import com.test.getninjas.pedidos.view.OfferView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vanlopes on 30/07/16.
 */
public class OfferPresenter implements BasePresenter<OfferView>, WebClient.WebClientListener {

    private OfferView view;
    private boolean isOffer;

    public void getOffer(String href, boolean isOffer) {
        this.isOffer = isOffer;
        WebClient client = new WebClient();
        client.get(href, this);
    }

    @Override
    public void attachView(OfferView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onSuccess(int statusCode, String response) {
        JSONObject o = new JSONObject();
        Offer offer = new Offer();
        try {
            o = new JSONObject(response);
            offer.distance = o.getInt("distance");
            offer.title = o.getString("title");

            //Verify if the request call offers. If yes, get links for reject/accept terms.
            if(isOffer)
                offer.links = new Gson().fromJson(o.getJSONObject(Key.LINKS).toString(), Links.class);
            JSONObject userJson = o.getJSONObject(Key.EMBEDDED).getJSONObject("user");
            offer.user = new Gson().fromJson(userJson.toString(), User.class);
            offer.user.phone = userJson.getJSONObject(Key.EMBEDDED)
                    .getJSONArray("phones")
                    .getJSONObject(0)
                    .getString("number");
            JSONObject addressJson = o.getJSONObject(Key.EMBEDDED).getJSONObject("address");
            offer.address = new Gson().fromJson(addressJson.toString(), Address.class);
            offer.address.lat = addressJson.getJSONObject("geolocation").getDouble("latitude");
            offer.address.lng = addressJson.getJSONObject("geolocation").getDouble("longitude");

            JSONArray infosJson = o.getJSONObject(Key.EMBEDDED).getJSONArray("info");
            offer.infos = new ArrayList<>();
            for (int i = 0; i < infosJson.length(); i++) {
                JSONObject obj = infosJson.getJSONObject(i);
                Info info = new Info();
                info.label = obj.getString("label");
                if(obj.get("value") instanceof JSONArray) {
                    JSONArray valueArray = obj.getJSONArray("value");
                    for (int j = 0; j < valueArray.length(); j++) {
                        if(info.value != null)
                            info.value += ", " + valueArray.get(j).toString();
                        else
                            info.value = valueArray.get(j).toString();
                    }
                }
                else
                    info.value = obj.getString("value");

                offer.infos.add(info);
            }


            view.onOfferDetailsReceived(offer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, String errorResponse) {
        view.onOfferDetailsReceived(null);
    }
}
