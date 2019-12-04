package com.bw.movie.view.adapter.my_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.ExChanegBean;
import com.bw.movie.bean.My_MovieBean;
import com.bw.movie.view.my_activity.My_MovieActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.my_adapter
 * @class describe
 * @anthor 24673
 * @time 2019/12/2 14:09
 * @change
 * @chang time
 * @class describe
 */
public class My_Movie_Adapter extends RecyclerView.Adapter {
    private List<My_MovieBean.ResultBean> result;
    private Context context;
    private Date date;
    private onRecordId onRecordId;

    public My_Movie_Adapter(List<My_MovieBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.text_cinema.setText(result.get(position).getCinemaName());
        holder.text_name.setText(result.get(position).getMovieName());
        holder.text_screeningHall.setText(result.get(position).getScreeningHall());
        holder.text_zuo.setText(result.get(position).getSeat());
        //设置时间戳
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        long time = result.get(position).getCreateTime();
        date = new Date(time);
        String str = format.format(date);
        holder.text_date.setText(str);

        String beginTime = result.get(position).getBeginTime();
        holder.text_time.setText(beginTime);

        holder.bt_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecordId != null) {
                    onRecordId.onRecordId(result.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button bt_reservation;
        private TextView text_time, text_date, text_screeningHall, text_cinema, text_zuo, text_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bt_reservation = itemView.findViewById(R.id.bt_reservation);
            text_time = itemView.findViewById(R.id.text_time);
            text_date = itemView.findViewById(R.id.text_date);
            text_screeningHall = itemView.findViewById(R.id.text_screeningHall);
            text_cinema = itemView.findViewById(R.id.text_cinema);
            text_zuo = itemView.findViewById(R.id.text_zuo);
            text_name = itemView.findViewById(R.id.text_name);
        }
    }

    public interface onRecordId {
        void onRecordId(int id);
    }

    public void setOnRecordId(My_Movie_Adapter.onRecordId onRecordId) {
        this.onRecordId = onRecordId;
    }
}
