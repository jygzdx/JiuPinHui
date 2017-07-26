package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.WineBrandEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineBrandAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private final LayoutInflater inflater;
    private Context mContext;
    private List<WineBrandEntity> wineBrandList;

    public WineBrandAdapter(Context mContext) {
        this.mContext = mContext;
        wineBrandList = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.wine_brand_item, viewGroup, false);
        BrandHolder holder = new BrandHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(wineBrandList.get(position).getThumb_img())
                .crossFade()
                .into(((BrandHolder) holder).ivWineBrand);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(wineBrandList.get(position));
    }

    @Override
    public int getItemCount() {
        return wineBrandList.size();
    }

    public void setData(List<WineBrandEntity> wineBrandList) {
        this.wineBrandList = wineBrandList;
        notifyDataSetChanged();

    }


    class BrandHolder extends RecyclerView.ViewHolder {
        ImageView ivWineBrand;

        public BrandHolder(View itemView) {
            super(itemView);
            ivWineBrand = (ImageView) itemView.findViewById(R.id.iv_wine_brand);
        }
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v, (WineBrandEntity) v.getTag());
    }


    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, WineBrandEntity data);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
