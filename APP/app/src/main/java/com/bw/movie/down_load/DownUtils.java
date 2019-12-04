package com.bw.movie.down_load;

import android.os.Environment;
import android.util.Log;

import com.bw.movie.api.HttpApi;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @name Jiaweixi20191111
 * @class name：com.example.jiaweixi20191111.utils
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 10:48
 * @change
 * @chang time
 * @class describe
 */
public class DownUtils implements Update_List {
    public static DownUtils downUtils = null;
    private final LoadBean bean;
    private final File file;
    private onProgress onProgress;
    private Retrofit retrofit;
    private static final String TAG = "DownUtils";
    private Subscription subscribe;

    private DownUtils() {
        bean = new LoadBean();
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "1111.apk");
        bean.setPath(file.getAbsolutePath());
    }

    public static DownUtils getInstance() {
        if (downUtils == null) {
            synchronized (DownUtils.class) {
                if (downUtils == null) {
                    downUtils = new DownUtils();
                }
            }
        }
        return downUtils;
    }

    @Override
    public void onUpdate_data(long size, long length, final boolean isSu) {
        if (bean.getDownLoadLength() > length) {
            size = size + (bean.getDownLoadLength() - length);
        } else {
            bean.setDownLoadLength(length);
        }
        bean.setContentSize(size);
        Observable.just(1).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (onProgress != null) {
                    onProgress.onProgress(bean.getContentSize(), bean.getDownLoadLength(), isSu);
                }
            }
        });
    }

    public synchronized void onStart() {
        Down_Interceptor interceptor = new Down_Interceptor(this);
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        DownLoad();
    }

    private void DownLoad() {
        subscribe = retrofit.create(HttpApi.class).onDownLoad("bytes=" + bean.getContentSize() + "-")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, LoadBean>() {
                    @Override
                    public LoadBean call(ResponseBody response) {
                        try {
                            FileUtil.writeCache(response, new File(file.getPath()), bean);
                            return bean;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoadBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(LoadBean loadBean) {
                        Log.i(TAG, "onNext: ");
                    }
                });
    }

    public void onPause() {
        if (subscribe != null) {
            subscribe.unsubscribe();
        }
    }

    public void onRestart() {
        DownLoad();
    }

    public interface onProgress {
        void onProgress(long size, long length, boolean isSu);
    }

    public void setOnProgress(onProgress onProgress) {
        this.onProgress = onProgress;
    }
}
