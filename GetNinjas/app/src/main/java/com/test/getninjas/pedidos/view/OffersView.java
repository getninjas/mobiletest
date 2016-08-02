package com.test.getninjas.pedidos.view;

import com.test.getninjas.pedidos.model.data.ResponseObject;


public interface OffersView extends BaseView {
    void onOffersReceived(ResponseObject o);
}
