package com.test.getninjas.pedidos.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.test.getninjas.pedidos.presenter.OffersPresenter;
import com.test.getninjas.pedidos.view.OffersView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends Fragment implements OffersView, OffersAdapter.OfferButtonClickListener {
    public final static String TITLE = "DISPONÍVEIS";

    private String href;
    private OffersPresenter presenter;
    private OffersAdapter adapter;

    @Bind(R.id.rv_offers)
    RecyclerView rvOffers;

    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    public OffersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        ButterKnife.bind(this, view);

        presenter = new OffersPresenter();
        presenter.attachView(this);
        presenter.getOffers(href);

        return view;
    }

    public void setHref(String href) {
        this.href = href;
    }


    @Override
    public void onOffersReceived(final ResponseObject o) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(o != null)
                    setupList(o);
                else
                    Toast.makeText(OffersFragment.this.getContext(), "It was not possible to retrieve data.", Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }

    private void setupList(final ResponseObject o) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
        rvOffers.setLayoutManager(manager);
        adapter = new OffersAdapter(o.offers, this.getContext(), this);
        rvOffers.setAlpha(1f);
        rvOffers.setClickable(true);
        rvOffers.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(true);
                        presenter.getOffers(o.links.self.href);
                        rvOffers.setAlpha(0.3f);
                        rvOffers.setClickable(false);
                    }
                }
        );
    }

    @Override
    public void onOfferButtonClicked(Offer offer, View view) {
        Intent i = new Intent(this.getActivity(), OfferActivity.class);
        i.putExtra("href", offer.links.self.href);
        i.putExtra("type", "offer");
        startActivity(i);
    }
}
