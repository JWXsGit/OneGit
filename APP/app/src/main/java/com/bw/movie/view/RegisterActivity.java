package com.bw.movie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.CodeBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.utils.EmailUtils;
import com.bw.movie.utils.EncryptUtil;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.example.my_utils_library.utils.ToastUtils;

public class RegisterActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private EditText edit_email, edit_pwd, edit_name, edit_code;
    private Button button_register, button_code;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        edit_code = findViewById(R.id.edit_code);
        edit_email = findViewById(R.id.edit_email);
        edit_pwd = findViewById(R.id.edit_pwd);
        edit_name = findViewById(R.id.edit_name);
        button_register = findViewById(R.id.button_register);
        button_code = findViewById(R.id.button_code);
    }

    @Override
    protected void initData() {
        button_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString();
                if (edit_email != null) {
                    boolean email1 = EmailUtils.isEmail(email);
                    if (email1) {
                        mPresenter.onCode(email);
                    } else {
                        Toast.makeText(RegisterActivity.this, "邮箱格式错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString();
                if (name != null) {
                    String pwd = edit_pwd.getText().toString();
                    if (pwd != null) {
                        String email = edit_email.getText().toString();
                        if (email != null) {
                            boolean email1 = EmailUtils.isEmail(email);
                            if (email1) {
                                String code = edit_code.getText().toString();
                                if (code != null) {
                                    String encrypt = EncryptUtil.encrypt(pwd);
                                    mPresenter.onRegister(name, encrypt, email, code);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "验证码不可为空", Toast.LENGTH_SHORT).show();
                                    ToastUtils.show("验证码不可为空");
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "邮箱格式错误", Toast.LENGTH_SHORT).show();
                                ToastUtils.show("邮箱格式错误");
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                            ToastUtils.show("邮箱不能为空");
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "密码不可为空", Toast.LENGTH_SHORT).show();
                        ToastUtils.show("密码不可为空");
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "昵称不可为空", Toast.LENGTH_SHORT).show();
                    ToastUtils.show("昵称不可为空");
                }
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof CodeBean) {
            CodeBean bean = (CodeBean) obj;
            if (bean.getStatus().equals("0000")) {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (obj instanceof RegisterBean) {
            RegisterBean bean = (RegisterBean) obj;
            if (bean.getStatus().equals("0000")) {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
