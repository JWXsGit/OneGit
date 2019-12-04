package com.bw.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager view_pager;
    private TabLayout tab;
    private List<String> list = new ArrayList<>();
    private String[] str = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = findViewById(R.id.tab);
        view_pager = findViewById(R.id.view_pager);
        init();
    }

    private void init() {
        list.add("11/20");
        list.add("11/21");
        list.add("11/22");
        list.add("11/23");

        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setupWithViewPager(view_pager);
        View_Adapter view_adapter = new View_Adapter(getSupportFragmentManager(), list,str);
        view_pager.setAdapter(view_adapter);
    }
}
