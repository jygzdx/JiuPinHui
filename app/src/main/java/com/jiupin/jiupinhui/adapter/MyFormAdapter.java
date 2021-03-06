package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.FormParticularActivity;
import com.jiupin.jiupinhui.activity.GoodsActivity;
import com.jiupin.jiupinhui.activity.MyFormActivity;
import com.jiupin.jiupinhui.activity.SendCommentActivity;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.FormEntity;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class MyFormAdapter extends RecyclerView.Adapter {
    private static final String TAG = "MyFormAdapter";
    private LayoutInflater inflater;
    private Context mContext;
    private List<FormEntity> forms;
    String orderStatus = 60+"";

    public MyFormAdapter(Context context) {
        this.mContext = context;
        forms = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = inflater.inflate(R.layout.my_form_item, viewGroup, false);
        MyFormViewHolder holder = new MyFormViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final FormEntity form = forms.get(position);
        String status = form.getOrder_status();
        MyFormViewHolder myFormViewHolder = (MyFormViewHolder) holder;

        myFormViewHolder.tvGoodsName.setText(form.getOrderDetailList().get(0).getGoods_name());
        myFormViewHolder.tvGoodsNumber.setText(form.getOrderDetailList().get(0).getCount()+"");
        myFormViewHolder.tvPayMoney.setText(form.getOrderDetailList().get(0).getPrice()*form.getOrderDetailList().get(0).getCount()+"");

        Glide.with(mContext)
                .load(form.getOrderDetailList().get(0).getMain_photo())
                .crossFade()
                .into(myFormViewHolder.ivPic);
        Glide.with(mContext)
                .load(form.getStoreInfo().getStore_img())
                .crossFade()
                .into(myFormViewHolder.ivLogo);
        //根据status，显示item的不同状态
        switch (status) {
            case Constant.WAIT_PAY:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                myFormViewHolder.tvFormStatus.setText("待付款");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.to_pay);
                myFormViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        intent.putExtra("status",Constant.WAIT_PAY);
                        intent.putExtra("orderId",form.getId());
                        LogUtils.d(TAG,"orderId = " +form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);
                    }
                });
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        intent.putExtra("status",Constant.WAIT_PAY);
                        intent.putExtra("orderId",form.getId());
                        LogUtils.d(TAG,"orderId = " +form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);
                    }
                });
                break;
            case Constant.TRANSACTION_CLOSED:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext, R.color.light_red));
                myFormViewHolder.tvFormStatus.setText("交易关闭");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.VISIBLE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.delete_form);
                myFormViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //待定======================================================
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        intent.putExtra("status",Constant.TRANSACTION_CLOSED);
                        intent.putExtra("orderId",form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);

                    }
                });
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(position);
                    }
                });
                break;
            case Constant.TRANSACTION_SUCCESS_HAS_COMMENT:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext, R.color.mainTextColor));
                myFormViewHolder.tvFormStatus.setText("已完成");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.btn_oncemore);
                myFormViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        intent.putExtra("status",Constant.TRANSACTION_SUCCESS_HAS_COMMENT);
                        intent.putExtra("orderId",form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);
                    }
                });
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, GoodsActivity.class);
                        intent.putExtra("id",form.getOrderDetailList().get(0).getId());
                        mContext.startActivity(intent);
                    }
                });
                break;
            case Constant.TRANSACTION_SUCCESS_NO_COMMENT:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                myFormViewHolder.tvFormStatus.setText("待评价");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.btn_to_comment);
                myFormViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        intent.putExtra("status",Constant.TRANSACTION_SUCCESS_NO_COMMENT);
                        intent.putExtra("orderId",form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);

                    }
                });
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {//跳转到评价列表
                        ToastUtils.showShort(mContext, "待评价");
                        Intent intent = new Intent(mContext, SendCommentActivity.class);
                        intent.putExtra("orderId",form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);
                    }
                });
                break;
            case Constant.WAIT_DELIVER_GOODS:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                myFormViewHolder.tvFormStatus.setText("待发货");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.btn_oncemore);
                myFormViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        intent.putExtra("status",Constant.WAIT_DELIVER_GOODS);
                        intent.putExtra("orderId",form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);

                    }
                });
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, GoodsActivity.class);
                        intent.putExtra("id",form.getOrderDetailList().get(0).getId());
                        mContext.startActivity(intent);
                    }
                });
                break;
            case Constant.WAIT_GAIN_GOODS:
                myFormViewHolder.tvFormStatus.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                myFormViewHolder.tvFormStatus.setText("待收货");
                myFormViewHolder.tvRefundSuccess.setVisibility(View.GONE);
                myFormViewHolder.ivBottom.setImageResource(R.drawable.wait_gain_goods);
                myFormViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        intent.putExtra("status",Constant.WAIT_GAIN_GOODS);
                        intent.putExtra("orderId",form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);

                    }
                });
                myFormViewHolder.ivBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, FormParticularActivity.class);
                        intent.putExtra("status",Constant.WAIT_GAIN_GOODS);
                        intent.putExtra("orderId",form.getId());
                        ((MyFormActivity)mContext).startActivityForResult(intent,1);

                    }
                });
                break;
        }
    }

    private void showDialog(final int position) {
        View view = inflater.inflate(R.layout.dialog_content, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);

        tvContent.setText("确定要删除订单？");
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.dismiss();
            }
        });
        tvEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除订单
                ((MyFormActivity) mContext).deleteForm(position,forms.get(position).getId());
                //确定
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    public void setData(List<FormEntity> forms) {
        this.forms = forms;
        notifyDataSetChanged();
    }

    public void clear() {
        forms.clear();
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.forms.remove(position);
        notifyItemRemoved(position);

        if(position != (this.forms.size())){ // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position,this.forms.size()-position);
        }
    }

    public void addAll(List<FormEntity> forms){
        int lastIndex = this.forms.size();
        if(this.forms.addAll(forms)){
            notifyItemRangeInserted(lastIndex, forms.size());
        }
    }

    private class MyFormViewHolder extends RecyclerView.ViewHolder {
        TextView tvFormStatus, tvGoodsName, tvRefundSuccess,tvGoodsNumber,tvPayMoney;
        ImageView ivPic, ivBottom,ivLogo;
        View itemView;

        public MyFormViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvFormStatus = (TextView) itemView.findViewById(R.id.tv_form_status);
            tvGoodsName = (TextView) itemView.findViewById(R.id.tv_goods_name);
            tvRefundSuccess = (TextView) itemView.findViewById(R.id.tv_refund_success);
            tvGoodsNumber = (TextView)itemView.findViewById(R.id.tv_goods_number);
            tvPayMoney = (TextView)itemView.findViewById(R.id.tv_pay_money);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_goods_pic);
            ivBottom = (ImageView) itemView.findViewById(R.id.iv_bottom);
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_logo);
        }
    }
}
