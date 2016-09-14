package com.example.wesley.myjobs.eventlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.wesley.myjobs.BaseActivity;
import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.eventdetail.EventDetailActivity;
import com.example.wesley.myjobs.leads.LeadsFragment;
import com.example.wesley.myjobs.offers.OffersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/9/16.
 */
public class EventListActivity extends BaseActivity {

    public static final int DETAIL_REQUEST = 213;
    public static final String DETAIL_REQUEST_RESPONSE = "confirmed";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPagerTabs) ViewPager viewPagerTabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_act);
        ButterKnife.bind(this);

        configureActionBar();
    }

    private Fragment[] fragmentList = new Fragment[] {
            new OffersFragment(),
            new LeadsFragment()
    };

    private void configureActionBar() {
        setSupportActionBar(toolbar);
        viewPagerTabs.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            private String tabTitles[] = new String[] { getString(R.string.tab_offers_title), getString(R.string.tab_leads_title)};

            @Override
            public Fragment getItem(int position) {
                return fragmentList[position];
            }

            @Override
            public int getCount() {
                return fragmentList.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPagerTabs);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == DETAIL_REQUEST && resultCode == RESULT_OK) {
            boolean confirmed = data.getBooleanExtra(DETAIL_REQUEST_RESPONSE, false);

            if(confirmed) {
                String url = data.getStringExtra(EventDetailActivity.DETAIL_URL_EXTRA);
                Intent intent = new Intent(EventListActivity.this, EventDetailActivity.class);
                intent.putExtra(EventDetailActivity.DETAIL_URL_EXTRA, url);
                intent.putExtra(EventDetailActivity.EVENT_TYPE_EXTRA, "lead");
                startActivity(intent);
            }
        }
    }
}
