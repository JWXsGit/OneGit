package com.bw.movie.view.fragment;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.fragment_adapter.CinemaFragment_Adapter;
import com.bw.movie.view.fragment.cinema_fragment.Cinema_Fragment;
import com.bw.movie.view.fragment.cinema_fragment.Nearby_Fragment;
import com.bw.movie.view.fragment.cinema_fragment.Recommend_Fragment;
import com.example.my_utils_library.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 21:32
 * @change
 * @chang time
 * @class describe
 */
public class Fragment_Cinema extends BaseFragment<Fragment_Presenter> {
    private TabLayout tab;
    private ViewPager view_pager;
    private List<String> tabs = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void initView(View view) {
        view_pager = view.findViewById(R.id.view_pager);
        tab = view.findViewById(R.id.tab);
    }

    @Override
    protected void initData() {
        tabs.add("推荐影院");
        tabs.add("附近影院");
        tabs.add("海淀区");
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        tab.setTabMode(TabLayout.MODE_FIXED);

        fragments.add(new Recommend_Fragment());
        fragments.add(new Nearby_Fragment());
        fragments.add(new Cinema_Fragment());
        view_pager.setOffscreenPageLimit(3);
        tab.setupWithViewPager(view_pager);
        CinemaFragment_Adapter adapter = new CinemaFragment_Adapter(getActivity().getSupportFragmentManager(), tabs, fragments);
        view_pager.setAdapter(adapter);
    }

    @Override
    public Context context() {
        return null;
    }
}
