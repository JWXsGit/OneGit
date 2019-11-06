package com.wd.aaa;

import java.util.Map;

/**
 * author: Xuexiandong
 * data: 2019/11/2 07:7:47
 * function：契约
 */
public interface Contract {
    //V
    interface IView {
        void onSuccess(Object vobj);
    }


    //P
    interface IModel {
        void onSuccess(Map<String, String> pmap, CallBack callBack);

        interface CallBack {
            void onInfo(Object pobj);
            void onInfo2(Object pobj);

        }
    }
}
