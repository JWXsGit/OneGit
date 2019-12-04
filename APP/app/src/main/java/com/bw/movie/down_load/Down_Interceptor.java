package com.bw.movie.down_load;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @name Jiaweixi20191111
 * @class name：com.example.jiaweixi20191111.utils
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 10:56
 * @change
 * @chang time
 * @class describe
 */
public class Down_Interceptor implements Interceptor {
    public Update_List update_list;

    public Down_Interceptor(Update_List update_list) {
        this.update_list = update_list;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        return proceed.newBuilder().body(new Down_Body(update_list, proceed.body())).build();
    }
}
