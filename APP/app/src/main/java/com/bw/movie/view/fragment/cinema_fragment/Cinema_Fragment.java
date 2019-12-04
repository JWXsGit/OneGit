package com.bw.movie.view.fragment.cinema_fragment;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.fragment_bean.Cinema_byBean;
import com.bw.movie.bean.fragment_bean.RegionBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.CinameInfoActivity;
import com.bw.movie.view.adapter.fragment_cinema.Fragment_Cinameby_Adapter;
import com.bw.movie.view.adapter.fragment_cinema.Fragment_Region_Adapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment.cinema_fragment
 * @class describe
 * @anthor 24673
 * @time 2019/11/13 19:32
 * @change
 * @chang time
 * @class describe
 */
public class Cinema_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView recycler_region;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_cinema_by;
    }

    @Override
    protected void initView(View view) {
        recycler_region = view.findViewById(R.id.recycler_region);
    }

    @Override
    protected void initData() {
        mPresenter.onRegion();
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        if (obj instanceof RegionBean) {
            RegionBean bean = (RegionBean) obj;
            List<RegionBean.ResultBean> list = bean.getResult();
            Fragment_Region_Adapter adapter = new Fragment_Region_Adapter(list, getActivity());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycler_region.setLayoutManager(linearLayoutManager);
            recycler_region.setAdapter(adapter);
            adapter.setOnRegion_id(new Fragment_Region_Adapter.onRegion_ID() {
                @Override
                public void onRegionId(int id) {
                    mPresenter.onCinema_by(id);
                }
            });
        } else if (obj instanceof Cinema_byBean) {
            Cinema_byBean bean = (Cinema_byBean) obj;
            if (bean.getStatus().equals("0000")) {
                initPopupWindow(bean);
            }
        }
    }


    private void initPopupWindow(Cinema_byBean bean) {
        RecyclerView recycler_cinema;
        View view = View.inflate(getContext(), R.layout.popup_window, null);
        recycler_cinema = view.findViewById(R.id.recycler_cinema);
        view.setAlpha(0.9f);

        final PopupWindow popupWindow = new PopupWindow(view, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);      //必须设置背景 即使为null或者透明 否则点击外部不会消失
        popupWindow.setBackgroundDrawable(null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        List<Cinema_byBean.ResultBean> result = bean.getResult();
        Fragment_Cinameby_Adapter adapter = new Fragment_Cinameby_Adapter(result, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_cinema.setLayoutManager(linearLayoutManager);
        recycler_cinema.setAdapter(adapter);
        adapter.setOnCinemaID(new Fragment_Cinameby_Adapter.onCinemaID() {
            @Override
            public void onCinema(int id) {
                Intent intent = new Intent(getContext(), CinameInfoActivity.class);
                intent.putExtra(Constant.CINEMAID, id);
                startActivity(intent);
            }
        });

        //动画样式
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        //显示位置
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onError(Throwable e) {

    }
}
