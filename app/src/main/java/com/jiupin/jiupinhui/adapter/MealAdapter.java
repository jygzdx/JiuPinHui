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
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：czb on 2017/7/25 17:20
 */

public class MealAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<MainShowEntity.DataBean.ListBean> mealInfoList;

    public MealAdapter(Context context) {
        mealInfoList = new ArrayList<>();
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_store, parent, false);
        RecyclerView.ViewHolder holder = new MealInfoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MealInfoViewHolder mealHolder = (MealInfoViewHolder) holder;
        mealHolder.tvMealName.setText(mealInfoList.get(position).getGoods_name());
        mealHolder.tvMealPrice.setText(mealInfoList.get(position).getStore_price()+"");
        mealHolder.tvMealDes.setText(mealInfoList.get(position).getGood_subtitle());
        Glide
                .with(mContext)
                .load(mealInfoList.get(position).getPath())
                .crossFade()
                .into(mealHolder.ivMealImg);
        mealHolder.tvNowBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("立即购买");
            }
        });
        mealHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsActivity.class);
                intent.putExtra("id",mealInfoList.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mealInfoList.size();
    }

    public void setData(List<MainShowEntity.DataBean.ListBean> mealInfoList) {
        this.mealInfoList = mealInfoList;
        notifyDataSetChanged();
    }

    public void addAll(List<MainShowEntity.DataBean.ListBean> mealInfoList) {
        int lastIndex = this.mealInfoList.size();
        if (this.mealInfoList.addAll(mealInfoList)) {
            notifyItemRangeInserted(lastIndex, mealInfoList.size());
        }
    }

    public void clear() {
        mealInfoList.clear();
        notifyDataSetChanged();
    }

    class MealInfoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_meal_img)
        ImageView ivMealImg;
        @BindView(R.id.tv_meal_name)
        TextView tvMealName;
        @BindView(R.id.tv_meal_des)
        TextView tvMealDes;
        @BindView(R.id.tv_meal_price)
        TextView tvMealPrice;
        @BindView(R.id.tv_now_buy)
        TextView tvNowBuy;
        View view;

        MealInfoViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
