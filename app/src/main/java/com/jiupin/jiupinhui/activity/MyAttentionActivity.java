package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.fragment.AttentionListFragment;
import com.jiupin.jiupinhui.fragment.RecommendListFragment;
import com.jiupin.jiupinhui.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的关注列表
 */
public class MyAttentionActivity extends AppCompatActivity {

    @BindView(R.id.tl_tab_layout)
    TabLayout tlTabLayout;
    @BindView(R.id.vp_penson)
    ViewPager vpPenson;
    private List<String> tabList = new ArrayList<>();
    private List<Fragment> fragList = new ArrayList<>();
    private MyAttentionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attention);
        ButterKnife.bind(this);


        initFragment();

        initTab();

        initViewPager();

        initListiener();

    }

    private void initListiener() {
        tlTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.d("onTabSelected   "+tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtils.d("onTabUnselected   "+tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtils.d("onTabReselected   "+tab.getText());
            }
        });
    }


    private void initFragment() {
        RecommendListFragment recListFragment = new RecommendListFragment();
        fragList.add(recListFragment);
        AttentionListFragment attListFragment = new AttentionListFragment();
        fragList.add(attListFragment);

    }

    private void initViewPager() {
        adapter = new MyAttentionAdapter(getSupportFragmentManager());
        vpPenson.setAdapter(adapter);
    }

    private void initTab() {
        tabList.add("推荐");
        tabList.add("全部关注");
        TabLayout.Tab tab1 = tlTabLayout.newTab();
        tab1.setText(tabList.get(0));
        tlTabLayout.addTab(tab1);
        TabLayout.Tab tab2 = tlTabLayout.newTab();
        tab1.setText(tabList.get(1));
        tlTabLayout.addTab(tab2);
        //        将tablelayout和ViewPager关联起来
        tlTabLayout.setupWithViewPager(vpPenson);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    private class MyAttentionAdapter extends FragmentPagerAdapter {

        public MyAttentionAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragList.get(position);
        }

        @Override
        public int getCount() {
            return tabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position);
        }
    }


}
