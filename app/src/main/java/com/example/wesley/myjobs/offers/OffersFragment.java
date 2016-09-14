package com.example.wesley.myjobs.offers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.myjobs.BaseFragment;
import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.eventlist.EventListAdapter;
import com.example.wesley.myjobs.eventlist.EventListContract;
import com.example.wesley.myjobs.eventlist.EventListFragment;
import com.example.wesley.myjobs.leads.LeadsAdapter;
import com.example.wesley.myjobs.leads.LeadsPresenter;
import com.example.wesley.myjobs.model.Event;
import com.example.wesley.myjobs.model.Offer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by wesley on 9/9/16.
 */
public class OffersFragment extends EventListFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getView(inflater, container);

        setPresenter(new OffersPresenter(this));
        super.presenter.loadList(true);
        configureLayoutManager();
        configureRefresh();

        return rootView;
    }

    @Override
    public void listReceived(List<Event> list) {
        EventListAdapter adapter = new OffersAdapter(list);
        configureListAdapter(adapter);
    }



    @Override
    public void setFabVisibility(FloatingActionButton fab) {

    }


}