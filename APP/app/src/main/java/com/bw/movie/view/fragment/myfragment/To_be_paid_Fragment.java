package com.bw.movie.view.fragment.myfragment;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Follow_CinemaBean;
import com.bw.movie.bean.TicketBean;
import com.bw.movie.presenter.Fragment_Presenter;
import com.bw.movie.view.adapter.my_adapter.My_Ticket_Adapter;
import com.bw.movie.view.adapter.my_adapter.My_cinema_Adapter;
import com.example.my_utils_library.base.BaseFragment;
import com.example.my_utils_library.contract.Contract;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.fragment
 * @class describe
 * @anthor 24673
 * @time 2019/12/2 19:39
 * @change
 * @chang time
 * @class describe
 */
public class To_be_paid_Fragment extends BaseFragment<Fragment_Presenter> implements Contract.IView {
    private RecyclerView cinema_recycler;
    private RelativeLayout lin_visi;

    @Override
    protected Fragment_Presenter providePresenter() {
        return new Fragment_Presenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.my_cinema_fragment;
    }

    @Override
    protected void initView(View view) {
        cinema_recycler = view.findViewById(R.id.cinema_recycler);
        lin_visi = view.findViewById(R.id.lin_visi);
    }

    @Override
    protected void initData() {
        mPresenter.onTicket(1,5,1);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSuccess(Object obj) {
        TicketBean bean = (TicketBean) obj;
        List<TicketBean.ResultBean> result = bean.getResult();
        if (result != null) {
            cinema_recycler.setVisibility(View.VISIBLE);
            lin_visi.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
            cinema_recycler.setLayoutManager(linearLayoutManager);
            My_Ticket_Adapter guan_cinema_adapter = new My_Ticket_Adapter(result, getActivity());
            cinema_recycler.setAdapter(guan_cinema_adapter);
        } else {
            cinema_recycler.setVisibility(View.GONE);
            lin_visi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
