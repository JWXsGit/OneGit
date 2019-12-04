package com.bw.movie.view;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MyFragment_Adapter;
import com.bw.movie.adapter.My_Tab_Fragment_Adapter;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.bean.FollowBean;
import com.bw.movie.bean.eventbus_bean.ID_Bean;
import com.bw.movie.bean.eventbus_bean.Movie_ID;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.view.fragment.detail_fragment.MovieComment;
import com.bw.movie.view.fragment.detail_fragment.MovieDirector;
import com.bw.movie.view.fragment.detail_fragment.PosterList;
import com.bw.movie.view.fragment.detail_fragment.ShortFilmList;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private SimpleDraweeView detail_image;
    private TextView detail_text_score, detail_text_comment, detail_text_name, detail_text_type, detail_text_time, detail_text_date, detail_text_place, detail_text_attention;
    private CheckBox check_info_heart;
    private TabLayout detail_tab;
    private ViewPager detail_vp;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();
    private int movieid;
    private Button btn_info_buy, btn_info_write;
    private DetailBean detailBean;
    private String name;
    private int movieId;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        detail_image = findViewById(R.id.detail_image);
        detail_image = findViewById(R.id.detail_image);
        detail_text_score = findViewById(R.id.detail_text_score);
        detail_text_comment = findViewById(R.id.detail_text_comment);
        detail_text_name = findViewById(R.id.detail_text_name);
        detail_text_type = findViewById(R.id.detail_text_type);
        detail_text_time = findViewById(R.id.detail_text_time);
        detail_text_date = findViewById(R.id.detail_text_date);
        detail_text_place = findViewById(R.id.detail_text_place);
        detail_text_attention = findViewById(R.id.detail_text_attention);
        detail_tab = findViewById(R.id.detail_tab);
        detail_vp = findViewById(R.id.detail_vp);
        check_info_heart = findViewById(R.id.check_info_heart);
        btn_info_buy = findViewById(R.id.btn_info_buy);
        btn_info_write = findViewById(R.id.btn_info_write);
    }


    @Override
    protected void initData() {
        final Intent intent = getIntent();
        movieid = intent.getIntExtra(Constant.MOVIEID, 0);
        SharedPreferences sp = getSharedPreferences(Constant.SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(Constant.MOVIEID, movieid);
        edit.commit();
        mPresenter.onDetail(movieid);

        check_info_heart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check_info_heart.setButtonDrawable(R.mipmap.movie_info_hreat_s);
                    detail_text_attention.setText("已关注");
                    mPresenter.onFollowMovie(movieid);
                } else {
                    check_info_heart.setButtonDrawable(R.mipmap.movie_info_hreat_n);
                    detail_text_attention.setText("未关注");
                    mPresenter.onCancelFollowMovie(movieid);
                }
            }
        });

        tabs.add("介绍");
        tabs.add("预告");
        tabs.add("剧照");
        tabs.add("影评");
        detail_tab.addTab(detail_tab.newTab().setText(tabs.get(0)));
        detail_tab.addTab(detail_tab.newTab().setText(tabs.get(1)));
        detail_tab.addTab(detail_tab.newTab().setText(tabs.get(2)));
        detail_tab.addTab(detail_tab.newTab().setText(tabs.get(3)));
        detail_tab.setTabMode(TabLayout.MODE_FIXED);

        fragments.add(new MovieDirector());
        fragments.add(new ShortFilmList());
        fragments.add(new PosterList());
        fragments.add(new MovieComment());
        detail_vp.setOffscreenPageLimit(4);
        detail_tab.setupWithViewPager(detail_vp);
        My_Tab_Fragment_Adapter fragment_adapter = new My_Tab_Fragment_Adapter(getSupportFragmentManager(), fragments, tabs);
        detail_vp.setAdapter(fragment_adapter);
        btn_info_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailActivity.this, ChooseActivity.class);
                EventBus.getDefault().postSticky(detailBean);
                startActivity(intent1);
            }
        });
        btn_info_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailActivity.this, Movie_Comment_Activity.class);
                intent1.putExtra("name", name);
                intent1.putExtra("movieId", movieId);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof DetailBean) {
            detailBean = (DetailBean) obj;
            name = detailBean.getResult().getName();
            movieId = detailBean.getResult().getMovieId();
            EventBus.getDefault().post(detailBean);
            DetailBean.ResultBean list = detailBean.getResult();
            detail_image.setImageURI(list.getImageUrl());
            detail_text_score.setText(list.getScore() + "分");
            detail_text_comment.setText(list.getCommentNum() + "万条");
            detail_text_name.setText(list.getName());
            detail_text_type.setText(list.getMovieType());
            detail_text_time.setText(list.getDuration());
            //时间戳
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            detail_text_date.setText(format);
            detail_text_place.setText(list.getPlaceOrigin() + "上映");

            int whetherFollow = detailBean.getResult().getWhetherFollow();
            if (whetherFollow == 1) {
                check_info_heart.setButtonDrawable(R.mipmap.movie_info_hreat_s);
                detail_text_attention.setText("已关注");
            } else {
                check_info_heart.setButtonDrawable(R.mipmap.movie_info_hreat_n);
                detail_text_attention.setText("未关注");
            }
        } else if (obj instanceof FollowBean) {
            FollowBean bean = (FollowBean) obj;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
