package com.example.getninjastest.model.details;

/**
 * Created by AsifMoinul on 8/1/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("accept")
    @Expose
    private Accept accept;
    @SerializedName("reject")
    @Expose
    private Reject reject;

    /**
     *
     * @return
     * The accept
     */
    public Accept getAccept() {
        return accept;
    }

    /**
     *
     * @param accept
     * The accept
     */
    public void setAccept(Accept accept) {
        this.accept = accept;
    }

    /**
     *
     * @return
     * The reject
     */
    public Reject getReject() {
        return reject;
    }

    /**
     *
     * @param reject
     * The reject
     */
    public void setReject(Reject reject) {
        this.reject = reject;
    }

}
