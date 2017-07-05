package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.presenter.IGoodsActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.GoodsActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IGoodsActivityView;
import com.jiupin.jiupinhui.widget.GoodsShowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情页
 */
public class GoodsActivity extends BaseActivity implements IGoodsActivityView{
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
    @BindView(R.id.wv_webview)
    WebView wvWebview;
    private GoodsShowView goodsShowView;
    private IGoodsActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);


        presenter = new GoodsActivityPresenterImpl(this);
        int id = getIntent().getExtras().getInt("id");
        presenter.getGoodsInfo(id);

        initGoodsShowView();
        initListener();
        initWebView();
    }

    /**
     * 初始化webview
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initWebView() {
        //禁止webview进行复制黏贴
        wvWebview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
//        wvWebview.setNestedScrollingEnabled(false);
        wvWebview.getSettings().setJavaScriptEnabled(true);

        wvWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });

//        String html = "";
//        wvWebview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }

    private void initListener() {

        nsvGoodsScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                float height = 300f;
                if (scrollY <= height && scrollY >= 0) {
                    float scale = (float) scrollY / height;
                    float alpha = 255 * scale;
                    //滑动时改变标题栏的透明度
                    rlGoodsTitle.setBackgroundColor(Color.argb((int) alpha, 0xd3, 0xac, 0x65));
//隐藏
                    //设置收藏和分享按钮是否可见
//                    if (ivGoodsCollectingEnd.getVisibility() == View.VISIBLE) {
//                        ivGoodsCollectingEnd.setVisibility(View.GONE);
//                    }
//                    if (ivGoodsShareEnd.getVisibility() == View.VISIBLE) {
//                        ivGoodsShareEnd.setVisibility(View.GONE);
//                    }
                } else if (scrollY > height) {
                    //滑动时改变标题栏的透明度
                    rlGoodsTitle.getBackground().setAlpha(255);

                    //设置收藏和分享按钮是否可见
//                    if (ivGoodsCollectingEnd.getVisibility() == View.GONE) {
//                        ivGoodsCollectingEnd.setVisibility(View.VISIBLE);
//                    }
//                    if (ivGoodsShareEnd.getVisibility() == View.GONE) {
//                        ivGoodsShareEnd.setVisibility(View.VISIBLE);
//                    }
                }
                //                LogUtils.d("scrollX = "+scrollX+",scrollY = "+scrollY+",oldScrollX = "+oldScrollX+",oldScrollY = "+oldScrollY);
                int childHeight = nestedScrollView.getChildAt(0).getHeight();
                int scrollviewHeight = nestedScrollView.getHeight();
                LogUtils.d("childHeight = " + childHeight + ",scrollY = " + scrollY + ",scrollviewHeight = " + scrollviewHeight);
            }
        });
    }

    private void initGoodsShowView() {
        goodsShowView = new GoodsShowView(this, true);
        llGoodsShow.addView(goodsShowView);
    }

    @OnClick({R.id.btn_contact_customer, R.id.rl_buy_car, R.id.btn_check_appraise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_contact_customer:
                Intent intent2 = new Intent(mContext, FamiliarQuestionActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_buy_car:
                Intent intent1 = new Intent(mContext, BuyCartActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_check_appraise:
                Intent intent = new Intent(this, CommentActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setData(GoodsEntity goodsEntity) {

        wvWebview.loadData(getHtmlData(goodsEntity.getData().getGoods_details()), "text/html; charset=utf-8", "utf-8");

        goodsShowView.loadAD(goodsEntity.getData());

        tvGoodsDownPrice.setText(goodsEntity.getData().getStore_price()+"");
        tvGoodsRealPrice.setText(goodsEntity.getData().getGoods_price()+"");
        tvGoodsName.setText(goodsEntity.getData().getGoods_name());
        tvCompanyName.setText(goodsEntity.getData().getStore_name());
    }

    /**
     * 设置webview头部
     * @param bodyHTML
     * @return
     */
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
