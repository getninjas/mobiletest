//
//  OffersPresenterImpl.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

class OffersPresenterImpl : OffersPresenter {
    var view:OffersView
    
    init(view:OffersView) {
        self.view = view
    }
    
    func loadOfferList() {
        view.showLoading()
        let baseService:BaseService = BaseService()
        
        URLStorage.getOffersLink({ (link: String) -> (Void) in
            baseService.getCustomURL(link,
                callback: { (dict: NSDictionary) -> (Void) in
                    let offers = dict["offers"]! as? NSArray
                    var offerList = [Offer]()
                    
                    for offDict in offers! {
                        let offer = Offer(dict: offDict as! NSDictionary, detail: false)
                        offerList.append(offer)
                    }
                    self.view.hideLoading()
                    self.view.offerListReceived(offerList)
                },
                errorCallback: { (e:NSError) -> (Void) in
                    self.view.errorReceived(e)
            })
        })
    }
}
