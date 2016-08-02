package com.test.getninjas.pedidos;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.getninjas.pedidos.fragment.LeadsFragment;
import com.test.getninjas.pedidos.fragment.OffersFragment;
import com.test.getninjas.pedidos.model.data.links.Links;
import com.test.getninjas.pedidos.presenter.MainPresenter;
import com.test.getninjas.pedidos.utils.ViewPagerAdapter;
import com.test.getninjas.pedidos.view.MainView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{

    @Bind(R.id.vp_pedidos)
    ViewPager vpPedidos;

    @Bind(R.id.tab_pedidos)
    TabLayout tabPedidos;

    private ViewPagerAdapter viewPagerAdapter;
    private MainPresenter presenter;
    private OffersFragment offersFragment;
    private LeadsFragment leadsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter();
        presenter.attachView(this);
        //Get links from entrypoint.
        presenter.getLinks();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onLinksReceived(final Links links) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setupViewPager(links);
            }
        });
    }

    private void setupViewPager(Links links) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpPedidos.setAdapter(viewPagerAdapter);
        tabPedidos.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabPedidos.setTabMode(TabLayout.MODE_FIXED);
        tabPedidos.setupWithViewPager(vpPedidos);

        offersFragment = new OffersFragment();
        offersFragment.setHref(links.offers.href);
        leadsFragment = new LeadsFragment();
        leadsFragment.setHref(links.leads.href);

        viewPagerAdapter.addFragment(offersFragment, OffersFragment.TITLE);
        viewPagerAdapter.addFragment(leadsFragment, LeadsFragment.TITLE);
        viewPagerAdapter.notifyDataSetChanged();
    }
}
