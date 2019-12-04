package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.fragment_bean.BannerBean;
import com.bw.movie.bean.fragment_bean.ComingBean;
import com.bw.movie.bean.fragment_bean.HotMovieBean;
import com.bw.movie.bean.fragment_bean.ReleaseBean;
import com.bw.movie.bean.eventbus_bean.ID_Bean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.SearchActivity;
import com.bw.movie.view.adapter.fragment_cinema.XRccycler_Adapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.androidx.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 21:32
 * @change
 * @chang time
 * @class describe
 */
public class Fragment_Home extends BaseFragment<Fragment_Presenter> implements View.OnClickListener, Contract.IView {
    private int userId;
    private String sessionId;

    private XRecyclerView xrecy_view;
    private List<HotMovieBean.ResultBean> movie_list;
    private List<ComingBean.ResultBean> coming_list;
    private List<BannerBean.ResultBean> banner_list;
    private List<ReleaseBean.ResultBean> release_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        xrecy_view = view.findViewById(R.id.xrecy_view);
    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        userId = intent.getIntExtra(Constant.USER_ID, 0);
        sessionId = intent.getStringExtra(Constant.SESSION_ID);
        SharedPreferences sp = getActivity().getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(Constant.USER_ID, userId);
        edit.putString(Constant.SESSION_ID, sessionId);
        edit.commit();
        //Banner轮播图
        mPresenter.onBannerImage();
        //正在上映的P层
        mPresenter.onReleaseResult(userId, sessionId, 1, 5);
        //即将上瘾的P层
        mPresenter.onComingResult(userId, sessionId, 1, 5);
        //热门电影的P层
        mPresenter.onHotMovieResult(userId, sessionId, 1, 4);
        xrecy_view.setLoadingMoreEnabled(true);
        xrecy_view.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecy_view.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecy_view.loadMoreComplete();
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_more_intent:
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
            case R.id.text_more_wait:
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
            case R.id.text_more_intent_popular:
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
            //定位
            case R.id.image_home_location:

                break;
            case R.id.image_home_search:

                break;
        }
    }

    @Override
    public void onSuccess(Object obj) {
        //轮播图请求成功后处理数据
        if (obj instanceof BannerBean) {
            final BannerBean bannerBean = (BannerBean) obj;
            banner_list = bannerBean.result;
        } else if (obj instanceof ReleaseBean) {//正在上映的请求后的数据处理
            ReleaseBean releaseBean = (ReleaseBean) obj;
            release_list = releaseBean.getResult();
        } else if (obj instanceof ComingBean) {//即将上映请求成功后的数据处理
            ComingBean comingBean = (ComingBean) obj;
            coming_list = comingBean.getResult();
        } else if (obj instanceof HotMovieBean) {
            //热门电影
            HotMovieBean hotMovieBean = (HotMovieBean) obj;
            movie_list = hotMovieBean.result;

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            xrecy_view.setLayoutManager(linearLayoutManager);
            XRccycler_Adapter adapter = new XRccycler_Adapter(getContext(), banner_list, release_list, coming_list, movie_list);
            xrecy_view.setAdapter(adapter);
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void ID_Data(ID_Bean id_bean) {
        sessionId = id_bean.getSessionId();
        userId = id_bean.getUserId();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}