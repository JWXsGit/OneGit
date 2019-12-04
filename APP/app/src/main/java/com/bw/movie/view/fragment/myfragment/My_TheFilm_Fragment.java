package com.bw.movie.view.fragment.myfragment;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Follow_MovieBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.my_adapter.My_TheFilm_Adapter;
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
public class My_TheFilm_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView thefilm_recycler;
    private RelativeLayout lin_visi;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.my_thefilm_fragment;
    }

    @Override
    protected void initView(View view) {
        thefilm_recycler = view.findViewById(R.id.thefilm_recycler);
        lin_visi = view.findViewById(R.id.lin_visi);
    }

    @Override
    protected void initData() {
        mPresenter.onFollow_Movie(1, 5);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        Follow_MovieBean bean = (Follow_MovieBean) obj;
        final List<Follow_MovieBean.ResultBean> result = bean.getResult();
        if (result != null) {
            thefilm_recycler.setVisibility(View.VISIBLE);
            lin_visi.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
            thefilm_recycler.setLayoutManager(linearLayoutManager);
            final My_TheFilm_Adapter guan_movie_adapter = new My_TheFilm_Adapter(result, getActivity());
            thefilm_recycler.setAdapter(guan_movie_adapter);

            guan_movie_adapter.setOnPosition(new My_TheFilm_Adapter.onPosition() {
                @Override
                public void onPosition(int position) {
                    mPresenter.onCancelFollowMovie(result.get(position).getMovieId());
                    guan_movie_adapter.removeData(position);
                }
            });
        } else {
            thefilm_recycler.setVisibility(View.GONE);
            lin_visi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
