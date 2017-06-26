package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.HomeLoveAdapter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.widget.ADBannerView;
import com.jiupin.jiupinhui.widget.DividerGridItemDecoration;

/**
 * Created by Administrator on 2017/3/15.
 */

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private View view;
    private NestedScrollView nsvScorllView;
    private View llTitleBar;
    private LinearLayout ll_advertis;
    private RecyclerView rvHomeLove;
    private HomeLoveAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        initListener();
        initData();

        LogUtils.d(TAG, "onCreateView");
        return view;
    }

    private void initData() {
    }

    private void initListener() {
        nsvScorllView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int height = ll_advertis.getHeight();
                if (scrollY <= height) {
                    float scale = (float) scrollY / height;
                    //滑动时改变标题栏的透明度
                    llTitleBar.setAlpha(scale);
                }
            }
        });
    }

    private void initView() {
        ll_advertis = (LinearLayout) view.findViewById(R.id.advertis);
        ADBannerView bannerView = new ADBannerView(getContext(), true);
        ll_advertis.addView(bannerView);

        nsvScorllView = (NestedScrollView) view.findViewById(R.id.nv_home_scrollview);
        llTitleBar = view.findViewById(R.id.ll_title_bar);
        //初始化RecyclerView
        rvHomeLove = (RecyclerView) view.findViewById(R.id.rv_home_love);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);

        ((GridLayoutManager)manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                switch (adapter.getItemViewType(i)){
                    case HomeLoveAdapter.FOOT_VIEW:
                        return 2;
                    case HomeLoveAdapter.DEFAUL_VIEW:
                        return 1;
                }
                return 0;
            }
        });

        rvHomeLove.setLayoutManager(manager);


        rvHomeLove.setNestedScrollingEnabled(false);
        rvHomeLove.addItemDecoration(new DividerGridItemDecoration(getContext()));
        adapter = new HomeLoveAdapter(getContext());
        rvHomeLove.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyview");
    }
}

