package com.bw.movie.view.fragment.detail_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.detail_adapter.Poster_Adapter;
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
public class PosterList extends BaseFragment<Fragment_Presenter> {
    private RecyclerView poster_recycler;

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
        return R.layout.fragment_posterlist;
    }

    @Override
    protected void initView(View view) {
        poster_recycler = view.findViewById(R.id.poster_recycler);
    }

    @Override
    protected void initData() {

    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void poster(DetailBean bean) {
        List<String> posterList = bean.getResult().getPosterList();

        Poster_Adapter adapter = new Poster_Adapter(posterList, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        poster_recycler.setLayoutManager(gridLayoutManager);
        poster_recycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
