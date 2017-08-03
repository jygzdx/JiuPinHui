package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.MealAdapter;
import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.entity.MealTypeEntity;
import com.jiupin.jiupinhui.presenter.IStoreFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.StoreFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IStoreFragmentView;
import com.jiupin.jiupinhui.widget.ADBannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 * 商城模块
 */

public class StoreFragment extends Fragment implements IStoreFragmentView {
    private static final String TAG = "StoreFragment";
    private View view;
    private TabLayout tabLayout;

    private List<String> mTitleList;

    private LinearLayout ll_advertis;
    private ADBannerView bannerView;
    private IStoreFragmentPresenter presenter;
    private LRecyclerView lrvStore;
    private MealAdapter adapter;
    private LRecyclerViewAdapter lrvAdapter;
    private boolean isFirst = true;

    public StoreFragment() {
        mTitleList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store, container, false);

        presenter = new StoreFragmentPresenterImpl(this);

        initBannerView();
        initTabLayout();
        initRecyclerView();

        initData();
        LogUtils.d(TAG, "onCreateView");
        return view;
    }

    private void initRecyclerView() {
        lrvStore = (LRecyclerView) view.findViewById(R.id.lrv_store);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        lrvStore.setLayoutManager(layout);
        adapter = new MealAdapter(getContext());
        lrvAdapter = new LRecyclerViewAdapter(adapter);
        lrvStore.setAdapter(lrvAdapter);
        lrvStore.setNestedScrollingEnabled(false);
        lrvStore.setLoadMoreEnabled(false);
        lrvStore.setPullRefreshEnabled(false);
    }

    private void initData() {
//        ProgressUtils.show(getContext());
        presenter.getBanner();
        presenter.getMealType();
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) view.findViewById(R.id.tl_store_tablayout);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                presenter.getMealInfo(tab.getTag().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtils.d(TAG,"onTabUnselected");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtils.d(TAG,"onTabUnselected");
            }
        });

    }

    private void initBannerView() {
        ll_advertis = (LinearLayout) view.findViewById(R.id.advertis);

    }

    @Override
    public void setBannerData(List<BannerEntity> bannerList) {
        LogUtils.d(TAG, "bannerList = " + bannerList);
        if (bannerList != null && bannerList.size() > 0) {
            bannerView = new ADBannerView(getContext(), true);
            bannerView.loadAD(bannerList);
            ll_advertis.addView(bannerView);
        }

    }

    @Override
    public void setMealTypeData(List<MealTypeEntity> mealTypeList) {
//        ProgressUtils.dismiss();
        if (mealTypeList != null && mealTypeList.size() > 0) {

//            //获取到套餐id之后展示套餐信息
//            presenter.getMealInfo(mealTypeList.get(0).getId()+"");

            for (int i = 0; i < mealTypeList.size(); i++) {
                TabLayout.Tab tab = tabLayout.newTab();
                tab.setTag(mealTypeList.get(i).getId()+"");
                tabLayout.addTab(tab.setText(mealTypeList.get(i).getClassName()));
            }
        }

    }

    @Override
    public void setMealInfoData(List<MainShowEntity.DataBean.ListBean> mainShowList) {
//        ProgressUtils.dismiss();
        LogUtils.d("isFirst = "+isFirst);
        if (mainShowList != null && mainShowList.size() > 0) {
            for (int i = 0; i < mainShowList.size(); i++) {
                adapter.setData(mainShowList);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy" + ll_advertis.getChildAt(0));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyview" + ll_advertis.getChildAt(0));
    }
}
