//
//  OffersContract.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

protocol OffersView : BaseView {
    func offerListReceived(offerList:NSArray)
    func errorReceived(e:NSError)
}

protocol OffersPresenter : BasePresenter {
    func loadOfferList()
}