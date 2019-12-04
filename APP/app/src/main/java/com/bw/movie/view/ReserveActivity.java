package com.bw.movie.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.Find_UserBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.view.adapter.my_adapter.My_Reserve_Adapter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ReserveActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private RecyclerView recycler_view;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        recycler_view = findViewById(R.id.recycler_view);
    }

    @Override
    protected void initData() {
        mPresenter.onFine_User();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_reserve;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        Find_UserBean bean = (Find_UserBean) obj;
        List<Find_UserBean.ResultBean> result = bean.getResult();
        My_Reserve_Adapter reserve_adapter = new My_Reserve_Adapter(result, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.setAdapter(reserve_adapter);
    }

    @Override
    public void onError(Throwable e) {

    }
}
