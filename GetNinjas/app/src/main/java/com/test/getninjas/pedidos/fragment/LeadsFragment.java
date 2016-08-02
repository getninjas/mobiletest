package com.test.getninjas.pedidos.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.getninjas.pedidos.R;
import com.test.getninjas.pedidos.activity.OfferActivity;
import com.test.getninjas.pedidos.adapter.OffersAdapter;
import com.test.getninjas.pedidos.model.data.ResponseObject;
import com.test.getninjas.pedidos.model.data.offer.Offer;
import com.test.getninjas.pedidos.presenter.LeadsPresenter;
import com.test.getninjas.pedidos.presenter.OffersPresenter;
import com.test.getninjas.pedidos.view.LeadsView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LeadsFragment extends Fragment implements LeadsView, OffersAdapter.OfferButtonClickListener {

    public final static String TITLE = "ACEITOS";

    private String href;
    private LeadsPresenter presenter;
    private OffersAdapter adapter;

    @Bind(R.id.rv_leads)
    RecyclerView rvLeads;

    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    public LeadsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leads, container, false);
        ButterKnife.bind(this, view);

        presenter = new LeadsPresenter();
        presenter.attachView(this);
        presenter.getLeads(href);

        return view;
    }

    public void setHref(String href) {
        this.href = href;
    }


    @Override
    public void onLeadsReceived(final ResponseObject object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(object != null)
                    setupList(object);
                else
                    Toast.makeText(LeadsFragment.this.getContext(), "It was not possible to retrieve data.", Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }

    private void setupList(final ResponseObject o) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
        rvLeads.setLayoutManager(manager);
        adapter = new OffersAdapter(o.leads, this.getContext(), this);
        rvLeads.setAdapter(adapter);
        rvLeads.setAlpha(1f);
        rvLeads.setClickable(true);
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(true);
                        presenter.getLeads(o.links.self.href);
                        rvLeads.setAlpha(0.3f);
                        rvLeads.setClickable(false);
                    }
                }
        );
    }

    @Override
    public void onOfferButtonClicked(Offer offer, View view) {
        Intent i = new Intent(this.getActivity(), OfferActivity.class);
        i.putExtra("href", offer.links.self.href);
        i.putExtra("type", "lead");
        startActivity(i);
    }
}
