package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.WineBrandEntity;
import com.jiupin.jiupinhui.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineKindAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private Context mContext;
    private List<WineBrandEntity> wineKindList;
    private int selected = 0;

    public WineKindAdapter(Context mContext) {
        this.mContext = mContext;
        wineKindList = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.item_wine_kind, viewGroup, false);
        WineKindHolder holder = new WineKindHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((WineKindHolder) holder).tvWine.setText(wineKindList.get(position).getName());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(wineKindList.get(position));

        ((WineKindHolder) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnViewClickListener.onClick(v,wineKindList.get(position),position);
            }
        });

        LogUtils.d("onBindViewHolder");
        if(position == selected){
            LogUtils.d("position = "+position);
            ((WineKindHolder) holder).tvWine.setBackgroundResource(R.drawable.wine_kind_bg);
        }else{
            ((WineKindHolder) holder).tvWine.setBackgroundResource(R.drawable.buttom_bg_yellow);
        }
    }

    @Override
    public int getItemCount() {
        return wineKindList.size();
    }

    /**
     * 设置选择项
     * @param position
     */
    public void setSelected(int position){
        selected = position;
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
        View view;

        public WineKindHolder(View itemView) {
            super(itemView);
            tvWine = (TextView) itemView.findViewById(R.id.tv_wine);
            this.view = itemView;
        }
    }

    public interface OnViewClickListener {
        void onClick(View view, Object data, int position);
    }

    private OnViewClickListener mOnViewClickListener = null;

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }

//    @Override
//    public void onClick(View v) {
//        mOnItemClickListener.onItemClick(v, (WineBrandEntity) v.getTag());
//    }
//
//
//    public static interface OnRecyclerViewItemClickListener {
//        void onItemClick(View view, WineBrandEntity data,int position);
//    }
//
//    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
//
//    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }

}
