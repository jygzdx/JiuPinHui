package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jph.takephoto.model.TImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ConditionAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private final LayoutInflater inflater;
    private Context mContext;
    private List<TImage> images;

    public ConditionAdapter(Context mContext) {
        this.mContext = mContext;
        images = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.item_image, viewGroup, false);
        ImageHolder holder = new ImageHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(new File(images.get(position).getCompressPath()))
                .crossFade()
                .into(((ImageHolder) holder).ivImg);
        //将数据保存在itemView的Tag中，以便点击时进行获取
//        holder.itemView.setTag(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setData(List<TImage> images) {
        this.images = images;
        notifyDataSetChanged();

    }


    class ImageHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;

        public ImageHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v, (TImage) v.getTag());
    }


    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, TImage data);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
