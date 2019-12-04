package com.bw.movie.view.adapter.detail_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.willy.ratingbar.ScaleRatingBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.detail_adapter
 * @class describe
 * @anthor 24673
 * @time 2019/11/19 19:12
 * @change
 * @chang time
 * @class describe
 */
public class MovieComment_Adapter extends RecyclerView.Adapter {
    private List<MovieBean.ResultBean> list;
    private Context context;

    public MovieComment_Adapter(List<MovieBean.ResultBean> result, Context context) {
        this.context = context;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_moviecomment, parent, false);
        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyVieHolder holder = (MyVieHolder) viewHolder;
        holder.simple_image.setImageURI(list.get(position).getCommentHeadPic());
        holder.text_name.setText(list.get(position).getCommentUserName());
        holder.text_score.setText(list.get(position).getScore() + "分");
        holder.text_num.setText(list.get(position).getCommentContent());

        //设置时间戳
        long time = list.get(position).getCommentTime();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String str = format.format(date);
        holder.text_time.setText(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVieHolder extends RecyclerView.ViewHolder {
        private TextView text_time, text_score, text_name,text_num;
        private ScaleRatingBar scale;
        private SimpleDraweeView simple_image;

        public MyVieHolder(@NonNull View itemView) {
            super(itemView);
            simple_image = itemView.findViewById(R.id.simple_image);
            text_time = itemView.findViewById(R.id.text_time);
            text_score = itemView.findViewById(R.id.text_score);
            text_name = itemView.findViewById(R.id.text_name);
            text_num = itemView.findViewById(R.id.text_num);
        }
    }
}
