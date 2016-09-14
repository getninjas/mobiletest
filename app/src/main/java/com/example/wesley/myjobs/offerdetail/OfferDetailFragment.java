package com.example.wesley.myjobs.offerdetail;

import android.app.Activity;
import android.content.Intent;
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
import com.example.wesley.myjobs.eventlist.EventListActivity;
import com.example.wesley.myjobs.model.Event;
import com.example.wesley.myjobs.model.Offer;

import butterknife.ButterKnife;

/**
 * Created by wesley on 9/9/16.
 */
public class OfferDetailFragment extends EventDetailFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_offer_frag, container, false);
        ButterKnife.bind(this, rootView);

        setPresenter(new OfferDetailPresenter(this));
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
    public void setFabVisibility(FloatingActionButton fab) {
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(event != null) {

                    Offer offer = (Offer) event;
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(EventListActivity.DETAIL_REQUEST_RESPONSE, true);
                    returnIntent.putExtra(EventDetailActivity.DETAIL_URL_EXTRA, offer.getAcceptLink());
                    getActivity().setResult(Activity.RESULT_OK, returnIntent);
                    getActivity().finish();
                }
            }
        });
    }

}