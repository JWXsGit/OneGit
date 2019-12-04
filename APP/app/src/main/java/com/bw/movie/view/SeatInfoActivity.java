package com.bw.movie.view;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.SeatInfoBean;
import com.bw.movie.bean.TicketsBean;
import com.bw.movie.bean.WXPayBean;
import com.bw.movie.bean.ZFBParBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.view.adapter.seatinfo_adapter.Schedule_Adapter;
import com.bw.movie.view.adapter.seatinfo_adapter.SeatInfo_Adapter;
import com.bw.movie.wxapi.WXUtils;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class SeatInfoActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private RecyclerView recycler_Schedule, recycler_view;
    private Button seat_save;
    private JCVideoPlayer videoPlayer_seat;
    private TextView text_zfb, text_wx;
    public static final int SDK_PAY_FLAG = 1;//原生端调用支付宝
    private double fare;
    private double price;
    private String str;
    private int num;
    private int id;
    private String s;

    //支付宝支付
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    //这里接收支付宝的回调信息
                    //需要注意的是，支付结果一定要调用自己的服务端来确定，不能通过支付宝的回调结果来判断
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        recycler_Schedule = findViewById(R.id.recycler_Schedule);
        recycler_view = findViewById(R.id.recycler_view);
        seat_save = findViewById(R.id.seat_save);
        videoPlayer_seat = findViewById(R.id.videoPlayer_seat);
    }

    @Override
    protected void initData() {
        seat_save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.onTickets(id, s);
                    }
                });
        Intent intent = getIntent();
        int cinema_id = intent.getIntExtra(Constant.CINEMAID, 0);
        mPresenter.onSchedule(cinema_id);
    }

    private void initPopupWindow(final String orderId) {
        View view = View.inflate(this, R.layout.popup_window_zhifu, null);
        text_wx = view.findViewById(R.id.text_wx);
        text_zfb = view.findViewById(R.id.text_zfb);

        final PopupWindow popupWindow = new PopupWindow(view, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT);

        text_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onWXPay(1, orderId);
                //点击选择完收回PopupWidow
                popupWindow.dismiss();
            }
        });
        text_zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onZFBPay(2, orderId);
                //点击选择完收回PopupWidow
                popupWindow.dismiss();
            }
        });

        //动画样式
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        //显示位置
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_seat_info;
    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(DetailBean detailBean) {
        DetailBean.ResultBean result = detailBean.getResult();
        List<DetailBean.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();
        String videoUrl = shortFilmList.get(0).getVideoUrl();
        videoPlayer_seat.setUp(videoUrl, "");
        ImageView ivThumb = videoPlayer_seat.ivThumb;
        Glide.with(this).load(result.getImageUrl()).into(ivThumb);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof ScheduleBean) {
            ScheduleBean bean = (ScheduleBean) obj;
            final List<ScheduleBean.ResultBean> list = bean.getResult();
            Schedule_Adapter schedule_adapter = new Schedule_Adapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recycler_Schedule.setLayoutManager(linearLayoutManager);
            recycler_Schedule.setAdapter(schedule_adapter);

            schedule_adapter.setOnItemHallId(new Schedule_Adapter.onItemHallId() {
                @Override
                public void onItemHallId(int position) {
                    mPresenter.onSeatInfo(list.get(position).getHallId());
                    id = list.get(position).getId();
                    fare = list.get(position).getFare();
                }
            });
        } else if (obj instanceof SeatInfoBean) {
            SeatInfoBean bean = (SeatInfoBean) obj;
            final List<SeatInfoBean.ResultBean> result = bean.getResult();
            SeatInfo_Adapter info_adapter = new SeatInfo_Adapter(result, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
            recycler_view.setLayoutManager(gridLayoutManager);
            recycler_view.setAdapter(info_adapter);

            info_adapter.setOnItemCheck(new SeatInfo_Adapter.onItemCheck() {
                @Override
                public void onPayON(String str) {
                    Toast.makeText(SeatInfoActivity.this, str, Toast.LENGTH_SHORT).show();
                    s = str;
                    for (int i = 0; i < result.size(); i++) {
                        if (result.get(i).getStatus() == 3) {
                            num++;
                        }
                    }

                    if (num > 5) {
                        Toast.makeText(SeatInfoActivity.this, "最多" + num + "张", Toast.LENGTH_SHORT).show();
                    } else {
                        if (num != 0) {
                            price = num * fare;
                            seat_save.setText(price + "");
                            num = 0;
                        }
                    }
                }

                @Override
                public void onPayOK(List<String> strings) {
                    str = "";
                    for (int i = 0; i < strings.size(); i++) {
                        if (str.length() == 0) {
                            str += strings.get(i);
                        } else {
                            str += "," + strings.get(i);
                        }
                    }
                    Log.i("qqq", "getStrng: " + str);
                }
            });
        } else if (obj instanceof TicketsBean) {
            TicketsBean bean = (TicketsBean) obj;
            String orderId = bean.getOrderId();
            initPopupWindow(orderId);
        } else if (obj instanceof WXPayBean) {
            WXPayBean bean = (WXPayBean) obj;
            if (bean.getStatus().equals("0000")) {
                PayReq req = new PayReq();
                req.appId = bean.getAppId();
                req.partnerId = bean.getPartnerId();
                req.prepayId = bean.getPrepayId();
                req.nonceStr = bean.getNonceStr();
                req.timeStamp = bean.getTimeStamp();
                req.packageValue = bean.getPackageValue();
                req.sign = bean.getSign();
                WXUtils.api.sendReq(req);//api为 IWXAPI
            }
        } else if (obj instanceof ZFBParBean) {
            final ZFBParBean bean = (ZFBParBean) obj;
            if (bean.getStatus().equals("0000")) {
                final Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(SeatInfoActivity.this);
                        Map<String, String> result = alipay.payV2(bean.getResult(), true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
