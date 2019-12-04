package com.bw.movie.view.fragment.datelist_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Schedule_ListBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.Date_List_Adapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment.datelist_fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/20 16:21
 * @change
 * @chang time
 * @class describe
 */
public class DateList_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView date_recycler;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_date;
    }

    @Override
    protected void initView(View view) {
        date_recycler = view.findViewById(R.id.date_recycler);
    }

    @Override
    protected void initData() {
        //获取Activity传来的数据
        Bundle bundle = getArguments();
        int cinemaId = bundle.getInt("cinemaId");
        mPresenter.onSchedule_List(cinemaId, 1, 5);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        Schedule_ListBean bean = (Schedule_ListBean) obj;
        List<Schedule_ListBean.ResultBean> result = bean.getResult();
        Date_List_Adapter list_adapter = new Date_List_Adapter(result, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        date_recycler.setLayoutManager(linearLayoutManager);
        date_recycler.setAdapter(list_adapter);
    }

    @Override
    public void onError(Throwable e) {

    }
}
