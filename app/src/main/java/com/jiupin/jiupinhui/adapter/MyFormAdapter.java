package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.FormParticularActivity;
import com.jiupin.jiupinhui.activity.SendCommentActivity;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.Form;
import com.jiupin.jiupinhui.utils.ToastUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class MyFormAdapter extends RecyclerView.Adapter{
    private LayoutInflater inflater;
    private Context mContext;
    List<Form> forms;
    public MyFormAdapter(Context context, List<Form> forms) {
        this.mContext = context;
        this.forms = forms;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = inflater.inflate(R.layout.my_form_item,viewGroup,false);
        MyFormViewHolder holder = new MyFormViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int status = forms.get(position).getStatus();
        MyFormViewHolder myFormViewHolder = (MyFormViewHolder) holder;
        myFormViewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(mContext,"position = "+position);
            }
        });
        switch (status){
            case Constant.WAIT_PAY:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext,R.color.light_black));
                myFormViewHolder.tvFormStatus.setText("待付款");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.to_pay);
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext,"待付款");
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case Constant.TRANSACTION_CLOSED:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext,R.color.light_red));
                myFormViewHolder.tvFormStatus.setText("交易关闭");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.VISIBLE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.delete_form);
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext,"交易关闭");
                    }
                });
                break;
            case Constant.TRANSACTION_SUCCESS_HAS_COMMENT:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext,R.color.mainTextColor));
                myFormViewHolder.tvFormStatus.setText("已完成");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.btn_oncemore);
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext,"已完成");
                    }
                });
                break;
            case Constant.TRANSACTION_SUCCESS_NO_COMMENT:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext,R.color.light_black));
                myFormViewHolder.tvFormStatus.setText("待评价");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.btn_to_comment);
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext,"待评价");
                        Intent intent = new Intent(mContext, SendCommentActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case Constant.WAIT_DELIVER_GOODS:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext,R.color.light_black));
                myFormViewHolder.tvFormStatus.setText("待发货");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.btn_oncemore);
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext,"待发货");
                    }
                });
                break;
            case Constant.WAIT_GAIN_GOODS:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext,R.color.light_black));
                myFormViewHolder.tvFormStatus.setText("待收货");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.wait_gain_goods);
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext,"待收货");
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }
    private class MyFormViewHolder extends RecyclerView.ViewHolder {
        TextView tvFormStatus,tvGoodsName,tvRefundSuccess;
        ImageView ivDelete,ivPic,ivBottom;
        public MyFormViewHolder(View itemView) {
            super(itemView);
            tvFormStatus = (TextView) itemView.findViewById(R.id.tv_form_status);
            tvGoodsName = (TextView) itemView.findViewById(R.id.tv_goods_name);
            tvRefundSuccess = (TextView) itemView.findViewById(R.id.tv_refund_success);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_goods_pic);
            ivBottom = (ImageView) itemView.findViewById(R.id.iv_bottom);
        }
    }
}
