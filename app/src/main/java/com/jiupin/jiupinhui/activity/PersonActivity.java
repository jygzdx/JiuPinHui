package com.jiupin.jiupinhui.activity;

import android.content.Intent;
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

/**
 * 酒圈的个人页面
 *
 */
public class PersonActivity extends BaseActivity implements IPersonActivityView {
    private static final String TAG = "PersonActivity";

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
    private UserEntity.DataBean userEntity;


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

    @OnClick({R.id.iv_back, R.id.iv_compile_info,R.id.tv_fans,R.id.tv_condition})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_fans://进去粉丝界面
                Intent fansIntent = new Intent(this,FansActivity.class);
                startActivity(fansIntent);
                break;
            case R.id.tv_condition://进去我的关注界面
                Intent conditionIntent = new Intent(this,MyAttentionActivity.class);
                startActivity(conditionIntent);
                break;
            case R.id.iv_compile_info:
                Intent intent = new Intent(mContext, CompilePersonInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userEntity", userEntity);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.d(TAG, "onActivityResult.requestCode = " + requestCode + ",resultCode = " + resultCode);
        if (resultCode == 1) {
            LogUtils.d(TAG, "onActivityResult.data" + data);
            Bundle bundle = data.getExtras();
            UserEntity.DataBean userEntity = (UserEntity.DataBean) bundle.getSerializable("userEntity");
            this.userEntity = userEntity;
            tvUserNickname.setText(userEntity.getNickName());
            if (userEntity.getIntro() == null || "" .equals(userEntity.getIntro())) {
                tvUserSignature.setText("暂无留下任何信息");
            } else {
                tvUserSignature.setText(userEntity.getIntro());
            }
            Fragment frag = fragList.get(1);
            if (frag != null) {
                frag.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    @Override
    public void setUserInfo(UserEntity.DataBean userEntity) {
        if (ActivityUtils.isFinish(mContext))
            return;
        if (userEntity == null)
            return;
        this.userEntity = userEntity;
        initView();
    }

    private void initView() {
        Glide.with(mContext)
                .load(userEntity.getImageUrl())
                .crossFade()
                .into(civHead);

        Glide.with(mContext)
                .load(userEntity.getImageUrl())
                .bitmapTransform(new BlurTransformation(mContext, 8, 1))
                .into(ivGaosiImg);
        tvFans.setText("粉丝" + userEntity.getFans_num());
        tvCondition.setText("关注" + userEntity.getConcern_num());

        tvUserNickname.setText(userEntity.getNickName());
        if (userEntity.getIntro() == null || "" .equals(userEntity.getIntro())) {
            tvUserSignature.setText("暂无留下任何信息");
        } else {
            tvUserSignature.setText(userEntity.getIntro());
        }
    }

    private class VpAdapter extends FragmentPagerAdapter {

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
