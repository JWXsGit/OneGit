package com.wd.aaa;

import java.lang.ref.WeakReference;

/**
 * author: Xuexiandong
 * data: 2019/11/2 07:7:57
 * function：
 */
public class BasePresenter<V> {
    private WeakReference<V> weakReference;

    //调用时创建
    public void attachView(V v) {
        weakReference = new WeakReference<>(v);
    }

    //哪里调用将数据传到哪里
    public V getView() {
        return weakReference.get();
    }

    //释放
    public void datechView() {
        weakReference.clear();
    }
}
