package com.example.wesley.myjobs.eventlist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.eventdetail.EventDetailActivity;
import com.example.wesley.myjobs.model.Event;

import java.util.List;

/**
 * Created by wesley on 9/9/16.
 */
public abstract class EventListAdapter extends RecyclerView.Adapter<EventListViewHolder> {
    private List<Event> list;

    public EventListAdapter(List<Event> leadList) {
        this.list = leadList;
    }

    protected View getView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tab_frag_item, parent, false);
    }

    @Override
    public void onBindViewHolder(final EventListViewHolder holder, int position) {
        final Event event = list.get(position);
        holder.refreshViewHolder(event);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
