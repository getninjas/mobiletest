package com.example.wesley.myjobs.model;

import java.util.Date;
import java.util.List;

/**
 * Created by wesley on 9/7/16.
 */
public class Event {
    protected Integer distance;
    protected Integer leadPrice;
    protected String title;
    protected List<Info> info;
    protected User user;
    protected Address address;
    protected Date creation;
    protected String detailLink;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getLeadPrice() {
        return leadPrice;
    }

    public void setLeadPrice(Integer leadPrice) {
        this.leadPrice = leadPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }
}