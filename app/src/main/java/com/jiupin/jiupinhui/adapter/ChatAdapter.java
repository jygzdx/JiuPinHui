package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.FormEntity;
import com.jiupin.jiupinhui.utils.ToastUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 */
public class ChatAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private Context mContext;
    private List<FormEntity> formEntities;
    private static final int LEFT_TYPE = 0;
    private static final int RIGHT_TYPE = 1;

    public ChatAdapter(Context mContext, List<FormEntity> formEntities) {
        this.mContext = mContext;
        this.formEntities = formEntities;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if (getItemViewType(i) == LEFT_TYPE) {
            View leftView = inflater.inflate(R.layout.left_chat_item, null);
            return new ChatViewHolder(leftView);
        } else {
            View rightView = inflater.inflate(R.layout.right_chat_item, null);
            return new ChatViewHolder(rightView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int i) {
        ChatViewHolder holder1 = (ChatViewHolder) holder;
        holder1.tvChatContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(mContext, "我点击了文本^_^" + i);
            }
        });


    }

    private class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvChatContent;

        public ChatViewHolder(View viewItem) {
            super(viewItem);
            tvChatContent = (TextView) viewItem.findViewById(R.id.tv_chat_content);
        }
    }

    @Override
    public int getItemCount() {
        return formEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (formEntities.get(position).getStatus()==LEFT_TYPE){
//            return LEFT_TYPE;
//        }else {
//            return RIGHT_TYPE;
//        }
        return position;

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
