package com.jiupin.jiupinhui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.FormParticularEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IFormParticularActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.FormParticularActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IFormParticularActivityView;

import java.util.List;

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
public class FormParticularActivity extends BaseActivity implements IFormParticularActivityView {
    private static final String TAG = "FormParticularActivity";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.tv_show_status)
    TextView tvShowStatus;
    @BindView(R.id.tv_remain_time)
    TextView tvRemainTime;
    @BindView(R.id.tv_consignee_name)
    TextView tvConsigneeName;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_insurance_price)
    TextView tvInsurancePrice;
    @BindView(R.id.tv_insurance_number)
    TextView tvInsuranceNumber;
    @BindView(R.id.tv_form_number)
    TextView tvFormNumber;
    @BindView(R.id.tv_form_time)
    TextView tvFormTime;
    @BindView(R.id.tv_transportation_price)
    TextView tvTransportationPrice;
    @BindView(R.id.tv_payment_money)
    TextView tvPaymentMoney;
    @BindView(R.id.tv_contact_customer)
    TextView tvContactCustomer;
    @BindView(R.id.tv_making_phone)
    TextView tvMakingPhone;
    @BindView(R.id.btn_left)
    Button btnLeft;
    @BindView(R.id.btn_right)
    Button btnRight;

    /**
     * 保存取消订单弹出窗的radiobutton
     */
    private RadioButton[] rbArray = new RadioButton[5];

    private String formStatus = "";

    private IFormParticularActivityPresenter presenter;
    private String token;
    private String orderId;

    private FormParticularEntity formParticularEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_particular);
        ButterKnife.bind(this);

        formStatus = getIntent().getExtras().getString("status");

        updateView();

        presenter = new FormParticularActivityPresenterImpl(this);

        token = UserInfoManager.getInstance().getToken(this);

        orderId = getIntent().getExtras().getString("orderId");

        LogUtils.d(TAG, "orderId=" + orderId + " ,token = " + token);
        presenter.getFormInfo(orderId, token);


    }

    @OnClick({R.id.btn_left, R.id.btn_right, R.id.tv_contact_customer, R.id.tv_making_phone, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_contact_customer://联系供应商
                break;
            case R.id.tv_making_phone://拨打电话
                break;
            case R.id.btn_left:
                switch (formStatus) {
                    case WAIT_PAY://取消订单
                        cancelForm();
                        break;
                    case TRANSACTION_SUCCESS_HAS_COMMENT://删除订单
                        deleteForm();
                        break;
                    case TRANSACTION_SUCCESS_NO_COMMENT://申请售后
                        applyAfterSale();
                        break;
                    case WAIT_DELIVER_GOODS://退款售后
                        refundAndAfterSale();
                        break;
                    case WAIT_GAIN_GOODS://退款/售后
                        refundAndAfterSale();
                        break;
                }
                break;
            case R.id.btn_right:

                switch (formStatus) {
                    case WAIT_PAY://付款
                        payMoney();
                        break;
                    case TRANSACTION_CLOSED://删除订单
                        deleteForm();
                        break;
                    case TRANSACTION_SUCCESS_HAS_COMMENT://申请售后
                        applyAfterSale();
                        break;
                    case TRANSACTION_SUCCESS_NO_COMMENT://立即评论
                        nowComment();
                        break;
                    case WAIT_DELIVER_GOODS://等待发货

                        ensureGainGoods();
                        break;
                    case WAIT_GAIN_GOODS://等待收货

                        ensureGainGoods();
                        break;
                }
                break;
        }
    }

    /**
     * 付款
     */
    private void payMoney() {//测试下评论
        ToastUtils.showShort(this, "功能还未开通");
    }

    /**
     * 取消订单
     */
    private void cancelForm() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_cancel_form, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        rbArray[0] = (RadioButton) view.findViewById(R.id.rb_unwillingness_pay);
        rbArray[1] = (RadioButton) view.findViewById(R.id.rb_info_error);
        rbArray[2] = (RadioButton) view.findViewById(R.id.rb_no_goods);
        rbArray[3] = (RadioButton) view.findViewById(R.id.rb_ready_item);
        rbArray[4] = (RadioButton) view.findViewById(R.id.rb_other_cause);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);

        for (int i = 0; i < 5; i++) {
            rbArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < 5; j++) {
                        rbArray[j].setChecked(false);
                    }
                    ((RadioButton) v).setChecked(true);
                }
            });
        }

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
                //确定
                dialog.dismiss();

                for (int i = 0; i < 5; i++) {
                    if (rbArray[i].isChecked()) {//根据i值得不同，传递不同的取消订单的原因
                        LogUtils.d("i= " + i);
                        presenter.cancelForm(orderId, token);
                    }

                }
            }
        });
        dialog.show();
    }

    /**
     * 删除订单
     */
    private void deleteForm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否取消订单")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.deleteForm(formParticularEntity.getOrder().getId() + "", token);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();

    }

    /**
     * 申请售后
     */
    private void applyAfterSale() {
        Intent intent = new Intent(this, ChatActivity.class);
        //        intent.putExtra("status",1);
        intent.putExtra("orderNum", formParticularEntity.getOrder().getOrder_id());
        startActivity(intent);

    }

    /**
     * 退款/售后
     */
    private void refundAndAfterSale() {
        ToastUtils.showShort(this, "功能未开放");
    }

    /**
     * 确定收货
     */
    private void ensureGainGoods() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否确定收货")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.ensureGainGoods(formParticularEntity.getOrder().getId() + "", token);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();

    }

    /**
     * 立即评论
     */
    private void nowComment() {
        Intent intent = new Intent(this, SendCommentActivity.class);
        intent.putExtra("orderId", formParticularEntity.getOrder().getId() + "");
        startActivity(intent);

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

    private void setRadioChecked(RadioButton radioButton) {

    }

    @Override
    public void getFormSuccess(FormParticularEntity formParticularEntity) {
        LogUtils.d(TAG, formParticularEntity.toString());
        if (formParticularEntity != null) {
            this.formParticularEntity = formParticularEntity;
            tvRemainTime.setText(formParticularEntity.getDeadline());
            tvConsigneeName.setText(formParticularEntity.getAddress().getTrueName());
            tvPhoneNumber.setText(formParticularEntity.getAddress().getMobile());
            tvAddress.setText("收货地址：" + formParticularEntity.getAddress().getArea_main().replace(" ", "")
                    + formParticularEntity.getAddress().getArea_info());
            LayoutInflater inflater = LayoutInflater.from(this);

            double price = 0;
            //添加订单商品
            if (formParticularEntity.getCart() != null) {
                List<FormParticularEntity.CartBean> cartBeanList = formParticularEntity.getCart();

                for (int i = 0; i < cartBeanList.size(); i++) {
                    View view = inflater.inflate(R.layout.form_goods_item, null);
                    TextView tvStoreName = (TextView) view.findViewById(R.id.tv_store_name);
                    TextView tvGoodsName = (TextView) view.findViewById(R.id.tv_goods_name);
                    TextView tvGoodsPrice = (TextView) view.findViewById(R.id.tv_goods_price);
                    TextView tvGoodsFormerPrice = (TextView) view.findViewById(R.id.tv_goods_former_price);
                    TextView tvGoodsNumber = (TextView) view.findViewById(R.id.tv_goods_number);

                    tvStoreName.setText(formParticularEntity.getStore().getStore_name());
                    tvGoodsName.setText(cartBeanList.get(i).getGoods_name());
                    tvGoodsPrice.setText("￥" + cartBeanList.get(i).getPrice());
                    tvGoodsFormerPrice.setText("￥" + cartBeanList.get(i).getOrigin_price());
                    tvGoodsNumber.setText("x" + cartBeanList.get(i).getCount());

                    price = price + cartBeanList.get(i).getPrice() * cartBeanList.get(i).getCount();

                    llContainer.addView(view);
                }
            }
            tvFormNumber.setText(formParticularEntity.getOrder().getOrder_id());

            String time = TimeUtils.getTime(formParticularEntity.getOrder().getAddTime());
            tvFormTime.setText(time);
            tvTransportationPrice.setText(formParticularEntity.getOrder().getShip_price() + "");

            tvPaymentMoney.setText((price - formParticularEntity.getOrder().getShip_price()) + "");
        }

    }

    @Override
    public void cancelFormSuccess() {
        ToastUtils.showShort(this, "取消订单成功");
    }

    @Override
    public void ensureGainGoodsSuccess() {
        formStatus = Constant.TRANSACTION_SUCCESS_NO_COMMENT;
        updateView();
    }

    @Override
    public void deleteFormSuccess() {
        ToastUtils.showShort(this, "删除订单成功");
    }


}
