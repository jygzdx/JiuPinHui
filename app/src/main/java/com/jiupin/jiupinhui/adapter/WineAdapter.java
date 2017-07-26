package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.WineInfoEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<WineInfoEntity> wineInfoList;

    public WineAdapter(Context mContext) {
        wineInfoList = new ArrayList<>();
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.wine_show_item, viewGroup, false);
        WineHolder wineHolder = new WineHolder(view);
        return wineHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WineHolder wineHolder = (WineHolder) holder;
        wineHolder.tvWineName.setText(wineInfoList.get(position).getGoods_name());
        Glide.with(mContext)
                .load(wineInfoList.get(position).getPath())
                .crossFade()
                .into(wineHolder.ivWineShow);
    }

    @Override
    public int getItemCount() {
        return wineInfoList.size();
    }

    class WineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_wine_show)
        ImageView ivWineShow;
        @BindView(R.id.tv_wine_name)
        TextView tvWineName;
        @BindView(R.id.tv_wine_time)
        TextView tvWineTime;

        public WineHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<WineInfoEntity> wineInfoList) {
        this.wineInfoList = wineInfoList;
        notifyDataSetChanged();
    }

    public void addAll(List<WineInfoEntity> wineInfoList) {
        int lastIndex = this.wineInfoList.size();
        if (this.wineInfoList.addAll(wineInfoList)) {
            notifyItemRangeInserted(lastIndex, wineInfoList.size());
        }
    }

    public void clear() {
        wineInfoList.clear();
        notifyDataSetChanged();
    }
}
