package com.test.getninjas.pedidos.model.data.offer;

import com.test.getninjas.pedidos.model.data.links.Links;

import java.util.List;


public class Offer {

    public String state;
    public String title;
    public String createdAt;
    public int distance;
    public User user;
    public Address address;
    public List<Info> infos;
    public Links links;

}
