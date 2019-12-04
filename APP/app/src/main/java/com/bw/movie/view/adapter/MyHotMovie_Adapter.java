package com.bw.movie.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.fragment_bean.HotMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：86157<p>
 * <p>创建时间：2019/10/10<p>
 * <p>更改时间：2019/10/10<p>
 */
public class MyHotMovie_Adapter extends RecyclerView.Adapter {
    private Context context;
    private List<HotMovieBean.ResultBean> list;

    public MyHotMovie_Adapter(Context context, List<HotMovieBean.ResultBean> result) {
        this.list = result;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = null;
        if (position == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_hot_movie_one, null);
            viewHolder = new MyViewHolder01(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_hot_movie_two, null);
            viewHolder = new MyViewHolder02(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            MyViewHolder01 viewHolder01 = (MyViewHolder01) holder;
            viewHolder01.hot_tv_name_one.setText(list.get(position).name);
            viewHolder01.hot_tv_score_one.setText(list.get(position).score + "分");
            viewHolder01.hot_image_pic_one.setImageURI(list.get(position).imageUrl);
        } else {
            MyViewHolder02 viewHolder02 = (MyViewHolder02) holder;
            viewHolder02.hot_tv_movieName_two.setText(list.get(position).name);
            viewHolder02.hot_tv_score_two.setText(list.get(position).score + "分");
            viewHolder02.hot_image_two.setImageURI(list.get(position).imageUrl);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder01 extends RecyclerView.ViewHolder {
        private TextView hot_tv_name_one, hot_tv_score_one;
        private SimpleDraweeView hot_image_pic_one;
        private Button bt_hot_movie_one;

        public MyViewHolder01(@NonNull View itemView) {
            super(itemView);
            hot_tv_name_one = itemView.findViewById(R.id.hot_tv_name_one);
            hot_tv_score_one = itemView.findViewById(R.id.hot_tv_score_one);
            hot_image_pic_one = itemView.findViewById(R.id.hot_image_pic_one);
            bt_hot_movie_one = itemView.findViewById(R.id.bt_hot_movie_one);
        }
    }

    public class MyViewHolder02 extends RecyclerView.ViewHolder {
        private TextView hot_tv_movieName_two, hot_tv_score_two;
        private SimpleDraweeView hot_image_two;
        private Button hot_bt_two;

        public MyViewHolder02(@NonNull View itemView) {
            super(itemView);
            hot_tv_movieName_two = itemView.findViewById(R.id.hot_tv_movieName_two);
            hot_tv_score_two = itemView.findViewById(R.id.hot_tv_score_two);
            hot_image_two = itemView.findViewById(R.id.hot_image_two);
            hot_bt_two = itemView.findViewById(R.id.hot_bt_two);
        }
    }
}
