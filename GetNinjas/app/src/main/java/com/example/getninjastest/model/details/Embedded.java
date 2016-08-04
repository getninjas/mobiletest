package com.example.getninjastest.model.details;

/**
 * Created by AsifMoinul on 8/1/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Embedded {

    @SerializedName("info")
    @Expose
    private List<Info> info = new ArrayList<Info>();
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("address")
    @Expose
    private Address address;

    /**
     *
     * @return
     * The info
     */
    public List<Info> getInfo() {
        return info;
    }

    /**
     *
     * @param info
     * The info
     */
    public void setInfo(List<Info> info) {
        this.info = info;
    }

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

}