package com.example.getninjastest.model.lead;

/**
 * Created by AsifMoinul on 7/31/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Request {

    @SerializedName("title")
    @Expose
    private String title;

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

}