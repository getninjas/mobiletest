package com.example.getninjastest.model.details;

/**
 * Created by AsifMoinul on 8/1/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Info {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("value")
    @Expose
    //// FIXME: 8/1/2016
    private Object value; //ugly fix

    /**
     *
     * @return
     * The label
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     * The label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @return
     * The value
     */
    public Object getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    public String getFormattedValue(){
        String s = "";
        boolean isFirst = true;
        if(value instanceof ArrayList){
            StringBuilder sb = new StringBuilder();

            for(String v : (ArrayList<String>)value){
                if(!isFirst){
                    sb.append(", ");
                }
                sb.append(v);
                isFirst = false;
            }
            s = sb.toString();
        }else {
            s = value.toString().replaceAll("N/A", "");
        }
        return s;
    }

}
