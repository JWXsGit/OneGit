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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.Follow_CinemaBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class My_cinema_Adapter extends RecyclerView.Adapter {
    List<Follow_CinemaBean.ResultBean> result;
    Context context;

    public My_cinema_Adapter(List<Follow_CinemaBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cinema_guanzhu, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.cinema_src.setImageURI(result.get(i).getLogo());
        myViewHolder.cinema_name.setText(result.get(i).getName());
        myViewHolder.cinema_address.setText(result.get(i).getAddress());
        myViewHolder.guan_tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPosition.onPosition(i);
            }
        });
    }

    public void removeData(int i) {
        result.remove(i);
        //删除动画
        notifyItemRemoved(i);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView cinema_src;
        private TextView cinema_address;
        private TextView cinema_name, guan_tv_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinema_src = itemView.findViewById(R.id.cinema_src);
            cinema_address = itemView.findViewById(R.id.cinema_address);
            cinema_name = itemView.findViewById(R.id.cinema_name);
            guan_tv_delete = itemView.findViewById(R.id.guan_tv_delete);
        }
    }

    public interface onPosition {
        void onPosition(int position);
    }

    public onPosition onPosition;

    public void setOnPosition(My_cinema_Adapter.onPosition onPosition) {
        this.onPosition = onPosition;
    }
}
