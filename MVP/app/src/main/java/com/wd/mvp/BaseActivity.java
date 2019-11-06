package com.wd.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/11/1 21:21:18
 * function：
 */
public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {

    public P mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLaYout());
        //初始化P
        mPresenter=contentPresentre();
        //传入指定V层
        mPresenter.attachView((V) this);

        bind = ButterKnife.bind(this);
        initData();
    }
    //布局
    protected abstract int getLaYout();
    //传入实例化P
    protected abstract P contentPresentre();
    //数据
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
