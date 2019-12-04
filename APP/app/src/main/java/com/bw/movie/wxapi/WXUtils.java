package com.bw.movie.wxapi;

import com.bw.movie.APP;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @name APP
 * @class name：com.bw.movie.utils.wxutils
 * @class describe
 * @anthor 24673
 * @time 2019/11/21 15:06
 * @change
 * @chang time
 * @class describe
 */
public class WXUtils {
    // IWXAPI 是第三方app和微信通信的openApi接口
    public static IWXAPI api;

    public static void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(APP.context, APP.APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP.APP_ID);

//        //建议动态监听微信启动广播进行注册到微信
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                // 将该app注册到微信
//                api.registerApp(Constants.APP_ID);
//            }
//        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }
}
