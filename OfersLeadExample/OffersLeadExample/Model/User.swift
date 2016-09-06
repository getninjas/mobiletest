//
//  User.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

class User {
    var name:String!
    var email:String!
    var phones:NSMutableArray!
    
    init(dict:NSDictionary) {
        if let name = dict["name"] as? String {
            self.name = name
        }
        
        if let email = dict["email"] as? String {
            self.email = email
        }
        
        if let _embedded = dict["_embedded"] as? [String: NSArray] {
            if let phones = _embedded["phones"] {
                self.phones = NSMutableArray()
                for phone in phones  {
                    self.phones.addObject((phone as? NSDictionary)!["number"]!)
                }
            }
        }
    }
}

class Phone {
    var number:NSString!
}
