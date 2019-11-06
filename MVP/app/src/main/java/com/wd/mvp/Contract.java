package com.wd.mvp;

import java.util.Map;

/**
 * author: Xuexiandong
 * data: 2019/11/1 20:20:55
 * function：契约
 */
public interface Contract {
    //V
    interface IView {
        void IHomeView(Object vobj);
        void IFailure(String failure);

    }

//    //P
//    interface IPresenter {
//        void IHomePresenter(Map<String, String> map);
//    }

    //M
    interface IModle {
        void IHomeModle(CallBack callBack);

        interface CallBack {
            void getInfo(Object mobj);
            void getFailure(String failure);
        }
    }

}
