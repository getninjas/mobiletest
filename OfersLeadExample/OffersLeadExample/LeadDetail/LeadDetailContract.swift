//
//  OfferDetailContract.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import Foundation

protocol LeadDetailView : BaseView {
    func leadDetailReceived(lead: Lead)
    func errorReceived(e:NSError)
}

protocol LeadDetailPresenter : BasePresenter {
    func loadLeadDetail(leadDetailLink: String)
}