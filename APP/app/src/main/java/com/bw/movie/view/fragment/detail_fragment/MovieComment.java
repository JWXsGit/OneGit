package com.bw.movie.view.fragment.detail_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.eventbus_bean.Movie_ID;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.detail_adapter.MovieComment_Adapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

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
public class MovieComment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView movie_recycler;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_actor;
    }

    @Override
    protected void initView(View view) {
        movie_recycler = view.findViewById(R.id.movie_recycler);
    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        int movieid = intent.getIntExtra(Constant.MOVIEID, 0);
        mPresenter.onMovieId(movieid, 1, 10);
    }

    @Override
    public Context context() {
        return null;
    }


    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof MovieBean) {
            MovieBean bean = (MovieBean) obj;
            List<MovieBean.ResultBean> result = bean.getResult();

            MovieComment_Adapter movieComment_adapter = new MovieComment_Adapter(result, getActivity());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            movie_recycler.setLayoutManager(linearLayoutManager);
            movie_recycler.setAdapter(movieComment_adapter);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
