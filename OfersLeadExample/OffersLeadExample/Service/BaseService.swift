//
//  BaseService.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation
import Alamofire

class BaseService {
    
    func getListUrls(callback:(NSDictionary) -> (Void), errorCallback:(NSError) -> (Void)) {
        var plistData: NSDictionary?
        if let path = NSBundle.mainBundle().pathForResource("ServicesConfig", ofType: "plist") {
            plistData = NSDictionary(contentsOfFile: path)
            let endpoint = plistData!["Service Endpoint"] as! String
            
            Alamofire
                .request(.GET, endpoint)
                .responseJSON { response in
                    switch response.result {
                    case .Success:
                        if let dictResponse = response.result.value as! NSDictionary?  {
                            callback(dictResponse)
                        }
                    case .Failure(let error):
                        errorCallback(error)
                    }
                }
        }
    }
    
    func getCustomURL(url:String, callback:(NSDictionary) -> (Void), errorCallback:(NSError) -> (Void)) {
        Alamofire
            .request(.GET, url)
            .responseJSON { response in
                switch response.result {
                case .Success:
                    if let dictResponse = response.result.value as! NSDictionary?  {
                        callback(dictResponse)
                    }
                case .Failure(let error):
                    errorCallback(error)
                }
        }
    }
}