package com.bw.movie.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.KeyWorkBean;
import com.bw.movie.bean.Schedule_ListBean;
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
public class Date_List_Adapter extends RecyclerView.Adapter {
    private List<Schedule_ListBean.ResultBean> list;
    private Context context;
    private onItemId onItemId;

    public Date_List_Adapter(List<Schedule_ListBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.atcivity_key, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.item_key_director.setText(list.get(position).getDirector().trim());
        holder.item_key_name.setText(list.get(position).getName().trim());
        holder.item_key_score.setText(list.get(position).getScore() + "");
        holder.item_key_starring.setText(list.get(position).getStarring().trim() + "");
        holder.item_key_image.setImageURI(list.get(position).getImageUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemId != null) {
                    onItemId.onItemID(list.get(position).getMovieId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView item_key_image;
        private TextView item_key_name, item_key_director, item_key_starring, item_key_score;
        private Button bt_key;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_key_image = itemView.findViewById(R.id.item_key_image);
            item_key_director = itemView.findViewById(R.id.item_key_director);
            item_key_name = itemView.findViewById(R.id.item_key_name);
            item_key_starring = itemView.findViewById(R.id.item_key_starring);
            item_key_score = itemView.findViewById(R.id.item_key_score);
        }
    }

    public interface onItemId {
        void onItemID(int id);
    }

    public void setOnItemId(Date_List_Adapter.onItemId onItemId) {
        this.onItemId = onItemId;
    }
}
