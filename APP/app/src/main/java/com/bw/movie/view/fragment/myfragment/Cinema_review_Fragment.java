package com.bw.movie.view.fragment.myfragment;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Cinema_ListBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.my_adapter.Cinema_review_Adapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment.myfragment
 * @class describe
 * @anthor 24673
 * @time 2019/12/3 19:55
 * @change
 * @chang time
 * @class describe
 */
public class Cinema_review_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView cinema_recycler;
    private RelativeLayout lin_visi;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.cinema_revie_fragment;
    }

    @Override
    protected void initView(View view) {
        cinema_recycler = view.findViewById(R.id.cinema_recycler);
        lin_visi = view.findViewById(R.id.lin_visi);
    }

    @Override
    protected void initData() {
        mPresenter.onCinema_List("0", "0", 1, 10);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        Cinema_ListBean bean = (Cinema_ListBean) obj;
        List<Cinema_ListBean.ResultBean> result = bean.getResult();
        if (result != null) {
            Cinema_review_Adapter review_adapter = new Cinema_review_Adapter(result, getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            cinema_recycler.setLayoutManager(linearLayoutManager);
            cinema_recycler.setAdapter(review_adapter);
        } else {
            cinema_recycler.setVisibility(View.GONE);
            lin_visi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
