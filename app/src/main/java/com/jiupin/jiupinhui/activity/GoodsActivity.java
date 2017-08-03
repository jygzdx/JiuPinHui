package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IGoodsActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.GoodsActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.DensityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IGoodsActivityView;
import com.jiupin.jiupinhui.widget.GoodsShowView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情页
 */
public class GoodsActivity extends BaseActivity implements IGoodsActivityView {
    private static final String TAG = "GoodsActivity";
    @BindView(R.id.ll_goods_show)
    LinearLayout llGoodsShow;
    @BindView(R.id.tv_goods_down_price)
    TextView tvGoodsDownPrice;
    @BindView(R.id.tv_goods_real_price)
    TextView tvGoodsRealPrice;
    @BindView(R.id.tv_goods_count)
    TextView tvGoodsCount;
    @BindView(R.id.ll_package_container)
    LinearLayout llPackageContainer;
    @BindView(R.id.btn_goods_collecting_start)
    Button btnGoodsCollectingStart;
    @BindView(R.id.btn_goods_share_start)
    Button btnGoodsShareStart;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_express_price)
    TextView tvExpressPrice;
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
    @BindView(R.id.rl_appraise)
    RelativeLayout rlAppraise;
    @BindView(R.id.tv_appraise_percent)
    TextView tvAppraisePercent;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.tv_appraise_content)
    TextView tvAppraiseContent;
    @BindView(R.id.ll_appraise_picture)
    LinearLayout llAppraisePicture;
    @BindView(R.id.tv_no_appraise)
    TextView tvNoAppraise;
    @BindView(R.id.btn_check_appraise)
    Button btnCheckAppraise;
    @BindView(R.id.iv_store_logo)
    ImageView ivStoreLogo;
    @BindView(R.id.ll_goods_stars)
    LinearLayout llGoodStars;
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
    @BindView(R.id.ll_goods_bottom)
    LinearLayout llGoodsBottom;
    @BindView(R.id.wv_webview)
    WebView wvWebview;
    private GoodsShowView goodsShowView;
    private IGoodsActivityPresenter presenter;
    private GoodsEntity goodsEntity;
    private LayoutInflater inflater;
    private boolean isQuarter;
    private boolean isPreference;
    private int quarterChecked;
    private int preferenceChecked;
    private List<GoodsEntity.DataBean.Detail> details;
    private GoodsEntity.DataBean.Detail showDetail;
    private int webviewTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);

        inflater = LayoutInflater.from(this);


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

        wvWebview.setWebViewClient(new WebViewClient() {
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

                float height = 900f;
                if (scrollY <= height && scrollY >= 0) {
                    float scale = (float) scrollY / height;
                    float alpha = 255 * scale;
                    //滑动时改变标题栏的透明度
                    rlGoodsTitle.setBackgroundColor(Color.argb((int) alpha, 0xd3, 0xac, 0x65));
                } else if (scrollY > height) {
                    //滑动时改变标题栏的透明度
                    rlGoodsTitle.getBackground().setAlpha(255);
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        webviewTop = wvWebview.getTop();
    }

    private void initGoodsShowView() {
        goodsShowView = new GoodsShowView(this, true);
        llGoodsShow.addView(goodsShowView);
    }

    @OnClick({R.id.btn_contact_customer, R.id.btn_check_appraise, R.id.btn_now_pay, R.id.ll_Store_customer,R.id.ll_back_main,
            R.id.iv_goods_back,R.id.tv_to_particulars
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_goods_back:
                finish();
                break;
            case R.id.tv_to_particulars://滑动到商品详情位置
                nsvGoodsScrollview.smoothScrollTo(0,wvWebview.getTop());

                break;
            case R.id.btn_contact_customer:
                Intent serverIntent = new Intent(mContext, ServerActivity.class);
                startActivity(serverIntent);
                break;
            case R.id.ll_Store_customer:
                Intent serverIntent2 = new Intent(mContext, ServerActivity.class);
                startActivity(serverIntent2);
                break;
            case R.id.ll_back_main:
                Intent mainIntent = new Intent(mContext, MainActivity.class);
                startActivity(mainIntent);
                break;

            case R.id.btn_check_appraise:
                Intent intent = new Intent(this, CommentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_now_pay://立即购买
                if (UserInfoManager.getInstance().isLogin()) {
                    if (goodsEntity.getData().getIs_meal() == 1) {
                        if (isQuarter && isPreference) {
                            if (quarterChecked != 0 && preferenceChecked != 0) {
                                Intent intent3 = new Intent(this, OrderActivity.class);
                                Bundle bundle = new Bundle();
                                goodsEntity.getData().setCount(1);
                                List<GoodsEntity> goodsEntityList = new ArrayList<>();
                                goodsEntityList.add(goodsEntity);
                                bundle.putString("selectedPrice", showDetail.getPrice());
                                bundle.putSerializable("list", (Serializable) goodsEntityList);
                                intent3.putExtras(bundle);
                                startActivity(intent3);
                            } else {
                                ToastUtils.showShort(this, "请选择商品属性");
                            }
                            return;
                        }
                        if (isQuarter) {
                            if (!isPreference) {
                                if (quarterChecked != 0) {
                                    Intent intent3 = new Intent(this, OrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    goodsEntity.getData().setCount(1);
                                    List<GoodsEntity> goodsEntityList = new ArrayList<>();
                                    goodsEntityList.add(goodsEntity);
                                    bundle.putString("selectedPrice", showDetail.getPrice());
                                    bundle.putSerializable("list", (Serializable) goodsEntityList);
                                    intent3.putExtras(bundle);
                                    startActivity(intent3);
                                } else {
                                    ToastUtils.showShort(this, "请选择商品属性");
                                }
                                return;
                            }
                        }


                    }
                } else {//没有登录
                    gotoLoginActivity();
                }
                break;
        }
    }

    private void gotoLoginActivity() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        this.startActivity(intentLogin);
    }

    @Override
    public void setData(final GoodsEntity goodsEntity) {
        this.goodsEntity = goodsEntity;
        wvWebview.loadData(getHtmlData(goodsEntity.getData().getGoods_details()), "text/html; charset=utf-8", "utf-8");

        goodsShowView.loadAD(goodsEntity.getData());


        //获取评论信息
        presenter.getAppraise(goodsEntity.getData().getId());

        //设置店铺信息
        Glide.with(this)
                .load(goodsEntity.getData().getStoreInfo().getStore_img())
                .crossFade()
                .into(ivStoreLogo);
        tvStoreName.setText(goodsEntity.getData().getStoreInfo().getStore_name());
        tvStoreDescription.setText(goodsEntity.getData().getStoreInfo().getStore_info());
        tvAttentionNumber.setText(goodsEntity.getData().getStoreInfo().getAttention_num()+"");
        tvAllProduct.setText(goodsEntity.getData().getStoreInfo().getGoods_num()+"");
        tvStoreState.setText(goodsEntity.getData().getStoreInfo().getDynamic_num()+"");

        //设置商品详情信息
        tvGoodsDownPrice.setText(goodsEntity.getData().getStore_price() + "");
        tvGoodsRealPrice.setText(goodsEntity.getData().getGoods_price() + "");
        tvGoodsName.setText(goodsEntity.getData().getGoods_name());
        tvGoodsCount.setText("库存：" + goodsEntity.getData().getGoods_inventory());
        String expressPrice = goodsEntity.getData().getGoods_transfee() == 1 ? "免邮" : "自费";
        tvExpressPrice.setText("快递：" + expressPrice);


        final GoodsEntity.DataBean goods = goodsEntity.getData();
        if (goods.getIs_meal() == 1) {//套餐商品
            llGoodsBottom.setVisibility(View.VISIBLE);

            String detail = goodsEntity.getData().getGoods_inventory_detail();
            String newDetail = detail.replace("\\", "");
            LogUtils.d("newDetail = " + newDetail);
            Gson gson = new Gson();
            details = gson.fromJson(newDetail, new TypeToken<List<GoodsEntity.DataBean.Detail>>() {
            }.getType());

            if (goods.getSpecifications().size() > 0) {
                for (int i = 0; i < goods.getSpecifications().size(); i++) {
                    if (goods.getSpecifications().get(i).getId() == 32769) {//购买会员
                        isQuarter = true;
                        View view = inflater.inflate(R.layout.quarter_item, null);
                        final CheckBox cbMonth = (CheckBox) view.findViewById(R.id.cb_month);
                        final CheckBox cbQuarter = (CheckBox) view.findViewById(R.id.cb_quarter);
                        final CheckBox cbHalfYear = (CheckBox) view.findViewById(R.id.cb_half_year);
                        final CheckBox cbYear = (CheckBox) view.findViewById(R.id.cb_year);
                        final int finalI = i;
                        cbMonth.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                quarterChecked = goods.getSpecifications().get(finalI).getProperties().get(0).getId();
                                goodsEntity.getData().setSelectedMemberId(quarterChecked);
                                cbQuarter.setChecked(false);
                                cbHalfYear.setChecked(false);
                                cbYear.setChecked(false);
                                if (preferenceChecked == 0) {
                                    if (!isPreference) {
                                        showDetail = getDetail2(quarterChecked);
                                    }
                                } else {
                                    showDetail = getDetail1(quarterChecked, preferenceChecked);
                                }
                                if (showDetail != null) {
                                    tvGoodsDownPrice.setText(showDetail.getPrice());
                                    tvGoodsRealPrice.setText(showDetail.getMemberPrice());
                                    tvGoodsCount.setText("库存：" + showDetail.getCount());
                                }

                            }
                        });

                        cbQuarter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                quarterChecked = goods.getSpecifications().get(finalI).getProperties().get(1).getId();
                                goodsEntity.getData().setSelectedMemberId(quarterChecked);
                                cbMonth.setChecked(false);
                                cbHalfYear.setChecked(false);
                                cbYear.setChecked(false);

                                if (preferenceChecked == 0) {
                                    if (!isPreference) {
                                        showDetail = getDetail2(quarterChecked);
                                    }
                                } else {
                                    showDetail = getDetail1(quarterChecked, preferenceChecked);
                                }
                                if (showDetail != null) {
                                    tvGoodsDownPrice.setText(showDetail.getPrice());
                                    tvGoodsRealPrice.setText(showDetail.getMemberPrice());
                                    tvGoodsCount.setText("库存：" + showDetail.getCount());
                                }
                            }
                        });
                        cbHalfYear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                quarterChecked = goods.getSpecifications().get(finalI).getProperties().get(2).getId();
                                goodsEntity.getData().setSelectedMemberId(quarterChecked);
                                cbMonth.setChecked(false);
                                cbQuarter.setChecked(false);
                                cbYear.setChecked(false);
                                if (preferenceChecked == 0) {
                                    if (!isPreference) {
                                        showDetail = getDetail2(quarterChecked);
                                    }
                                } else {
                                    showDetail = getDetail1(quarterChecked, preferenceChecked);
                                }
                                if (showDetail != null) {
                                    tvGoodsDownPrice.setText(showDetail.getPrice());
                                    tvGoodsRealPrice.setText(showDetail.getMemberPrice());
                                    tvGoodsCount.setText("库存：" + showDetail.getCount());
                                }
                            }
                        });
                        cbYear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                quarterChecked = goods.getSpecifications().get(finalI).getProperties().get(3).getId();
                                goodsEntity.getData().setSelectedMemberId(quarterChecked);
                                cbMonth.setChecked(false);
                                cbHalfYear.setChecked(false);
                                cbQuarter.setChecked(false);
                                if (preferenceChecked == 0) {
                                    if (!isPreference) {
                                        showDetail = getDetail2(quarterChecked);
                                    }
                                } else {
                                    showDetail = getDetail1(quarterChecked, preferenceChecked);
                                }
                                if (showDetail != null) {
                                    tvGoodsDownPrice.setText(showDetail.getPrice());
                                    tvGoodsRealPrice.setText(showDetail.getMemberPrice());
                                    tvGoodsCount.setText("库存：" + showDetail.getCount());
                                }
                            }
                        });

                        llPackageContainer.addView(view);
                    } else if (goods.getSpecifications().get(i).getId() == 32770) {//酒款偏好
                        isPreference = true;
                        View view = inflater.inflate(R.layout.preference_item, null);
                        final CheckBox cbMan = (CheckBox) view.findViewById(R.id.cb_man);
                        final CheckBox cbWomen = (CheckBox) view.findViewById(R.id.cb_woman);
                        final int finalI = i;
                        cbMan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                preferenceChecked = goods.getSpecifications().get(finalI).getProperties().get(0).getId();
                                goodsEntity.getData().setSelectedTypeId(preferenceChecked);
                                cbWomen.setChecked(false);
                                if (quarterChecked == 0) {
                                    if (!isQuarter) {
                                        showDetail = getDetail2(quarterChecked);
                                    }
                                } else {
                                    showDetail = getDetail1(quarterChecked, preferenceChecked);
                                }
                                if (showDetail != null) {
                                    tvGoodsDownPrice.setText(showDetail.getPrice());
                                    tvGoodsRealPrice.setText(showDetail.getMemberPrice());
                                    tvGoodsCount.setText("库存：" + showDetail.getCount());
                                }

                            }
                        });

                        cbWomen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                preferenceChecked = goods.getSpecifications().get(finalI).getProperties().get(1).getId();
                                goodsEntity.getData().setSelectedTypeId(preferenceChecked);
                                cbMan.setChecked(false);
                                if (quarterChecked == 0) {
                                    if (!isQuarter) {
                                        showDetail = getDetail2(quarterChecked);
                                    }
                                } else {
                                    showDetail = getDetail1(quarterChecked, preferenceChecked);
                                }
                                if (showDetail != null) {
                                    tvGoodsDownPrice.setText(showDetail.getPrice());
                                    tvGoodsRealPrice.setText(showDetail.getMemberPrice());
                                    tvGoodsCount.setText("库存：" + showDetail.getCount());
                                }

                            }
                        });

                        llPackageContainer.addView(view);
                    }

                }
            }
        } else {
            llGoodsBottom.setVisibility(View.GONE);
        }

    }

    @Override
    public void setUserAppraise(List<AppraiseEntity> appraiseList) {
        //设置用户评论信息
        if(appraiseList!=null&&appraiseList.size()>0){
            rlAppraise.setVisibility(View.VISIBLE);
            AppraiseEntity appraise = appraiseList.get(0);
            tvUserNickname.setText(appraise.getUserName());
            tvAppraiseContent.setText(appraise.getEvaluate_info());
            for (int i = 0; i < 5; i++) {//添加星形图片
                if(appraise.getDescription_evaluate()>i){
                    ImageView image = new ImageView(this);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dp2px(this, 20), DensityUtils.dp2px(this, 20));
                    image.setLayoutParams(params);
                    image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    image.setPadding(DensityUtils.dp2px(this, 5),0,DensityUtils.dp2px(this, 5),0);
                    Glide.with(this)
                            .load(R.drawable.star_selected)
                            .into(image);
                    llGoodStars.addView(image);
                }else{
                    ImageView image = new ImageView(this);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dp2px(this, 20), DensityUtils.dp2px(this, 20));
                    image.setLayoutParams(params);
                    image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    image.setPadding(DensityUtils.dp2px(this, 5),0,DensityUtils.dp2px(this, 5),0);
                    Glide.with(this)
                            .load(R.drawable.star_unselected)
                            .into(image);
                    llGoodStars.addView(image);
                }
            }
            //添加用户评论图片
            if(appraise.getImage_list()!=null&&appraise.getImage_list()!=""){
                String[] apps = appraise.getImage_list().split(";");
                for (int i = 0; i < apps.length; i++) {
                    ImageView image = new ImageView(this);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dp2px(this, 80), DensityUtils.dp2px(this, 80));
                    image.setLayoutParams(params);
                    image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    image.setPadding(DensityUtils.dp2px(this, 5),0,DensityUtils.dp2px(this, 5),0);
                    Glide.with(this)
                            .load(apps[i])
                            .crossFade()
                            .into(image);
                    llAppraisePicture.addView(image);
                }
            }
        }else{
            tvNoAppraise.setVisibility(View.VISIBLE);
        }
        
    }

    private GoodsEntity.DataBean.Detail getDetail1(int id, int id1) {
        for (int i = 0; i < details.size(); i++) {
            if (details.get(i).getId().contains(id + "") && details.get(i).getId().contains(id1 + "")) {
                return details.get(i);
            }
        }
        return null;
    }

    private GoodsEntity.DataBean.Detail getDetail2(int id) {
        for (int i = 0; i < details.size(); i++) {
            if (details.get(i).getId().contains(id + "")) {
                return details.get(i);
            }
        }
        return new GoodsEntity.DataBean.Detail();
    }

    /**
     * 设置webview头部
     *
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
