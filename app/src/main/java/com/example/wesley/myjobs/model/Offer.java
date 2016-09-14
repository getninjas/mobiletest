package com.example.wesley.myjobs.model;

import android.util.Log;

import com.example.wesley.myjobs.util.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 * Created by wesley on 9/7/16.
 */
public class Offer extends Event {
    private String acceptLink;
    private String rejectLink;
    private String state;

    public Offer(JsonObject json, boolean detail) {
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
        if(json.has("_links")) {
            JsonObject _links = json.get("_links").getAsJsonObject();
            JsonObject acceptedJson = _links.get("accept").getAsJsonObject();
            this.acceptLink = acceptedJson.get("href").getAsString();
            JsonObject rejectJson = _links.get("reject").getAsJsonObject();
            this.rejectLink = rejectJson.get("href").getAsString();
        }
    }

    private void initList(JsonObject json) {
        if(json.has("state")) {
            this.state = json.get("state").getAsString();
        }
        if(json.has("_embedded")) {
            JsonObject _embedded = json.get("_embedded").getAsJsonObject();
            if(_embedded.has("request")) {
                JsonObject request = _embedded.get("request").getAsJsonObject();
                if(request.has("created_at")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                    try {
                        String dateStr = StringUtil.clearDateNoTimezone(request.get("created_at").getAsString());
                        this.creation = sdf.parse(dateStr);
                    } catch (ParseException e) {
                        Log.e("Offer", e.getMessage());
                    }
                }
                if(request.has("title")) {
                    this.title = request.get("title").getAsString();
                }
                if(request.has("_embedded")) {
                    JsonObject reqEmbedded = request.get("_embedded").getAsJsonObject();
                    if(reqEmbedded.has("user")) {
                        this.user = new User(reqEmbedded.get("user").getAsJsonObject());
                    }
                    if(reqEmbedded.has("address")) {
                        this.address = new Address(reqEmbedded.get("address").getAsJsonObject());
                    }
                }
            }
        }
        if(json.has("_links")) {
            JsonObject _links = json.get("_links").getAsJsonObject();
            this.detailLink = _links.get("self").getAsJsonObject().get("href").getAsString();
        }
    }

    public String getAcceptLink() {
        return acceptLink;
    }

    public void setAcceptLink(String acceptLink) {
        this.acceptLink = acceptLink;
    }

    public String getRejectLink() {
        return rejectLink;
    }

    public void setRejectLink(String rejectLink) {
        this.rejectLink = rejectLink;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
