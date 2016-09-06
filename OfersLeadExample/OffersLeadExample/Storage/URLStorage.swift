//
//  URLStorage.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

class URLStorage {
    private static var offersLink:String!
    private static var leadsLink:String!
    
    static func getOffersLink(callback:(String) -> (Void)) {
        if(leadsLink != nil || offersLink != nil) {
            callback(offersLink)
        } else {
            let baseService:BaseService = BaseService()
            baseService.getListUrls({
                (dict: NSDictionary) -> (Void) in
                if let _links = dict["_links"] as? [String: NSDictionary] {
                    if let leads = _links["leads"] as? [String: String] {
                        if let href = leads["href"] {
                            URLStorage.setLeadsLink(href)
                        }
                    }
                    if let offers = _links["offers"] as? [String: String] {
                        if let href = offers["href"] {
                            URLStorage.setOffersLink(href)
                        }
                    }
                    callback(offersLink)
                }
                },
                errorCallback: {(e:NSError) -> (Void) in
                    print("Erro: \(e)")
            })
        }
    }
    
    static func getLeadsLink(callback:(String) -> (Void)) {
        if(leadsLink != nil || offersLink != nil) {
            callback(leadsLink)
        } else {
            let baseService:BaseService = BaseService()
            baseService.getListUrls({
                (dict: NSDictionary) -> (Void) in
                if let _links = dict["_links"] as? [String: NSDictionary] {
                    if let leads = _links["leads"] as? [String: String] {
                        if let href = leads["href"] {
                            URLStorage.setLeadsLink(href)
                        }
                    }
                    if let offers = _links["offers"] as? [String: String] {
                        if let href = offers["href"] {
                            URLStorage.setOffersLink(href)
                        }
                    }
                    callback(leadsLink)
                }
                },
                errorCallback: {(e:NSError) -> (Void) in
                    print("Erro: \(e)")
            })
        }
    }
    
    static func setOffersLink(link:String) {
        offersLink = link
    }
    
    static func setLeadsLink(link:String) {
        leadsLink = link
    }
}