package com.example.jun.bisaixiangmu.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mViews;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> mViews) {
        super(fm);
        this.mViews=mViews;
    }


    @Override
    public Fragment getItem(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }
}
