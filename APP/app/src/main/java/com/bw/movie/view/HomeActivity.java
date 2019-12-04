package com.bw.movie.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.bw.movie.R;
import com.bw.movie.adapter.MyFragment_Adapter;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.eventbus_bean.ID_Bean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.utils.PermissionUtils;
import com.bw.movie.view.fragment.Fragment_Cinema;
import com.bw.movie.view.fragment.Fragment_Home;
import com.bw.movie.view.fragment.Fragment_My;
import com.bw.movie.wxapi.LocaionGD;
import com.example.my_utils_library.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<MyPresenter> implements View.OnClickListener {
    private ViewPager home_viewpager;
    private List<Fragment> fragments = new ArrayList<>();
    private LinearLayout linear_my, linear_home, linear_cinema, linear_home_null, linear_my_null, linear_cinema_null, line1, line2, line3;
    private RelativeLayout lin;
    private ImageView image_home_location, image_home_search;
    private TextView text_home_location_text;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        home_viewpager = findViewById(R.id.home_viewpager);
        linear_my = findViewById(R.id.linear_my);
        linear_home = findViewById(R.id.linear_home);
        linear_cinema = findViewById(R.id.linear_cinema);
        linear_home_null = findViewById(R.id.linear_home_null);
        linear_my_null = findViewById(R.id.linear_my_null);
        linear_cinema_null = findViewById(R.id.linear_cinema_null);
        image_home_location = findViewById(R.id.image_home_location);
        image_home_search = findViewById(R.id.image_home_search);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        lin = findViewById(R.id.lin);
        text_home_location_text = findViewById(R.id.text_home_location_text);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int userId = intent.getIntExtra(Constant.USER_ID, 0);
        String sessionId = intent.getStringExtra(Constant.SESSION_ID);
        ID_Bean id_bean = new ID_Bean();
        id_bean.setUserId(userId);
        id_bean.setSessionId(sessionId);
        EventBus.getDefault().post(id_bean);

        fragments.add(new Fragment_Home());
        fragments.add(new Fragment_Cinema());
        fragments.add(new Fragment_My());
        MyFragment_Adapter fragment_adapter = new MyFragment_Adapter(getSupportFragmentManager(), fragments);
        home_viewpager.setAdapter(fragment_adapter);
        home_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        lin.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        lin.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        lin.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ClickList();
        image_home_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PermissionUtils.requestPermissions(HomeActivity.this, PermissionUtils.CODE_LOCATION)) {
                    new LocaionGD().onMap(new LocaionGD.onMapListener() {
                        @Override
                        public void onMapResult(AMapLocation aMapLocation) {
                            if (aMapLocation != null) {
                                String adCode = aMapLocation.getCity();
                                text_home_location_text.setText(adCode);
                            }
                        }
                    });
                }
            }
        });
        image_home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ByKeywordActivity.class));
            }
        });
    }

    private void ClickList() {
        line1.setOnClickListener(this);
        line2.setOnClickListener(this);
        line3.setOnClickListener(this);

        home_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                linear_home.setSelected(true);
                checkTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void checkTab(int position) {
        switch (position) {
            case R.id.line1:
                home_viewpager.setCurrentItem(0);
                break;
            case R.id.line2:
                home_viewpager.setCurrentItem(1);
                break;
            case R.id.line3:
                home_viewpager.setCurrentItem(2);
                break;
            case 0:
                linear_home.setVisibility(View.VISIBLE);
                linear_home_null.setVisibility(View.GONE);
                linear_cinema_null.setVisibility(View.VISIBLE);
                linear_my_null.setVisibility(View.VISIBLE);
                linear_cinema.setVisibility(View.GONE);
                linear_my.setVisibility(View.GONE);
                break;
            case 1:
                linear_cinema.setVisibility(View.VISIBLE);
                linear_cinema_null.setVisibility(View.GONE);
                linear_my_null.setVisibility(View.VISIBLE);
                linear_home_null.setVisibility(View.VISIBLE);
                linear_my.setVisibility(View.GONE);
                linear_home.setVisibility(View.GONE);
                break;
            case 2:
                linear_my.setVisibility(View.VISIBLE);
                linear_my_null.setVisibility(View.GONE);
                linear_home_null.setVisibility(View.VISIBLE);
                linear_cinema_null.setVisibility(View.VISIBLE);
                linear_home.setVisibility(View.GONE);
                linear_cinema.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onClick(View view) {
        checkTab(view.getId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
