package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.ConditionCommentListActivity;
import com.jiupin.jiupinhui.activity.TranConditionActivity;
import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;
import com.jiupin.jiupinhui.widget.CircleImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class AttentionAdapter extends RecyclerView.Adapter {
    private static final String TAG = "AttentionAdapter";
    private LayoutInflater inflater;
    private Context mContext;
    private List<CommunityEntity> communityList;
    private static final int SPANCOUNT_4 = 4;
    private static final int SPANCOUNT_2 = 2;

    public AttentionAdapter(Context mContext) {
        communityList = new ArrayList<>();
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.community_item, viewGroup, false);
        AttentionViewHolder holder = new AttentionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LogUtils.d(TAG, "onBindViewHolder");
        final AttentionViewHolder attentionViewHolder = (AttentionViewHolder) holder;
        final CommunityEntity community = communityList.get(position);
        Glide.with(mContext)
                .load(community.getUser_img())
                .crossFade()
                .into(attentionViewHolder.civHead);
        attentionViewHolder.tvConditionNickname.setText(community.getNickName());
        attentionViewHolder.tvContent.setText(community.getContent());
        attentionViewHolder.tvConditionTime.setText(TimeUtils.getTime(community.getAddTime()));

        //先让recyclerview移除所有的控件，防止布局错乱
        attentionViewHolder.rvTranImg.removeAllViews();
        attentionViewHolder.rvUserImg.removeAllViews();
        //如果当前的发布动态的用户和登录的用户是同一个人，隐藏关注按钮
        String userId = UserInfoManager.getInstance().getUserId(mContext);
        LogUtils.d("community.getUser_id() = "+community.getUser_id()+"  userid = "+userId);
        if((community.getUser_id()+"").equals(userId)){
            attentionViewHolder.btnAttention.setVisibility(View.GONE);
        }else {
            attentionViewHolder.btnAttention.setVisibility(View.VISIBLE);
        }

        if (!StringUtils.isEmpty(community.getImage_list())) {//设置用户自己上传的图片
            attentionViewHolder.rvUserImg.setVisibility(View.VISIBLE);
            String[] imgUrls = community.getImage_list().split(";");
            initUserImgRv(attentionViewHolder, imgUrls);
        } else {
            attentionViewHolder.rvUserImg.setVisibility(View.GONE);
        }
        if (community.isIs_trans()) {//设置用户转发他人的图片
            attentionViewHolder.llTranspond.setVisibility(View.VISIBLE);
            if (!StringUtils.isEmpty(community.getTrans_img_list())) {
                String[] imgUrls = community.getTrans_img_list().split(";");
                initTranImgRv(attentionViewHolder, imgUrls);
            }
            attentionViewHolder.tvTranspondContent.setText(community.getTrans_content());
            attentionViewHolder.tvTranspondName.setText("@" + community.getTrans_nickName());
        } else {
            attentionViewHolder.llTranspond.setVisibility(View.GONE);
        }

        attentionViewHolder.llTransmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname;
                String tranContent;
                String imgUrl;
                if(community.isIs_trans()){
                    nickname = community.getTrans_nickName();
                    tranContent = community.getTrans_content();
                    imgUrl = community.getTrans_user_img();
                }else{
                    nickname = community.getNickName();
                    tranContent = community.getContent();
                    imgUrl = community.getUser_img();
                }
                Intent intent = new Intent(mContext, TranConditionActivity.class);
                intent.putExtra("dynamicId",community.getId());
                intent.putExtra("imgUrl",imgUrl);
                intent.putExtra("nickname",nickname);
                intent.putExtra("tranContent",tranContent);
                mContext.startActivity(intent);
            }
        });
        attentionViewHolder.llCommunityCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ConditionCommentListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("community",community);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        attentionViewHolder.btnAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnConcernExpertClickListener.onClick(attentionViewHolder.btnAttention, community.getUser_id(), community.isConcern_status(), position);
            }
        });

        attentionViewHolder.llSetLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnThumbDynamicClickListener.onClick(attentionViewHolder.ivSetLike, community.getId(), position);
            }
        });

        setConditionStatus(attentionViewHolder, position);
        setThumbStatus(attentionViewHolder, position);

    }

    public interface OnThumbDynamicClickListener {
        void onClick(View view, int communityId, int position);
    }

    private OnThumbDynamicClickListener mOnThumbDynamicClickListener = null;

    public void setOnThumbDynamicClickListener(OnThumbDynamicClickListener listener) {
        this.mOnThumbDynamicClickListener = listener;
    }

    public interface OnConcernExpertClickListener {
        void onClick(View view, int userId, boolean concernStatus, int position);
    }

    private OnConcernExpertClickListener mOnConcernExpertClickListener = null;

    public void setOnConcernExpertClickListener(OnConcernExpertClickListener listener) {
        this.mOnConcernExpertClickListener = listener;
    }


    private void initTranImgRv(AttentionViewHolder attentionViewHolder, String[] imgUrls) {
        GridLayoutManager manager;
        switch (imgUrls.length) {
            case 1:
            case 2:
                manager = new GridLayoutManager(mContext, SPANCOUNT_2);
                break;
            default:
                manager = new GridLayoutManager(mContext, SPANCOUNT_4);
                break;
        }
        attentionViewHolder.rvTranImg.setLayoutManager(manager);
        attentionViewHolder.rvTranImg.setAdapter(new ImageAdapter(mContext, imgUrls));
        attentionViewHolder.rvTranImg.setNestedScrollingEnabled(false);
    }

    private void initUserImgRv(AttentionViewHolder attentionViewHolder, String[] imgUrls) {

        GridLayoutManager manager;
        switch (imgUrls.length) {
            case 1:
            case 2:
                manager = new GridLayoutManager(mContext, SPANCOUNT_2);
                break;
            default:
                manager = new GridLayoutManager(mContext, SPANCOUNT_4);
                break;

        }
        attentionViewHolder.rvUserImg.setLayoutManager(manager);
        attentionViewHolder.rvUserImg.setAdapter(new ImageAdapter(mContext, imgUrls));
        attentionViewHolder.rvUserImg.setNestedScrollingEnabled(false);
    }

    /**
     * 设置用户关注状态
     *
     * @param attentionViewHolder
     * @param position
     */
    public void setConditionStatus(AttentionViewHolder attentionViewHolder, int position) {
        if (communityList.get(position).isConcern_status()) {//已经关注了
            attentionViewHolder.btnAttention.setBackgroundResource(R.drawable.btn_cancel_condition);
            attentionViewHolder.btnAttention.setText("取消关注");
        } else {//还未关注
            attentionViewHolder.btnAttention.setBackgroundResource(R.drawable.uncheckouted);
            attentionViewHolder.btnAttention.setText("+关注");
        }
    }

    /**
     * 设置用户点赞状态
     *
     * @param attentionViewHolder
     * @param position
     */
    private void setThumbStatus(AttentionViewHolder attentionViewHolder, int position) {
        if (communityList.get(position).isThumb_status()) {
            attentionViewHolder.ivSetLike.setImageResource(R.drawable.set_like_checked);
            attentionViewHolder.llSetLike.setClickable(false);
        } else {
            attentionViewHolder.ivSetLike.setImageResource(R.drawable.set_like);
            attentionViewHolder.llSetLike.setClickable(true);
        }

    }

    /**
     * 刷新点赞
     *
     * @param position
     */
    public void notifyItemChangeOnThumbStatus(int position) {
        communityList.get(position).setThumb_status(true);
        notifyItemChanged(position);
    }

    /**
     * 关注达人
     *
     * @param userId
     */
    public void notifyItemChangeOnConcernExpert(String userId) {
        Iterator<CommunityEntity> it = communityList.iterator();
        while (it.hasNext()){
            CommunityEntity community = it.next();
            if(userId.equals(community.getUser_id()+"")){
                it.remove();
            }
        }
        notifyDataSetChanged();
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

    public void remove(int position){
        CommunityEntity community = communityList.remove(position);
        LogUtils.d("community = "+community.toString());
    }

    class AttentionViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.rv_user_img)
        RecyclerView rvUserImg;
        @BindView(R.id.tv_transpond_name)
        TextView tvTranspondName;
        @BindView(R.id.tv_transpond_content)
        TextView tvTranspondContent;
        @BindView(R.id.rv_tran_img)
        RecyclerView rvTranImg;
        @BindView(R.id.ll_transpond)
        LinearLayout llTranspond;

        @BindView(R.id.ll_transmit)
        LinearLayout llTransmit;
        @BindView(R.id.ll_community_com)
        LinearLayout llCommunityCom;
        @BindView(R.id.ll_set_like)
        LinearLayout llSetLike;
        @BindView(R.id.iv_set_like)
        ImageView ivSetLike;

        public AttentionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
