package com.example.getninjastest.model.details;

/**
 * Created by AsifMoinul on 8/1/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OfferDetails {

    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("lead_price")
    @Expose
    private Integer leadPrice;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    @SerializedName("_links")
    @Expose
    private Links links;

    /**
     *
     * @return
     * The distance
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     * The distance
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     *
     * @return
     * The leadPrice
     */
    public Integer getLeadPrice() {
        return leadPrice;
    }

    /**
     *
     * @param leadPrice
     * The lead_price
     */
    public void setLeadPrice(Integer leadPrice) {
        this.leadPrice = leadPrice;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The embedded
     */
    public Embedded getEmbedded() {
        return embedded;
    }

    /**
     *
     * @param embedded
     * The _embedded
     */
    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    /**
     *
     * @return
     * The links
     */
    public Links getLinks() {
        return links;
    }

    /**
     *
     * @param links
     * The _links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

}
