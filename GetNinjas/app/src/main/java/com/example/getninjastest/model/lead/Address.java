package com.example.getninjastest.model.lead;

/**
 * Created by AsifMoinul on 7/31/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Address {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("neighborhood")
    @Expose
    private String neighborhood;
    @SerializedName("uf")
    @Expose
    private String uf;

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return The neighborhood
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * @param neighborhood The neighborhood
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * @return The uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf The uf
     */
    public void setUf(String uf) {
        this.uf = uf;
    }
}