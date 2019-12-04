package com.bw.movie.view.my_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.WX_LoginBean;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.utils.ImageUtils;
import com.example.my_utils_library.base.BaseActivity;
import com.example.my_utils_library.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class My_ResultActivity extends BaseActivity<MyPresenter> implements Contract.IView {
    private SimpleDraweeView head_pic;
    private TextView text_birthday, text_sex, text_nickName;

    @Override
    protected MyPresenter providePresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        text_birthday = findViewById(R.id.text_birthday);
        text_sex = findViewById(R.id.text_sex);
        text_nickName = findViewById(R.id.text_nickName);
        head_pic = findViewById(R.id.head_pic);
    }

    @Override
    protected void initData() {
        head_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
            }
        });
        Intent intent = getIntent();
        String image = intent.getStringExtra(Constant.WX_PIC);
        String name = intent.getStringExtra(Constant.WX_NAME);
        int sex = intent.getIntExtra(Constant.WX_SEX, 0);
        long longExtra = intent.getLongExtra(Constant.WX_BIRTHDAY, 0);

        Glide.with(this).load(image).apply(new RequestOptions().circleCrop()).into(head_pic);
        text_nickName.setText(name);
        Date date = new Date(longExtra);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String time = format.format(date);
        text_birthday.setText(time);
        if (sex == 1) {
            text_sex.setText("男");
        } else {
            text_sex.setText("女");
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_my__result;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {

    }

    @Override
    public void onError(Throwable e) {

    }

    private void initPopupWindow() {
        View view = View.inflate(this, R.layout.popup_window_image, null);
        TextView text_Gallery = view.findViewById(R.id.text_Gallery);
        TextView text_camera = view.findViewById(R.id.text_camera);

        final PopupWindow popupWindow = new PopupWindow(view, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT);

        //打开图库
        text_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过隐式意图跳转到相册
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                //设置结果码
                startActivityForResult(intent, 100);
                popupWindow.dismiss();
            }
        });
        //打开相机
        text_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 300);
                popupWindow.dismiss();
            }
        });
        //动画样式
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        //显示位置
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (data.getData() != null) {
                //添加裁剪权限
                Intent intent = new Intent("com.android.camera.action.CROP");
                Uri uri = data.getData();
                //设置数据和类型
                intent.setDataAndType(uri, "image/*");
                //支持可裁剪
                intent.putExtra("crop", "true");
                //设置裁剪比例
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                //设置裁剪图片大小
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                //设置图片格式
                intent.putExtra("outputFormat", "JPEG");
                //返回数据   必须写入
                intent.putExtra("return-data", true);
                getFile(data.getData());
                //把相册获取的图片跳转传值到裁剪
                startActivityForResult(intent, 200);
            }
        } else if (requestCode == 200) {
            Bitmap bitmap = data.getParcelableExtra("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes = baos.toByteArray();
            Glide.with(this).load(bytes).apply(new RequestOptions().circleCrop()).into(head_pic);
        } else if (requestCode == 300) {
            Bitmap bitmap = data.getParcelableExtra("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes = baos.toByteArray();
            Glide.with(this).load(bytes).apply(new RequestOptions().circleCrop()).into(head_pic);
        }
    }

    //在这里抽取了一个方法   可以封装到自己的工具类中...
    public void getFile(Uri uri) {
        if (uri != null) {
            String path = ImageUtils.getRealPathFromUri(this, uri);
            //添加到File文件中
            File file = new File(path);
            //添加到请求正文中
            RequestBody requestBody = MultipartBody.create(MediaType.parse("image/*"), file);
            //添加请求部分
            MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            mPresenter.onImage(part);
        }
    }
}
