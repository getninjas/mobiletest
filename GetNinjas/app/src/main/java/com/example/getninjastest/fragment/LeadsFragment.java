package com.example.getninjastest.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.getninjastest.R;
import com.example.getninjastest.adapter.LeadDataAdapter;
import com.example.getninjastest.model.lead.Lead;
import com.example.getninjastest.model.lead.Leads;
import com.example.getninjastest.webservice.ApiClient;
import com.example.getninjastest.webservice.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by AsifMoinul on 7/26/2016.
 */
public class LeadsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private LeadDataAdapter adapter;
    ArrayList<Lead> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_leads, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            loadLeads();
        }
    }

    @Override
    public void onRefresh() {
        loadLeads();
    }

    private void loadLeads() {
        Retrofit retrofit = ApiClient.getClient();
        ApiInterface request = retrofit.create(ApiInterface.class);
        if(swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
        //leads
        Call<Leads> call = request.getLeads();
        call.enqueue(new Callback<Leads>() {
            @Override
            public void onResponse(Call<Leads> call, Response<Leads> response) {
                if(swipeRefreshLayout != null){
                    swipeRefreshLayout.setRefreshing(false);
                }
                Leads jsonResponse = response.body();
                data = new ArrayList<>(jsonResponse.getLeads());
                adapter = new LeadDataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Leads> call, Throwable t) {
                if(swipeRefreshLayout != null){
                    swipeRefreshLayout.setRefreshing(false);
                }
                Log.d("Error", t.getMessage());
            }
        });

    }
}
