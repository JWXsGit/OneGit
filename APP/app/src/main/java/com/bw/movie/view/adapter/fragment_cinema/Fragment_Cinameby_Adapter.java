package com.bw.movie.view.adapter.fragment_cinema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.fragment_bean.Cinema_byBean;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.fragment_cinema
 * @class describe
 * @anthor 24673
 * @time 2019/11/13 20:39
 * @change
 * @chang time
 * @class describe
 */
public class Fragment_Cinameby_Adapter extends RecyclerView.Adapter {
    private List<Cinema_byBean.ResultBean> list;
    private Context context;
    private onCinemaID onCinemaID;

    public Fragment_Cinameby_Adapter(List<Cinema_byBean.ResultBean> result, Context context) {
        this.context = context;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cinema, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.text_cinema.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCinemaID != null) {
                    onCinemaID.onCinema(list.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text_cinema;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_cinema = itemView.findViewById(R.id.text_cinema);
        }
    }

    public interface onCinemaID {
        void onCinema(int id);
    }

    public void setOnCinemaID(Fragment_Cinameby_Adapter.onCinemaID onCinemaID) {
        this.onCinemaID = onCinemaID;
    }
}
