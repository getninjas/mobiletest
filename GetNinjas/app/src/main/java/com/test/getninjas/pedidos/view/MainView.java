package com.test.getninjas.pedidos.view;

import com.test.getninjas.pedidos.model.data.links.Links;


public interface MainView extends BaseView {
    void onLinksReceived(Links links);
}
