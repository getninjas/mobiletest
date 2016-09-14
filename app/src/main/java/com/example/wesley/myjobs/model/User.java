package com.example.wesley.myjobs.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wesley on 9/7/16.
 */
public class User {
    private String name;
    private String email;
    private List<String> phones;

    public User(JsonObject json) {
        if(json.has("name")) {
            this.name = json.get("name").getAsString();
        }

        if(json.has("email")) {
            this.email = json.get("email").getAsString();
        }

        if(json.has("_embedded")) {
            JsonObject _embedded = json.get("_embedded").getAsJsonObject();
            if(_embedded.has("phones")) {
                JsonArray phones = _embedded.get("phones").getAsJsonArray();
                this.phones = new LinkedList<>();
                for(int i = 0; i < phones.size(); i++) {
                    JsonObject phone = phones.get(i).getAsJsonObject();
                    this.phones.add(phone.get("number").getAsString());
                }
            }
            this.email = json.get("email").getAsString();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
