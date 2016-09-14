package com.example.wesley.myjobs;

import com.example.wesley.myjobs.api.EventService;
import com.example.wesley.myjobs.model.Lead;
import com.example.wesley.myjobs.model.MasterLinks;
import com.example.wesley.myjobs.model.Offer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

/**
 * Created by wesley on 9/7/16.
 */
public class ServiceTest {

    private EventService eventService;

    @Before
    public void setUp() {
        eventService = new EventService();

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.newThread();
            }
        });
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void getURLList() throws Exception {
        TestSubscriber<JsonElement> testSubscriber = new TestSubscriber<>();
        eventService.getListURL(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        JsonElement urlList = testSubscriber.getOnNextEvents().get(0);
        MasterLinks masterLinks = new MasterLinks(urlList.getAsJsonObject());
        String offersListLink = masterLinks.getOffers();
        String leadsListLink = masterLinks.getLeads();
        Assert.assertNotNull(urlList);
        Assert.assertNotNull(offersListLink);
        Assert.assertNotNull(leadsListLink);
    }

    @Test
    public void testLeadsListRequest() throws Exception {
        TestSubscriber<JsonElement> testSubscriber = new TestSubscriber<>();
        eventService.getBaseURL("http://testemobile.getninjas.com.br/leads", testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        JsonElement leadsList = testSubscriber.getOnNextEvents().get(0);
        JsonObject resultObj = leadsList.getAsJsonObject();
        JsonArray leadsArray = resultObj.get("leads").getAsJsonArray();
        List<Lead> leadList = new ArrayList<>();
        for(int i = 0; i < leadsArray.size(); i++) {
            JsonObject json = leadsArray.get(i).getAsJsonObject();
            Lead lead = new Lead(json, false);
            leadList.add(lead);
        }
        Assert.assertNotNull(leadsList);
        Assert.assertNotNull(leadList);
    }

    @Test
    public void testOfferListRequest() throws Exception {
        TestSubscriber<JsonElement> testSubscriber = new TestSubscriber<>();
        eventService.getBaseURL("http://testemobile.getninjas.com.br/offers", testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        JsonElement leadsList = testSubscriber.getOnNextEvents().get(0);
        JsonObject resultObj = leadsList.getAsJsonObject();
        JsonArray leadsArray = resultObj.get("offers").getAsJsonArray();
        List<Offer> offerList = new ArrayList<>();
        for(int i = 0; i < leadsArray.size(); i++) {
            JsonObject json = leadsArray.get(i).getAsJsonObject();
            Offer offer = new Offer(json, false);
            offerList.add(offer);
        }
        Assert.assertNotNull(leadsList);
        Assert.assertNotNull(offerList);
    }

    @Test
    public void testOfferDetailRequest() throws Exception {
        TestSubscriber<JsonElement> testSubscriber = new TestSubscriber<>();
        eventService.getBaseURL("https://testemobile.getninjas.com.br/offer-1", testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        JsonElement leadsList = testSubscriber.getOnNextEvents().get(0);
        JsonObject resultObj = leadsList.getAsJsonObject();
        Offer offer = new Offer(resultObj, true);
        Assert.assertNotNull(leadsList);
        Assert.assertNotNull(offer);
    }

    @Test
    public void testLeadDetailRequest() throws Exception {
        TestSubscriber<JsonElement> testSubscriber = new TestSubscriber<>();
        eventService.getBaseURL("https://testemobile.getninjas.com.br/lead-1", testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        JsonElement leadsList = testSubscriber.getOnNextEvents().get(0);
        JsonObject resultObj = leadsList.getAsJsonObject();
        Lead lead = new Lead(resultObj, true);
        Assert.assertNotNull(leadsList);
        Assert.assertNotNull(lead);
    }
}
