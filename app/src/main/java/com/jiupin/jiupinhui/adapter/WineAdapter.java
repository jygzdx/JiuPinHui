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
import com.jiupin.jiupinhui.entity.WineBrandEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    private LayoutInflater inflater;
    private Context mContext;
    private List<WineBrandEntity> wineList;

    public WineAdapter(Context mContext,List<WineBrandEntity> wineList) {
        this.wineList = wineList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.wine_show_item, viewGroup, false);
        WineHolder wineHolder = new WineHolder(view);
        view.setOnClickListener(this);
        return wineHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WineHolder wineHolder = (WineHolder) holder;
        wineHolder.tvWineName.setText(wineList.get(position).getName());
        Glide.with(mContext)
                .load(wineList.get(position).getThumb_img())
                .crossFade()
                .into(wineHolder.ivWineShow);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(wineList.get(position));
    }

    @Override
    public int getItemCount() {
        return wineList.size();
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

    public void setData(List<WineBrandEntity> wineList) {
        this.wineList = wineList;
        notifyDataSetChanged();
    }

    public void addAll(List<WineBrandEntity> wineList) {
        int lastIndex = this.wineList.size();
        if (this.wineList.addAll(wineList)) {
            notifyItemRangeInserted(lastIndex, wineList.size());
        }
    }

    public void clear() {
        wineList.clear();
        notifyDataSetChanged();
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
