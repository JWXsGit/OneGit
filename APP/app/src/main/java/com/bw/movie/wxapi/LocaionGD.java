package com.bw.movie.wxapi;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bw.movie.APP;

/**
 * @name APP
 * @class name：com.bw.movie.wxapi
 * @class describe
 * @anthor 24673
 * @time 2019/11/30 18:05
 * @change
 * @chang time
 * @class describe
 */
public class LocaionGD {
    public interface onMapListener {
        void onMapResult(AMapLocation aMapLocation);
    }

    //声明AMapLocationClient类对象
    public static AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public static AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    public static AMapLocationClientOption mLocationOption = null;

    public static void onMap(final onMapListener onMapListener) {
        mLocationClient = new AMapLocationClient(APP.context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //高德地图SDK定位
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        if (onMapListener != null) {
                            onMapListener.onMapResult(aMapLocation);
                        }
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
}
