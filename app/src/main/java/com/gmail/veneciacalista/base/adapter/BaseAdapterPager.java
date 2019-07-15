package com.gmail.veneciacalista.base.adapter;

import android.util.SparseArray;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BaseAdapterPager extends FragmentPagerAdapter {

    private final SparseArray<Fragment> fragments;

    public BaseAdapterPager(FragmentManager fm, SparseArray<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
