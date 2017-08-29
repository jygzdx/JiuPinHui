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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class RecommentAdapter extends RecyclerView.Adapter {
    private static final String TAG = "RecommentAdapter";
    private LayoutInflater inflater;
    private Context mContext;
    private List<CommunityEntity> communityList;
    private static final int SPANCOUNT_4 = 4;
    private static final int SPANCOUNT_2 = 2;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LogUtils.d(TAG, "onBindViewHolder");
        final RecommentViewHolder recommentViewHolder = (RecommentViewHolder) holder;
        final CommunityEntity community = communityList.get(position);
        Glide.with(mContext)
                .load(community.getUser_img())
                .crossFade()
                .into(recommentViewHolder.civHead);
        recommentViewHolder.tvConditionNickname.setText(community.getNickName());
        recommentViewHolder.tvContent.setText(community.getContent());
        recommentViewHolder.tvConditionTime.setText(TimeUtils.getTime(community.getAddTime()));

        //先让recyclerview移除所有的控件，防止布局错乱
        recommentViewHolder.rvTranImg.removeAllViews();
        recommentViewHolder.rvUserImg.removeAllViews();
        //如果当前的发布动态的用户和登录的用户是同一个人，隐藏关注按钮
        String userId = UserInfoManager.getInstance().getUserId(mContext);
        LogUtils.d("community.getUser_id() = "+community.getUser_id()+"  userid = "+userId);
        if((community.getUser_id()+"").equals(userId)){
            recommentViewHolder.btnAttention.setVisibility(View.GONE);
        }else {
            recommentViewHolder.btnAttention.setVisibility(View.VISIBLE);
        }

        LogUtils.d("getImage_list = "+community.getImage_list());
        LogUtils.d("getTrans_img_list = "+community.getTrans_img_list());

        if (!StringUtils.isEmpty(community.getImage_list())) {//设置用户自己上传的图片
            recommentViewHolder.rvUserImg.setVisibility(View.VISIBLE);
            String[] imgUrls = community.getImage_list().split(";");
            initUserImgRv(recommentViewHolder, imgUrls);
        } else {
            recommentViewHolder.rvUserImg.setVisibility(View.GONE);
        }
        if (community.isIs_trans()) {//设置用户转发他人的图片
            recommentViewHolder.llTranspond.setVisibility(View.VISIBLE);
            if (!StringUtils.isEmpty(community.getTrans_img_list())) {
                String[] imgUrls = community.getTrans_img_list().split(";");
                initTranImgRv(recommentViewHolder, imgUrls);
            }
            recommentViewHolder.tvTranspondContent.setText(community.getTrans_content());
            recommentViewHolder.tvTranspondName.setText("@" + community.getTrans_nickName());
        } else {
            recommentViewHolder.llTranspond.setVisibility(View.GONE);
        }

        recommentViewHolder.llTransmit.setOnClickListener(new View.OnClickListener() {
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
        recommentViewHolder.llCommunityCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ConditionCommentListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("community",community);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        recommentViewHolder.btnAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnConcernExpertClickListener.onClick(recommentViewHolder.btnAttention, community.getUser_id(), community.isConcern_status(), position);
            }
        });

        recommentViewHolder.llSetLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnThumbDynamicClickListener.onClick(recommentViewHolder.ivSetLike, community.getId(), position);
            }
        });

        setConditionStatus(recommentViewHolder, position);
        setThumbStatus(recommentViewHolder, position);

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


    private void initTranImgRv(RecommentViewHolder recommentViewHolder, String[] imgUrls) {
        LogUtils.d("initTranImgRv/imgUrls.length = "+imgUrls.length);
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
        recommentViewHolder.rvTranImg.setLayoutManager(manager);
        recommentViewHolder.rvTranImg.setAdapter(new ImageAdapter(mContext, imgUrls));
        recommentViewHolder.rvTranImg.setNestedScrollingEnabled(false);
    }

    private void initUserImgRv(RecommentViewHolder recommentViewHolder, String[] imgUrls) {
        LogUtils.d("initUserImgRv/imgUrls.length = "+imgUrls.length);
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
        recommentViewHolder.rvUserImg.setLayoutManager(manager);
        recommentViewHolder.rvUserImg.setAdapter(new ImageAdapter(mContext, imgUrls));
        recommentViewHolder.rvUserImg.setNestedScrollingEnabled(false);
    }

    /**
     * 设置用户关注状态
     *
     * @param recommentViewHolder
     * @param position
     */
    public void setConditionStatus(RecommentViewHolder recommentViewHolder, int position) {
        if (communityList.get(position).isConcern_status()) {//已经关注了
            recommentViewHolder.btnAttention.setBackgroundResource(R.drawable.btn_cancel_condition);
            recommentViewHolder.btnAttention.setText("取消关注");
        } else {//还未关注
            recommentViewHolder.btnAttention.setBackgroundResource(R.drawable.uncheckouted);
            recommentViewHolder.btnAttention.setText("+关注");
        }
    }

    /**
     * 设置用户点赞状态
     *
     * @param recommentViewHolder
     * @param position
     */
    private void setThumbStatus(RecommentViewHolder recommentViewHolder, int position) {
        if (communityList.get(position).isThumb_status()) {
            recommentViewHolder.ivSetLike.setImageResource(R.drawable.set_like_checked);
            recommentViewHolder.llSetLike.setClickable(false);
        } else {
            recommentViewHolder.ivSetLike.setImageResource(R.drawable.set_like);
            recommentViewHolder.llSetLike.setClickable(true);
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
     * @param position
     */
    public void notifyItemChangeOnConcernExpert(int position) {
        CommunityEntity community = communityList.get(position);
        int userId = community.getUser_id();
        boolean status = community.isConcern_status();
        for (int i = 0; i < communityList.size(); i++) {
            if (userId == communityList.get(i).getUser_id()) {
                communityList.get(i).setConcern_status(!status);
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
        communityList.remove(position);
    }

    class RecommentViewHolder extends RecyclerView.ViewHolder {
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

        public RecommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
