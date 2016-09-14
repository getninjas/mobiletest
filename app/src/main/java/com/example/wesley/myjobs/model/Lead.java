package com.example.wesley.myjobs.model;

import android.util.Log;

import com.example.wesley.myjobs.util.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by wesley on 9/7/16.
 */
public class Lead extends Event {

    public Lead(JsonObject json, boolean detail) {
        if(detail) {
            initDetail(json);
        } else {
            initList(json);
        }
    }

    private void initDetail(JsonObject json) {
        if(json.has("distance")) {
            this.distance = json.get("distance").getAsInt();
        }
        if(json.has("lead_price")) {
            this.leadPrice = json.get("lead_price").getAsInt();
        }
        if(json.has("title")) {
            this.title = json.get("title").getAsString();
        }
        if(json.has("_embedded")) {
            JsonObject _embedded = json.get("_embedded").getAsJsonObject();
            if(_embedded.has("info")) {
                JsonArray infoList = _embedded.get("info").getAsJsonArray();
                this.info = new LinkedList<>();
                for(int i = 0; i < infoList.size(); i++) {
                    JsonObject info = infoList.get(i).getAsJsonObject();
                    this.info.add(new Info(info));
                }
            }
            if(_embedded.has("user")) {
                this.user = new User(_embedded.get("user").getAsJsonObject());
            }
            if(_embedded.has("address")) {
                this.address = new Address(_embedded.get("address").getAsJsonObject());
            }
        }
    }

    private void initList(JsonObject json) {
        if(json.has("created_at")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            try {
                String dateStr = StringUtil.clearDateNoTimezone(json.get("created_at").getAsString());
                this.creation = sdf.parse(dateStr);
            } catch (Exception e) {
                Log.e("LEAD_PARSE", e.getMessage());
            }
        }
        if(json.has("_embedded")) {
            JsonObject _embedded = json.get("_embedded").getAsJsonObject();
            if(_embedded.has("address")) {
                this.address = new Address(_embedded.get("address").getAsJsonObject());
            }
            if(_embedded.has("user")) {
                this.user = new User(_embedded.get("user").getAsJsonObject());
            }
            if(_embedded.has("request")) {
                JsonObject request = _embedded.get("request").getAsJsonObject();
                if(request.has("title")) {
                    this.title = request.get("title").getAsString();
                }
            }
        }
        if(json.has("_links")) {
            JsonObject _links = json.get("_links").getAsJsonObject();
            this.detailLink = _links.get("self").getAsJsonObject().get("href").getAsString();
        }
    }
}
