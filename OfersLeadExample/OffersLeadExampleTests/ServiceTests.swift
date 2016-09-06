//
//  ServiceTests.swift
//  OffersLeadExample
//
//  Created by Wesley on 9/4/16.
//  Copyright © 2016 Wesley. All rights reserved.
//

import XCTest
@testable import OffersLeadExample

class ServiceTests: XCTestCase {
    
    override func setUp() {
        super.setUp()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    func testGetURLsFromEndPointNotNil() {
        let baseService:BaseService = BaseService()
        let expectation = expectationWithDescription("BaseService")
        baseService.getListUrls(
            { (dict: NSDictionary) -> (Void) in
                XCTAssertNotNil(dict)
                expectation.fulfill()
            }, errorCallback:
            {(e:NSError) -> (Void) in
                print("\(e)")
            })
        waitForExpectationsWithTimeout(2.0, handler: nil)
    }
    
    func testGetOffersNotNil() {
        let baseService:BaseService = BaseService()
        let expectation = expectationWithDescription("Offers")
        baseService.getCustomURL("http://testemobile.getninjas.com.br/offers",
            callback: { (dict:NSDictionary) -> (Void) in
                XCTAssertNotNil(dict)
                expectation.fulfill()
            },
            errorCallback: { (e:NSError) -> (Void) in
                print("\(e)")
            })
        waitForExpectationsWithTimeout(2.0, handler: nil)
    }
    
    func testGetLeadsNotNil() {
        let baseService:BaseService = BaseService()
        let expectation = expectationWithDescription("Offers")
        baseService.getCustomURL("http://testemobile.getninjas.com.br/leads",
            callback: { (dict:NSDictionary) -> (Void) in
                XCTAssertNotNil(dict)
                expectation.fulfill()
            },
            errorCallback: { (e:NSError) -> (Void) in
                print("\(e)")
            })
        waitForExpectationsWithTimeout(2.0, handler: nil)
    }
    
    func testGetOffers404Error() {
        let baseService:BaseService = BaseService()
        let expectation = expectationWithDescription("Offers")
        baseService.getCustomURL("http://testemobile.getninjas.com.br/offers/1",
            callback: { (dict:NSDictionary) -> (Void) in
            },
            errorCallback: { (e:NSError) -> (Void) in
                XCTAssertNotNil(e)
                expectation.fulfill()
            })
        waitForExpectationsWithTimeout(2.0, handler: nil)
    }
    
    func testGetLeadsDetailNotNil() {
        let baseService:BaseService = BaseService()
        let expectation = expectationWithDescription("Offers")
        baseService.getCustomURL("https://testemobile.getninjas.com.br/lead-1",
            callback: { (dict:NSDictionary) -> (Void) in
                XCTAssertNotNil(dict)
                expectation.fulfill()
            },
                errorCallback: { (e:NSError) -> (Void) in
                print("\(e)")
            })
        waitForExpectationsWithTimeout(2.0, handler: nil)
    }
    
    func testGetOfferDetailNotNil() {
        let baseService:BaseService = BaseService()
        let expectation = expectationWithDescription("Offers")
        baseService.getCustomURL("https://testemobile.getninjas.com.br/offer-1",
            callback: { (dict:NSDictionary) -> (Void) in
                XCTAssertNotNil(dict)
                expectation.fulfill()
            },
            errorCallback: { (e:NSError) -> (Void) in
                print("\(e)")
            })
        waitForExpectationsWithTimeout(2.0, handler: nil)
    }
}
