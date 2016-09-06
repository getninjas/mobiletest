//
//  LeadsContract.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

protocol LeadsView : BaseView {
    func leadsListReceived(leadsList:NSArray)
    func errorReceived(e:NSError)
}

protocol LeadsPresenter : BasePresenter {
    func loadLeadsList()
}