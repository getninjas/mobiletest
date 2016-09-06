//
//  InfoTableViewCell.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/5/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import UIKit

class InfoTableViewCell : UITableViewCell {
    
    @IBOutlet weak var lblInfoTitle: UILabel!
    @IBOutlet weak var lblInfoDetail: UILabel!
    
    func updateView(info: Info) {
        lblInfoTitle.text = info.label
        lblInfoDetail.text = info.value.transformInString()
    }    
}

extension NSArray {
    func transformInString() -> String {
        var finalString = ""
        
        if self.count > 0 {
            for index in 0..<self.count {
                finalString += self.objectAtIndex(index) as! String
                if index < self.count - 1 {
                    finalString += " - "
                }
            }
        }
        return finalString
    }
}
