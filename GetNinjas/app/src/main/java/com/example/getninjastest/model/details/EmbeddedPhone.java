package com.example.getninjastest.model.details;

/**
 * Created by AsifMoinul on 8/1/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EmbeddedPhone {

    @SerializedName("phones")
    @Expose
    private List<Phone> phones = new ArrayList<Phone>();

    /**
     *
     * @return
     * The phones
     */
    public List<Phone> getPhones() {
        return phones;
    }

    /**
     *
     * @param phones
     * The phones
     */
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

}