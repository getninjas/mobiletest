package com.example.getninjastest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.getninjastest.fragment.LeadsFragment;
import com.example.getninjastest.fragment.OffersFragment;

/**
 * Created by AsifMoinul on 7/26/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                OffersFragment offersFragment = new OffersFragment();
                return offersFragment;
            case 1:
                LeadsFragment leadsFragment = new LeadsFragment();
                return leadsFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
