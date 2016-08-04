package com.example.getninjastest.webservice;



import com.example.getninjastest.model.details.LeadDetails;
import com.example.getninjastest.model.details.OfferDetails;
import com.example.getninjastest.model.lead.Leads;
import com.example.getninjastest.model.offer.Offers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by AsifMoinul on 7/30/2016.
 */
public interface ApiInterface {

    @GET("/offers")
    Call<Offers> getOffers();

    @GET("/leads")
    Call<Leads> getLeads();

    @GET
    Call<OfferDetails> getOfferDetails(@Url String url);

    @GET
    Call<LeadDetails> getLeadDetails(@Url String url);
}
