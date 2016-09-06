//
//  LeadsPresenterImpl.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

class LeadsPresenterImpl : LeadsPresenter {
    let view:LeadsView
    
    init(view:LeadsView) {
        self.view = view
    }
    
    func loadLeadsList() {
        view.showLoading()
        let baseService:BaseService = BaseService()
        
        URLStorage.getLeadsLink({ (link: String) -> (Void) in
            baseService.getCustomURL(link,
                callback: { (dict: NSDictionary) -> (Void) in
                    let leads = dict["leads"]! as? NSArray
                    var leadList = [Lead]()
                    
                    for leadDict in leads! {
                        let lead = Lead(dict: leadDict as! NSDictionary, detail: false)
                        leadList.append(lead)
                    }
                    self.view.hideLoading()
                    self.view.leadsListReceived(leadList)
                },
                errorCallback: { (e:NSError) -> (Void) in
                    self.view.errorReceived(e)
            })
        })
    }
}