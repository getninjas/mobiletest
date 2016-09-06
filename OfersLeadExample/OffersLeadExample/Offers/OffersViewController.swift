//
//  FirstViewController.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//
import UIKit
import MBProgressHUD

class OffersViewController: UITableViewController, OffersView, OfferDetailDelegate {
    var presenter:OffersPresenter!
    var offerList:NSArray = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.presenter = OffersPresenterImpl(view: self)
        self.presenter.loadOfferList()
        
        tableView.separatorStyle = .None
        
        refreshControl = UIRefreshControl()
        refreshControl!.attributedTitle = NSAttributedString(string: "Carregando...")
        refreshControl!.addTarget(self, action: #selector(OffersViewController.refresh(_:)), forControlEvents: UIControlEvents.ValueChanged)
        tableView.addSubview(refreshControl!)
    }
    
    override func viewWillAppear(animated: Bool) {
        self.tabBarController?.tabBar.hidden = false
    }

    func refresh(sender:AnyObject) {
        self.presenter.loadOfferList()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    func showLoading() {
        let loadingNotification = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        loadingNotification.mode = MBProgressHUDMode.Indeterminate
        loadingNotification.label.text = "Carregando"
    }
    
    func hideLoading() {
        MBProgressHUD.hideHUDForView(self.view, animated: true)
    }
    
    func errorReceived(e: NSError) {
        
    }
    
    func offerListReceived(offerList: NSArray) {
        self.offerList = offerList
        self.tableView.reloadData()
        refreshControl!.endRefreshing()
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.offerList.count
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! OfferLeadTableViewCell

        let event = offerList[indexPath.row]
        cell.updateView(event as! Event)
        
        return cell
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let viewController:OfferDetailViewController = segue.destinationViewController as! OfferDetailViewController
        viewController.delegate = self
        let index = tableView.indexPathForSelectedRow?.row
        let selectedOffer = offerList[index!] as! Offer
        viewController.offerDetailLink = selectedOffer.detailLink
    }
    
    func offerAccepted(link: String) {
        let storyBoard : UIStoryboard = UIStoryboard(name: "Main", bundle:nil)
        
        let nextViewController:LeadDetailViewController = storyBoard.instantiateViewControllerWithIdentifier("LeadDetailViewController") as! LeadDetailViewController
        nextViewController.leadDetailLink = link
        self.navigationController?.pushViewController(nextViewController, animated: true)

    }
}

