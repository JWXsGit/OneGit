package com.example.my_utils_library.app;

import android.app.Application;

import com.example.my_utils_library.utils.ToastUtils;

public class App extends Application {
    //全局上下文
    public static App sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        ToastUtils.init(sContext);
    }

    public static App getAppContext() {
        return sContext;
    }
}
