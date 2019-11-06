package com.wd.aaa;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Xuexiandong
 * data: 2019/11/2 07:7:53
 * function：M
 */
public class HomeModel implements Contract.IModel{
    //获取数据
    @Override
    public void onSuccess(Map<String, String> pmap, final CallBack callBack) {
        RxJavaUtil.getInstance()
                .getiApi()
                .getLogin(pmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (callBack!=null) {
                            callBack.onInfo(object);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callBack!=null) {
                            callBack.onInfo2(throwable.getMessage());
                        }
                    }
                });
    }
}
