package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.WX_LoginBean;
import com.bw.movie.bean.eventbus_bean.ID_Bean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.my_activity.AttentionActivity;
import com.bw.movie.view.my_activity.CommentActivity;
import com.bw.movie.view.ReserveActivity;
import com.bw.movie.view.my_activity.My_ResultActivity;
import com.bw.movie.view.my_activity.Buy_ticketsActivity;
import com.bw.movie.view.my_activity.FeedbackActivity;
import com.bw.movie.view.my_activity.My_MovieActivity;
import com.bw.movie.view.my_activity.Seen_MovieActivity;
import com.bw.movie.view.my_activity.SettingsActivity;
import com.example.my_utils_library.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.jessyan.autosize.AutoSizeConfig;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 21:32
 * @change
 * @chang time
 * @class describe
 */
public class Fragment_My extends BaseFragment<Fragment_Presenter> implements View.OnClickListener {
    private ImageView image_copy1, my_settings, image_pic, Buy_tickets, Seen_Movie, Feedback, image_reserve, comment;
    private int userId;
    private String sessionId;
    private static final String TAG = "Fragment_My";
    private TextView text_Gallery, text_camera;
    private RelativeLayout my_movie;
    private RelativeLayout my_r1;
    private String nickName;
    private String headPic;
    private int sex;
    private long birthday;
    private WX_LoginBean.ResultBean.UserInfoBean userInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoSizeConfig.getInstance().setCustomFragment(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected Fragment_Presenter providePresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        my_settings = view.findViewById(R.id.my_settings);
        image_pic = view.findViewById(R.id.image_pic);
        my_movie = view.findViewById(R.id.my_r2);
        image_copy1 = view.findViewById(R.id.image_copy1);
        Buy_tickets = view.findViewById(R.id.Buy_tickets);
        Seen_Movie = view.findViewById(R.id.Seen_Movie);
        Feedback = view.findViewById(R.id.Feedback);
        my_r1 = view.findViewById(R.id.my_r1);
        comment = view.findViewById(R.id.comment);
        image_reserve = view.findViewById(R.id.image_reserve);
    }

    @Override
    protected void initData() {
        //设置
        my_settings.setOnClickListener(this);
        //电影票
        my_movie.setOnClickListener(this);
        //我的关注
        image_copy1.setOnClickListener(this);
        //购票记录
        Buy_tickets.setOnClickListener(this);
        //看过的电影
        Seen_Movie.setOnClickListener(this);
        //意见反馈
        Feedback.setOnClickListener(this);
        //我的信息
        my_r1.setOnClickListener(this);
        //我的预约
        image_reserve.setOnClickListener(this);
        //我的评论
        comment.setOnClickListener(this);
    }

    @Override
    public Context context() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void ID_Data(ID_Bean id_bean) {
        sessionId = id_bean.getSessionId();
        userId = id_bean.getUserId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_settings:
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                intent.putExtra(Constant.USER_ID, userId);
                intent.putExtra(Constant.SESSION_ID, sessionId);
                startActivity(intent);
                break;
            case R.id.my_r2:
                startActivity(new Intent(getContext(), My_MovieActivity.class));
                break;
            case R.id.image_copy1:
                startActivity(new Intent(getContext(), AttentionActivity.class));
                break;
            case R.id.Buy_tickets:
                startActivity(new Intent(getContext(), Buy_ticketsActivity.class));
                break;
            case R.id.Seen_Movie:
                startActivity(new Intent(getContext(), Seen_MovieActivity.class));
                break;
            case R.id.Feedback:
                startActivity(new Intent(getContext(), FeedbackActivity.class));
                break;
            case R.id.image_reserve:
                startActivity(new Intent(getContext(), ReserveActivity.class));
                break;
            case R.id.comment:
                startActivity(new Intent(getContext(), CommentActivity.class));
                break;
            case R.id.my_r1:
                intent = new Intent(getContext(), My_ResultActivity.class);
                intent.putExtra(Constant.WX_PIC, headPic);
                intent.putExtra(Constant.WX_NAME, nickName);
                intent.putExtra(Constant.WX_SEX, sex);
                intent.putExtra(Constant.WX_BIRTHDAY, birthday);
                intent.putExtra(Constant.USER_ID, userId);
                intent.putExtra(Constant.SESSION_ID, sessionId);
                startActivity(intent);
                break;
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onWXResult(WX_LoginBean bean) {
        userId = bean.getResult().getUserId();
        sessionId = bean.getResult().getSessionId();
        headPic = bean.getResult().getUserInfo().getHeadPic();
        nickName = bean.getResult().getUserInfo().getNickName();
        birthday = bean.getResult().getUserInfo().getBirthday();
        sex = bean.getResult().getUserInfo().getSex();
        Glide.with(getContext()).load(headPic).apply(new RequestOptions().circleCrop()).into(image_pic);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
