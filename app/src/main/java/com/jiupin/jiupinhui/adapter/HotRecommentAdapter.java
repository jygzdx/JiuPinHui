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
import com.jiupin.jiupinhui.activity.GoodsActivity;
import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：czb on 2017/6/28 10:40
 */

public class HotRecommentAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<HotRecommentEntity.DataBean.ListBean> recommentList;
    private LayoutInflater inflater;

    public HotRecommentAdapter(Context context, List<HotRecommentEntity.DataBean.ListBean> recommentList) {
        this.recommentList = recommentList;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HotRecommentViewHolder hotRecomentViewHolder = (HotRecommentViewHolder) holder;
        final HotRecommentEntity.DataBean.ListBean bean = recommentList.get(position);

        hotRecomentViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("position = "+position);
                Intent intent = new Intent(mContext, GoodsActivity.class);
                intent.putExtra("id",bean.getId());
                mContext.startActivity(intent);


            }
        });

        hotRecomentViewHolder.tvRealPrice.setText("￥"+bean.getStore_price());
        hotRecomentViewHolder.tvOldedPrice.setText("￥"+bean.getGoods_price());
        Glide.with(mContext)
                .load(bean.getPath())
                .crossFade()
                .into(hotRecomentViewHolder.ivHotRecomment);

    }

    @Override
    public int getItemCount() {
        return recommentList.size();
    }


    class HotRecommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_recomment)
        ImageView ivHotRecomment;
        @BindView(R.id.tv_real_price)
        TextView tvRealPrice;
        @BindView(R.id.tv_olded_price)
        TextView tvOldedPrice;
        View view ;

        HotRecommentViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
