package com.bw.movie.view.adapter.fragment_cinema;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.fragment_bean.BannerBean;
import com.bw.movie.bean.fragment_bean.ComingBean;
import com.bw.movie.bean.fragment_bean.HotMovieBean;
import com.bw.movie.bean.fragment_bean.ReleaseBean;
import com.bw.movie.view.DetailActivity;
import com.bw.movie.view.adapter.MyComingList_Adapter;
import com.bw.movie.view.adapter.MyHotMovie_Adapter;
import com.bw.movie.view.adapter.MyShowingList_Adapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.SimpleBannerInfo;

import org.greenrobot.eventbus.EventBus;

import java.util.AbstractList;
import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.view.adapter.fragment_cinema
 * @class describe
 * @anthor 24673
 * @time 2019/11/15 10:22
 * @change
 * @chang time
 * @class describe
 */
public class XRccycler_Adapter extends RecyclerView.Adapter {
    Context context;
    List<BannerBean.ResultBean> banner_list;
    List<ReleaseBean.ResultBean> release_list;
    List<ComingBean.ResultBean> coming_list;
    List<HotMovieBean.ResultBean> movie_list;

    public XRccycler_Adapter(Context context, List<BannerBean.ResultBean> banner_list, List<ReleaseBean.ResultBean> release_list, List<ComingBean.ResultBean> coming_list, List<HotMovieBean.ResultBean> movie_list) {
        this.context = context;
        this.banner_list = banner_list;
        this.release_list = release_list;
        this.coming_list = coming_list;
        this.movie_list = movie_list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else if (position == 3) {
            return 3;
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);
                return new MyBannerViewHolder(view);
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_hot_showing, parent, false);
                return new MyReleaseViewHolder(view);
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.item_coming_soon, parent, false);
                return new MyComingHolder(view);
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.item_hot_movie, parent, false);
                return new MyMovieHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int type = getItemViewType(position);
        LinearLayoutManager linearLayoutManager = null;
        switch (type) {
            case 0:
                MyBannerViewHolder holder = (MyBannerViewHolder) viewHolder;
                AbstractList<SimpleBannerInfo> info = new AbstractList<SimpleBannerInfo>() {
                    @Override
                    public SimpleBannerInfo get(int index) {
                        return null;
                    }

                    @Override
                    public int size() {
                        return banner_list.size();
                    }
                };
                holder.banner.setBannerData(R.layout.image_fresco, info);
                holder.banner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        String imageUrl = banner_list.get(position).imageUrl;
                        SimpleDraweeView simpleDraweeView = view.findViewById(R.id.simple);
                        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                .setUri(imageUrl)
                                .setAutoPlayAnimations(true).build();
                        simpleDraweeView.setController(controller);
                    }
                });
                break;
            case 1:
                MyReleaseViewHolder release_holder = (MyReleaseViewHolder) viewHolder;
                linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                release_holder.recycler_hot_showing.setLayoutManager(linearLayoutManager);
                MyShowingList_Adapter showListAdapter = new MyShowingList_Adapter(context, release_list);
                release_holder.recycler_hot_showing.setAdapter(showListAdapter);

                showListAdapter.setData_list(new MyShowingList_Adapter.Item_AdapterData() {
                    @Override
                    public void onItem_Data(int id) {
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra(Constant.MOVIEID, id);
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                MyComingHolder comingHolder = (MyComingHolder) viewHolder;
                MyComingList_Adapter comingList_adapter = new MyComingList_Adapter(context, coming_list);
                linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                comingHolder.recycler_coming_soon.setLayoutManager(linearLayoutManager);
                comingHolder.recycler_coming_soon.setAdapter(comingList_adapter);
                break;
            case 3:
                MyMovieHolder movieHolder = (MyMovieHolder) viewHolder;
                MyHotMovie_Adapter hotMovie_adapter = new MyHotMovie_Adapter(context, movie_list);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                gridLayoutManager.setAutoMeasureEnabled(true);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position == 0) {
                            return 3;
                        }
                        return 1;
                    }
                });
                movieHolder.recycler_hot_movie.setAdapter(hotMovie_adapter);
                movieHolder.recycler_hot_movie.setLayoutManager(gridLayoutManager);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyBannerViewHolder extends RecyclerView.ViewHolder {
        private XBanner banner;

        public MyBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class MyReleaseViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycler_hot_showing;

        public MyReleaseViewHolder(@NonNull View itemView) {
            super(itemView);
            recycler_hot_showing = itemView.findViewById(R.id.recycler_hot_showing);
        }
    }

    public class MyComingHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycler_coming_soon;

        public MyComingHolder(@NonNull View itemView) {
            super(itemView);
            recycler_coming_soon = itemView.findViewById(R.id.recycler_coming_soon);
        }
    }

    public class MyMovieHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycler_hot_movie;

        public MyMovieHolder(@NonNull View itemView) {
            super(itemView);
            recycler_hot_movie = itemView.findViewById(R.id.recycler_hot_movie);
        }
    }
}
