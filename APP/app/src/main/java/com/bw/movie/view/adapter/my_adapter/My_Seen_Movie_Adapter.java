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
import com.bw.movie.bean.Seen_MovieBean;
import com.bw.movie.bean.TicketBean;
import com.bw.movie.view.my_activity.Seen_MovieActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class My_Seen_Movie_Adapter extends RecyclerView.Adapter {
    List<Seen_MovieBean.ResultBean> result;
    Context context;


    public My_Seen_Movie_Adapter(List<Seen_MovieBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_seenmovie_guanzhu, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.seen_src.setImageURI(result.get(position).getImageUrl());
        myViewHolder.seen_name.setText(result.get(position).getMovieName());

        //设置时间戳
        long time = result.get(position).getEndTime();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:MM:SS" + "观影");
        String str = format.format(date);
        myViewHolder.seen_time.setText(str);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView seen_src;
        private TextView seen_time, seen_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seen_src = itemView.findViewById(R.id.seen_src);
            seen_time = itemView.findViewById(R.id.seen_time);
            seen_name = itemView.findViewById(R.id.seen_name);
        }
    }
}
