package com.wd.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
//注册
public class MainActivity extends BaseActivity<Contract.IView,Presenter<Contract.IView>>implements Contract.IView {

    private static final String TAG = "MainActivity";
    //布局
    @Override
    protected int getLaYout() {
        return R.layout.activity_main;
    }
    //实例化P
    @Override
    protected Presenter<Contract.IView> contentPresentre() {
        return new Presenter<>();
    }
    //数据
    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        mPresenter.reqyese();
    }
    //成功
    @Override
    public void IHomeView(Object vobj) {
        Log.i(TAG, "IHomeView: "+vobj.toString());
    }
    //失败
    @Override
    public void IFailure(String failure) {
        Log.i(TAG, "IFailure: "+failure);
    }
}
