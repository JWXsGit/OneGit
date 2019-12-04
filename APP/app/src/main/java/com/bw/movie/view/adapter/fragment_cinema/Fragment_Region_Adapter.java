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
import com.bw.movie.bean.fragment_bean.RegionBean;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.fragment_cinema
 * @class describe
 * @anthor 24673
 * @time 2019/11/13 20:22
 * @change
 * @chang time
 * @class describe
 */
public class Fragment_Region_Adapter extends RecyclerView.Adapter {
    private List<RegionBean.ResultBean> list;
    private Context context;
    private onRegion_ID onRegion_id;

    public Fragment_Region_Adapter(List<RegionBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_region, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.text_region.setText(list.get(position).getRegionName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int regionId = list.get(position).getRegionId();
                if (onRegion_id != null) {
                    onRegion_id.onRegionId(regionId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text_region;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_region = itemView.findViewById(R.id.text_region);
        }
    }

    public interface onRegion_ID {
        void onRegionId(int id);
    }

    public void setOnRegion_id(onRegion_ID onRegion_id) {
        this.onRegion_id = onRegion_id;
    }
}
