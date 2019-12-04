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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Cinema_ListBean;
import com.bw.movie.bean.Movie_ListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;


public class Movie_review_Adapter extends RecyclerView.Adapter {
    List<Movie_ListBean.ResultBean> result;
    Context context;

    public Movie_review_Adapter(List<Movie_ListBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_moviediscuss, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder movieViewHolder = (MyViewHolder) viewHolder;
        movieViewHolder.imag_view.setImageURI(result.get(i).getImageUrl());
        movieViewHolder.text_name.setText(result.get(i).getMovieName());
        movieViewHolder.text_director.setText("导演: " + result.get(i).getDirector());
        movieViewHolder.text_starring.setText("主演: " + result.get(i).getStarring());
        movieViewHolder.text_score.setText("评分: " + result.get(i).getMovieScore() + "分");
        movieViewHolder.text_discuss_name.setText("我的评价");
        movieViewHolder.text_discuss_content.setText(result.get(i).getMyCommentContent());
        movieViewHolder.filmReview_ratingBar.setRating((float) result.get(i).getMyCommentScore());
        movieViewHolder.text_discuss_score.setText(result.get(i).getMyCommentScore() + "分");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = formatter.format(result.get(i).getCommentTime());
        movieViewHolder.text_discuss_time.setText(format);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView text_discuss_time, text_discuss_score, text_discuss_content, text_discuss_name, text_score, text_starring, text_director, text_name;
        private LinearLayout linear_layout;
        private SimpleDraweeView imag_view;
        private RatingBar filmReview_ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_discuss_time = itemView.findViewById(R.id.text_discuss_time);
            text_discuss_score = itemView.findViewById(R.id.text_discuss_score);
            text_discuss_content = itemView.findViewById(R.id.text_discuss_content);
            text_discuss_name = itemView.findViewById(R.id.text_discuss_name);
            text_score = itemView.findViewById(R.id.text_score);
            text_starring = itemView.findViewById(R.id.text_starring);
            text_director = itemView.findViewById(R.id.text_director);
            text_name = itemView.findViewById(R.id.text_name);
            imag_view = itemView.findViewById(R.id.imag_view);
            filmReview_ratingBar = itemView.findViewById(R.id.filmReview_ratingBar);
        }
    }
}
