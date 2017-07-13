package com.jiupin.jiupinhui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.presenter.IOrderActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.OrderActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.utils.WindowUtils;
import com.jiupin.jiupinhui.view.IOrderActivityView;

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

    private IOrderActivityPresenter presenter;

    private List<GoodsEntity> goodsEntityList;

    private LayoutInflater inflater;
    private static final int REQUEST_ADDRESS_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        presenter = new OrderActivityPresenterImpl(this);
        String token = (String) SPUtils.get(this, SPUtils.LOGIN_TOKEN, "");
        presenter.getDefaultAddress(token);

        Bundle bundle = getIntent().getExtras();
        goodsEntityList = (List<GoodsEntity>)bundle.getSerializable("list");
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(mContext);
        initGoodsData();
    }

    /**
     * 根据后台返回的数据，添加各种商品数量
     */
    private void initGoodsData() {
        for (int i = 0; i <= goodsEntityList.size()-1; i++) {
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
            tvStoreName.setText(goodsEntity.getData().getStore_name());
            tvGoodsName.setText(goodsEntity.getData().getGoods_name());
            tvGoodsPrice.setText("￥"+goodsEntity.getData().getStore_price());
            tvTotalNumber.setText(goodsEntity.getData().getCount()+"");
            tvGoodsNumber.setText("x"+goodsEntity.getData().getCount());
            double totalPrice = goodsEntity.getData().getStore_price()*goodsEntity.getData().getCount();
            tvTotalPrice.setText(totalPrice+"");
            Glide.with(this)
                    .load(goodsEntity.getData().getPath())
                    .crossFade()
                    .into(ivGoodsPic);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_express_way, R.id.btn_close,
            R.id.view_bg, R.id.tv_transport_insurance,
            R.id.btn_negative, R.id.btn_positive, R.id.tv_submit_order,
            R.id.ll_address
    })
    public void onViewClicked(View view) {
        int screenHeight = WindowUtils.getWindowHeight(this);
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_address:
                Intent intent = new Intent(OrderActivity.this, ManageAddressActivity.class);
                startActivityForResult(intent, REQUEST_ADDRESS_CODE);
                break;
            case R.id.tv_submit_order:
                //弹出选择保险弹出窗
                rlPayPopupWindow.setVisibility(View.VISIBLE);
                viewBg.setVisibility(View.VISIBLE);
                showAnimator(rlPayPopupWindow, screenHeight, 0f);
                showAlphaAnimator(0f, 0.6f);
                break;
            case R.id.view_bg:
                if (rlExpressPopupWindow.getVisibility() == View.VISIBLE) {
                    hidePopupWindow(rlExpressPopupWindow);//隐藏快递弹窗
                } else if (rlInsurancePopupWindow.getVisibility() == View.VISIBLE) {
                    hidePopupWindow(rlInsurancePopupWindow);//隐藏保险弹窗
                } else if (rlPayPopupWindow.getVisibility() == View.VISIBLE) {
                    hidePopupWindow(rlPayPopupWindow);//隐藏支付弹窗
                }
                break;
            case R.id.tv_transport_insurance:
                //弹出选择保险弹出窗
                rlInsurancePopupWindow.setVisibility(View.VISIBLE);
                viewBg.setVisibility(View.VISIBLE);
                showAnimator(rlInsurancePopupWindow, screenHeight, 0f);
                showAlphaAnimator(0f, 0.6f);
                break;
            case R.id.tv_express_way:
                //弹出选择快递方式弹出窗
                rlExpressPopupWindow.setVisibility(View.VISIBLE);
                viewBg.setVisibility(View.VISIBLE);
                showAnimator(rlExpressPopupWindow, screenHeight, 0f);
                showAlphaAnimator(0f, 0.6f);
                break;
            case R.id.btn_close:
                //处理关闭交通方式
                hidePopupWindow(rlExpressPopupWindow);
                break;
            case R.id.btn_negative:
                //处理取消保险费方式
                hidePopupWindow(rlInsurancePopupWindow);
                break;
            case R.id.btn_positive:
                //处理确定保险费方式
                hidePopupWindow(rlInsurancePopupWindow);
                break;
        }
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
            Bundle bundle = data.getExtras();
            String address = bundle.getString("address");
            String phone = bundle.getString("phone");
            String name = bundle.getString("name");
            tvConsigneeName.setText(name);
            tvAddress.setText("收货地址："+address);
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
        tvConsigneeName.setText(addressEntity.getTrueName());
        tvAddress.setText("收货地址："+addressEntity.getArea_main().replace(" ", "") + addressEntity.getArea_info());
        tvPhoneNumber.setText(addressEntity.getMobile());
    }
}
