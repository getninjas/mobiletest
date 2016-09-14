package com.example.wesley.myjobs.model;

import com.google.gson.JsonObject;

/**
 * Created by wesley on 9/7/16.
 */
public class Address {
    private String city;
    private String uf;
    private String neighborhood;
    private Double latitude;
    private Double longitude;

    public Address(JsonObject json) {
        if(json.has("city")) {
            this.city = json.get("city").getAsString();
        }
        if(json.has("neighborhood")) {
            this.neighborhood = json.get("neighborhood").getAsString();
        }
        if(json.has("uf")) {
            this.uf = json.get("uf").getAsString();
        }
        if(json.has("geolocation")) {
            JsonObject geolocation = json.get("geolocation").getAsJsonObject();
            this.latitude = geolocation.get("latitude").getAsDouble();
            this.longitude = geolocation.get("longitude").getAsDouble();
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
