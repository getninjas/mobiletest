package com.example.wesley.myjobs.leads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.myjobs.eventlist.EventListAdapter;
import com.example.wesley.myjobs.eventlist.EventListFragment;
import com.example.wesley.myjobs.model.Event;

import java.util.List;

/**
 * Created by wesley on 9/9/16.
 */
public class LeadsFragment extends EventListFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getView(inflater, container);

        setPresenter(new LeadsPresenter(this));
        super.presenter.loadList(true);
        configureLayoutManager();
        configureRefresh();
        return rootView;
    }

    @Override
    public void listReceived(List<Event> list) {
        EventListAdapter adapter = new LeadsAdapter(list);
        configureListAdapter(adapter);
    }

    @Override
    public void setFabVisibility(FloatingActionButton fab) {

    }
}