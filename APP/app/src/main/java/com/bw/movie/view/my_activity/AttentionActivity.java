package com.bw.movie.view.my_activity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.adapter.My_Tab_Fragment_Adapter;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.view.fragment.myfragment.My_Cinema_Fragment;
import com.bw.movie.view.fragment.myfragment.My_TheFilm_Fragment;
import com.example.my_utils_library.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AttentionActivity extends BaseActivity<MyPresenter> {
    private ViewPager attention_viewpager;
    private TabLayout guan_tab;
    private ImageView find_letf;

    @Override
    protected MyPresenter providePresenter() {
        return null;
    }

    @Override
    protected void initView() {
        find_letf = findViewById(R.id.find_letf);
        guan_tab = findViewById(R.id.guan_tab);
        attention_viewpager = findViewById(R.id.attention_viewpager);
    }

    @Override
    protected void initData() {
        find_letf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final List<Fragment> fraglist = new ArrayList<>();
        fraglist.add(new My_TheFilm_Fragment());
        fraglist.add(new My_Cinema_Fragment());
        final List<String> list = new ArrayList<>();
        list.add("电影");
        list.add("影院");
        guan_tab.setTabMode(TabLayout.MODE_FIXED);
        guan_tab.setupWithViewPager(attention_viewpager);

        My_Tab_Fragment_Adapter fragment_adapter = new My_Tab_Fragment_Adapter(getSupportFragmentManager(), fraglist, list);
        attention_viewpager.setAdapter(fragment_adapter);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_attention;
    }

    @Override
    public Context context() {
        return null;
    }
}
