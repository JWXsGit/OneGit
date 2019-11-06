package com.wd.mvp;

import java.lang.ref.WeakReference;

/**
 * author: Xuexiandong
 * data: 2019/11/1 21:21:10
 * function：
 */
public class BasePresenter<V> {

    public WeakReference<V> weakReference;
    //初始化WeakReference
    public void attachView(V v){
        weakReference=new WeakReference<>(v);
    }

    //哪个V调用就传入哪个V
    public V getView(){
        return weakReference.get();
    }
    //释放
    public void datechView(){
        weakReference.clear();
    }
}
