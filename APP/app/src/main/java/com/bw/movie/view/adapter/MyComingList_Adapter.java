package com.bw.movie.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.fragment_bean.ComingBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：86157<p>
 * <p>创建时间：2019/10/10<p>
 * <p>更改时间：2019/10/10<p>
 */
public class MyComingList_Adapter extends XRecyclerView.Adapter<MyComingList_Adapter.MyViewHolder> {
    private Context context;
    private List<ComingBean.ResultBean> list;

    public MyComingList_Adapter(Context context, List<ComingBean.ResultBean> result) {
        this.context = context;
        this.list = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_coming, null, true);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder Myholder, int position) {
        MyViewHolder holder = Myholder;
        //设置电影名称
        holder.coming_text_name.setText(list.get(position).getName());
        //设置时间戳
        long time = list.get(position).getReleaseTime();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        String str = format.format(date);
        holder.coming_text_time.setText(str + "上映");
        //设置观看人数
        holder.coming_text_num.setText(list.get(position).getWantSeeNum() + "人想看");
        //设置电影图片
        holder.coming_image.setImageURI(list.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends XRecyclerView.ViewHolder {
        private TextView coming_text_name, coming_text_time, coming_text_num;
        private SimpleDraweeView coming_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coming_text_name = itemView.findViewById(R.id.coming_text_name);
            coming_text_time = itemView.findViewById(R.id.coming_text_time);
            coming_text_num = itemView.findViewById(R.id.coming_text_num);
            coming_image = itemView.findViewById(R.id.coming_image);
        }
    }
}
