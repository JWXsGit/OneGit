package com.bw.movie.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.fragment_bean.ReleaseBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：86157<p>
 * <p>创建时间：2019/10/10<p>
 * <p>更改时间：2019/10/10<p>
 */
public class MyShowingList_Adapter extends XRecyclerView.Adapter<MyShowingList_Adapter.MyViewHolder> {
    private Context context;
    private List<ReleaseBean.ResultBean> result;
    public Item_AdapterData data_list;

    public MyShowingList_Adapter(Context context, List<ReleaseBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_showing, null, true);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder Myholder, final int position) {
        MyViewHolder holder = Myholder;
        holder.showing_text_name.setText(result.get(position).getName());
        holder.showing_image.setImageURI(result.get(position).getImageUrl());
        //条目点击事件
        if (data_list != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data_list.onItem_Data(result.get(position).getMovieId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends XRecyclerView.ViewHolder {
        private TextView showing_text_name;
        private SimpleDraweeView showing_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            showing_image = itemView.findViewById(R.id.showing_image);
            showing_text_name = itemView.findViewById(R.id.showing_text_name);
        }
    }

    //定义接口
    public interface Item_AdapterData {
        void onItem_Data(int id);
    }

    public void setData_list(Item_AdapterData data_list) {
        this.data_list = data_list;
    }
}
