package com.bw.movie.down_load;

/**
 * @name Jiaweixi20191111
 * @class name：com.example.jiaweixi20191111.bean
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 9:09
 * @change
 * @chang time
 * @class describe
 */
public class LoadBean {
    private String path;
    private long DownLoadLength;
    private long ContentSize;

    @Override
    public String toString() {
        return "LoadBean{" +
                "path='" + path + '\'' +
                ", DownLoadLength=" + DownLoadLength +
                ", ContentSize=" + ContentSize +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDownLoadLength() {
        return DownLoadLength;
    }

    public void setDownLoadLength(long downLoadLength) {
        DownLoadLength = downLoadLength;
    }

    public long getContentSize() {
        return ContentSize;
    }

    public void setContentSize(long contentSize) {
        ContentSize = contentSize;
    }
}
