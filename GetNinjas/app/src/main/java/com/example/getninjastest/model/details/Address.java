package com.example.getninjastest.model.details;

/**
 * Created by AsifMoinul on 8/1/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Address {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("neighborhood")
    @Expose
    private String neighborhood;
    @SerializedName("uf")
    @Expose
    private String uf;
    @SerializedName("geolocation")
    @Expose
    private Geolocation geolocation;

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The neighborhood
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     *
     * @param neighborhood
     * The neighborhood
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     *
     * @return
     * The uf
     */
    public String getUf() {
        return uf;
    }

    /**
     *
     * @param uf
     * The uf
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     *
     * @return
     * The geolocation
     */
    public Geolocation getGeolocation() {
        return geolocation;
    }

    /**
     *
     * @param geolocation
     * The geolocation
     */
    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

}
