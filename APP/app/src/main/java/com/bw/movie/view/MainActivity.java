package com.bw.movie.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.APP;
import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.WX_LoginBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.utils.EmailUtils;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.wxapi.WXUtils;
import com.example.my_utils_library.app.App;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.example.my_utils_library.utils.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.bw.movie.wxapi.WXUtils.api;

public class MainActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private Button bt_login, wx_login, bt_shear;
    private TextView text_register;
    private EditText edit_pwd, edit_email;

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
        edit_email = findViewById(R.id.edit_email);
        edit_pwd = findViewById(R.id.edit_pwd);
        text_register = findViewById(R.id.text_register);
        bt_login = findViewById(R.id.bt_login);
        wx_login = findViewById(R.id.wx_login);
        bt_shear = findViewById(R.id.bt_shear);
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences(Constant.SP, MODE_PRIVATE);
        String email = sp.getString(Constant.SP_EMAIL, "");
        String pwd = sp.getString(Constant.SP_PWD, "");
        edit_email.setText(email);
        edit_pwd.setText(pwd);
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString();
                String pwd_text = edit_pwd.getText().toString();
                if (EmailUtils.isEmail(email)) {
                    if (pwd_text.isEmpty()) {
                        ToastUtils.show("密码不能为空");
                    } else {
                        String pwd = EncryptUtil.encrypt(pwd_text);
                        mPresenter.onLogin(email, pwd);
                        SharedPreferences sp = getSharedPreferences(Constant.SP, MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putString(Constant.SP_EMAIL, email);
                        edit.putString(Constant.SP_PWD, pwd_text);
                        edit.commit();
                    }
                } else {
                    ToastUtils.show("邮箱格式错误");
                }
            }
        });
        wx_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!api.isWXAppInstalled()) {
                    Toast.makeText(MainActivity.this, "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    final SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    api.sendReq(req);
                }
            }
        });
        bt_shear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //初始化一个 WXTextObject 对象，填写分享的文本内容
                WXTextObject textObj = new WXTextObject();
                textObj.text = "1561516";

                //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObj;
                msg.description = "1";

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());  //transaction字段用与唯一标示一个请求
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;

                //调用api接口，发送数据到微信
                WXUtils.api.sendReq(req);
            }
        });
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof LoginBean) {
            LoginBean bean = (LoginBean) obj;
            if (bean.getStatus().equals("0000")) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra(Constant.USER_ID, bean.getResult().getUserId());
                intent.putExtra(Constant.SESSION_ID, bean.getResult().getSessionId());
                startActivity(intent);
            }
        } else if (obj instanceof WX_LoginBean) {
            WX_LoginBean bean = (WX_LoginBean) obj;
            if (bean.getStatus().equals("0000")) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra(Constant.USER_ID, bean.getResult().getUserId());
                intent.putExtra(Constant.SESSION_ID, bean.getResult().getSessionId());
                EventBus.getDefault().postSticky(bean);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWX(String code) {
        mPresenter.onWX_Login(code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
