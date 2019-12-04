package com.bw.movie.view.fragment.myfragment;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Movie_ListBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.my_adapter.Movie_review_Adapter;
import com.bw.movie.view.adapter.my_adapter.My_Ticket_Adapter;
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
public class Movie_review_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView movie_recycler;
    private RelativeLayout lin_visi;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.movie_revie_fragment;
    }

    @Override
    protected void initView(View view) {
        movie_recycler = view.findViewById(R.id.movie_recycler);
        lin_visi = view.findViewById(R.id.lin_visi);
    }

    @Override
    protected void initData() {
        mPresenter.onComment_List(1, 5);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        Movie_ListBean bean = (Movie_ListBean) obj;
        List<Movie_ListBean.ResultBean> result = bean.getResult();
        if (result != null) {
            Movie_review_Adapter review_adapter = new Movie_review_Adapter(result, getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            movie_recycler.setLayoutManager(linearLayoutManager);
            movie_recycler.setAdapter(review_adapter);
        } else {
            movie_recycler.setVisibility(View.GONE);
            lin_visi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
