package com.js.retrofitdownload.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.js.retrofitdownload.R;
import com.js.retrofitdownload.base.BaseLazyLoadFragment;

public class FragmentLazyLoad extends BaseLazyLoadFragment {
    SimpleDraweeView simpleDraweeView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lazyload, container, false);
        simpleDraweeView = view.findViewById(R.id.simpleDraweeView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void showPic() {
        //以下渐进式加载
        //网络图片资源
        Uri uri = Uri.parse("http://172.17.8.100/images/small/default/user.jpg");
        //设置渐进渲染已启用
        ImageRequest build = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true).build();

        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(build)
                .build();
        simpleDraweeView.setController(controller);
    }

    @Override
    public void initData() {
        Log.w("dj", "onLazyLoad");
        showPic();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("dj", "onResume");
    }
}
