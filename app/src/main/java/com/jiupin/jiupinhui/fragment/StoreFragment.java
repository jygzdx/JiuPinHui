package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.widget.ADBannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 * 商城模块
 */

public class StoreFragment extends Fragment {
    private static final String TAG = "StoreFragment";
    private View view;
    private TabLayout tabLayout;
    private ViewPager vpFunction;
    private List<View> mViewList;
    private LayoutInflater mInflater;
    private View view1, view2, view3;

    private List<String> mTitleList;

    private MyPagerAdapter adapter;

    public StoreFragment() {
        mViewList = new ArrayList<>();
        mTitleList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store, container, false);
        LinearLayout ll_advertis = (LinearLayout) view.findViewById(R.id.advertis);
        ADBannerView bannerView = new ADBannerView(getContext(), true);
        ll_advertis.addView(bannerView);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_store_tablayout);
        vpFunction = (ViewPager) view.findViewById(R.id.vp_function);

        mInflater = LayoutInflater.from(getContext());
        view1 = mInflater.inflate(R.layout.store_func_frag_place, null);
        view2 = mInflater.inflate(R.layout.store_func_frag_place, null);
        view3 = mInflater.inflate(R.layout.store_func_frag_place, null);

        //添加页卡视图
        mViewList.clear();
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        //添加tab文字
        mTitleList.clear();
        mTitleList.add("场合");
        mTitleList.add("对象");
        mTitleList.add("功效");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(2)));


        adapter = new MyPagerAdapter(mViewList);
        vpFunction.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpFunction);
        LogUtils.d(TAG, "onCreateView");
        return view;
    }

    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(List<View> ViewList) {
            mViewList = ViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
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
        LogUtils.d(TAG, "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyview");
    }
}
