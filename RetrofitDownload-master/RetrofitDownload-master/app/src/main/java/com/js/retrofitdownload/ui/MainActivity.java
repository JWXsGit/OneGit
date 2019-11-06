package com.js.retrofitdownload.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.js.retrofitdownload.R;
import com.js.retrofitdownload.adapter.TabFragmentPagerAdapter;
import com.js.retrofitdownload.http.DownloadManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> list;
    private ViewPager myViewPager;
    private TabFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        myViewPager = (ViewPager) findViewById(R.id.viewPager);
        list = new ArrayList<>();
        list.add(new FragmentDownLoad());
        list.add(new FragmentLazyLoad());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
    }
}
