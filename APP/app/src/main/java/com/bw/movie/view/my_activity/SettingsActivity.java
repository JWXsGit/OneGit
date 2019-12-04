package com.bw.movie.view.my_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.eventbus_bean.ID_Bean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.MainActivity;
import com.example.my_utils_library.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SettingsActivity extends BaseActivity<Fragment_Presenter> {
    private LinearLayout lin_update;
    private int userId;
    private String sessionId;
    private TextView text_Feedback, bt_dropout;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected void initView() {
        lin_update = findViewById(R.id.lin_update);
        text_Feedback = findViewById(R.id.text_Feedback);
        bt_dropout = findViewById(R.id.bt_dropout);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        userId = intent.getIntExtra(Constant.USER_ID, 0);
        sessionId = intent.getStringExtra(Constant.SESSION_ID);
        lin_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, UpdateActivity.class);
                intent.putExtra(Constant.USER_ID, userId);
                intent.putExtra(Constant.SESSION_ID, sessionId);
                startActivity(intent);
            }
        });
        text_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, FeedbackActivity.class));
            }
        });
        bt_dropout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences(Constant.SP, MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_settings;
    }

    @Override
    public Context context() {
        return null;
    }
}
