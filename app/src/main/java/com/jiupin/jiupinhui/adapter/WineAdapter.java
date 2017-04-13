package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.Wine;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Wine.DataBean.InfoBean> info;

    public WineAdapter(Context mContext, List<Wine.DataBean.InfoBean> info) {
        this.mContext = mContext;
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.wine_show_item,viewGroup,false);
        wineHolder wineHolder = new wineHolder(view);
        return wineHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        ((wineHolder)holder).tvName.setText(info.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    class wineHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public wineHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_wine_name);
        }
    }

}
