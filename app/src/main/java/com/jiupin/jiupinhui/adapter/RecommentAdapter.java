package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class RecommentAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<CommunityEntity> communityList;

    public RecommentAdapter(Context mContext) {
        communityList = new ArrayList<>();
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.community_item, viewGroup, false);
        RecommentViewHolder holder = new RecommentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecommentViewHolder recommentViewHolder = (RecommentViewHolder)holder;
        Glide.with(mContext)
                .load(communityList.get(position).getUser_img())
                .crossFade()
                .into(recommentViewHolder.civHead);
        recommentViewHolder.tvConditionNickname.setText(communityList.get(position).getNickName());
        recommentViewHolder.tvContent.setText(communityList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return communityList.size();
    }

    public void setData(List<CommunityEntity> communityList) {
        this.communityList = communityList;
        notifyDataSetChanged();
    }

    public void addAll(List<CommunityEntity> communityList) {
        int lastIndex = this.communityList.size();
        if (this.communityList.addAll(communityList)) {
            notifyItemRangeInserted(lastIndex, communityList.size());
        }
    }

    public void clear() {
        communityList.clear();
        notifyDataSetChanged();
    }

    class RecommentViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.civ_head)
        CircleImageView civHead;
        @BindView(R.id.tv_condition_nickname)
        TextView tvConditionNickname;
        @BindView(R.id.tv_condition_time)
        TextView tvConditionTime;
        @BindView(R.id.btn_attention)
        Button btnAttention;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_container)
        LinearLayout llContainer;
        @BindView(R.id.tv_transpond_name)
        TextView tvTranspondName;
        @BindView(R.id.tv_transpond_content)
        TextView tvTranspondContent;
        @BindView(R.id.ll_transpond_pic)
        LinearLayout llTranspondPic;
        @BindView(R.id.ll_transpond)
        LinearLayout llTranspond;

        public RecommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
