package com.example.getninjastest.model.lead;

/**
 * Created by AsifMoinul on 7/31/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lead {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    @SerializedName("_links")
    @Expose
    private Links links;

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
