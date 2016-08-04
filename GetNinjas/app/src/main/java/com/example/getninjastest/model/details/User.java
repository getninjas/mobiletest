package com.example.getninjastest.model.details;

/**
 * Created by AsifMoinul on 8/1/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("_embedded")
    @Expose
    private EmbeddedPhone embedded;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The embedded
     */
    public EmbeddedPhone getEmbedded() {
        return embedded;
    }

    /**
     *
     * @param embedded
     * The _embedded
     */
    public void setEmbedded(EmbeddedPhone embedded) {
        this.embedded = embedded;
    }

}
