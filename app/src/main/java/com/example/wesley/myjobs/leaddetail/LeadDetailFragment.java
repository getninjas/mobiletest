package com.example.wesley.myjobs.leaddetail;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.eventdetail.EventDetailActivity;
import com.example.wesley.myjobs.eventdetail.EventDetailFragment;
import com.example.wesley.myjobs.model.Event;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by wesley on 9/9/16.
 */
public class LeadDetailFragment extends EventDetailFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_lead_frag, container, false);
        ButterKnife.bind(this, rootView);

        setPresenter(new LeadDetailPresenter(this));
        Intent intent = getActivity().getIntent();
        detailUrl = intent.getStringExtra(EventDetailActivity.DETAIL_URL_EXTRA);
        loadDetail();

        return rootView;
    }


    @Override
    protected void configureView(Event event) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(event.getTitle());
        this.event = event;

        txtUserName.setText(event.getUser().getName());
        StringBuilder addresStr = new StringBuilder();
        addresStr.append(event.getAddress().getNeighborhood()).append(" - ");
        addresStr.append(event.getAddress().getCity()).append(" - ");
        addresStr.append(event.getAddress().getUf());
        txtUserLocation.setText(addresStr.toString());

        configureMap();
        configureDistanceText();
        configureListAdapter();
    }

    @Override
    protected void configureListAdapter() {
        if(event != null) {
            List<Object> totalList = new LinkedList<>();
            totalList.addAll(event.getInfo());
            totalList.add(event.getUser());
            totalList.add(event.getUser().getPhones());
            listViewInfo.setAdapter(new LeadDetailListAdapter(totalList));
        }
    }

    @Override
    public void setFabVisibility(FloatingActionButton fab) {
        fab.setVisibility(View.INVISIBLE);
    }
}
