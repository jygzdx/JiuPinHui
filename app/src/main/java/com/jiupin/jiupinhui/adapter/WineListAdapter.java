package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.WineShowActivity;
import com.jiupin.jiupinhui.entity.WineInfoEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineListAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<WineInfoEntity> wineInfoList;

    public WineListAdapter(Context mContext) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        WineHolder wineHolder = (WineHolder) holder;
        wineHolder.tvWineName.setText(wineInfoList.get(position).getGoods_name());
        Glide.with(mContext)
                .load(wineInfoList.get(position).getPath())
                .crossFade()
                .into(wineHolder.ivWineShow);

        wineHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WineShowActivity.class);
                intent.putExtra("goodsId",wineInfoList.get(position).getId()+"");
                mContext.startActivity(intent);
            }
        });
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
        View view;

        public WineHolder(View itemView) {
            super(itemView);
            this.view = itemView;
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
