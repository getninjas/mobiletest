package com.example.wesley.myjobs.model;

import com.google.gson.JsonObject;

/**
 * Created by wesley on 9/7/16.
 */
public class Phone {
    private String number;

    public Phone(JsonObject json) {
        if(json.has("number")) {
            this.number = json.get("number").getAsString();
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}