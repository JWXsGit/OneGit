package com.bw.movie.view.adapter.detail_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.DetailBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * <p>文件描述：<p>
 * <p>作者：86157<p>
 * <p>创建时间：2019/10/22<p>
 * <p>更改时间：2019/10/22<p>
 */
public class TrailNotice_Adapter extends RecyclerView.Adapter {
    private List<DetailBean.ResultBean.ShortFilmListBean> list;
    private Context context;

    public TrailNotice_Adapter(List<DetailBean.ResultBean.ShortFilmListBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trail, null, true);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.videoPlayer.setUp(list.get(position).getVideoUrl(), "");
        ImageView ivThumb = holder.videoPlayer.ivThumb;
        Glide.with(context).load(list.get(position).getImageUrl()).into(ivThumb);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public static JCVideoPlayer videoPlayer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoPlayer = itemView.findViewById(R.id.videoPlayer);
        }
    }

    public void VideoRemove() {
        MyViewHolder.videoPlayer.releaseAllVideos();
    }
}
