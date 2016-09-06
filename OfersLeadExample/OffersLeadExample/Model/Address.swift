//
//  Address.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation
import CoreLocation

class Address {
    var city:String!
    var uf:String!
    var neighborhood:String!
    var geolocation:CLLocationCoordinate2D!
    
    init(dict: NSDictionary) {
        if let city = dict["city"] as? String {
            self.city = city
        }
        if let neighborhood = dict["neighborhood"] as? String {
            self.neighborhood = neighborhood
        }
        if let uf = dict["uf"] as? String {
            self.uf = uf
        }
        if let geolocation = dict["geolocation"] as? [String: Double] {
            let lat = geolocation["latitude"]! as Double
            let lon = geolocation["longitude"]! as Double
            self.geolocation = CLLocationCoordinate2D(latitude: lat, longitude: lon)
        }
    }
}