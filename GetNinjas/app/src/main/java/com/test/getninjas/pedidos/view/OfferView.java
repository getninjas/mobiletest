package com.test.getninjas.pedidos.view;

import com.test.getninjas.pedidos.model.data.offer.Offer;

/**
 * Created by vanlopes on 30/07/16.
 */
public interface OfferView extends BaseView {
    void onOfferDetailsReceived(Offer offer);

}
