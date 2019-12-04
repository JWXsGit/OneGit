package com.bw.movie.view.adapter.seatinfo_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.SeatInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：86157<p>
 * <p>创建时间：2019/10/25<p>
 * <p>更改时间：2019/10/25<p>
 */
public class SeatInfo_Adapter extends RecyclerView.Adapter {
    private List<SeatInfoBean.ResultBean> list;
    private Context context;
    private onItemCheck onItemCheck;
    final private List<String> strings = new ArrayList<>();

    public SeatInfo_Adapter(List<SeatInfoBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seatinfo, null, true);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final MyViewHolder holder = (MyViewHolder) viewHolder;
        int status = list.get(position).getStatus();

        if (status == 2) {
            holder.checkbox.setChecked(true);
            holder.checkbox.setBackgroundResource(R.drawable.seatable_view_visibie);
        }

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int row = list.get(position).getRow();
                int seat = list.get(position).getSeat();
                String str = row + "-" + seat;

                if (holder.checkbox.isChecked()) {
                    holder.checkbox.setChecked(true);
                    holder.checkbox.setBackgroundResource(R.drawable.seatable_view_visibie);

                    if (onItemCheck != null) {
                        list.get(position).setStatus(3);
                        strings.add(str);
                        onItemCheck.onPayOK(strings);
                        onItemCheck.onPayON(list.get(position).getRow() + "-" + list.get(position).getSeat() + "");
                    }
                } else {
                    if (onItemCheck != null) {
                        list.get(position).setStatus(1);
                        onItemCheck.onPayON("选择座位");
                        strings.remove(str);
                        onItemCheck.onPayOK(strings);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }

    public interface onItemCheck {
        void onPayON(String str);

        void onPayOK(List<String> strings);
    }

    public void setOnItemCheck(SeatInfo_Adapter.onItemCheck onItemCheck) {
        this.onItemCheck = onItemCheck;
    }
}
