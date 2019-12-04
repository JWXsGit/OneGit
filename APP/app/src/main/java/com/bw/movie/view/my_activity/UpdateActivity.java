package com.bw.movie.view.my_activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.fragment_bean.NewVersionBean;
import com.bw.movie.down_load.DownUtils;
import com.bw.movie.down_load.NewVersion_Code;
import com.bw.movie.presenter.Fragment_Presenter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;

public class UpdateActivity extends BaseActivity<Fragment_Presenter> implements Contract.IView, DownUtils.onProgress {
    private Button bt_start, bt_stop, bt_update;
    private ImageView image;
    private ProgressBar progress_bar;
    private int userId;
    private String sessionId;
    private TextView text_pro;
    private DownUtils down_load;
    private int i = 0;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected void initView() {
        image = findViewById(R.id.image);
        bt_start = findViewById(R.id.bt_start);
        bt_stop = findViewById(R.id.bt_stop);
        progress_bar = findViewById(R.id.progress_bar);
        bt_update = findViewById(R.id.bt_update);
        text_pro = findViewById(R.id.text_pro);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        userId = intent.getIntExtra(Constant.USER_ID, 0);
        sessionId = intent.getStringExtra(Constant.SESSION_ID);
        down_load = DownUtils.getInstance();
        down_load.setOnProgress(this);
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = NewVersion_Code.getVersionCode(UpdateActivity.this) + "";
                mPresenter.onNetVersion(userId, sessionId, code);
            }
        });
        bt_start.setClickable(false);
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void start() {
        down_load.onStart();
    }

    private void stop() {
        if (i % 2 == 0) {
            down_load.onPause();
            bt_stop.setText("继续更新");
        } else {
            down_load.onRestart();
            bt_stop.setText("暂停更新");
        }
        i++;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_update;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        NewVersionBean bean = (NewVersionBean) obj;
        if (bean.getFlag() == 1) {
            Toast.makeText(this, bean.getFlag()+"有新版本", Toast.LENGTH_SHORT).show();
            start();
        } else {
            Toast.makeText(this, bean.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onProgress(long size, long length, boolean isSu) {
        int data = (int) (100 * size / length);
        progress_bar.setProgress(data);
        text_pro.setText(data + "%");
    }
}
