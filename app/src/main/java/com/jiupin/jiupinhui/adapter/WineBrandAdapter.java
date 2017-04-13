package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.WineBrand;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineBrandAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<WineBrand.DataBean.InfoBean> info;

    public WineBrandAdapter(Context mContext, List<WineBrand.DataBean.InfoBean> info) {
        this.mContext = mContext;
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.wine_brand_item, viewGroup, false);
        BrandHolder holder = new BrandHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        Glide.with(mContext)
                .load(info.get(i).getLogoUrl())
                .into(((BrandHolder) holder).ivWineBrand);
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    class BrandHolder extends RecyclerView.ViewHolder {
        ImageView ivWineBrand;

        public BrandHolder(View itemView) {
            super(itemView);
            ivWineBrand = (ImageView) itemView.findViewById(R.id.iv_wine_brand);
        }
    }

}
