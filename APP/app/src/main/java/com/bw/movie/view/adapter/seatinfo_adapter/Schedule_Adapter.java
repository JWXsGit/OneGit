package com.bw.movie.view.adapter.seatinfo_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.view.SeatInfoActivity;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.seatinfo_adapter
 * @class describe
 * @anthor 24673
 * @time 2019/11/21 10:56
 * @change
 * @chang time
 * @class describe
 */
public class Schedule_Adapter extends RecyclerView.Adapter {
    private List<ScheduleBean.ResultBean> result;
    private Context context;
    private onItemHallId onItemHallId;

    public Schedule_Adapter(List<ScheduleBean.ResultBean> result, Context context) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.end_time.setText(result.get(position).getEndTime());
        holder.start_time.setText(result.get(position).getBeginTime());
        holder.schedule_name.setText(result.get(position).getScreeningHall());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemHallId != null) {
                    onItemHallId.onItemHallId(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView schedule_name, start_time, end_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            schedule_name = itemView.findViewById(R.id.schedule_name);
            start_time = itemView.findViewById(R.id.start_time);
            end_time = itemView.findViewById(R.id.end_time);
        }
    }

    public interface onItemHallId {
        void onItemHallId(int position);
    }

    public void setOnItemHallId(Schedule_Adapter.onItemHallId onItemHallId) {
        this.onItemHallId = onItemHallId;
    }
}
