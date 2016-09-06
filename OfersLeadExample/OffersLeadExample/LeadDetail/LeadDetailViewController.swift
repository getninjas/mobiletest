//
//  OfferDetailViewController.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//
import UIKit
import MBProgressHUD
import MapKit
import CoreLocation

class LeadDetailViewController: UIViewController, LeadDetailView, UITableViewDelegate, UITableViewDataSource, CLLocationManagerDelegate {
    var leadDetailLink:String!
    var presenter:LeadDetailPresenter!
    var selectedLead:Lead!
    var locationManager:CLLocationManager!
    var userLocation:CLLocation!
    
    @IBOutlet weak var lblUserName: UILabel!
    @IBOutlet weak var lblUserLocation: UILabel!
    @IBOutlet weak var lblUserDistance: UILabel!
    @IBOutlet weak var lblUserPhone: UILabel!
    @IBOutlet weak var lblUserMail: UILabel!
    @IBOutlet weak var mapUserLocation: MKMapView!
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    
        self.presenter = LeadDetailPresenterImpl(view: self)
        if leadDetailLink != nil {
            self.presenter.loadLeadDetail(leadDetailLink)
        }
        
        self.tabBarController?.tabBar.hidden = true

        self.locationManager = CLLocationManager()
        self.locationManager.delegate = self;
        self.locationManager.desiredAccuracy = kCLLocationAccuracyBest
        self.locationManager.requestAlwaysAuthorization()
        self.locationManager.startUpdatingLocation()
        lblUserDistance.hidden = true
    }
    
    func locationManager(manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if userLocation == nil && self.selectedLead != nil {
            userLocation = locations[0]
            updateDistanceText()
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func showLoading() {
        let loadingNotification = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        loadingNotification.mode = MBProgressHUDMode.Indeterminate
        loadingNotification.label.text = "Carregando"
    }
    
    func hideLoading() {
        MBProgressHUD.hideHUDForView(self.view, animated: true)
    }
    
    func leadDetailReceived(lead: Lead) {
        self.selectedLead = lead
        updateView()
        tableView.delegate = self
        tableView.dataSource = self
        tableView.reloadData()
        updateMapView()
    }
    
    func updateView() {
        lblUserName.text = self.selectedLead.user.name
        lblUserLocation.text = "\(self.selectedLead.address.neighborhood) - \(self.selectedLead.address.city)"
        lblUserPhone.text = self.selectedLead.user.phones.transformInString()
        lblUserMail.text = self.selectedLead.user.email
        self.navigationItem.title = self.selectedLead.title
    }
    
    func updateDistanceText() {
        let loc:CLLocation = CLLocation(latitude: self.selectedLead.address.geolocation.latitude, longitude: self.selectedLead.address.geolocation.longitude)
        let meters:CLLocationDistance = (userLocation.distanceFromLocation(loc))/1000
        let meterFormated = NSString(format: "%.2f", meters)
        lblUserDistance.text = "a \(meterFormated) Km de você"
        lblUserDistance.hidden = false
    }
    
    func updateMapView() {
        let center = CLLocationCoordinate2D(latitude: self.selectedLead.address.geolocation.latitude, longitude: self.selectedLead.address.geolocation.longitude)
        let region = MKCoordinateRegion(center: center, span: MKCoordinateSpan(latitudeDelta: 0.01, longitudeDelta: 0.01))
        
        self.mapUserLocation.setRegion(region, animated: true)
        
        let dropPin = MKPointAnnotation()
        dropPin.coordinate = self.selectedLead.address.geolocation
        self.mapUserLocation.addAnnotation(dropPin)
    }
    
    func errorReceived(e: NSError) {
        
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return selectedLead.info.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("InfoTableViewCell", forIndexPath: indexPath) as! InfoTableViewCell
      
        let info = selectedLead.info.objectAtIndex(indexPath.row) as! Info
        cell.updateView(info)
        
        return cell
    }
    
    @IBAction func btnCallPhone(sender: AnyObject) {
        if let url = NSURL(string: "tel://\(replacePhone(self.selectedLead.user.phones[0] as! String))") {
            UIApplication.sharedApplication().openURL(url)
        }
    }
    
    @IBAction func btnCallWhatsapp(sender: AnyObject) {
        let urlString = "Olá!"
        let urlStringEncoded = urlString.stringByAddingPercentEncodingWithAllowedCharacters(.URLHostAllowedCharacterSet())
        let url  = NSURL(string: "whatsapp://send?text=\(urlStringEncoded!)")
        
        if UIApplication.sharedApplication().canOpenURL(url!) {
            UIApplication.sharedApplication().openURL(url!)
        }
    }
    
    func replacePhone(phone:String) -> String {
        var ret:String = phone
        ret = phone.stringByReplacingOccurrencesOfString("(", withString: "")
        ret = ret.stringByReplacingOccurrencesOfString(")", withString: "")
        ret = ret.stringByReplacingOccurrencesOfString("-", withString: "")
        ret = ret.stringByReplacingOccurrencesOfString(" ", withString: "")
        return ret
    }
}
