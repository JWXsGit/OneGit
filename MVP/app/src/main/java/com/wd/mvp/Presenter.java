package com.wd.mvp;

import android.util.Log;

import java.util.Map;

/**
 * author: Xuexiandong
 * data: 2019/11/1 21:21:10
 * function：
 */
public class Presenter<V extends Contract.IView> extends BasePresenter<V> {
    private HomeModle homeModle;
    private static final String TAG = "Presenter";

    public Presenter() {
        homeModle=new HomeModle();
    }

    public void reqyese() {
        homeModle.IHomeModle(new Contract.IModle.CallBack() {
            //成功
            @Override
            public void getInfo(Object mobj) {
                Log.i(TAG, "getInfo: "+mobj.toString());
                //返回到V层
                getView().IHomeView(mobj);
            }

            //失败
            @Override
            public void getFailure(String failure) {
                Log.i(TAG, "getFailure: "+failure);
                getView().IFailure(failure);
            }
        });
    }
}
