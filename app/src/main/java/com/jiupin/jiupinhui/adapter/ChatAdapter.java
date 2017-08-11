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
import com.jiupin.jiupinhui.entity.ChatEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/28.
 */
public class ChatAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private Context mContext;
    private static final int LEFT_TYPE = 0;
    private static final int RIGHT_TYPE = 1;
    private List<ChatEntity> chatList;

    public ChatAdapter(Context mContext) {
        this.mContext = mContext;
        chatList = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {

        if (type == LEFT_TYPE) {
            View leftView = inflater.inflate(R.layout.left_chat_item, null);
            return new LeftChatViewHolder(leftView);
        } else {
            View rightView = inflater.inflate(R.layout.right_chat_item, null);
            return new RightChatViewHolder(rightView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof LeftChatViewHolder){
            LeftChatViewHolder leftHolder = (LeftChatViewHolder) holder;
            leftHolder.tvChatContent.setText(chatList.get(position).getContent());
            leftHolder.tvChatTime.setText(TimeUtils.getTime(chatList.get(position).getCreate_time()));
        }else {
            RightChatViewHolder rightHolder = (RightChatViewHolder) holder;
            rightHolder.tvChatContent.setText(chatList.get(position).getContent());
            rightHolder.tvChatTime.setText(TimeUtils.getTime(chatList.get(position).getCreate_time()));
            if(UserInfoManager.getInstance().getUser()!=null){
                Glide.with(mContext)
                        .load(UserInfoManager.getInstance().getUser().getImageUrl())
                        .crossFade()
                        .into(rightHolder.ivUserPic);
            }
        }
    }

    class LeftChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_user_pic)
        ImageView ivUserPic;
        @BindView(R.id.tv_chat_content)
        TextView tvChatContent;
        @BindView(R.id.tv_chat_time)
        TextView tvChatTime;

        public LeftChatViewHolder(View viewItem) {
            super(viewItem);
            ButterKnife.bind(this, viewItem);
        }
    }

    class RightChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_user_pic)
        ImageView ivUserPic;
        @BindView(R.id.tv_chat_content)
        TextView tvChatContent;
        @BindView(R.id.tv_chat_time)
        TextView tvChatTime;

        public RightChatViewHolder(View viewItem) {
            super(viewItem);
            ButterKnife.bind(this, viewItem);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getContent_side()==LEFT_TYPE){
            return LEFT_TYPE;
        }else {
            return RIGHT_TYPE;
        }

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void setData(List<ChatEntity> chatList){
        this.chatList = chatList;
        notifyDataSetChanged();
    }

    public void addAll(List<ChatEntity> chatList) {
        int lastIndex = this.chatList.size();
        if (this.chatList.addAll(chatList)) {
            notifyItemRangeInserted(lastIndex, chatList.size());
        }
    }

    public void clear() {
        chatList.clear();
        notifyDataSetChanged();
    }
}
