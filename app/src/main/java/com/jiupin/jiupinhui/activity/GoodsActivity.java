package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.widget.GoodsShowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情页
 */
public class GoodsActivity extends AppCompatActivity {
    private static final String TAG = "GoodsActivity";
    @BindView(R.id.ll_goods_show)
    LinearLayout llGoodsShow;
    @BindView(R.id.tv_goods_down_price)
    TextView tvGoodsDownPrice;
    @BindView(R.id.tv_goods_real_price)
    TextView tvGoodsRealPrice;
    @BindView(R.id.btn_goods_collecting_start)
    Button btnGoodsCollectingStart;
    @BindView(R.id.btn_goods_share_start)
    Button btnGoodsShareStart;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_express_price)
    TextView tvExpressPrice;
    @BindView(R.id.tv_sale_number)
    TextView tvSaleNumber;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_get_grade)
    TextView tvGetGrade;
    @BindView(R.id.ll_get_grade)
    LinearLayout llGetGrade;
    @BindView(R.id.tv_get_grade2)
    TextView tvGetGrade2;
    @BindView(R.id.ll_get_coupon)
    LinearLayout llGetCoupon;
    @BindView(R.id.tv_appraise_number)
    TextView tvAppraiseNumber;
    @BindView(R.id.tv_appraise_percent)
    TextView tvAppraisePercent;
    @BindView(R.id.ll_goods_stars)
    LinearLayout llGoodsStars;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.tv_appraise_content)
    TextView tvAppraiseContent;
    @BindView(R.id.ll_appraise_picture)
    LinearLayout llAppraisePicture;
    @BindView(R.id.btn_check_appraise)
    Button btnCheckAppraise;
    @BindView(R.id.iv_store_logo)
    ImageView ivStoreLogo;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_store_description)
    TextView tvStoreDescription;
    @BindView(R.id.tv_attention_number)
    TextView tvAttentionNumber;
    @BindView(R.id.tv_all_product)
    TextView tvAllProduct;
    @BindView(R.id.tv_store_state)
    TextView tvStoreState;
    @BindView(R.id.ll_contact_customer)
    LinearLayout llContactCustomer;
    @BindView(R.id.ll_enter_store)
    LinearLayout llEnterStore;
    @BindView(R.id.nsv_goods_scrollview)
    NestedScrollView nsvGoodsScrollview;
    @BindView(R.id.iv_goods_back)
    ImageView ivGoodsBack;
    @BindView(R.id.iv_goods_middle)
    ImageView ivGoodsMiddle;
    @BindView(R.id.iv_goods_collecting_end)
    ImageView ivGoodsCollectingEnd;
    @BindView(R.id.iv_goods_share_end)
    ImageView ivGoodsShareEnd;
    @BindView(R.id.rl_goods_title)
    RelativeLayout rlGoodsTitle;
    @BindView(R.id.btn_contact_customer)
    Button btnContactCustomer;
    @BindView(R.id.tv_buy_car)
    TextView tvBuyCar;
    @BindView(R.id.tv_buy_car_mark)
    TextView tvBuyCarMark;
    @BindView(R.id.rl_buy_car)
    RelativeLayout rlBuyCar;
    @BindView(R.id.ll_goods_bottom)
    LinearLayout llGoodsBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);

        initGoodsShowView();
        initListener();
    }

    private void initListener() {
        nsvGoodsScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                float height = btnGoodsCollectingStart.getY() + btnGoodsCollectingStart.getHeight();
                float height = btnGoodsCollectingStart.getY();
                if (scrollY <= height && scrollY >= 0) {
                    float scale = (float) scrollY / height;
                    float alpha = 255 * scale;
                    //滑动时改变标题栏的透明度
                    rlGoodsTitle.setBackgroundColor(Color.argb((int) alpha, 0xd3, 0xac, 0x65));

                    //设置收藏和分享按钮是否可见
                    if(ivGoodsCollectingEnd.getVisibility()==View.VISIBLE){
                        ivGoodsCollectingEnd.setVisibility(View.GONE);
                    }
                    if(ivGoodsShareEnd.getVisibility()==View.VISIBLE){
                        ivGoodsShareEnd.setVisibility(View.GONE);
                    }
                } else if (scrollY > height) {
                    //滑动时改变标题栏的透明度
                    rlGoodsTitle.getBackground().setAlpha(255);

                    //设置收藏和分享按钮是否可见
                    if(ivGoodsCollectingEnd.getVisibility()==View.GONE){
                        ivGoodsCollectingEnd.setVisibility(View.VISIBLE);
                    }
                    if(ivGoodsShareEnd.getVisibility()==View.GONE){
                        ivGoodsShareEnd.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void initGoodsShowView() {
        GoodsShowView goodsShowView = new GoodsShowView(this, false);
        llGoodsShow.addView(goodsShowView);
    }

    @OnClick({R.id.btn_contact_customer, R.id.rl_buy_car,R.id.btn_check_appraise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_contact_customer:
                break;
            case R.id.rl_buy_car:
                break;
            case R.id.btn_check_appraise:
                Intent intent = new Intent(this,CommentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
