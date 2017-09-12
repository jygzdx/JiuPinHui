package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.OrderCopyEntity;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.WeChatPayEntity;
import com.jiupin.jiupinhui.manage.PopWinManager;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IFormCopyActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.FormCopyActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IFormCopyActivityView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormCopyActivity extends BaseActivity implements IFormCopyActivityView{

    @BindView(R.id.tv_consignee_name)
    TextView tvConsigneeName;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_coupon_price)
    TextView tvCouponPrice;
    @BindView(R.id.tv_transportation_price)
    TextView tvTransportationPrice;
    @BindView(R.id.tv_payment_money)
    TextView tvPaymentMoney;
    @BindView(R.id.tv_order_money)
    TextView tvOrderMoney;
    @BindView(R.id.tv_submit_order)
    TextView tvSubmitOrder;

    private static final int ALIPAY_STATUS = 1;
    private static final int UNION_PAY_STATUS = 2;
    private static final int WECHAT_PAY_STATUS = 3;
    private static final int SWIPING_CARD_STATUS = 4;
    private static final int MONEY_PAY_STATUS = 5;
    /**
     * 支付宝handle
     */
    private static final int ALIPAY_HANDLE = 201;
    private int paystatus = 1;
    private OrderCopyEntity order;
    private LayoutInflater inflater;
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
                        Intent intent = new Intent(FormCopyActivity.this, MyFormActivity.class);
                        intent.putExtra("orderStatus", "");
                        startActivity(intent);
                        finish();
                    }else{
                        ToastUtils.showShort(mContext,"支付失败");
                        Intent intent = new Intent(FormCopyActivity.this, MyFormActivity.class);
                        intent.putExtra("orderStatus", Constant.WAIT_PAY);
                        startActivity(intent);
                        finish();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private IFormCopyActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_copy);
        ButterKnife.bind(this);

        order = (OrderCopyEntity) getIntent().getSerializableExtra("form");

        api = WXAPIFactory.createWXAPI(mContext, Constant.APP_ID);
        // 将该app注册到微信
        api.registerApp(Constant.APP_ID);

        presenter = new FormCopyActivityPresenterImpl(this);

        inflater = LayoutInflater.from(mContext);

        initView();

        showPayView();

    }

    /**
     * 添加订单数据
     */
    private void initView() {
        if (order == null)
            return;

        initAddress();

        initFormData();
    }

    /**
     * 初始化订单的数据
     */
    private void initFormData() {
        //计算总运费
        double tranPrice = 0.0;

        List<OrderCopyEntity.StoreListInCartBean> storeList = order.getStoreListInCart();
        for (int i = 0; i < storeList.size(); i++) {//遍历所有的店铺
            OrderCopyEntity.StoreListInCartBean store = storeList.get(i);
            View view = inflater.inflate(R.layout.item_order_header, (ViewGroup) tvOrderMoney.getParent(),false);
            ImageView ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
            TextView tvName = (TextView) view.findViewById(R.id.tv_store_name);
            Glide.with(mContext)
                    .load(store.getStoreLogo())
                    .crossFade()
                    .into(ivLogo);
            tvName.setText(store.getStoreName());
            if (view.getParent() != null)
                ((ViewGroup) view.getParent()).removeView(view);
            llContainer.addView(view);

            List<OrderCopyEntity.StoreListInCartBean.OrderListBean> orderList = store.getOrderList();

            for (int j = 0; j < orderList.size(); j++) {//遍历所有的订单
                OrderCopyEntity.StoreListInCartBean.OrderListBean orderListBean = orderList.get(j);
                View orderNumView = inflater.inflate(R.layout.item_order_number, (ViewGroup) tvOrderMoney.getParent(),false);
                TextView tvOrderNumber = (TextView) orderNumView.findViewById(R.id.tv_order_number);
                tvOrderNumber.setText("订单("+(j+1)+")");
                llContainer.addView(orderNumView);

                List<OrderCopyEntity.StoreListInCartBean.OrderListBean.OrderDetailListBean> goodsDetailList
                        = orderListBean.getOrderDetailList();
                for (int k = 0; k < goodsDetailList.size(); k++) {//遍历单品或者套餐的种类有多少，分别展示
                    OrderCopyEntity.StoreListInCartBean.OrderListBean.OrderDetailListBean goodsDetail = goodsDetailList.get(k);
                    View middleView = inflater.inflate(R.layout.item_order_middle,(ViewGroup) tvOrderMoney.getParent(),false);
                    TextView tvGoodsName = (TextView) middleView.findViewById(R.id.tv_goods_name);
                    TextView tvGoodsPrice = (TextView) middleView.findViewById(R.id.tv_goods_price);
                    TextView tvGoodsNumber = (TextView) middleView.findViewById(R.id.tv_goods_number);
                    TextView tvGoodsSpecText = (TextView) middleView.findViewById(R.id.tv_goods_spec_text);
                    ImageView ivGoodsPic = (ImageView) middleView.findViewById(R.id.iv_goods_pic);
                    tvGoodsName.setText(goodsDetail.getProd_name());
                    tvGoodsPrice.setText("￥" + goodsDetail.getProd_price());
                    tvGoodsNumber.setText("x" + goodsDetail.getProd_count());
                    tvGoodsSpecText.setText(goodsDetail.getProd_spec_text());
                    Glide.with(this)
                            .load(goodsDetail.getProd_image())
                            .crossFade()
                            .into(ivGoodsPic);
                    llContainer.addView(middleView);
                }

                //添加每个订单后面的订单号和运费
                View infoView = inflater.inflate(R.layout.item_order_info,(ViewGroup) tvOrderMoney.getParent(),false);
                TextView tvTranPrice = (TextView) infoView.findViewById(R.id.tv_tran_price);
                TextView tvOrderId = (TextView) infoView.findViewById(R.id.tv_order_id);
                TextView tvTime = (TextView) infoView.findViewById(R.id.tv_time);

                tranPrice = tranPrice+orderListBean.getShip_price();
                tvTranPrice.setText("￥" +orderListBean.getShip_price());
                tvTime.setText(TimeUtils.getTime(orderListBean.getAddTime()));
                tvOrderId.setText(orderListBean.getOrder_id());
                llContainer.addView(infoView);
            }
        }

        tvPaymentMoney.setText("￥" +order.getCartPaymentAmount());
        tvOrderMoney.setText("￥" +order.getCartPaymentAmount());
        tvCouponPrice.setText("￥-" +order.getCouponAmount());
        tvTransportationPrice.setText("￥" +tranPrice);
    }

    /**
     * 展示地址
     */
    private void initAddress() {
        tvConsigneeName.setText(order.getAddress().getTrueName());
        tvAddress.setText("收货地址：" + order.getAddress().getArea_main().replace(" ", "") + order.getAddress().getArea_info());
        tvPhoneNumber.setText(order.getAddress().getMobile());
    }

    @OnClick({R.id.iv_back, R.id.iv_more,R.id.tv_submit_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more:
                PopWinManager popWinManager = new PopWinManager(mContext, (View) view.getParent());
                popWinManager.createPopupWindow();
                break;
            case R.id.tv_submit_order:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();// 关闭
                } else {
                    popupWindow.showAtLocation(tvPaymentMoney.getRootView(), Gravity.BOTTOM, 0, 0);// 显示
                    backgroundAlpha(0.7f);
                }
                break;
        }
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
        TextView tvPayAllNumber = (TextView) popupView.findViewById(R.id.tv_pay_all_number);

        //展示支付金额
        tvPayAllNumber.setText("￥" +order.getCartPaymentAmount());
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
                String token = UserInfoManager.getInstance().getToken(mContext);
                if (paystatus == ALIPAY_STATUS) {//支付宝支付
                    presenter.getAlipayInfo(token, order.getCartPaymentId());
                } else if (paystatus == WECHAT_PAY_STATUS) {//微信支付
                    if(api.isWXAppInstalled()){
                        presenter.getWeChatPayInfo(token,order.getCartPaymentId());
                    }else{
                        ToastUtils.showShort(mContext,"微信客户端没有安装，无法调起微信支付");
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

    @Override
    public void alipaySuccess(ResponseBase responseBase) {//支付宝支付
        if (ActivityUtils.isFinish(mContext))return;

        final String orderInfo = responseBase.getData().toString();
        LogUtils.d("orderInfo = " + orderInfo);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(FormCopyActivity.this);
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
        request.extData = "cart";
        boolean isPay = api.sendReq(request);
    }
}
