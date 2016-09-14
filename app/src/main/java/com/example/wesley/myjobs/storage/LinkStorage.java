package com.example.wesley.myjobs.storage;

import com.example.wesley.myjobs.model.MasterLinks;

/**
 * Created by wesley on 9/9/16.
 */
public class LinkStorage {
    private static MasterLinks masterLinks;

    public static void setMasterLinks(MasterLinks masterLinks) {
        LinkStorage.masterLinks = masterLinks;
    }

    public static MasterLinks getMasterLinks() {
        return masterLinks;
    }
}
