package com.example.getninjastest.model.lead;

/**
 * Created by AsifMoinul on 7/31/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Embedded {

    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("request")
    @Expose
    private Request request;

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
     * The request
     */
    public Request getRequest() {
        return request;
    }

    /**
     *
     * @param request
     * The request
     */
    public void setRequest(Request request) {
        this.request = request;
    }

}