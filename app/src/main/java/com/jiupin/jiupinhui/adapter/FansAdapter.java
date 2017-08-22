package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.AttListEntity;
import com.jiupin.jiupinhui.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FansAdapter extends RecyclerView.Adapter {
    private static final String TAG = "FansAdapter";
    private LayoutInflater inflater;
    private Context mContext;
    private List<AttListEntity> attListEntities;

    public FansAdapter(Context mContext) {
        attListEntities = new ArrayList<>();
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.recommend_list_item, viewGroup, false);
        RecommentListViewHolder holder = new RecommentListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final RecommentListViewHolder viewHolder = (RecommentListViewHolder)holder;
        Glide.with(mContext)
                .load(attListEntities.get(position).getImageUrl())
                .crossFade()
                .into(viewHolder.civHead);
        viewHolder.tvNickname.setText(attListEntities.get(position).getNickName());
        viewHolder.tvIntro.setText(attListEntities.get(position).getIntro());
        viewHolder.tvFansCount.setText(attListEntities.get(position).getFans_num()+"人关注");

        viewHolder.rlAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnViewClickListener.onClick(viewHolder.rlAttention,attListEntities.get(position),position);
            }
        });

        if(attListEntities.get(position).isConcern_status()){
            viewHolder.llHasAtt.setVisibility(View.VISIBLE);
            viewHolder.llNohasAtt.setVisibility(View.GONE);
        }else {
            viewHolder.llHasAtt.setVisibility(View.GONE);
            viewHolder.llNohasAtt.setVisibility(View.VISIBLE);
        }
    }


    public interface OnViewClickListener {
        void onClick(View view, Object data, int position);
    }

    private OnViewClickListener mOnViewClickListener = null;

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return attListEntities.size();
    }

    public void setData(List<AttListEntity> attListEntities) {
        this.attListEntities = attListEntities;
        notifyDataSetChanged();
    }

    public void addAll(List<AttListEntity> attListEntities) {
        int lastIndex = this.attListEntities.size();
        if (this.attListEntities.addAll(attListEntities)) {
            notifyItemRangeInserted(lastIndex, attListEntities.size());
        }
    }

    /**
     * 关注达人
     *
     * @param position
     */
    public void notifyItemChangeOnConcernExpert(int position) {
        AttListEntity attListEntity = attListEntities.get(position);
        int userId = attListEntity.getId();
        boolean status = attListEntity.isConcern_status();
        for (int i = 0; i < attListEntities.size(); i++) {
            if (userId == attListEntities.get(i).getId()) {
                attListEntities.get(i).setConcern_status(!status);
            }
        }
        notifyDataSetChanged();
    }

    public void clear() {
        attListEntities.clear();
        notifyDataSetChanged();
    }

    class RecommentListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_head)
        CircleImageView civHead;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_intro)
        TextView tvIntro;
        @BindView(R.id.tv_fans_count)
        TextView tvFansCount;
        @BindView(R.id.ll_nohas_att)
        LinearLayout llNohasAtt;
        @BindView(R.id.ll_has_att)
        LinearLayout llHasAtt;
        @BindView(R.id.rl_attention)
        RelativeLayout rlAttention;
        public RecommentListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
