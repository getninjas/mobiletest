//
//  OfferDetailPresenterImpl.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

class OfferDetailPresenterImpl : OfferDetailPresenter {
    let view:OfferDetailView
    
    init(view: OfferDetailView) {
        self.view = view
    }
    
    func loadOfferDetail(offerDetailLink: String) {
        view.showLoading()
        let baseService:BaseService = BaseService()
        
        baseService.getCustomURL(offerDetailLink,
            callback: { (dict: NSDictionary) -> (Void) in
            let offer = Offer(dict: dict, detail: true)
            self.view.offerDetailReceived(offer)
            self.view.hideLoading()
        },
        errorCallback: { (e:NSError) -> (Void) in
            self.view.errorReceived(e)
            self.view.hideLoading()
        })
    }
}