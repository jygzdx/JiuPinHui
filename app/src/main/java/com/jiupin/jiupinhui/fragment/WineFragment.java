package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.WineAdapter;
import com.jiupin.jiupinhui.adapter.WineBrandAdapter;
import com.jiupin.jiupinhui.entity.Wine;
import com.jiupin.jiupinhui.entity.WineBrand;
import com.jiupin.jiupinhui.presenter.IWineFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.WineFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IWineFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/15.
 */

public class WineFragment extends Fragment implements IWineFragmentView {
    private static final String TAG = "WineFragment";
    @BindView(R.id.et_store_search)
    EditText etStoreSearch;
    @BindView(R.id.rv_wine_brand)
    RecyclerView rvWineBrand;
    @BindView(R.id.rv_Wine_show)
    RecyclerView rvWineShow;
    @BindView(R.id.btn_red_wine)
    Button btnRedWine;
    @BindView(R.id.btn_white_wine)
    Button btnWhiteWine;
    @BindView(R.id.btn_foreign_wine)
    Button btnForeignWine;
    @BindView(R.id.btn_yellow_wine)
    Button btnYellowWine;
    @BindView(R.id.btn_beer)
    Button btnBeer;
    private View view;
    private Unbinder unbinder;
    private Wine wine;
    private WineAdapter adapter;

    private WineBrand wineBrand;
    private WineBrandAdapter brandAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wine, container, false);
        unbinder = ButterKnife.bind(this, view);
        //设置美酒详细数据RecyclerView
        //reverseLayout->是否从尾部开始显示
        RecyclerView.LayoutManager wineManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvWineShow.setLayoutManager(wineManager);
        //设置品牌RecyclerView
        RecyclerView.LayoutManager brandManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvWineBrand.setLayoutManager(brandManager);
        //获取数据
        IWineFragmentPresenter presenter = new WineFragmentPresenterImpl(this);
        presenter.getData();
        presenter.getBrandData();
        LogUtils.d(TAG,"onCreateView");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG,"onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void setData(Wine wine) {
        this.wine = wine;
    }

    @Override
    public void SetAdapter() {
        if (adapter == null) {
            adapter = new WineAdapter(this.getContext(), wine.getData().getInfo());
        }
        rvWineShow.setAdapter(adapter);
    }

    @Override
    public void setBrandData(WineBrand wineBrand) {
        this.wineBrand = wineBrand;
    }

    @Override
    public void SetBrandAdapter() {
        if (brandAdapter == null) {
            brandAdapter = new WineBrandAdapter(this.getContext(), wineBrand.getData().getInfo());
        }
        rvWineBrand.setAdapter(brandAdapter);
    }
}
