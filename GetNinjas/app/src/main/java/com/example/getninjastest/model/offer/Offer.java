package com.example.getninjastest.model.offer;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AsifMoinul on 7/31/2016.
 */
public class Offer {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("_embedded")
    @Expose
    private EmbeddedRequest embeddedRequest;

    @SerializedName("_links")
    @Expose
    private Links links;

    /**
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The embedded
     */
    public EmbeddedRequest getEmbeddedRequest() {
        return embeddedRequest;
    }

    /**
     * @param embeddedRequest The _embedded
     */
    public void setEmbeddedRequest(EmbeddedRequest embeddedRequest) {
        this.embeddedRequest = embeddedRequest;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
