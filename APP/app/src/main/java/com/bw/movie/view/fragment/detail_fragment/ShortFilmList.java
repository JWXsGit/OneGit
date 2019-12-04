package com.bw.movie.view.fragment.detail_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.detail_adapter.TrailNotice_Adapter;
import com.example.my_utils_library.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment.detail_fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/18 16:21
 * @change
 * @chang time
 * @class describe
 */
public class ShortFilmList extends BaseFragment<Fragment_Presenter> {
    private RecyclerView short_trail;
    private TrailNotice_Adapter trailNotice_adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected Fragment_Presenter providePresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_shortfilmlist;
    }

    @Override
    protected void initView(View view) {
        short_trail = view.findViewById(R.id.short_trail);
    }

    @Override
    protected void initData() {

    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShort(DetailBean detailBean) {
        List<DetailBean.ResultBean.ShortFilmListBean> shortFilmList = detailBean.getResult().getShortFilmList();

        trailNotice_adapter = new TrailNotice_Adapter(shortFilmList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        short_trail.setLayoutManager(linearLayoutManager);
        short_trail.setAdapter(trailNotice_adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        trailNotice_adapter.VideoRemove();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);

    }
}
