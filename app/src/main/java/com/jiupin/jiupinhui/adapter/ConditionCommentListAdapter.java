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
import com.jiupin.jiupinhui.entity.ConditionCommentEntity;
import com.jiupin.jiupinhui.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ConditionCommentListAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<ConditionCommentEntity> commentList;

    public ConditionCommentListAdapter(Context mContext) {
        commentList = new ArrayList<>();
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.condition_comment_list_item, viewGroup, false);
        ConditionListViewHolder conditionListViewHolder = new ConditionListViewHolder(view);
        return conditionListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ConditionListViewHolder conditionListViewHolder = (ConditionListViewHolder) holder;
        ConditionCommentEntity conditionCommentEntity = commentList.get(position);
        conditionListViewHolder.tvContent.setText(conditionCommentEntity.getComment_content());
        conditionListViewHolder.tvCommentTime.setText(TimeUtils.getTime(conditionCommentEntity.getComment_time()));
        conditionListViewHolder.tvUserName.setText(conditionCommentEntity.getNickName());
        Glide.with(mContext)
                .load(conditionCommentEntity.getUser_img())
                .crossFade()
                .into(conditionListViewHolder.ivLogo);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void setData(List<ConditionCommentEntity> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    public void addAll(List<ConditionCommentEntity> commentList) {
        int lastIndex = this.commentList.size();
        if (this.commentList.addAll(commentList)) {
            notifyItemRangeInserted(lastIndex, commentList.size());
        }
    }

    public void clear() {
        commentList.clear();
        notifyDataSetChanged();
    }
    
    class ConditionListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ConditionListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
