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
import com.jiupin.jiupinhui.entity.HotRecommentEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：czb on 2017/6/28 10:40
 */

public class HotRecommentAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private HotRecommentEntity.DataBean datas;
    private LayoutInflater inflater;

    public HotRecommentAdapter(Context context, HotRecommentEntity.DataBean datas) {
        this.datas = datas;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hot_recomment_item, null);
        HotRecommentViewHolder holder = new HotRecommentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HotRecommentViewHolder hotRecomentViewHolder = (HotRecommentViewHolder) holder;
        HotRecommentEntity.DataBean.ListBean bean = datas.getList().get(position);
        hotRecomentViewHolder.tvRealPrice.setText("￥"+bean.getGoods_price());
        hotRecomentViewHolder.tvOldedPrice.setText("￥"+bean.getStore_price());
        Glide.with(mContext)
                .load(bean.getPath())
                .placeholder(R.drawable.middlebanner1)
                .crossFade()
                .into(hotRecomentViewHolder.ivHotRecomment);

    }

    @Override
    public int getItemCount() {
        return datas.getList().size();
    }


    class HotRecommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_recomment)
        ImageView ivHotRecomment;
        @BindView(R.id.tv_real_price)
        TextView tvRealPrice;
        @BindView(R.id.tv_olded_price)
        TextView tvOldedPrice;

        HotRecommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
