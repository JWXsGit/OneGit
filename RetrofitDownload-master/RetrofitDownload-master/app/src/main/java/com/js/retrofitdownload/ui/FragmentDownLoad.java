package com.js.retrofitdownload.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.js.retrofitdownload.R;
import com.js.retrofitdownload.http.DownloadManager;
import com.js.retrofitdownload.Api.MyConstants;

public class FragmentDownLoad extends Fragment implements DownloadManager.ProgressListener{
    private ProgressBar pb_progress;
    private TextView tv_progress;
    private DownloadManager downloadManager;
    private int i = 0;
    private Button btn_start, btn_pasuse;

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view=inflater .inflate(R.layout.fragment_download,container,false) ;

        pb_progress = view.findViewById(R.id.pb_progress);
        tv_progress = view.findViewById(R.id.tv_progress);
        btn_start = view.findViewById(R.id.btn_start);
        btn_pasuse = view.findViewById(R.id.btn_pasuse);

        downloadManager = DownloadManager.getInstance();
        downloadManager.setProgressListener(this);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        btn_pasuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasuse();
            }
        });

        return view;
    }

    /**
     * 点击开始下载
     */
    public void start() {
        btn_start.setClickable(false);
        downloadManager.start(MyConstants.DOWNLOAD_URL);
    }

    /**
     * 点击暂停下载或继续下载
     */
    public void pasuse() {
        if (i % 2 == 0) {
            downloadManager.pause();
            btn_pasuse.setText("继续下载");
        } else {
            downloadManager.reStart();
            btn_pasuse.setText("暂停下载");
        }
        i++;
    }

    /**
     * 进度回调接口
     */
    @Override
    public void progressChanged(long read, long contentLength, boolean done) {
        final int progress = (int) (100 * read / contentLength);
        pb_progress.setProgress(progress);
        tv_progress.setText(progress + "%");

        if(done){
            btn_start.setClickable(true);
        }
    }
}
