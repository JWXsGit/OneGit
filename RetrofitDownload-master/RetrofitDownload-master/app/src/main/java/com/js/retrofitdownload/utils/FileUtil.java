package com.js.retrofitdownload.utils;

import com.js.retrofitdownload.bean.DownloadInfo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import okhttp3.ResponseBody;

/**
 * Created by ${R.js} on 2018/3/22.
 */

public class FileUtil {
    /**
     * 写入文件
     *
     * @param file
     * @param info
     * @throws IOException
     */
    public static void writeCache(ResponseBody responseBody, File file, DownloadInfo info) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        long allLength;
        if (info.getContentLength() == 0) {
            allLength = responseBody.contentLength();
        } else {
            allLength = info.getContentLength();
        }

        FileChannel channelOut = null;

        //RandomAccessFile可以读写指定位置的文件类
        RandomAccessFile randomAccessFile = null;
        //赋予可读可写的权限
        randomAccessFile = new RandomAccessFile(file, "rwd");
        //底下就是向文件内写入下载的数据
        channelOut = randomAccessFile.getChannel();
        MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                info.getReadLength(), allLength - info.getReadLength());
        byte[] buffer = new byte[1024 * 4];
        int len;
        while ((len = responseBody.byteStream().read(buffer)) != -1) {
            mappedBuffer.put(buffer, 0, len);
        }

        //写完一定要记得关闭
        responseBody.byteStream().close();
        channelOut.close();
        randomAccessFile.close();
    }
}
