package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.entity.WineShowBackEntity;
import com.jiupin.jiupinhui.presenter.IWineShowActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.WineShowActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.view.IWineShowActivityView;
import com.jiupin.jiupinhui.widget.GoodsShowView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WineShowActivity extends BaseActivity implements IWineShowActivityView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.ll_parameter_container)
    LinearLayout llParameterContainer;
    @BindView(R.id.ll_banner)
    LinearLayout llBanner;
    private IWineShowActivityPresenter presenter;
    private GoodsShowView goodsShowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_show);
        ButterKnife.bind(this);

        goodsShowView = new GoodsShowView(this, true);
        llBanner.addView(goodsShowView);

        String goodsId = getIntent().getExtras().getString("goodsId");
        presenter = new WineShowActivityPresenterImpl(this);
        presenter.getGoodsInfo(goodsId);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setData(GoodsEntity goodsEntity) {
        if (ActivityUtils.isFinish(this))
            return;
        tvName.setText(goodsEntity.getData().getGoods_name());
        tvPrice.setText(goodsEntity.getData().getStore_price() + "");
        Gson gson = new Gson();
        List<WineShowBackEntity> wineList = gson.fromJson(goodsEntity.getData().getGoods_details(),
                new TypeToken<List<WineShowBackEntity>>() {}.getType());
        WineShowBackEntity wineShowBackEntity = wineList.get(0);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        int size = wineShowBackEntity.getSpecification_item_list().size();
        for (int i = 0; i < size; i++) {
            if (i%2==1){
                View view = inflater.inflate(R.layout.item_wine_show,llParameterContainer,false);
                TextView tvKey1 = (TextView) view.findViewById(R.id.tv_key1);
                TextView tvValue1 = (TextView) view.findViewById(R.id.tv_value1);
                TextView tvKey2 = (TextView) view.findViewById(R.id.tv_key2);
                TextView tvValue2 = (TextView) view.findViewById(R.id.tv_value2);
                tvKey1.setText(wineShowBackEntity.getSpecification_item_list().get(i-1).getTitle());
                tvValue1.setText(wineShowBackEntity.getSpecification_item_list().get(i-1).getVal());
                tvKey2.setText(wineShowBackEntity.getSpecification_item_list().get(i).getTitle());
                tvValue2.setText(wineShowBackEntity.getSpecification_item_list().get(i).getVal());
                llParameterContainer.addView(view);
            }
        }
        if(size%2==1){
            View view = inflater.inflate(R.layout.item_wine_show,llParameterContainer,false);
            TextView tvKey1 = (TextView) view.findViewById(R.id.tv_key1);
            TextView tvValue1 = (TextView) view.findViewById(R.id.tv_value1);
            tvKey1.setText(wineShowBackEntity.getSpecification_item_list().get(size-1).getTitle());
            tvValue1.setText(wineShowBackEntity.getSpecification_item_list().get(size-1).getVal());
            llParameterContainer.addView(view);
        }
        //添加轮播图图片
        goodsShowView.loadAD(goodsEntity.getData());
    }
}
