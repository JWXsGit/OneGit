package com.bw.movie.view.adapter.detail_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.DetailBean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：86157<p>
 * <p>创建时间：2019/10/19<p>
 * <p>更改时间：2019/10/19<p>
 */
public class Director_Adapter extends RecyclerView.Adapter {
    private List<DetailBean.ResultBean.MovieDirectorBean> list;
    private Context context;

    public Director_Adapter(List<DetailBean.ResultBean.MovieDirectorBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.director_item, null, true);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.director_item_name.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getPhoto()).into(holder.director_item_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView director_item_image;
        private TextView director_item_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            director_item_image = itemView.findViewById(R.id.director_item_image);
            director_item_name = itemView.findViewById(R.id.director_item_name);
        }
    }
}
