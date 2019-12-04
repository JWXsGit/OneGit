package com.example.my_utils_library.base;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my_utils_library.utils.NetUtils;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initLayout() != 0) {
            setContentView(initLayout());
            mPresenter = providePresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
            initView();
            initData();
        }
    }

    protected abstract P providePresenter();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public boolean IsNetWork() {
        return NetUtils.IsNetWork(this);
    }

    public void NotNetWork() {
        Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
    }

    protected abstract int initLayout();
}
