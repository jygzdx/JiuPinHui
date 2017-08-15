package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.utils.DensityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：czb on 2017/8/14 15:18
 */

public class TranImageAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private String[] imgUrls;
    private Context mContext;

    public TranImageAdapter(Context mContext, String[] imgUrls) {
        this.mContext = mContext;
        this.imgUrls = imgUrls;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_grid_image, null);
        ImgViewHolder holder = new ImgViewHolder(view);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dp2px(mContext, 70), DensityUtils.dp2px(mContext, 70));
        params.setMargins(DensityUtils.dp2px(mContext,2),DensityUtils.dp2px(mContext,10),DensityUtils.dp2px(mContext,10),DensityUtils.dp2px(mContext,2));
        holder.ivImg.setLayoutParams(params);
        holder.ivImg.setScaleType(ImageView.ScaleType.CENTER);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(imgUrls[position])
                .crossFade()
                .into(((ImgViewHolder) holder).ivImg);
    }

    @Override
    public int getItemCount() {
        return imgUrls.length;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ImgViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;

        ImgViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
