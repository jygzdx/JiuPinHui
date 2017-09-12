package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class PersonConditionAdapter extends RecyclerView.Adapter {
    private static final String TAG = "PersonConditionAdapter";
    private LayoutInflater inflater;
    private Context mContext;
    private List<CommunityEntity> communityList;
    private static final int SPANCOUNT_4 = 4;
    private static final int SPANCOUNT_2 = 2;

    public PersonConditionAdapter(Context mContext) {
        communityList = new ArrayList<>();
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.person_condition_list_item, viewGroup, false);
        PersonViewHolder holder = new PersonViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LogUtils.d(TAG, "onBindViewHolder");
        final PersonViewHolder personViewHolder = (PersonViewHolder) holder;
        final CommunityEntity community = communityList.get(position);
        Glide.with(mContext)
                .load(community.getUser_img())
                .crossFade()
                .into(personViewHolder.civHead);
        personViewHolder.tvConditionNickname.setText(community.getNickName());
        personViewHolder.tvContent.setText(community.getContent());
        personViewHolder.tvConditionTime.setText(TimeUtils.getTime(community.getAddTime()));

        //先让recyclerview移除所有的控件，防止布局错乱
        personViewHolder.rvTranImg.removeAllViews();
        personViewHolder.rvUserImg.removeAllViews();
        //如果当前的发布动态的用户和登录的用户是同一个人，隐藏关注按钮
        String userId = UserInfoManager.getInstance().getUserId(mContext);
        LogUtils.d("community.getUser_id() = " + community.getUser_id() + "  userid = " + userId);

        //设置更多选择按钮不可见
        personViewHolder.llMore.setVisibility(View.GONE);

        if (!StringUtils.isEmpty(community.getImage_list())) {//设置用户自己上传的图片
            personViewHolder.rvUserImg.setVisibility(View.VISIBLE);
            String[] imgUrls = community.getImage_list().split(";");
            initUserImgRv(personViewHolder, imgUrls);
        } else {
            personViewHolder.rvUserImg.setVisibility(View.GONE);
        }
        if (community.isIs_trans()) {//设置用户转发他人的图片
            personViewHolder.llTranspond.setVisibility(View.VISIBLE);
            if (!StringUtils.isEmpty(community.getTrans_img_list())) {
                String[] imgUrls = community.getTrans_img_list().split(";");
                initTranImgRv(personViewHolder, imgUrls);
            }
            personViewHolder.tvTranspondContent.setText(community.getTrans_content());
            personViewHolder.tvTranspondName.setText("@" + community.getTrans_nickName());
        } else {
            personViewHolder.llTranspond.setVisibility(View.GONE);
        }

        personViewHolder.llTransmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname;
                String tranContent;
                String imgUrl;
                if (community.isIs_trans()) {
                    nickname = community.getTrans_nickName();
                    tranContent = community.getTrans_content();
                    imgUrl = community.getTrans_user_img();
                } else {
                    nickname = community.getNickName();
                    tranContent = community.getContent();
                    imgUrl = community.getUser_img();
                }
                Intent intent = new Intent(mContext, TranConditionActivity.class);
                intent.putExtra("dynamicId", community.getId());
                intent.putExtra("imgUrl", imgUrl);
                intent.putExtra("nickname", nickname);
                intent.putExtra("tranContent", tranContent);
                mContext.startActivity(intent);
            }
        });
        personViewHolder.llCommunityCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ConditionCommentListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("community",community);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        personViewHolder.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(personViewHolder.llMore.getVisibility()==View.GONE){
                    personViewHolder.llMore.setVisibility(View.VISIBLE);
                }else{
                    personViewHolder.llMore.setVisibility(View.GONE);
                }

            }
        });

        personViewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除动态
                mOnViewClickListener.onClick(personViewHolder.tvDelete,community,position);
            }
        });

        personViewHolder.llSetLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点赞
                mOnViewClickListener.onClick(personViewHolder.ivSetLike, community, position);
            }
        });

        personViewHolder.tvMoveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnViewClickListener.onClick(personViewHolder.tvMoveTop, community, position);
            }
        });

        personViewHolder.tvOnlyYouselfLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnViewClickListener.onClick(personViewHolder.tvOnlyYouselfLook, community, position);
            }
        });

        //设置用户点赞
        setThumbStatus(personViewHolder, position);

        //设置动态是不是自己可见
        setConditionOnlyYouselfLook(personViewHolder,position);

    }

    /**
     * 设置动态是不是自己可见
     * @param personViewHolder
     * @param position
     */
    private void setConditionOnlyYouselfLook(PersonViewHolder personViewHolder, int position) {
        if(communityList.get(position).is_visible()){
            personViewHolder.tvOnlyYouselfLook.setText("仅自己可见");
        }else{
            personViewHolder.tvOnlyYouselfLook.setText("公开可见");
        }
    }

    public interface OnViewClickListener {
        void onClick(View view, Object data, int position);
    }

    private OnViewClickListener mOnViewClickListener = null;

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }

    private void initTranImgRv(PersonViewHolder personViewHolder, String[] imgUrls) {
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
        personViewHolder.rvTranImg.setLayoutManager(manager);
        personViewHolder.rvTranImg.setAdapter(new ImageAdapter(mContext, imgUrls));
        personViewHolder.rvTranImg.setNestedScrollingEnabled(false);
    }

    private void initUserImgRv(PersonViewHolder personViewHolder, String[] imgUrls) {

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
        personViewHolder.rvUserImg.setLayoutManager(manager);
        personViewHolder.rvUserImg.setAdapter(new ImageAdapter(mContext, imgUrls));
        personViewHolder.rvUserImg.setNestedScrollingEnabled(false);
    }

    /**
     * 设置用户点赞状态
     *
     * @param personViewHolder
     * @param position
     */
    private void setThumbStatus(PersonViewHolder personViewHolder, int position) {
        if (communityList.get(position).isThumb_status()) {
            personViewHolder.ivSetLike.setImageResource(R.drawable.set_like_checked);
//            personViewHolder.llSetLike.setClickable(false);
        } else {
            personViewHolder.ivSetLike.setImageResource(R.drawable.set_like);
//            personViewHolder.llSetLike.setClickable(true);
        }

    }

    /**
     * 刷新点赞
     *
     * @param position
     */
    public void notifyItemChangeOnThumbStatus(int position) {
        communityList.get(position).setThumb_status(!communityList.get(position).isThumb_status());
        notifyItemChanged(position);
    }

    /**
     * 删除动态
     *
     * @param position
     */
    public void removeCondition(int position) {
        communityList.remove(position);
        notifyItemRemoved(position);
        if(position != communityList.size()){
            notifyItemRangeChanged(position, communityList.size() - position);
        }

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

    public void remove(int position) {
        CommunityEntity community = communityList.remove(position);
        LogUtils.d("community = " + community.toString());
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_head)
        CircleImageView civHead;
        @BindView(R.id.tv_condition_nickname)
        TextView tvConditionNickname;
        @BindView(R.id.tv_condition_time)
        TextView tvConditionTime;

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

        @BindView(R.id.iv_more)
        ImageView ivMore;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.tv_move_top)
        TextView tvMoveTop;
        @BindView(R.id.tv_only_youself_look)
        TextView tvOnlyYouselfLook;
        @BindView(R.id.ll_more)
        LinearLayout llMore;

        public PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    
}
