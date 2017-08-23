package com.jiupin.jiupinhui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.presenter.IPackageActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.PackageActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IPackageActivityView;
import com.jiupin.jiupinhui.widget.GoodsShowView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 废弃
 */
public class PackageActivity extends AppCompatActivity implements IPackageActivityView {

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
    @BindView(R.id.rb_month)
    RadioButton rbMonth;
    @BindView(R.id.rb_quarter)
    RadioButton rbQuarter;
    @BindView(R.id.rb_half_year)
    RadioButton rbHalfYear;
    @BindView(R.id.rb_year)
    RadioButton rbYear;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.rg_love)
    RadioGroup rgLove;
    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_woman)
    RadioButton rbWoman;
    @BindView(R.id.wv_webview)
    WebView wvWebview;
    private GoodsShowView goodsShowView;

    private IPackageActivityPresenter presenter;

    private List<GoodsEntity.DataBean.Detail> details;

    private GoodsEntity mGoodsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        ButterKnife.bind(this);

        presenter = new PackageActivityPresenterImpl(this);
        int id = getIntent().getExtras().getInt("id");
        presenter.getGoodsInfo(id);

        initGoodsShowView();

        initWebView();

        initListener();
    }

    private void initListener() {
        rgLove.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int i = 0;
                switch (checkedId) {
                    case R.id.rb_man:
                        i = 0;
                        break;
                    case R.id.rb_woman:
                        i = 1;
                        break;
                }

                if (mGoodsEntity.getData().getSpecifications().size() <= 1) {
                    return;
                }
                int rb = getMainChecked();
                GoodsEntity.DataBean.Detail detail = getDetail(mGoodsEntity.getData().getSpecifications().get(0).getProperties().get(rb).getId(), mGoodsEntity.getData().getSpecifications().get(1).getProperties().get(i).getId());
                tvGoodsDownPrice.setText(detail.getPrice());
                tvGoodsRealPrice.setText(detail.getMemberPrice());
            }
        });
    }

    private int getMainChecked() {
        if (rbMonth.isChecked()) {
            return 0;
        } else if (rbQuarter.isChecked()) {
            return 1;
        } else if (rbHalfYear.isChecked()) {
            return 2;
        } else if (rbYear.isChecked()) {
            return 3;
        }
        return 0;
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
        //wvWebview.setNestedScrollingEnabled(false);

        WebSettings setting = wvWebview.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        wvWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
    }

    private void initGoodsShowView() {
        goodsShowView = new GoodsShowView(this, true);
        llGoodsShow.addView(goodsShowView);
    }

    @Override
    public void setData(GoodsEntity goodsEntity) {

        mGoodsEntity = goodsEntity;

        wvWebview.loadData(getHtmlData(goodsEntity.getData().getGoods_details()), "text/html; charset=utf-8", "utf-8");

        goodsShowView.loadAD(goodsEntity.getData());

        tvGoodsDownPrice.setText(goodsEntity.getData().getStore_price() + "");
        tvGoodsRealPrice.setText(goodsEntity.getData().getGoods_price() + "");
        tvGoodsName.setText(goodsEntity.getData().getGoods_name());
        tvCompanyName.setText(goodsEntity.getData().getStore_name());

        String detail = goodsEntity.getData().getGoods_inventory_detail();
        String newDetail = detail.replace("\\", "");
        LogUtils.d("newDetail = " + newDetail);
        Gson gson = new Gson();
        details = gson.fromJson(newDetail, new TypeToken<List<GoodsEntity.DataBean.Detail>>() {
        }.getType());
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

    @OnClick({R.id.rb_month, R.id.rb_quarter, R.id.rb_half_year, R.id.rb_year})
    public void onViewClicked(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.rb_month:
                setChecked(rbMonth);
                i = 0;
                break;
            case R.id.rb_quarter:
                setChecked(rbQuarter);
                i = 1;
                break;
            case R.id.rb_half_year:
                setChecked(rbHalfYear);
                i = 2;
                break;
            case R.id.rb_year:
                setChecked(rbYear);
                i = 3;
                break;
        }
        GoodsEntity.DataBean.Detail detail = null;
        if (mGoodsEntity.getData().getSpecifications().size() <= 1) {
            return;
        }
        if (R.id.rb_man == rgLove.getCheckedRadioButtonId()) {
            detail = getDetail(mGoodsEntity.getData().getSpecifications().get(0).getProperties().get(i).getId(), mGoodsEntity.getData().getSpecifications().get(1).getProperties().get(0).getId());
        } else if (R.id.rb_woman == rgLove.getCheckedRadioButtonId()) {
            detail = getDetail(mGoodsEntity.getData().getSpecifications().get(0).getProperties().get(i).getId(), mGoodsEntity.getData().getSpecifications().get(1).getProperties().get(1).getId());
        }

        tvGoodsDownPrice.setText(detail.getPrice());
        tvGoodsRealPrice.setText(detail.getMemberPrice());
    }

    private GoodsEntity.DataBean.Detail getDetail(int id, int id1) {
        for (int i = 0; i < details.size(); i++) {
            if (details.get(i).getId().contains(id + "") && details.get(i).getId().contains(id1 + "")) {
                return details.get(i);
            }
        }
        return null;
    }

    private void setChecked(RadioButton rb) {
        rbMonth.setChecked(false);
        rbQuarter.setChecked(false);
        rbHalfYear.setChecked(false);
        rbYear.setChecked(false);
        rb.setChecked(true);
    }
}
