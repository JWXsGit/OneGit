package com.bw.movie.view.my_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.bean.Seen_MovieBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.view.adapter.my_adapter.My_Seen_Movie_Adapter;
import com.bw.movie.view.adapter.my_adapter.My_Ticket_Adapter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;

import java.util.List;

public class Seen_MovieActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private RecyclerView recycler_view;
    private RelativeLayout lin_visi;
    private ImageView find_letf;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        recycler_view = findViewById(R.id.recycler_view);
        lin_visi = findViewById(R.id.lin_visi);
        find_letf = findViewById(R.id.find_letf);
    }

    @Override
    protected void initData() {
        mPresenter.onSeen_Movie();
        find_letf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_seen__movie;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        Seen_MovieBean bean = (Seen_MovieBean) obj;
        List<Seen_MovieBean.ResultBean> result = bean.getResult();
        if (result != null) {
            recycler_view.setVisibility(View.VISIBLE);
            lin_visi.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Seen_MovieActivity.this, LinearLayoutManager.VERTICAL, true);
            recycler_view.setLayoutManager(linearLayoutManager);
            My_Seen_Movie_Adapter guan_cinema_adapter = new My_Seen_Movie_Adapter(result, Seen_MovieActivity.this);
            recycler_view.setAdapter(guan_cinema_adapter);
        } else {
            recycler_view.setVisibility(View.GONE);
            lin_visi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
