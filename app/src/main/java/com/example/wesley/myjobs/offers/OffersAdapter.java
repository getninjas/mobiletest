package com.example.wesley.myjobs.offers;

import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.myjobs.eventlist.EventListAdapter;
import com.example.wesley.myjobs.eventlist.EventListViewHolder;
import com.example.wesley.myjobs.model.Event;

import java.util.List;

/**
 * Created by wesley on 9/9/16.
 */
public class OffersAdapter extends EventListAdapter {

    public OffersAdapter(List<Event> leadList) {
        super(leadList);
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent);
        OffersViewHolder offersViewHolder = new OffersViewHolder(view);
        return offersViewHolder;
    }
}
