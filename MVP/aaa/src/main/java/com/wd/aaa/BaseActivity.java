package com.wd.aaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/11/2 08:8:06
 * function：
 */
public abstract class BaseActivity<V,P extends BasePresenter<V>>extends AppCompatActivity {
    public P mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());

        mPresenter=initPresenter();
        //在指定V进行绑定
        mPresenter.attachView((V) this);
        bind = ButterKnife.bind(this);
        initData();
    }

    protected abstract void initData();

    protected abstract P initPresenter();

    protected abstract int initLayout();
    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
