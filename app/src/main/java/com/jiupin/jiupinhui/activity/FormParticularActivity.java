package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jiupin.jiupinhui.config.Constant.TRANSACTION_CLOSED;
import static com.jiupin.jiupinhui.config.Constant.TRANSACTION_SUCCESS_HAS_COMMENT;
import static com.jiupin.jiupinhui.config.Constant.TRANSACTION_SUCCESS_NO_COMMENT;
import static com.jiupin.jiupinhui.config.Constant.WAIT_DELIVER_GOODS;
import static com.jiupin.jiupinhui.config.Constant.WAIT_GAIN_GOODS;
import static com.jiupin.jiupinhui.config.Constant.WAIT_PAY;

/**
 * 订单详情页
 */
public class FormParticularActivity extends BaseActivity {

    @BindView(R.id.tv_show_status)
    TextView tvShowStatus;
    @BindView(R.id.tv_remain_time)
    TextView tvRemainTime;
    @BindView(R.id.btn_left)
    Button btnLeft;
    @BindView(R.id.btn_right)
    Button btnRight;
    private int formStatus = WAIT_PAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_particular);
        ButterKnife.bind(this);
        updateView();
    }


    @OnClick({R.id.btn_left, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                switch (formStatus) {
                    case WAIT_PAY://取消订单
                        ToastUtils.showShort(mContext, "取消订单");
                        cancelForm();
                        break;
                    case TRANSACTION_SUCCESS_HAS_COMMENT://删除订单
                        ToastUtils.showShort(mContext, "删除订单");
                        deleteForm();
                        break;
                    case TRANSACTION_SUCCESS_NO_COMMENT://申请售后
                        ToastUtils.showShort(mContext, "申请售后");
                        applyAfterSale();
                        break;
                    case WAIT_DELIVER_GOODS://退款售后
                        ToastUtils.showShort(mContext, "退款/售后");
                        refundAndAfterSale();
                        break;
                    case WAIT_GAIN_GOODS://退款/售后
                        ToastUtils.showShort(mContext, "退款/售后");
                        refundAndAfterSale();
                        break;
                }
                break;
            case R.id.btn_right:

                switch (formStatus) {
                    case WAIT_PAY://付款
                        ToastUtils.showShort(mContext, "付款");
                        payMoney();
                        break;
                    case TRANSACTION_CLOSED://删除订单
                        ToastUtils.showShort(mContext, "删除订单");
                        deleteForm();
                        break;
                    case TRANSACTION_SUCCESS_HAS_COMMENT://申请售后
                        ToastUtils.showShort(mContext, "申请售后");
                        applyAfterSale();
                        break;
                    case TRANSACTION_SUCCESS_NO_COMMENT://立即评论
                        ToastUtils.showShort(mContext, "立即评论");
                        nowComment();
                        break;
                    case WAIT_DELIVER_GOODS://等待发货
                        ToastUtils.showShort(mContext, "确认收货");
                        ensureGainGoods();
                        break;
                    case WAIT_GAIN_GOODS://等待收货
                        ToastUtils.showShort(mContext, "确认收货");
                        ensureGainGoods();
                        break;
                }
                break;
        }
    }

    /**
     *付款
     */
    private void payMoney() {
    }
    /**
     *取消订单
     */
    private void cancelForm() {
    }
    /**
     *删除订单
     */
    private void deleteForm() {
    }
    /**
     *申请售后
     */
    private void applyAfterSale() {
    }
    /**
     *退款/售后
     */
    private void refundAndAfterSale() {
    }
    /**
     *确定收货
     */
    private void ensureGainGoods() {
    }
    /**
     *立即评论
     */
    private void nowComment() {
    }

    /**
     * 更新控件
     */
    private void updateView() {
        switch (formStatus) {
            case WAIT_PAY://等待付款
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setText(R.string.cancel_form);
                btnLeft.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                btnRight.setText(R.string.pay_money);
                btnRight.setTextColor(ContextCompat.getColor(mContext, R.color.mainTextColor));
                btnRight.setBackgroundResource(R.drawable.buttom_yellow);
                tvShowStatus.setText("等待买家付款");
                break;
            case TRANSACTION_CLOSED://交易关闭
                btnLeft.setVisibility(View.GONE);
                btnRight.setText(R.string.delete_form);
                btnRight.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                btnRight.setBackgroundResource(R.drawable.buttom_grey);
                tvShowStatus.setText("交易关闭");
                break;
            case TRANSACTION_SUCCESS_HAS_COMMENT://交易成功(已评论)
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setText(R.string.delete_form);
                btnLeft.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                btnRight.setText(R.string.apply_after_sale);
                btnRight.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                btnRight.setBackgroundResource(R.drawable.buttom_grey);
                tvShowStatus.setText("交易成功");
                break;
            case TRANSACTION_SUCCESS_NO_COMMENT://交易成功(还未评论)
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setText(R.string.apply_after_sale);
                btnLeft.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                btnRight.setText(R.string.now_comment);
                btnRight.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                btnRight.setBackgroundResource(R.drawable.buttom_grey);
                tvShowStatus.setText("交易成功");
                break;
            case WAIT_DELIVER_GOODS://等待发货
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setText(R.string.refund_and_after_sale);
                btnLeft.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                btnRight.setText(R.string.ensure_gain_goods);
                btnRight.setTextColor(ContextCompat.getColor(mContext, R.color.mainTextColor));
                btnRight.setBackgroundResource(R.drawable.buttom_yellow);
                tvShowStatus.setText("等待卖家发货");
                break;
            case WAIT_GAIN_GOODS://等待收货
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setText(R.string.refund_and_after_sale);
                btnLeft.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                btnRight.setText(R.string.ensure_gain_goods);
                btnRight.setTextColor(ContextCompat.getColor(mContext, R.color.mainTextColor));
                btnRight.setBackgroundResource(R.drawable.buttom_yellow);
                tvShowStatus.setText("卖家已发货");
                break;
        }
    }
}
