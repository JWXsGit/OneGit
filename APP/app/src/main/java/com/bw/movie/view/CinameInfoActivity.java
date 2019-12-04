package com.bw.movie.view;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.My_Tab_Fragment_Adapter;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.CinemainfoBean;
import com.bw.movie.bean.FollowBean;
import com.bw.movie.bean.eventbus_bean.Detali_EventBus;
import com.bw.movie.bean.eventbus_bean.ID_Bean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.view.fragment.yingyuan_fragment.YyComment_Fragment;
import com.bw.movie.view.fragment.yingyuan_fragment.YyInfo_Fragment;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class CinameInfoActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private int userId;
    private String sessionId;
    private ImageView ima_finish;
    private TextView cinema_name, cinema_label, cinema_label_1, cinema_label_2;
    private TabLayout tab;
    private ViewPager cinema_view;
    public Button cinema_but;
    private List<String> tabs = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private EventBus aDefault;
    private int id;
    private int cinemaId;
    private CheckBox checkbox_xin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aDefault = EventBus.getDefault();
        aDefault.register(this);
    }

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        ima_finish = findViewById(R.id.ima_finish);
        checkbox_xin = findViewById(R.id.checkbox_xin);
        cinema_name = findViewById(R.id.cinema_name);
        cinema_label = findViewById(R.id.cinema_label);
        cinema_label_1 = findViewById(R.id.cinema_label_1);
        cinema_label_2 = findViewById(R.id.cinema_label_2);
        tab = findViewById(R.id.tab);
        cinema_view = findViewById(R.id.cinema_view);
        cinema_but = findViewById(R.id.cinema_but);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.CINEMAID, 0);
        mPresenter.onNameInfo(userId, sessionId, id);

        ima_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkbox_xin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkbox_xin.setButtonDrawable(R.mipmap.movie_info_hreat_s);
                    mPresenter.onCinema_FollowMovie(cinemaId);
                } else {
                    checkbox_xin.setButtonDrawable(R.mipmap.movie_info_hreat_n);
                    mPresenter.onCinema_CancelFollowMovie(cinemaId);
                }
            }
        });
        cinema_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CinameInfoActivity.this, SchedulingActivity.class);
                intent.putExtra("cinemaId", cinemaId);
                startActivity(intent);
            }
        });
        tabs.add("影院详情");
        tabs.add("影院评论");

        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));

        fragments.add(new YyInfo_Fragment());
        fragments.add(new YyComment_Fragment());
        cinema_view.setOffscreenPageLimit(2);
        tab.setupWithViewPager(cinema_view);
        My_Tab_Fragment_Adapter tab_fragment_adapter = new My_Tab_Fragment_Adapter(getSupportFragmentManager(), fragments, tabs);
        cinema_view.setAdapter(tab_fragment_adapter);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_ciname_info;
    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe
    public void ID_Data(ID_Bean id_bean) {
        sessionId = id_bean.getSessionId();
        userId = id_bean.getUserId();
    }


    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof CinemainfoBean) {
            CinemainfoBean bean = (CinemainfoBean) obj;
            CinemainfoBean.ResultBean result = bean.getResult();
            cinemaId = result.getId();
            cinema_name.setText(result.getName());
            String str = result.getLabel();
            String[] strArr = str.split(",");
            for (int i = 0; i < strArr.length; i++) {
                cinema_label.setText(strArr[0]);
            }
            Detali_EventBus bus = new Detali_EventBus();
            bus.setName(result.getName());
            bus.setPhone(result.getPhone());
            bus.setVehicleRoute(result.getVehicleRoute());
            bus.setCinemaId(id);
            EventBus.getDefault().postSticky(bus);
        } else if (obj instanceof FollowBean) {
            FollowBean bean = (FollowBean) obj;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
