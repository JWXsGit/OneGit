package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.fragment_bean.NearbyBean;
import com.bw.movie.bean.fragment_bean.RecommendBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.DateListActivity;
import com.bw.movie.view.adapter.fragment_cinema.Fragment_NearAdapter;
import com.bw.movie.view.adapter.fragment_cinema.Fragment_RecommendAdapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/20 14:49
 * @change
 * @chang time
 * @class describe
 */
public class ShowFragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView show_recycler;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_show;
    }

    @Override
    protected void initView(View view) {
        show_recycler = view.findViewById(R.id.show_recycler);
    }

    @Override
    protected void initData() {
        mPresenter.onRecommend(0, null, 1, 5);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        RecommendBean bean = (RecommendBean) obj;
        List<RecommendBean.ResultBean> result = bean.getResult();
        Fragment_NearAdapter nearAdapter = new Fragment_NearAdapter(result, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        show_recycler.setLayoutManager(linearLayoutManager);
        show_recycler.setAdapter(nearAdapter);

        nearAdapter.setOnItemId(new Fragment_NearAdapter.onItemId() {
            @Override
            public void onItemID(int id) {
                Intent intent = new Intent(getContext(), DateListActivity.class);
                intent.putExtra(Constant.CINEMAID, id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onError(Throwable e) {

    }
}
