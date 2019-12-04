package com.bw.movie.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.KeyWorkBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.view.adapter.Atcivity_Key_Adapter;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;

import java.util.List;

public class ByKeywordActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private RecyclerView recycler_view;
    private EditText edit_key;
    private ImageView image_finish, image_home_search;
    private LinearLayout lin, lin2;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        image_finish = findViewById(R.id.image_finish);
        edit_key = findViewById(R.id.edit_key);
        recycler_view = findViewById(R.id.recycler_view);
        image_home_search = findViewById(R.id.image_home_search);
        lin = findViewById(R.id.lin);
        lin2 = findViewById(R.id.lin2);
    }

    @Override
    protected void initData() {
        image_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String edit = edit_key.getText().toString();
        mPresenter.onKeyWord(edit, 0, 0);
        edit_key.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = edit_key.getText().toString().trim();
                mPresenter.onKeyWord(trim, 1, 5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_by_keyword;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        KeyWorkBean bean = (KeyWorkBean) obj;
        List<KeyWorkBean.ResultBean> result = bean.getResult();
        if (result != null) {
            lin.setVisibility(View.VISIBLE);
            lin2.setVisibility(View.GONE);
            Atcivity_Key_Adapter key_adapter = new Atcivity_Key_Adapter(result, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycler_view.setLayoutManager(linearLayoutManager);
            recycler_view.setAdapter(key_adapter);
        } else {
            lin2.setVisibility(View.VISIBLE);
            lin.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
