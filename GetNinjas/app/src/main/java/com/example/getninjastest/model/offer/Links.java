package com.example.getninjastest.model.offer;

/**
 * Created by AsifMoinul on 7/31/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;

    /**
     *
     * @return
     * The self
     */
    public Self getSelf() {
        return self;
    }

    /**
     *
     * @param self
     * The self
     */
    public void setSelf(Self self) {
        this.self = self;
    }

}