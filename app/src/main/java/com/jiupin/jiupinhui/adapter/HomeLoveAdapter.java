package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.HomeLoveEntity;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class HomeLoveAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<HomeLoveEntity.DataBean.ListBean> stores;

    public static final int FOOT_VIEW = 0;
    public static final int DEFAUL_VIEW = 1;

    public HomeLoveAdapter(Context context, List<HomeLoveEntity.DataBean.ListBean> stores) {
        this.mContext = context;
        this.stores = stores;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LogUtils.d("viewType =  "+viewType);
        if(viewType == FOOT_VIEW){
            View view = inflater.inflate(R.layout.foot_layout,viewGroup,false);
            RecyclerView.ViewHolder holder = new FootViewHolder(view);
            return holder;
        }else{
            View viewFoot = inflater.inflate(R.layout.home_love_item,viewGroup,false);
            RecyclerView.ViewHolder holder = new HomeLoveViewHolder(viewFoot);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        LogUtils.d("position =  "+position);

        if(stores.size()==0){
            return;
        }

        if(holder instanceof HomeLoveViewHolder){
            HomeLoveViewHolder homeLoveViewHolder = (HomeLoveViewHolder)holder;
            Glide.with(mContext)
                    .load(stores.get(position).getPath())
                    .crossFade()
                    .into(homeLoveViewHolder.ivGuessLove);
            homeLoveViewHolder.tvGuessLoveDes.setText(stores.get(position).getGoods_name());
            homeLoveViewHolder.tvGuessLovePrice.setText(stores.get(position).getStore_price()+"");
            homeLoveViewHolder.tvGuessLovePriceNext.setText(stores.get(position).getGoods_price()+"");
        }
        else if(holder instanceof FootViewHolder){
            ((FootViewHolder)holder).tvLoadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.show(mContext,"加载更多", Toast.LENGTH_SHORT);
                    ((FootViewHolder)holder).tvLoadMore.setVisibility(View.GONE);
                    ((FootViewHolder)holder).rlFoot.setVisibility(View.VISIBLE);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount()-1){
            return FOOT_VIEW;
        }else{
            return DEFAUL_VIEW;
        }

    }

    @Override
    public int getItemCount() {
        return stores.size()+1;
    }

    class FootViewHolder extends RecyclerView.ViewHolder{
        private TextView tvLoadMore;
        private RelativeLayout rlFoot;
        public FootViewHolder(View itemView) {
            super(itemView);
            tvLoadMore = (TextView) itemView.findViewById(R.id.tv_load_more);
            rlFoot = (RelativeLayout) itemView.findViewById(R.id.rl_reflash);
        }
    }

    class HomeLoveViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGuessLove, ivGuessLoveMore;
        private TextView tvGuessLoveDes, tvGuessLovePrice, tvGuessLovePriceNext, tvBuyNumber;


        public HomeLoveViewHolder(View itemView) {
            super(itemView);
            ivGuessLove = (ImageView) itemView.findViewById(R.id.iv_guess_love);
            ivGuessLoveMore = (ImageView) itemView.findViewById(R.id.iv_guess_love_more);

            tvBuyNumber = (TextView) itemView.findViewById(R.id.tv_buy_number);
            tvGuessLoveDes = (TextView) itemView.findViewById(R.id.tv_guess_love_des);
            tvGuessLovePrice = (TextView) itemView.findViewById(R.id.tv_guess_love_price);
            tvGuessLovePriceNext = (TextView) itemView.findViewById(R.id.tv_guess_love_price_next);
//            tvShareLink = (TextView) itemView.findViewById(R.id.tv_share_link);
//            tvAddCart = (TextView) itemView.findViewById(R.id.tv_add_cart);
//
//            llLoveCover = (LinearLayout) itemView.findViewById(R.id.ll_love_cover);
        }
    }
}
