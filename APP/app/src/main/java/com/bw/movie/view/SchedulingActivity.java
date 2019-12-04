package com.bw.movie.view;

import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;

import com.bw.movie.R;
import com.bw.movie.adapter.View_Adapter;
import com.bw.movie.bean.DateListBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SchedulingActivity extends BaseActivity<Fragment_Presenter> implements Contract.IView {
    private TabLayout ling_tab;
    private ViewPager view_pager;
    private int cinemaId;

    //数据添加
    //private String[] str = {"0","1","2","3","4","5","6","7"};

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected void initView() {
        view_pager = findViewById(R.id.view_pager);
        ling_tab = findViewById(R.id.ling_tab);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        cinemaId = intent.getIntExtra("cinemaId", 0);
        mPresenter.onDateList();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_scheduling;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        List<String> list = new ArrayList<>();
        DateListBean bean = (DateListBean) obj;
        List<String> result = bean.getResult();
        for (int i = 0; i < result.size(); i++) {
            list.add(result.get(i));
        }
        ling_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        ling_tab.setupWithViewPager(view_pager);
        View_Adapter view_adapter = new View_Adapter(getSupportFragmentManager(), list, cinemaId);
        view_pager.setAdapter(view_adapter);
    }

    @Override
    public void onError(Throwable e) {

    }
}
