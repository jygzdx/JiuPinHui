package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.WineBrandEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineKindAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private final LayoutInflater inflater;
    private Context mContext;
    private List<WineBrandEntity> wineKindList;

    public WineKindAdapter(Context mContext) {
        this.mContext = mContext;
        wineKindList = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.item_wine_kind, viewGroup, false);
        WineKindHolder holder = new WineKindHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((WineKindHolder) holder).tvWine.setText(wineKindList.get(position).getName());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(wineKindList.get(position));
    }

    @Override
    public int getItemCount() {
        return wineKindList.size();
    }

    public void setData(List<WineBrandEntity> wineKindList) {
        this.wineKindList = wineKindList;
        notifyDataSetChanged();

    }

    public void addAll(List<WineBrandEntity> wineKindList) {
        int lastIndex = this.wineKindList.size();
        if (this.wineKindList.addAll(wineKindList)) {
            notifyItemRangeInserted(lastIndex, wineKindList.size());
        }
    }

    class WineKindHolder extends RecyclerView.ViewHolder {
        TextView tvWine;

        public WineKindHolder(View itemView) {
            super(itemView);
            tvWine = (TextView) itemView.findViewById(R.id.tv_wine);
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
