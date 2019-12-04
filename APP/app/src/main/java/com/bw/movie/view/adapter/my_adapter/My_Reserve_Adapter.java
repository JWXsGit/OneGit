package com.bw.movie.view.adapter.my_adapter;
/*
 *@auther:张安恒
 *@Date: 2019/12/2
 *@Time:19:00
 *@Description:${DESCRIPTION}
 **/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Find_UserBean;
import com.bw.movie.bean.Follow_CinemaBean;
import com.bw.movie.view.ReserveActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class My_Reserve_Adapter extends RecyclerView.Adapter {
    List<Find_UserBean.ResultBean> result;
    Context context;


    public My_Reserve_Adapter(List<Find_UserBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_reserve, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.notice_img.setImageURI(result.get(i).getImageUrl());
        myViewHolder.notice_tv.setText(result.get(i).getName());
        myViewHolder.notice_proper.setText(result.get(i).getWantSeeNum() + "人想看");

        //设置时间戳
        long time = result.get(i).getReleaseTime();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String str = format.format(date);
        myViewHolder.notice_time.setText(str);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView notice_img;
        private TextView notice_proper, notice_time, notice_tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            notice_img = itemView.findViewById(R.id.notice_img);
            notice_proper = itemView.findViewById(R.id.notice_proper);
            notice_time = itemView.findViewById(R.id.notice_time);
            notice_tv = itemView.findViewById(R.id.notice_tv);
        }
    }
}
