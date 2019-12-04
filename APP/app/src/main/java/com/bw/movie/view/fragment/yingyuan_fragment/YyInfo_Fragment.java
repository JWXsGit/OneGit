package com.bw.movie.view.fragment.yingyuan_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.bean.eventbus_bean.Detali_EventBus;
import com.bw.movie.bean.fragment_bean.CommentBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment.yingyuan_fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/18 19:53
 * @change
 * @chang time
 * @class describe
 */
public class YyInfo_Fragment extends BaseFragment<Fragment_Presenter>{
    private TextView text_address, text_phone, text_name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_yyinfo;
    }

    @Override
    protected void initView(View view) {
        text_name = view.findViewById(R.id.text_name);
        text_phone = view.findViewById(R.id.text_phone);
        text_address = view.findViewById(R.id.text_address);
    }

    @Override
    protected void initData() {

    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDetail(Detali_EventBus bus) {
        String name = bus.getName();
        String phone = bus.getPhone();
        String vehicleRoute = bus.getVehicleRoute();
        text_address.setText(vehicleRoute);
        text_name.setText(name);
        text_phone.setText(phone);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
