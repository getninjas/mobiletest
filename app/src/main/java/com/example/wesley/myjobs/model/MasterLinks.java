package com.example.wesley.myjobs.model;

import com.google.gson.JsonObject;

/**
 * Created by wesley on 9/7/16.
 */
public class MasterLinks {
    private String leads;
    private String offers;

    public MasterLinks(JsonObject json) throws Exception {
        if(json.has("_links")) {
            JsonObject _links = json.getAsJsonObject("_links");
            if(_links.has("leads")) {
                JsonObject leads = _links.getAsJsonObject("leads");
                this.leads = leads.get("href").getAsString();
            }
            if(_links.has("offers")) {
                JsonObject offers = _links.getAsJsonObject("offers");
                this.offers = offers.get("href").getAsString();
            }
        }
    }

    public String getLeads() {
        return leads;
    }

    public void setLeads(String leads) {
        this.leads = leads;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }
}
