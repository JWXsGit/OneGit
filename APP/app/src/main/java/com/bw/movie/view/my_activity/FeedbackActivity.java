package com.bw.movie.view.my_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.RecordBean;
import com.bw.movie.presenter.MyPresenter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;

public class FeedbackActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private Button bt_submit;
    private EditText edit_content;
    private ImageView find_letf;
    private RelativeLayout ok_re;
    private LinearLayout lin;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        find_letf = findViewById(R.id.find_letf);
        edit_content = findViewById(R.id.edit_content);
        bt_submit = findViewById(R.id.bt_submit);
        ok_re = findViewById(R.id.ok_re);
        lin = findViewById(R.id.lin);
    }

    @Override
    protected void initData() {
        find_letf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = edit_content.getText().toString();
                if (string != null) {
                    mPresenter.onRecord(string);
                } else {
                    Toast.makeText(FeedbackActivity.this, "输入内容不可为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        RecordBean bean = (RecordBean) obj;
        if (bean.getStatus().equals("0000")) {
            ok_re.setVisibility(View.VISIBLE);
            lin.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
