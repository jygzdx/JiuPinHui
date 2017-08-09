package com.jiupin.jiupinhui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.GoodsBack;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.entity.OrderSubmitEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IOrderActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.OrderActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.utils.SoftKeyboardUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.utils.WindowUtils;
import com.jiupin.jiupinhui.view.IOrderActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单页面
 */
public class OrderActivity extends BaseActivity implements IOrderActivityView {
    private static final String TAG = "OrderActivity";

    private static final int ALIPAY_STATUS = 1;
    private static final int UNION_PAY_STATUS = 2;
    private static final int WECHAT_PAY_STATUS = 3;
    private static final int SWIPING_CARD_STATUS = 4;
    private static final int MONEY_PAY_STATUS = 5;
    private int paystatus = 1;

    @BindView(R.id.cb_express_radio)
    CheckBox cbExpressRadio;
    @BindView(R.id.cb_insurance_radio)
    CheckBox cbInsuranceRadio;
    @BindView(R.id.tv_consignee_name)
    TextView tvConsigneeName;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_order_container)
    LinearLayout llOrderContainer;
    @BindView(R.id.tv_express_way)
    TextView tvExpressWay;
    @BindView(R.id.rl_express_popup_window)
    RelativeLayout rlExpressPopupWindow;
    @BindView(R.id.btn_close)
    Button btnCloss;
    @BindView(R.id.view_bg)
    View viewBg;
    @BindView(R.id.rl_insurance_popup_window)
    RelativeLayout rlInsurancePopupWindow;
    @BindView(R.id.tv_transport_insurance)
    TextView tvTransportInsurance;
    @BindView(R.id.btn_negative)
    Button btnNegative;
    @BindView(R.id.btn_positive)
    Button btnPositive;
    @BindView(R.id.iv_pay_back)
    ImageView ivPayBack;
    @BindView(R.id.iv_alipay_right)
    ImageView ivAlipayRight;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.iv_union_pay_right)
    ImageView ivUnionPayRight;
    @BindView(R.id.rl_union_pay)
    RelativeLayout rlUnionPay;
    @BindView(R.id.iv_wechat_pay_right)
    ImageView ivWechatPayRight;
    @BindView(R.id.rl_wechat_pay)
    RelativeLayout rlWechatPay;
    @BindView(R.id.iv_swiping_card)
    ImageView ivSwipingCard;
    @BindView(R.id.rl_swiping_card)
    RelativeLayout rlSwipingCard;
    @BindView(R.id.iv_money_pay)
    ImageView ivMoneyPay;
    @BindView(R.id.rl_money_pay)
    RelativeLayout rlMoneyPay;
    @BindView(R.id.tv_pay_all_number)
    TextView tvPayAllNumber;
    @BindView(R.id.btn_ensure_pay)
    Button btnEnsurePay;
    @BindView(R.id.rl_pay_popup_window)
    RelativeLayout rlPayPopupWindow;
    @BindView(R.id.tv_submit_order)
    TextView tvSubmitOrder;
    @BindView(R.id.et_buyer_msg)
    EditText etBuyerMsg;
    @BindView(R.id.tv_order_money)
    TextView tvOrderMoney;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.tv_add_address)
    TextView tvAddAddress;

    private IOrderActivityPresenter presenter;

    private List<GoodsEntity> goodsEntityList;

    private LayoutInflater inflater;
    private static final int REQUEST_ADDRESS_CODE = 101;
    private String token;
    private String addressId;
    private String selectedPrice;
    private double payAllNumber;
    private OrderSubmitEntity orderSubmitEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        presenter = new OrderActivityPresenterImpl(this);
        token = (String) SPUtils.get(this, SPUtils.LOGIN_TOKEN, "");
        presenter.getDefaultAddress(token);

        Bundle bundle = getIntent().getExtras();
        goodsEntityList = (List<GoodsEntity>) bundle.getSerializable("list");

        selectedPrice = bundle.getString("selectedPrice");

        inflater = LayoutInflater.from(mContext);
        initGoodsData();
    }

    /**
     * 根据传递过来的数据，添加各种商品数量
     */
    private void initGoodsData() {
        int price = Integer.parseInt(selectedPrice);
        for (int i = 0; i <= goodsEntityList.size() - 1; i++) {
            View view = inflater.inflate(R.layout.order_container_item, null);
            TextView tvStoreName = (TextView) view.findViewById(R.id.tv_store_name);
            TextView tvGoodsName = (TextView) view.findViewById(R.id.tv_goods_name);
            TextView tvGoodsPrice = (TextView) view.findViewById(R.id.tv_goods_price);
            TextView tvTotalNumber = (TextView) view.findViewById(R.id.tv_total_number);
            TextView tvGoodsNumber = (TextView) view.findViewById(R.id.tv_goods_number);
            TextView tvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);
            ImageView ivGoodsPic = (ImageView) view.findViewById(R.id.iv_goods_pic);
            llOrderContainer.addView(view);
            GoodsEntity goodsEntity = goodsEntityList.get(i);
            tvStoreName.setText(goodsEntity.getData().getStoreInfo().getStore_name());
            tvGoodsName.setText(goodsEntity.getData().getGoods_name());
            tvGoodsPrice.setText("￥" + selectedPrice);
            tvTotalNumber.setText(goodsEntity.getData().getCount() + "");
            tvGoodsNumber.setText("x" + goodsEntity.getData().getCount());


            double totalPrice = price * goodsEntity.getData().getCount();
            tvTotalPrice.setText(totalPrice + "");
            Glide.with(this)
                    .load(goodsEntity.getData().getPath())
                    .crossFade()
                    .into(ivGoodsPic);
        }

        payAllNumber = price * goodsEntityList.get(0).getData().getCount();
        tvOrderMoney.setText("￥" + payAllNumber);
    }

    @OnClick({R.id.iv_back, R.id.ll_express_way, R.id.btn_close,
            R.id.view_bg, R.id.ll_transport_insurance,
            R.id.btn_negative, R.id.btn_positive, R.id.tv_submit_order,
            R.id.ll_address, R.id.cb_express_radio, R.id.cb_insurance_radio,
            R.id.tv_add_address
    })
    public void onViewClicked(View view) {
        SoftKeyboardUtils.hideSoftKeyboard(OrderActivity.this);
        int screenHeight = WindowUtils.getWindowHeight(this);
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.cb_insurance_radio://点击保险费

                LogUtils.d("checked = " + cbInsuranceRadio.isChecked());
                break;
            case R.id.cb_express_radio://点击运费

                break;
            case R.id.ll_address://修改地址
                Intent intent = new Intent(OrderActivity.this, ManageAddressActivity.class);
                intent.putExtra("fromActivity", "OrderActivity");
                startActivityForResult(intent, REQUEST_ADDRESS_CODE);
                break;
            case R.id.tv_add_address://修改地址
                Intent intent1 = new Intent(OrderActivity.this, ManageAddressActivity.class);
                intent1.putExtra("fromActivity", "OrderActivity");
                startActivityForResult(intent1, REQUEST_ADDRESS_CODE);
                break;
            case R.id.tv_submit_order://提交订单
//                if (cbExpressRadio.isChecked()) {//是否有选择快递方式
                    //弹出选择支付方式弹出窗
                    rlPayPopupWindow.setVisibility(View.VISIBLE);
                    viewBg.setVisibility(View.VISIBLE);
                    showAnimator(rlPayPopupWindow, screenHeight, 0f);
                    showAlphaAnimator(0f, 0.6f);

                    tvPayAllNumber.setText("￥" + payAllNumber);

                    String userId = (String) SPUtils.get(this, SPUtils.USER_ID, "");
                    String storeId = goodsEntityList.get(0).getData().getGoods_store_id() + "";
                    String msg = etBuyerMsg.getText().toString();
                    String couponInfoId = "";
                    String order_type = "app商城";
                    List<GoodsBack> goodsBackList = new ArrayList<>();
                    for (int i = 0; i < goodsEntityList.size(); i++) {
                        GoodsBack goodsBack = new GoodsBack();
                        goodsBack.setId(goodsEntityList.get(i).getData().getId());
                        goodsBack.setCount(goodsEntityList.get(i).getData().getCount());
                        if (goodsEntityList.get(i).getData().getIs_meal() == 1) {
                            String member = goodsEntityList.get(i).getData().getSelectedMemberId() == 0 ? "" : goodsEntityList.get(i).getData().getSelectedMemberId() + "_";
                            String type = goodsEntityList.get(i).getData().getSelectedTypeId() == 0 ? "" : goodsEntityList.get(i).getData().getSelectedTypeId() + "_";
                            goodsBack.setSpec_id(member + type);
                        } else {
                            goodsBack.setSpec_id("");
                        }
                        goodsBackList.add(goodsBack);
                    }

                    String goodList = new Gson().toJson(goodsBackList);
                    LogUtils.d("goodList->" + goodList);
                    token = UserInfoManager.getInstance().getToken(this);
                    presenter.submitForm(userId, storeId, token, msg, couponInfoId, order_type, addressId + "", goodList);
//                } else {
//                    ToastUtils.showShort(this, "请选择配送方式");
//                }

                break;
            case R.id.view_bg:
                if (rlExpressPopupWindow.getVisibility() == View.VISIBLE) {
                    hidePopupWindow(rlExpressPopupWindow);//隐藏快递弹窗
                } else if (rlInsurancePopupWindow.getVisibility() == View.VISIBLE) {
                    hidePopupWindow(rlInsurancePopupWindow);//隐藏保险弹窗
                } else if (rlPayPopupWindow.getVisibility() == View.VISIBLE) {
                    hidePopupWindow(rlPayPopupWindow);//隐藏支付弹窗
                    //订单已经提交
                    //跳转到订单详情
                    if (orderSubmitEntity != null) {
                        gotoFormParticularActivity();
                    }

                }
                break;
            case R.id.ll_transport_insurance:
                //弹出选择保险弹出窗
                rlInsurancePopupWindow.setVisibility(View.VISIBLE);
                viewBg.setVisibility(View.VISIBLE);
                showAnimator(rlInsurancePopupWindow, screenHeight, 0f);
                showAlphaAnimator(0f, 0.6f);
                break;
            case R.id.ll_express_way:
                //弹出选择快递方式弹出窗
                rlExpressPopupWindow.setVisibility(View.VISIBLE);
                viewBg.setVisibility(View.VISIBLE);
                showAnimator(rlExpressPopupWindow, screenHeight, 0f);
                showAlphaAnimator(0f, 0.6f);
                break;
            case R.id.btn_close:
                //处理关闭交通方式
                if (cbExpressRadio.isChecked()) {
                    tvExpressWay.setText("快递免邮");
                }

                hidePopupWindow(rlExpressPopupWindow);
                break;
            case R.id.btn_negative:
                //处理取消保险费方式
                hidePopupWindow(rlInsurancePopupWindow);
                break;
            case R.id.btn_positive:
                //处理确定保险费方式
                if (cbInsuranceRadio.isChecked()) {
                    tvTransportInsurance.setText("已投保退货运费险");
                }
                hidePopupWindow(rlInsurancePopupWindow);
                break;
        }
    }

    /**
     * 跳转到订单详情
     */
    private void gotoFormParticularActivity() {
        Intent intent = new Intent(mContext, FormParticularActivity.class);
        intent.putExtra("status", Constant.WAIT_PAY);
        intent.putExtra("orderId", orderSubmitEntity.getOrder().getId() + "");
        startActivity(intent);
    }

    @OnClick({R.id.iv_pay_back, R.id.rl_alipay, R.id.rl_union_pay, R.id.rl_wechat_pay, R.id.rl_swiping_card, R.id.rl_money_pay, R.id.btn_ensure_pay})
    public void onPayClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pay_back:
                hidePopupWindow(rlPayPopupWindow);
                break;
            case R.id.rl_alipay:
                paystatus = ALIPAY_STATUS;
                showRight(ivAlipayRight);
                break;
            case R.id.rl_union_pay:
                paystatus = UNION_PAY_STATUS;
                showRight(ivUnionPayRight);
                break;
            case R.id.rl_wechat_pay:
                paystatus = WECHAT_PAY_STATUS;
                showRight(ivWechatPayRight);
                break;
            case R.id.rl_swiping_card:
                paystatus = SWIPING_CARD_STATUS;
                showRight(ivSwipingCard);
                break;
            case R.id.rl_money_pay:
                paystatus = MONEY_PAY_STATUS;
                showRight(ivMoneyPay);
                break;
            case R.id.btn_ensure_pay:
                //通过paystatus处理不同的支付方式
                ToastUtils.showShort(mContext, "点击了-->" + paystatus);
                hidePopupWindow(rlPayPopupWindow);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADDRESS_CODE && resultCode == Activity.RESULT_OK) {
            llAddress.setVisibility(View.VISIBLE);
            tvAddAddress.setVisibility(View.GONE);
            Bundle bundle = data.getExtras();
            String address = bundle.getString("address");
            String phone = bundle.getString("phone");
            String name = bundle.getString("name");
            addressId = bundle.getString("id");
            tvConsigneeName.setText(name);
            tvAddress.setText("收货地址：" + address);
            tvPhoneNumber.setText(phone);
        }


    }

    public void showRight(View view) {
        ivAlipayRight.setVisibility(View.GONE);
        ivUnionPayRight.setVisibility(View.GONE);
        ivWechatPayRight.setVisibility(View.GONE);
        ivMoneyPay.setVisibility(View.GONE);
        ivSwipingCard.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏popupwindow
     */
    public void hidePopupWindow(final View view) {
        int screenHeight = WindowUtils.getWindowHeight(this);
        ObjectAnimator animator = showAnimator(view, 0f, screenHeight);
        showAlphaAnimator(0.6f, 0f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                viewBg.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 展示动画
     *
     * @param fromY 启示y轴
     * @param toY   结束y轴
     *              return ObjectAnimator
     */
    public ObjectAnimator showAnimator(View view, float fromY, float toY) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", fromY, toY);
        animator.setDuration(300)
                .start();
        return animator;
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha
     * @param toAlpha
     */
    public void showAlphaAnimator(float fromAlpha, float toAlpha) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewBg, "alpha", fromAlpha, toAlpha);
        animator.setDuration(300)
                .start();
    }

    @Override
    public void setData(AddressEntity addressEntity) {
        if (addressEntity == null) {
            tvAddAddress.setVisibility(View.VISIBLE);
            llAddress.setVisibility(View.GONE);
        } else {
            llAddress.setVisibility(View.VISIBLE);
            tvAddAddress.setVisibility(View.GONE);
            tvConsigneeName.setText(addressEntity.getTrueName());
            tvAddress.setText("收货地址：" + addressEntity.getArea_main().replace(" ", "") + addressEntity.getArea_info());
            tvPhoneNumber.setText(addressEntity.getMobile());
            addressId = addressEntity.getId() + "";
        }

    }

    @Override
    public void submitFormSuccess(OrderSubmitEntity orderSubmitEntity) {
        LogUtils.d("submitFormSuccess");
        this.orderSubmitEntity = orderSubmitEntity;
    }

    @Override
    public void submitFormFail() {
        LogUtils.d("submitFormFail");
    }
}
