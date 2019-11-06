package com.wd.mvp;

import android.util.Log;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Xuexiandong
 * data: 2019/11/1 21:21:03
 * function：M
 */
public class HomeModle implements Contract.IModle {
    private static final String TAG = "HomeModle";
    @Override
    public void IHomeModle(final CallBack callBack) {
        RxJavaUtil.getInstance()
                .getiApi()
                .getBenner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanBenner>() {
                    @Override
                    public void accept(BeanBenner beanBenner) throws Exception {
                        Log.i(TAG, "accept: "+beanBenner.getMessage());
                        if (callBack!=null) {
                            callBack.getInfo(beanBenner);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "accept: "+throwable.getMessage());
                        if (callBack!=null) {
                            callBack.getFailure(throwable.getMessage());
                        }
                    }
                });
    }
}
