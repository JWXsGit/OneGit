package com.js.retrofitdownload.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * 懒加载的基类
 */
public abstract class BaseLazyLoadFragment extends Fragment {
    //给个标记，标记这个fragment是否已经准备好
    private boolean isPrepared;

    //Activity加载完就执行的生命周期
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }
    //设置用户是否可见生命周期
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * 懒加载具体方法
     */
    private void lazyLoad() {
        //如果设置用户可见与标记等于trued的情况下让他展示加载，设置数据    不成功就不展示不加载
        if (getUserVisibleHint() && isPrepared) {
            initData();
            Log.w("dj", "展示，加载");
        } else {
            Log.w("dj", "没展示，不加载");
        }
    }

    public abstract void initData();
}
