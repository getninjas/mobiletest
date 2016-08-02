package com.test.getninjas.pedidos.model.data;

import com.test.getninjas.pedidos.model.data.links.Links;
import com.test.getninjas.pedidos.model.data.links.Offers;
import com.test.getninjas.pedidos.model.data.offer.Offer;

import java.util.List;


public class ResponseObject {

    public Links links;
    public List<Offer> offers;
    public List<Offer> leads;

    public ResponseObject() {}
}
