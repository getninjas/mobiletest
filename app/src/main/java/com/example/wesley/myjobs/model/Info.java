package com.example.wesley.myjobs.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by wesley on 9/7/16.
 */
public class Info {
    private String label;
    private String[] value;

    public Info(JsonObject json) {
        if(json.has("label")) {
            this.label = json.get("label").getAsString();
        }

        if(json.has("value")) {
            if(json.get("value").isJsonArray()) {
                JsonArray arrValue = json.get("value").getAsJsonArray();
                this.value = new String[arrValue.size()];
                for(int i = 0; i < arrValue.size(); i++) {
                    this.value[i] = arrValue.get(i).getAsString();
                }
            } else {
                this.value = new String[] { json.get("value").getAsString() };
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }
}
