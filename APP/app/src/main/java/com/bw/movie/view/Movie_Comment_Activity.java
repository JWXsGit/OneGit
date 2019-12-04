package com.bw.movie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.Movie_CommentBean;
import com.bw.movie.presenter.MyPresenter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;

public class Movie_Comment_Activity extends BaseActivity<MyPresenter> implements Contract.IView {
    private ImageView img_yp_back;
    private TextView tv_pingfen, tv_yp_name;
    private RatingBar rb_yp_pingfen;
    private EditText et_yp_movie, et_yp_cni;
    private Button bt_yp_push;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        img_yp_back = findViewById(R.id.img_yp_back);
        tv_yp_name = findViewById(R.id.tv_yp_name);
        rb_yp_pingfen = findViewById(R.id.rb_yp_pingfen);
        tv_pingfen = findViewById(R.id.tv_pingfen);
        et_yp_movie = findViewById(R.id.et_yp_movie);
        et_yp_cni = findViewById(R.id.et_yp_cni);
        bt_yp_push = findViewById(R.id.bt_yp_push);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        final int movieId = intent.getIntExtra("movieId", 0);
        tv_yp_name.setText(name);
        img_yp_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_yp_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_comment = et_yp_movie.getText().toString();
                mPresenter.onMovie_Comment(movieId, edit_comment, 4.5);
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_movie__comment_;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        Movie_CommentBean bean = (Movie_CommentBean) obj;
        String message = bean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError(Throwable e) {

    }
}
