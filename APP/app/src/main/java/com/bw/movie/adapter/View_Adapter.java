package com.bw.movie.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bw.movie.view.fragment.ShowFragment;
import com.bw.movie.view.fragment.datelist_fragment.DateList_Fragment;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.tablayout
 * @class describe
 * @anthor 24673
 * @time 2019/11/20 11:42
 * @change
 * @chang time
 * @class describe
 */
public class View_Adapter extends FragmentPagerAdapter {
    private List<String> list;
    private int str;

    public View_Adapter(FragmentManager supportFragmentManager, List<String> list, int cinemaId) {
        super(supportFragmentManager);
        this.list = list;
        this.str = cinemaId;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //创建fragment对象并返回
        Bundle bundle = new Bundle();
        bundle.putString("url", list.get(position));//数组添加 str[position]
        bundle.putInt("cinemaId", str);//数组添加 str[position]
        //实例化Fragment
        DateList_Fragment allFragment = new DateList_Fragment();
        allFragment.setArguments(bundle);
        return allFragment;
    }

    //返回选项卡的文本 ，，，添加选项卡
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
