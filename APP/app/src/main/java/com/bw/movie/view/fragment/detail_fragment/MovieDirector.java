package com.bw.movie.view.fragment.detail_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.detail_adapter.Actor_Adapter;
import com.bw.movie.view.adapter.detail_adapter.Director_Adapter;
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
public class MovieDirector extends BaseFragment<Fragment_Presenter> {
    private TextView item_text;
    private RecyclerView actor_rcy, director_rcy;

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
        return R.layout.fragment_director;
    }

    @Override
    protected void initView(View view) {
        director_rcy = view.findViewById(R.id.director_rcy);
        actor_rcy = view.findViewById(R.id.actor_rcy);
        item_text = view.findViewById(R.id.item_text);
    }

    @Override
    protected void initData() {

    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDetailBean(DetailBean detailBean) {
        DetailBean.ResultBean result = detailBean.getResult();
        //设置介绍简介
        String summary = result.getSummary();
        item_text.setText(summary + "");
        //导演介绍
        List<DetailBean.ResultBean.MovieDirectorBean> list = result.getMovieDirector();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Director_Adapter director_adapter = new Director_Adapter(list, getContext());
        director_rcy.setAdapter(director_adapter);
        director_rcy.setLayoutManager(linearLayoutManager);
        //演员介绍
        List<DetailBean.ResultBean.MovieActorBean> movieActor = result.getMovieActor();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        Actor_Adapter actor_adapter = new Actor_Adapter(movieActor, getContext());
        actor_rcy.setAdapter(actor_adapter);
        actor_rcy.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
