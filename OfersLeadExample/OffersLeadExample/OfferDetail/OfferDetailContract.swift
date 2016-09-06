//
//  OfferDetailContract.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

protocol OfferDetailView : BaseView {
    func offerDetailReceived(offer: Offer)
    func errorReceived(e:NSError)
}

protocol OfferDetailPresenter : BasePresenter {
    func loadOfferDetail(offerDetailLink: String)
}

protocol OfferDetailDelegate {
    func offerAccepted(link : String)
}