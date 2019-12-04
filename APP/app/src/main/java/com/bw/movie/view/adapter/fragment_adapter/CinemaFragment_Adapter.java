package com.bw.movie.view.adapter.fragment_adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.fragment_adapter
 * @class describe
 * @anthor 24673
 * @time 2019/11/13 19:43
 * @change
 * @chang time
 * @class describe
 */
public class CinemaFragment_Adapter extends FragmentPagerAdapter {
    private List<String> tabs;
    private List<Fragment> fragments;

    public CinemaFragment_Adapter(FragmentManager supportFragmentManager, List<String> tabs, List<Fragment> fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
