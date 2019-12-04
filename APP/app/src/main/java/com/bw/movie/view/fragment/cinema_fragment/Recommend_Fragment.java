package com.bw.movie.view.fragment.cinema_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.eventbus_bean.ID_Bean;
import com.bw.movie.bean.fragment_bean.NearbyBean;
import com.bw.movie.bean.fragment_bean.RecommendBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.fragment_cinema.Fragment_NearAdapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment.cinema_fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/13 19:32
 * @change
 * @chang time
 * @class describe
 */
public class Recommend_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView recycler_recommend;
    private int userId;
    private String sessionId;


    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_recommend_by;
    }

    @Override
    protected void initView(View view) {
        recycler_recommend = view.findViewById(R.id.recycler_recommend);
    }

    @Override
    protected void initData() {
        mPresenter.onRecommend(userId, sessionId, 1, 10);
    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void ID_Data(ID_Bean id_bean) {
        sessionId = id_bean.getSessionId();
        userId = id_bean.getUserId();
    }


    @Override
    public void onSuccess(Object obj) {
        RecommendBean bean = (RecommendBean) obj;
        List<RecommendBean.ResultBean> result = bean.getResult();
        Fragment_NearAdapter nearAdapter = new Fragment_NearAdapter(result, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_recommend.setLayoutManager(linearLayoutManager);
        recycler_recommend.setAdapter(nearAdapter);
    }

    @Override
    public void onError(Throwable e) {

    }
}
