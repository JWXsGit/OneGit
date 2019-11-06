package com.wd.aaa;

import java.util.Map;

/**
 * author: Xuexiandong
 * data: 2019/11/2 08:8:02
 * function：
 */
public class Presenter<V extends Contract.IView>extends BasePresenter<V> {
    private HomeModel homeModel;

    public Presenter() {
        this.homeModel = homeModel;
    }
    public void come(Map<String ,String>map){
        homeModel.onSuccess(map, new Contract.IModel.CallBack() {
            @Override
            public void onInfo(Object pobj) {
                //将数据返回到V层
                getView().onSuccess(pobj);
            }

            @Override
            public void onInfo2(Object pobj) {

            }
        });
    }
}
