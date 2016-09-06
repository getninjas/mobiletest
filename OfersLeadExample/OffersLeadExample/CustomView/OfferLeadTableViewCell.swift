//
//  OfferLeadTableViewCell.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/5/16.
//  Copyright © 2016 Wesley. All rights reserved.
//
import UIKit

class OfferLeadTableViewCell: UITableViewCell {

    @IBOutlet weak var topTitleView: UIView!
    @IBOutlet weak var lblEventName: UILabel!
    @IBOutlet weak var lblEventUserName: UILabel!
    @IBOutlet weak var lblEventDate: UILabel!
    @IBOutlet weak var lblEventPlace: UILabel!
    @IBOutlet weak var imgIconUser: UIImageView!
    @IBOutlet weak var imgIconLocation: UIImageView!
    @IBOutlet weak var imgIconCalendar: UIImageView!
    @IBOutlet weak var cellContentView: UIView!
    
    
    func updateView(event: Event) {
        lblEventName.text = event.title
        lblEventUserName.text = event.user.name
        
        let dateFormater = NSDateFormatter()
        dateFormater.dateFormat = "dd MMM yyyy";
        lblEventDate.text = dateFormater.stringFromDate(event.creation)
        lblEventPlace.text = "\(event.address.neighborhood) - \(event.address.city) - \(event.address.uf)"
        
        if event is Offer {
            updateOfferViewColors(event as! Offer)
        }
        addShadowToContent()
    }
    
    func updateOfferViewColors(offer: Offer) {
        if offer.state == "unread" {
            topTitleView.backgroundColor = UIColor(hex: 0x4A4A4A)
            imgIconUser.image = UIImage.init(named: "ic_user_gray")
            imgIconLocation.image = UIImage.init(named: "ic_location_gray")
            imgIconCalendar.image = UIImage.init(named: "ic_calendar_gray")
        }
    }
    
    func addShadowToContent() {
        cellContentView.layer.shadowColor = UIColor.grayColor().CGColor
        cellContentView.layer.shadowOpacity = 1
        cellContentView.layer.shadowOffset = CGSizeMake(0, 1);
        cellContentView.layer.shadowRadius = 1
    }
}

extension UIColor {
    convenience init(hex: Int) {
        let components = (
            R: CGFloat((hex >> 16) & 0xff) / 255,
            G: CGFloat((hex >> 08) & 0xff) / 255,
            B: CGFloat((hex >> 00) & 0xff) / 255
        )
        
        self.init(red: components.R, green: components.G, blue: components.B, alpha: 1)
    }
}
