package com.example.wesley.myjobs.leads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.myjobs.eventlist.EventListAdapter;
import com.example.wesley.myjobs.eventlist.EventListViewHolder;
import com.example.wesley.myjobs.model.Event;

import java.util.List;

/**
 * Created by wesley on 9/9/16.
 */
public class LeadsAdapter extends EventListAdapter {

    public LeadsAdapter(List<Event> leadList) {
        super(leadList);
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent);
        LeadsViewHolder leadsViewHolder = new LeadsViewHolder(view);
        return leadsViewHolder;
    }


}
