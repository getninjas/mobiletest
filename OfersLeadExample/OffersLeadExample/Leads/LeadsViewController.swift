//
//  SecondViewController.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import UIKit
import MBProgressHUD

class LeadsViewController: UITableViewController, LeadsView {

    var presenter:LeadsPresenter!
    var leadList:NSArray = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.presenter = LeadsPresenterImpl(view: self)
        self.presenter.loadLeadsList()
        
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
        self.presenter.loadLeadsList()
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
    
    func leadsListReceived(leadsList: NSArray) {
        self.leadList = leadsList
        self.tableView.reloadData()
        refreshControl!.endRefreshing()
    }
    
    func errorReceived(e: NSError) {
        
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.leadList.count
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! OfferLeadTableViewCell
        
        let event = leadList[indexPath.row]
        cell.updateView(event as! Event)
        
        return cell
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let viewController:LeadDetailViewController = segue.destinationViewController as! LeadDetailViewController
        let index = tableView.indexPathForSelectedRow?.row
        let selectedLead = leadList[index!] as! Lead
        viewController.leadDetailLink = selectedLead.detailLink
    }
}

