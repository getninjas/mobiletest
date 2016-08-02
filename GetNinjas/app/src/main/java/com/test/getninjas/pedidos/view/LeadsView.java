package com.test.getninjas.pedidos.view;

import com.test.getninjas.pedidos.model.data.ResponseObject;


public interface LeadsView extends BaseView {
    void onLeadsReceived(ResponseObject object);
}
