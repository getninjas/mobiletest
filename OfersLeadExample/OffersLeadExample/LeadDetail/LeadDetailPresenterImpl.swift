//
//  OfferDetailPresenterImpl.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

class LeadDetailPresenterImpl : LeadDetailPresenter {
    let view:LeadDetailView
    
    init(view: LeadDetailView) {
        self.view = view
    }
    
    func loadLeadDetail(leadDetailLink: String) {
        view.showLoading()
        let baseService:BaseService = BaseService()
        
        baseService.getCustomURL(leadDetailLink,
            callback: { (dict: NSDictionary) -> (Void) in
            let lead = Lead(dict: dict, detail: true)
            self.view.leadDetailReceived(lead)
            self.view.hideLoading()
        },
        errorCallback: { (e:NSError) -> (Void) in
            self.view.errorReceived(e)
            self.view.hideLoading()
        })
    }
}