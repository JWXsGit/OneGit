package com.bw.movie.view.adapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.view.ChooseActivity;
import com.bw.movie.view.adapter.detail_adapter.MovieComment_Adapter;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.adapter
 * @class describe
 * @anthor 24673
 * @time 2019/11/20 21:13
 * @change
 * @chang time
 * @class describe
 */
public class Fragment_Text_Adapter extends RecyclerView.Adapter {
    private List<String> list;
    private Context context;
    private onTextView onTextView;

    public Fragment_Text_Adapter(List<String> result, Context context) {
        this.context = context;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.text_date.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTextView != null) {
                    onTextView.onTextView(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_date = itemView.findViewById(R.id.text_date);
        }
    }

    public interface onTextView {
        void onTextView(int position);
    }

    public void setOnTextView(Fragment_Text_Adapter.onTextView onTextView) {
        this.onTextView = onTextView;
    }
}
