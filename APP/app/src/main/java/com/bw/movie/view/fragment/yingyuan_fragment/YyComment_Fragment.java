package com.bw.movie.view.fragment.yingyuan_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.eventbus_bean.Detali_EventBus;
import com.bw.movie.bean.fragment_bean.CommentBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.YyComment_Adapter;
import com.bw.movie.view.adapter.detail_adapter.MovieComment_Adapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment.yingyuan_fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/18 19:53
 * @change
 * @chang time
 * @class describe
 */
public class YyComment_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView recycler_yy_comment;

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
        return R.layout.fragment_yycomment;
    }

    @Override
    protected void initView(View view) {
        recycler_yy_comment = view.findViewById(R.id.recycler_yy_comment);
    }

    @Override
    protected void initData() {
    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDetail(Detali_EventBus bus) {
        int cinemaId = bus.getCinemaId();
        mPresenter.onComment(cinemaId, 1, 5);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccess(Object obj) {
        CommentBean bean = (CommentBean) obj;
        List<CommentBean.ResultBean> result = bean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_yy_comment.setLayoutManager(linearLayoutManager);
        YyComment_Adapter comment_adapter = new YyComment_Adapter(result, getContext());
        recycler_yy_comment.setAdapter(comment_adapter);
    }

    @Override
    public void onError(Throwable e) {

    }
}
