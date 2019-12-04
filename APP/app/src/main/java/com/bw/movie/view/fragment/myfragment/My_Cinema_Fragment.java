package com.bw.movie.view.fragment.myfragment;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Follow_CinemaBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.my_adapter.My_TheFilm_Adapter;
import com.bw.movie.view.adapter.my_adapter.My_cinema_Adapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment
 * @class describe
 * @anthor 24673
 * @time 2019/12/2 19:39
 * @change
 * @chang time
 * @class describe
 */
public class My_Cinema_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView cinema_recycler;
    private RelativeLayout lin_visi;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.my_cinema_fragment;
    }

    @Override
    protected void initView(View view) {
        cinema_recycler = view.findViewById(R.id.cinema_recycler);
        lin_visi = view.findViewById(R.id.lin_visi);
    }

    @Override
    protected void initData() {
        mPresenter.onFollow_Cinema(1, 5);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        Follow_CinemaBean bean = (Follow_CinemaBean) obj;
        final List<Follow_CinemaBean.ResultBean> result = bean.getResult();
        if (result != null) {
            cinema_recycler.setVisibility(View.VISIBLE);
            lin_visi.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
            cinema_recycler.setLayoutManager(linearLayoutManager);
            final My_cinema_Adapter guan_cinema_adapter = new My_cinema_Adapter(result, getActivity());
            cinema_recycler.setAdapter(guan_cinema_adapter);

            guan_cinema_adapter.setOnPosition(new My_cinema_Adapter.onPosition() {
                @Override
                public void onPosition(int position) {
                    mPresenter.onCinema_CancelFollowMovie(result.get(position).getCinemaId());
                    guan_cinema_adapter.removeData(position);
                }
            });
        } else {
            cinema_recycler.setVisibility(View.GONE);
            lin_visi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
