package com.bw.movie.down_load;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

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
public class Down_Body extends ResponseBody {
    public Update_List update_list;
    public ResponseBody responseBody;
    public BufferedSource bufferedSource;

    public Down_Body(Update_List update_list, ResponseBody responseBody) {
        this.update_list = update_list;
        this.responseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(BufferedSource source) {
        return new ForwardingSource(source) {
            long tol = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytes = super.read(sink, byteCount);
                tol += bytes != -1 ? bytes : 0;
                if (null != update_list) {
                    update_list.onUpdate_data(tol, responseBody.contentLength(), bytes == -1);
                }
                return bytes;
            }
        };
    }
}
