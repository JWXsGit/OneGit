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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Follow_CinemaBean;
import com.bw.movie.bean.Follow_MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class My_TheFilm_Adapter extends RecyclerView.Adapter {
    List<Follow_MovieBean.ResultBean> result;
    Context context;

    public My_TheFilm_Adapter(List<Follow_MovieBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_thefilm, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.movie_src.setImageURI(result.get(i).getImageUrl());
        holder.movie_name.setText(result.get(i).getName());
        holder.movie_dao.setText("导演:" + result.get(i).getDirector());
        holder.movie_zhu.setText("主演:" + result.get(i).getStarring());
        holder.movie_pin.setText("评分:" + result.get(i).getScore() + "分");
        holder.guan_tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPosition.onPosition(i);
            }
        });
    }

    public void removeData(int i) {
        result.remove(i);
        //删除动画
        notifyItemRemoved(i);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView movie_src;
        private final TextView movie_name, movie_dao, movie_zhu, movie_pin, guan_tv_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_src = itemView.findViewById(R.id.guan_src);
            movie_name = itemView.findViewById(R.id.guan_name);
            movie_dao = itemView.findViewById(R.id.guan_dao);
            movie_zhu = itemView.findViewById(R.id.guan_zhu);
            movie_pin = itemView.findViewById(R.id.guan_pin);
            guan_tv_delete = itemView.findViewById(R.id.guan_tv_delete);
        }
    }

    public onPosition onPosition;

    public interface onPosition {
        void onPosition(int position);
    }

    public void setOnPosition(My_TheFilm_Adapter.onPosition onPosition) {
        this.onPosition = onPosition;
    }
}
