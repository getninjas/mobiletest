//
//  Info.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

class Info {
    var label:String!
    var value:NSArray!
    
    init(dict:NSDictionary) {
        if let label = dict["label"] as? String {
            self.label = label
        }
        if let value = dict["value"] as? String {
            self.value = NSArray(array: [value])
        } else if let value = dict["value"] as? NSArray {
            self.value = value
        }
    }
}
