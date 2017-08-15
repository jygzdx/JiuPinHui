package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.BeforeChatActivity;
import com.jiupin.jiupinhui.activity.ServerActivity;
import com.jiupin.jiupinhui.entity.ChatHistotyEntity;
import com.jiupin.jiupinhui.utils.DensityUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ServerAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<ChatHistotyEntity> historyList;

    public ServerAdapter(Context mContext) {
        historyList = new ArrayList<>();
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = inflater.inflate(R.layout.server_item, viewGroup, false);
        ServerViewHolder serverViewHolder = new ServerViewHolder(view);
        return serverViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ServerViewHolder serverViewHolder = (ServerViewHolder) holder;
        final ChatHistotyEntity chatHistotyEntity = historyList.get(position);
        serverViewHolder.tvFinishState.setText(chatHistotyEntity.getStatus()==0?"关闭":((chatHistotyEntity.getStatus()==1)?"待客服处理":"已解决"));
        serverViewHolder.tvFormNumber.setText(chatHistotyEntity.getId()+"");
        serverViewHolder.tvQuestionContent.setText(chatHistotyEntity.getTitle());
        serverViewHolder.tvSubmitTime.setText(TimeUtils.getTime(chatHistotyEntity.getCreate_time()));
        //要先初始化linearlayout，不然recyclerview会复用错误
        serverViewHolder.llShowPicture.removeAllViews();
        if(chatHistotyEntity.getImg()!=null&&chatHistotyEntity.getImg()!=""){
            String[] imgUrls = chatHistotyEntity.getImg().split(";");
            if(imgUrls.length>0){
                for (int i = 0; i < imgUrls.length; i++) {
                    ImageView image = new ImageView(mContext);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dp2px(mContext, 80), DensityUtils.dp2px(mContext, 80));
                    image.setLayoutParams(params);
                    image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    image.setPadding(DensityUtils.dp2px(mContext, 5),0,DensityUtils.dp2px(mContext, 5),0);
                    Glide.with(mContext)
                            .load(imgUrls[i])
                            .crossFade()
                            .into(image);
                    serverViewHolder.llShowPicture.addView(image);

                }
            }
        }

        serverViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BeforeChatActivity.class);
                intent.putExtra("consultId",chatHistotyEntity.getId()+"");
                ((ServerActivity) mContext).startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void setData(List<ChatHistotyEntity> historyList) {
        this.historyList = historyList;
        notifyDataSetChanged();
    }

    public void addAll(List<ChatHistotyEntity> historyList) {
        int lastIndex = this.historyList.size();
        if (this.historyList.addAll(historyList)) {
            notifyItemRangeInserted(lastIndex, historyList.size());
        }
    }

    public void clear() {
        historyList.clear();
        notifyDataSetChanged();
    }

    class ServerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_form_number)
        TextView tvFormNumber;
        @BindView(R.id.tv_finish_state)
        TextView tvFinishState;
        @BindView(R.id.tv_question_content)
        TextView tvQuestionContent;
        @BindView(R.id.tv_submit_time)
        TextView tvSubmitTime;
        @BindView(R.id.ll_show_picture)
        LinearLayout llShowPicture;
        View view;

        ServerViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
