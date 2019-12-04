package com.bw.movie.view.my_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.bean.ExChanegBean;
import com.bw.movie.bean.My_MovieBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.view.adapter.my_adapter.My_Movie_Adapter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class My_MovieActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private RecyclerView recycler_view;
    private RelativeLayout lin_visi;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        recycler_view = findViewById(R.id.recycler_view);
        lin_visi = findViewById(R.id.lin_visi);
    }

    @Override
    protected void initData() {
        mPresenter.onMy_Movie();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_my__movie;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof My_MovieBean) {
            My_MovieBean bean = (My_MovieBean) obj;
            List<My_MovieBean.ResultBean> result = bean.getResult();
            if (result != null) {
                My_Movie_Adapter my_movie_adapter = new My_Movie_Adapter(result, this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycler_view.setLayoutManager(linearLayoutManager);
                recycler_view.setAdapter(my_movie_adapter);

                my_movie_adapter.setOnRecordId(new My_Movie_Adapter.onRecordId() {
                    @Override
                    public void onRecordId(int id) {
                        mPresenter.onExChange(id);
                    }
                });
            } else {
                recycler_view.setVisibility(View.GONE);
                lin_visi.setVisibility(View.VISIBLE);
            }
        } else if (obj instanceof ExChanegBean) {
            ExChanegBean bean = (ExChanegBean) obj;
            ExChanegBean.ResultBean result = bean.getResult();
            initPopupWindow(result.getExchangeCode());
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    private void initPopupWindow(String exchangeCode) {
        View view = View.inflate(this, R.layout.popup_window_exchaneg, null);
        SimpleDraweeView simple_exchange = view.findViewById(R.id.simple_exchange);
        Button bt_cancel = view.findViewById(R.id.bt_cancel);

        final PopupWindow popupWindow = new PopupWindow(view, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT);
        simple_exchange.setImageURI(exchangeCode);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击选择完收回PopupWidow
                popupWindow.dismiss();
            }
        });

        //动画样式
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        //显示位置
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
}
