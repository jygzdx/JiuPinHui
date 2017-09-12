package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.FormParticularEntity;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.WeChatPayEntity;
import com.jiupin.jiupinhui.manage.PopWinManager;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IFormParticularActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.FormParticularActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IFormParticularActivityView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;
import java.util.Map;

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
//    private RadioButton[] rbArray = new RadioButton[5];

    private static final int ALIPAY_STATUS = 1;
    private static final int UNION_PAY_STATUS = 2;
    private static final int WECHAT_PAY_STATUS = 3;
    private static final int SWIPING_CARD_STATUS = 4;
    private static final int MONEY_PAY_STATUS = 5;
    private int paystatus = 1;

    private String formStatus = "";

    private IFormParticularActivityPresenter presenter;
    private String token;
    private String orderId;

    private FormParticularEntity formParticularEntity;
    private static final int COMMENT_CODE = 1;
    private static final int ALIPAY_HANDLE = 201;
    private PopupWindow popupWindow;
    private IWXAPI api;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ALIPAY_HANDLE:
                    Map<String,String> result = (Map<String, String>) msg.obj;
                    String resultInfo = result.get("resultStatus");
                    if("9000".equals(resultInfo)){
                        ToastUtils.showShort(FormParticularActivity.this,"支付成功");
                        Intent intent = new Intent(FormParticularActivity.this, PaySuccessActivity.class);
                        intent.putExtra("orderId",formParticularEntity.getOrder().getId()+"");
                        startActivity(intent);
                    }else{
                        ToastUtils.showShort(FormParticularActivity.this,"支付失败");
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private double truePrice;
    private TextView tvPayAllNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_particular);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(mContext, Constant.APP_ID);
        // 将该app注册到微信
        api.registerApp(Constant.APP_ID);

        formStatus = getIntent().getExtras().getString("status");

        updateView();
        //初始化支付窗口
        showPayView();

        presenter = new FormParticularActivityPresenterImpl(this);

        token = UserInfoManager.getInstance().getToken(this);

        orderId = getIntent().getExtras().getString("orderId");

        LogUtils.d(TAG, "orderId=" + orderId + " ,token = " + token);
        presenter.getFormInfo(orderId, token);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COMMENT_CODE && resultCode == SendCommentActivity.COMMENT_SUCCESS_RESULT) {
            formStatus = Constant.TRANSACTION_SUCCESS_HAS_COMMENT;
            updateView();
        }
    }

    @OnClick({R.id.btn_left, R.id.btn_right, R.id.tv_contact_customer, R.id.tv_making_phone, R.id.iv_back, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more://弹出右上角窗口
                PopWinManager popupWindow = new PopWinManager(this, (View) view.getParent());
                popupWindow.createPopupWindow();
                break;
            case R.id.tv_contact_customer://联系供应商
                Intent intent = new Intent(this, ChatActivity.class);
                intent.putExtra("orderNum", formParticularEntity.getOrder().getOrder_id());
                startActivity(intent);
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
    private void payMoney() {
        tvPayAllNumber.setText(truePrice +"");
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();// 关闭
            LogUtils.d("dismiss");
        } else {
            popupWindow.showAtLocation(ivBack.getRootView(), Gravity.BOTTOM, 0, 0);// 显示
            LogUtils.d("showAtLocation");
            backgroundAlpha(0.7f);
        }
    }

    /**
     * 取消订单
     */
    private void cancelForm() {

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_content, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setView(view);

        final AlertDialog dialog = builder.create();

        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);

        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);

        tvContent.setText("确定要取消订单？");

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

                //取消订单
                token = UserInfoManager.getInstance().getToken(FormParticularActivity.this);
                presenter.cancelForm(orderId, token);

                //确定

                dialog.dismiss();

            }

        });

        dialog.show();

    }

    /**
     * 删除订单
     */
    private void deleteForm() {

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_content, null);

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
                token = UserInfoManager.getInstance().getToken(FormParticularActivity.this);
                presenter.deleteForm(formParticularEntity.getOrder().getId() + "", token);

                //确定

                dialog.dismiss();

            }

        });

        dialog.show();

    }

    /**
     * 申请售后
     */
    private void applyAfterSale() {
        Intent intent = new Intent(this, ChatActivity.class);
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

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_content, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setView(view);

        final AlertDialog dialog = builder.create();

        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);

        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);

        tvContent.setText("确定收到货了么？");

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
                dialog.dismiss();
                token = UserInfoManager.getInstance().getToken(FormParticularActivity.this);
                presenter.ensureGainGoods(formParticularEntity.getOrder().getId() + "", token);
            }

        });

        dialog.show();

    }

    /**
     * 立即评论
     */
    private void nowComment() {
        Intent intent = new Intent(this, SendCommentActivity.class);
        intent.putExtra("orderId", formParticularEntity.getOrder().getId() + "");
        startActivityForResult(intent, COMMENT_CODE);

    }

    /**
     * 展示支付窗口
     */
    private void showPayView(){
        popupWindow = new PopupWindow(this);
        View popupView = LayoutInflater.from(this).inflate(R.layout.pay_popup_window,null);
        RelativeLayout rlAliPay = (RelativeLayout) popupView.findViewById(R.id.rl_alipay);
        RelativeLayout rlWeChatPay = (RelativeLayout) popupView.findViewById(R.id.rl_wechat_pay);
        ImageView ivPayBack = (ImageView) popupView.findViewById(R.id.iv_pay_back);
        Button btnEnsurePay = (Button) popupView.findViewById(R.id.btn_ensure_pay);
        tvPayAllNumber = (TextView) popupView.findViewById(R.id.tv_pay_all_number);
        final ImageView ivAliPayRight = (ImageView) popupView.findViewById(R.id.iv_alipay_right);
        final ImageView ivWeChatPayRight = (ImageView) popupView.findViewById(R.id.iv_wechat_pay_right);
        rlAliPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //支付宝支付
                paystatus = ALIPAY_STATUS;
                ivAliPayRight.setVisibility(View.VISIBLE);
                ivWeChatPayRight.setVisibility(View.GONE);
            }
        });
        rlWeChatPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //微信支付
                paystatus = WECHAT_PAY_STATUS;
                ivAliPayRight.setVisibility(View.GONE);
                ivWeChatPayRight.setVisibility(View.VISIBLE);
            }
        });
        ivPayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消支付
                popupWindow.dismiss();
            }
        });
        btnEnsurePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                //提交支付
                token = UserInfoManager.getInstance().getToken(FormParticularActivity.this);
                if (paystatus == ALIPAY_STATUS) {//支付宝支付
                    presenter.getAlipayInfo(token, formParticularEntity.getOrder().getOrder_id() + "");
                } else if (paystatus == WECHAT_PAY_STATUS) {//微信支付
                    if(api.isWXAppInstalled()){
                        presenter.getWeChatPayInfo(token,formParticularEntity.getOrder().getOrder_id() + "");
                    }else{
                        ToastUtils.showShort(FormParticularActivity.this,"微信客户端没有安装，无法调起微信支付");
                    }
                }
            }
        });

        popupWindow.setContentView(popupView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupwindow消失的时候恢复成原来的透明度
                backgroundAlpha(1f);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
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
        if (ActivityUtils.isFinish(mContext))return;

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
                    ImageView ivStoreName = (ImageView) view.findViewById(R.id.iv_store_name);
                    TextView tvGoodsName = (TextView) view.findViewById(R.id.tv_goods_name);
                    TextView tvGoodsPrice = (TextView) view.findViewById(R.id.tv_goods_price);
                    TextView tvGoodsFormerPrice = (TextView) view.findViewById(R.id.tv_goods_former_price);
                    TextView tvGoodsNumber = (TextView) view.findViewById(R.id.tv_goods_number);
                    ImageView ivGoodsPic = (ImageView) view.findViewById(R.id.iv_goods_pic);

                    Glide.with(mContext)
                            .load(cartBeanList.get(i).getMain_photo())
                            .crossFade()
                            .into(ivGoodsPic);

                    //返回的数据没有商店头像地址
//                    Glide.with(mContext)
//                            .load(cartBeanList.get(i).getMain_photo()).....
//                            .crossFade()
//                            .into(ivGoodsPic);

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
            truePrice = price - formParticularEntity.getOrder().getShip_price();

            tvPaymentMoney.setText(truePrice + "");
        }

    }

    @Override
    public void cancelFormSuccess() {
        if (ActivityUtils.isFinish(mContext))return;

        ToastUtils.showShort(this, "取消订单成功");
        formStatus = Constant.TRANSACTION_CLOSED;
        updateView();
    }

    @Override
    public void ensureGainGoodsSuccess() {
        if (ActivityUtils.isFinish(mContext))return;

        formStatus = Constant.TRANSACTION_SUCCESS_NO_COMMENT;
        updateView();
    }

    @Override
    public void deleteFormSuccess() {
        ToastUtils.showShort(this, "删除订单成功");
        finish();
    }
    @Override
    public void alipaySuccess(ResponseBase responseBase) {//支付宝支付
        if (ActivityUtils.isFinish(mContext))return;

        final String orderInfo = responseBase.getData().toString();
        LogUtils.d("orderInfo = " + orderInfo);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(FormParticularActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                LogUtils.d("result = " + result);
                Message msg = new Message();
                msg.what = ALIPAY_HANDLE;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void weChatPaySuccess(WeChatPayEntity weChatPayEntity) {
        if (ActivityUtils.isFinish(mContext))return;

        PayReq request = new PayReq();
        request.appId = weChatPayEntity.getAppid();
        request.partnerId = weChatPayEntity.getPartnerid();
        request.prepayId= weChatPayEntity.getPrepayid();
        request.packageValue = weChatPayEntity.getPackageX();
        request.nonceStr= weChatPayEntity.getNoncestr();
        request.timeStamp= weChatPayEntity.getTimestamp();
        request.sign= weChatPayEntity.getSign();
        request.extData = formParticularEntity.getOrder().getId()+"";
        boolean isPay = api.sendReq(request);
    }


}
