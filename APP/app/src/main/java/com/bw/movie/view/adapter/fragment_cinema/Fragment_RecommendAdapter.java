package com.bw.movie.view.adapter.fragment_cinema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.fragment_bean.NearbyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.fragment_cinema
 * @class describe
 * @anthor 24673
 * @time 2019/11/14 20:05
 * @change
 * @chang time
 * @class describe
 */
public class Fragment_RecommendAdapter extends RecyclerView.Adapter {
    private List<NearbyBean.ResultBean> list;
    private Context context;
    private onItemView onItemView;

    public Fragment_RecommendAdapter(List<NearbyBean.ResultBean> result, Context context) {
        this.context = context;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_near, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.item_cinema_address.setText(list.get(position).getAddress());
        holder.item_cinema_name.setText(list.get(position).getName());
        holder.item_cinema_meter.setText(list.get(position).getDistance() + "");
        holder.item_cinema_image.setImageURI(list.get(position).getLogo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemView != null) {
                    onItemView.onItemView(list.get(position).getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView item_cinema_image;
        private TextView item_cinema_name, item_cinema_address, item_cinema_meter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_cinema_meter = itemView.findViewById(R.id.item_cinema_meter);
            item_cinema_address = itemView.findViewById(R.id.item_cinema_address);
            item_cinema_name = itemView.findViewById(R.id.item_cinema_name);
            item_cinema_image = itemView.findViewById(R.id.item_cinema_image);
        }
    }

    public interface onItemView {
        void onItemView(int id);
    }

    public void setOnItemView(Fragment_RecommendAdapter.onItemView onItemView) {
        this.onItemView = onItemView;
    }
}
