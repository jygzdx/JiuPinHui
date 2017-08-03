package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.utils.DensityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class CommentAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<AppraiseEntity> appraiseList;

    public CommentAdapter(Context mContext) {
        appraiseList = new ArrayList<>();
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.comment_item, viewGroup, false);
        CommentViewHolder commentViewHolder = new CommentViewHolder(view);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
        AppraiseEntity appraiseEntity = appraiseList.get(position);
        commentViewHolder.tvContent.setText(appraiseEntity.getEvaluate_info());
        commentViewHolder.tvCommentTime.setText(TimeUtils.getTime(appraiseEntity.getAddTime()));
        commentViewHolder.tvUserName.setText(appraiseEntity.getUserName());
        Glide.with(mContext)
                .load(appraiseEntity.getImageUrl())
                .crossFade()
                .into(commentViewHolder.ivLogo);

        //要先初始化linearlayout，不然recyclerview会复用错误
        commentViewHolder.llShowPicture.removeAllViews();
        if(appraiseEntity.getImage_list()!=null&&appraiseEntity.getImage_list()!=""){
            String[] imgUrls = appraiseEntity.getImage_list().split(";");
            if(imgUrls.length>0){
                for (int i = 0; i < imgUrls.length; i++) {
                    LogUtils.d("imgurl = "+imgUrls[i]);
                    if(imgUrls[i].trim().length()==0){
                        continue;
                    }
                    ImageView image = new ImageView(mContext);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dp2px(mContext, 80), DensityUtils.dp2px(mContext, 80));
                    image.setLayoutParams(params);
                    image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    image.setPadding(DensityUtils.dp2px(mContext, 5),0,DensityUtils.dp2px(mContext, 5),0);
                    Glide.with(mContext)
                            .load(imgUrls[i])
                            .crossFade()
                            .into(image);
                    commentViewHolder.llShowPicture.addView(image);

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return appraiseList.size();
    }

    public void setData(List<AppraiseEntity> appraiseList) {
        this.appraiseList = appraiseList;
        notifyDataSetChanged();
    }

    public void addAll(List<AppraiseEntity> appraiseList) {
        int lastIndex = this.appraiseList.size();
        if (this.appraiseList.addAll(appraiseList)) {
            notifyItemRangeInserted(lastIndex, appraiseList.size());
        }
    }

    public void clear() {
        appraiseList.clear();
        notifyDataSetChanged();
    }
    
    class CommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_show_picture)
        LinearLayout llShowPicture;

        CommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
