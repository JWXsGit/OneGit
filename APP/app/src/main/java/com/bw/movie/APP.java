package com.bw.movie;

import android.app.Application;
import android.content.Context;

import com.bw.movie.wxapi.WXUtils;
import com.example.my_utils_library.utils.ToastUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @name APP
 * @class name：com.bw.movie
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 21:45
 * @change
 * @chang time
 * @class describe
 */
public class APP extends Application {
    public static Context context;
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    public static final String APP_ID = "wxb3852e6a6b7d9516";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ToastUtils.init(context);
        WXUtils.regToWx();
        //Fresco初始化
        Fresco.initialize(this);
    }
}
