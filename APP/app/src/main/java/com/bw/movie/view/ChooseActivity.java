package com.bw.movie.view;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.ByDateBean;
import com.bw.movie.bean.DateListBean;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.bean.fragment_bean.NearbyBean;
import com.bw.movie.bean.fragment_bean.RecommendBean;
import com.bw.movie.bean.fragment_bean.RegionBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.adapter.Fragment_ByDateAdapter;
import com.bw.movie.view.adapter.adapter.Fragment_Choose_Adapter;
import com.bw.movie.view.adapter.adapter.Fragment_Text_Adapter;
import com.bw.movie.view.adapter.fragment_cinema.Fragment_NearAdapter;
import com.bw.movie.view.adapter.fragment_cinema.Fragment_RecommendAdapter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class ChooseActivity extends BaseActivity<Fragment_Presenter> implements View.OnClickListener, Contract.IView {
    private TextView director_choose, score_choose, time_choose, movie_name;
    private JCVideoPlayer choose_jc;
    private TextView text_address, text_time, text_shaixuan;
    private RecyclerView choose_recycler;
    private DetailBean detailBean1;
    private int movieId;
    private String str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected void initView() {
        choose_jc = findViewById(R.id.choose_jc);
        director_choose = findViewById(R.id.director_choose);
        score_choose = findViewById(R.id.score_choose);
        time_choose = findViewById(R.id.time_choose);
        movie_name = findViewById(R.id.movie_name);
        text_shaixuan = findViewById(R.id.text_shaixuan);
        text_time = findViewById(R.id.text_time);
        text_address = findViewById(R.id.text_address);
        choose_recycler = findViewById(R.id.choose_recycler);
    }

    @Override
    protected void initData() {
        text_shaixuan.setOnClickListener(this);
        text_address.setOnClickListener(this);
        text_time.setOnClickListener(this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");// HH:mm:ss//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        text_time.setText("今天" + simpleDateFormat.format(date));
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_choose;
    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onChoose(DetailBean detailBean) {
        detailBean1 = detailBean;
        DetailBean.ResultBean list = detailBean.getResult();
        director_choose.setText(list.getMovieDirector().get(0).getName());
        score_choose.setText(list.getScore() + "分");
        movie_name.setText(list.getName());
        time_choose.setText(list.getDuration() + "");
        List<DetailBean.ResultBean.ShortFilmListBean> shortFilmList = list.getShortFilmList();
        String videoUrl = shortFilmList.get(0).getVideoUrl();
        choose_jc.setUp(videoUrl, "");
        ImageView ivThumb = choose_jc.ivThumb;
        Glide.with(this).load(list.getImageUrl()).into(ivThumb);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_address:
                mPresenter.onRegion();
                break;
            case R.id.text_time:
                mPresenter.onDateList();
                break;
            case R.id.text_shaixuan:
                mPresenter.onByDate(str, 1, 10);
                break;
        }
    }

    private void initPopupWindow(RegionBean bean) {
        RecyclerView recycler_cinema;
        View view = View.inflate(this, R.layout.popup_window_choose, null);
        recycler_cinema = view.findViewById(R.id.recycler_cinema);
        view.setAlpha(0.9f);

        final PopupWindow popupWindow = new PopupWindow(view, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);      //必须设置背景 即使为null或者透明 否则点击外部不会消失
        popupWindow.setBackgroundDrawable(null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        final List<RegionBean.ResultBean> result = bean.getResult();
        Fragment_Choose_Adapter adapter = new Fragment_Choose_Adapter(result, ChooseActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ChooseActivity.this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_cinema.setLayoutManager(gridLayoutManager);
        recycler_cinema.setAdapter(adapter);
        adapter.setOnCinemaID(new Fragment_Choose_Adapter.onChoose() {
            @Override
            public void onCinema(int position) {
                text_address.setText(result.get(position).getRegionName());
                mPresenter.onDateList();
                popupWindow.dismiss();
            }
        });
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        //显示位置
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof RegionBean) {
            RegionBean regionBean = (RegionBean) obj;
            if (regionBean.getStatus().equals("0000")) {
                initPopupWindow(regionBean);
            }
        } else if (obj instanceof DateListBean) {
            DateListBean bean = (DateListBean) obj;
            List<String> result = bean.getResult();
            initPopupWindow_DateLisr(result);
        } else if (obj instanceof ByDateBean) {
            ByDateBean bean = (ByDateBean) obj;
            List<ByDateBean.ResultBean> result = bean.getResult();
            Fragment_ByDateAdapter dateAdapter = new Fragment_ByDateAdapter(result, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            choose_recycler.setLayoutManager(linearLayoutManager);
            choose_recycler.setAdapter(dateAdapter);

            dateAdapter.setOnItemId(new Fragment_ByDateAdapter.onItemId() {
                @Override
                public void onItemID(int id) {
                    Intent intent = new Intent(ChooseActivity.this, SeatInfoActivity.class);
                    intent.putExtra(Constant.CINEMAID, id);
                    EventBus.getDefault().postSticky(detailBean1);
                    startActivity(intent);
                }
            });
        }
    }

    private void initPopupWindow_DateLisr(final List<String> result) {
        RecyclerView recycler_cinema;
        View view = View.inflate(this, R.layout.popup_window_choose, null);
        recycler_cinema = view.findViewById(R.id.recycler_cinema);
        view.setAlpha(0.9f);

        final PopupWindow popupWindow = new PopupWindow(view, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);      //必须设置背景 即使为null或者透明 否则点击外部不会消失
        popupWindow.setBackgroundDrawable(null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        Fragment_Text_Adapter text_adapter = new Fragment_Text_Adapter(result, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ChooseActivity.this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_cinema.setLayoutManager(gridLayoutManager);
        recycler_cinema.setAdapter(text_adapter);
        text_adapter.setOnTextView(new Fragment_Text_Adapter.onTextView() {
            @Override
            public void onTextView(int position) {
                text_time.setText(result.get(position));
                str = result.get(position);
                popupWindow.dismiss();
            }
        });

        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        //显示位置
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onError(Throwable e) {

    }
}
