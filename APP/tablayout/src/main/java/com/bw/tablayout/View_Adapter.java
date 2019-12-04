package com.bw.tablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
    private String[] str = {};

    public View_Adapter(FragmentManager supportFragmentManager, List<String> list, String[] str) {
        super(supportFragmentManager);
        this.list = list;
        this.str = str;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //创建fragment对象并返回
        Bundle bundle = new Bundle();
        bundle.putString("url", str[position]);
        //实例化Fragment
        ShowFragment allFragment = new ShowFragment();
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
