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

class OfferDetailViewController: UIViewController, OfferDetailView, UITableViewDelegate, UITableViewDataSource, CLLocationManagerDelegate {
    var offerDetailLink:String!
    var presenter:OfferDetailPresenter!
    var selectedOffer:Offer!
    var userLocation:CLLocation!
    var locationManager:CLLocationManager!
    var delegate:OfferDetailDelegate!
    
    @IBOutlet weak var lblUserName: UILabel!
    @IBOutlet weak var lblUserLocation: UILabel!
    @IBOutlet weak var lblUserDistance: UILabel!
    @IBOutlet weak var mapUserLocation: MKMapView!
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    
        self.presenter = OfferDetailPresenterImpl(view: self)
        if offerDetailLink != nil {
            self.presenter.loadOfferDetail(offerDetailLink)
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
        if userLocation == nil && self.selectedOffer != nil {
            userLocation = locations[0]
            updateDistanceText()
        }
    }
    
    func updateDistanceText() {
        let loc:CLLocation = CLLocation(latitude: self.selectedOffer.address.geolocation.latitude, longitude: self.selectedOffer.address.geolocation.longitude)
        let meters:CLLocationDistance = (userLocation.distanceFromLocation(loc))/1000
        let meterFormated = NSString(format: "%.2f", meters)
        lblUserDistance.text = "a \(meterFormated) Km de você"
        lblUserDistance.hidden = false
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
    
    func offerDetailReceived(offer: Offer) {
        self.selectedOffer = offer
        updateView()
        tableView.delegate = self
        tableView.dataSource = self
        tableView.reloadData()
        updateMapView()
    }
    
    func updateMapView() {
        let center = CLLocationCoordinate2D(latitude: self.selectedOffer.address.geolocation.latitude, longitude: self.selectedOffer.address.geolocation.longitude)
        let region = MKCoordinateRegion(center: center, span: MKCoordinateSpan(latitudeDelta: 0.01, longitudeDelta: 0.01))
        
        self.mapUserLocation.setRegion(region, animated: true)
        
        let dropPin = MKPointAnnotation()
        dropPin.coordinate = self.selectedOffer.address.geolocation
        self.mapUserLocation.addAnnotation(dropPin)
    }
    
    func errorReceived(e: NSError) {
        
    }
    
    func updateView() {
        lblUserName.text = self.selectedOffer.user.name
        lblUserLocation.text = "\(self.selectedOffer.address.neighborhood) - \(self.selectedOffer.address.city)"
        self.navigationItem.title = self.selectedOffer.title
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return selectedOffer.info.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("InfoTableViewCell", forIndexPath: indexPath) as! InfoTableViewCell
        
        let info = selectedOffer.info.objectAtIndex(indexPath.row) as! Info
        cell.updateView(info)
        
        return cell
    }
    
    @IBAction func refuseOffer(sender: AnyObject) {
        navigationController?.popViewControllerAnimated(true)
    }
    
    @IBAction func acceptOffer(sender: AnyObject) {
        navigationController?.popViewControllerAnimated(true)
        delegate.offerAccepted(selectedOffer.acceptLink)
    }
}
