package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.fragment.PersonConditionFragment;
import com.jiupin.jiupinhui.fragment.PersonInfoFragment;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IPersonActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.PersonActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IPersonActivityView;
import com.jiupin.jiupinhui.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PersonActivity extends BaseActivity implements IPersonActivityView {

    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_condition)
    TextView tvCondition;
    @BindView(R.id.tv_user_signature)
    TextView tvUserSignature;
    @BindView(R.id.iv_compile_info)
    ImageView ivCompileInfo;
    @BindView(R.id.tl_tab_layout)
    TabLayout tlTabLayout;
    @BindView(R.id.vp_penson)
    ViewPager vpPenson;

    List<Fragment> fragList = new ArrayList<>();
    List<String> tabList = new ArrayList<>();
    @BindView(R.id.iv_gaosi_img)
    ImageView ivGaosiImg;
    private VpAdapter adapter;
    private IPersonActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        presenter = new PersonActivityPresenterImpl(this);

        initFragment();

        initTab();
        initViewPager();

        initData();


    }

    private void initData() {
        String userId = UserInfoManager.getInstance().getUserId(mContext);
        LogUtils.d("userId = " + userId);
        presenter.getUserInfo(userId);
    }

    private void initFragment() {
        PersonConditionFragment conditionFra = new PersonConditionFragment();
        fragList.add(conditionFra);
        PersonInfoFragment infoFra = new PersonInfoFragment();
        fragList.add(infoFra);
    }

    private void initViewPager() {
        adapter = new VpAdapter(getSupportFragmentManager());
        vpPenson.setAdapter(adapter);
    }

    private void initTab() {
        tabList.add("主页");
        tabList.add("个人中心");
        TabLayout.Tab tab1 = tlTabLayout.newTab();
        tab1.setText(tabList.get(0));
        tlTabLayout.addTab(tab1);
        TabLayout.Tab tab2 = tlTabLayout.newTab();
        tab1.setText(tabList.get(1));
        tlTabLayout.addTab(tab2);
        //        将tablelayout和ViewPager关联起来
        tlTabLayout.setupWithViewPager(vpPenson);
    }

    @OnClick({R.id.iv_back, R.id.iv_compile_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_compile_info:
                break;
        }
    }

    @Override
    public void setUserInfo(UserEntity userEntity) {
        if (ActivityUtils.isFinish(mContext))
            return;
        if (userEntity == null)
            return;
        Glide.with(mContext)
                .load(userEntity.getData().getImageUrl())
                .crossFade()
                .into(civHead);

        Glide.with(mContext)
                .load(userEntity.getData().getImageUrl())
                .bitmapTransform(new BlurTransformation(mContext, 8, 1))
                .into(ivGaosiImg);
        tvFans.setText("粉丝"+userEntity.getData().getFans_num());
        tvCondition.setText("关注"+userEntity.getData().getConcern_num());

        tvUserNickname.setText(userEntity.getData().getNickName());
        if(userEntity.getData().getSignature()==null||"".equals(userEntity.getData().getSignature())){
            tvUserSignature.setText("暂无留下任何信息");
        }else{
            tvUserSignature.setText(userEntity.getData().getSignature());
        }
    }

    class VpAdapter extends FragmentPagerAdapter {

        public VpAdapter(FragmentManager fm) {
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
