package com.example.wesley.myjobs.eventlist;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.myjobs.BaseFragment;
import com.example.wesley.myjobs.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/9/16.
 */
public abstract class EventListFragment extends BaseFragment implements EventListContract.View {
    protected EventListContract.Presenter presenter;
    protected EventListAdapter eventListAdapter;
    protected RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.leadsListView) RecyclerView leadsListView;

    protected View getView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.tab_frag, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    protected void configureLayoutManager() {
        layoutManager = new LinearLayoutManager(getActivity());
        leadsListView.setLayoutManager(layoutManager);
    }

    protected void configureRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadList(false);
            }
        });
    }

    protected void configureListAdapter(EventListAdapter adapter) {
        eventListAdapter = adapter;
        eventListAdapter.setHasStableIds(true);
        leadsListView.setAdapter(eventListAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(EventListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        this.showDialog();
    }

    @Override
    public void hideLoading() {
        this.hideDialog();
    }

    @Override
    public void showError(Throwable e) {
        this.showErrorDialog(e);
    }
}