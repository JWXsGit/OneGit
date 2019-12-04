package com.bw.movie.view;

import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;

import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.DateListBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.adapter.DateList_Adapter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DateListActivity extends BaseActivity<MyPresenter> implements Contract.IView {

    private List<String> list = new ArrayList<>();
    private TabLayout date_tab;
    private ViewPager date_view;
    private List<String> tabs = new ArrayList<>();

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        date_view = findViewById(R.id.date_view);
        date_tab = findViewById(R.id.date_tab);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int cinemaId = intent.getIntExtra(Constant.CINEMAID, 0);
        int momvieId = intent.getIntExtra(Constant.MOVIEID, 0);

        for (int i = 0; i < list.size(); i++) {
            String data = list.get(i);
            tabs.add(data);
        }
        date_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        date_tab.setupWithViewPager(date_view);

        DateList_Adapter dateList_adapter = new DateList_Adapter(getSupportFragmentManager(), tabs);
        date_view.setAdapter(dateList_adapter);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_date_list;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof DateListBean) {
            DateListBean listBean = (DateListBean) obj;
            list = listBean.getResult();
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
