package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.fragment.CommunityFragment;
import com.jiupin.jiupinhui.fragment.HomeFragment;
import com.jiupin.jiupinhui.fragment.MyFragment;
import com.jiupin.jiupinhui.fragment.StoreFragment;
import com.jiupin.jiupinhui.fragment.WineFragment;
import com.jiupin.jiupinhui.utils.LogUtils;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    /**
     * 底部RadioButton按钮
     */
    private RadioGroup rgMain;
    //Fragment
    private HomeFragment fgHome;
    private WineFragment fgWine;
    private StoreFragment fgStore;
    private CommunityFragment fgCommunity;
    private MyFragment fgMy;
    public static final String HOME_STATUS = "home";
    public static final String COMMUNITY_STATUS = "community";
    public static final String MY_STATUS = "my";
    public static final String WINE_STATUS = "wine";
    public static final String STORE_STATUS = "store";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initListener();
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setDefaultFragment();
        } else {//根据传过来的status选择不同的fragment
            String status = extras.getString("status");
            if (status == null) {
                setDefaultFragment();
            } else {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                LogUtils.d("status = " + status);
                if (HOME_STATUS.equals(status)) {
                    rgMain.check(R.id.rb_home);
                    if (fgHome == null) {
                        fgHome = new HomeFragment();
                    }
                    transaction.replace(R.id.fl_main_container, fgHome);
                } else if (COMMUNITY_STATUS.equals(status)) {
                    rgMain.check(R.id.rb_community);
                    if (fgCommunity == null) {
                        fgCommunity = new CommunityFragment();
                    }
                    transaction.replace(R.id.fl_main_container, fgCommunity);
                } else if (MY_STATUS.equals(status)) {
                    rgMain.check(R.id.rb_my);
                    if (fgMy == null) {
                        fgMy = new MyFragment();
                    }
                    transaction.replace(R.id.fl_main_container, fgMy);
                } else if (WINE_STATUS.equals(status)) {
                    rgMain.check(R.id.rb_wine);
                    if (fgWine == null) {
                        fgWine = new WineFragment();
                    }
                    transaction.replace(R.id.fl_main_container, fgWine);
                } else if (STORE_STATUS.equals(status)) {
                    rgMain.check(R.id.rb_store);
                    if (fgStore == null) {
                        fgStore = new StoreFragment();
                    }
                    transaction.replace(R.id.fl_main_container, fgStore);
                }
                transaction.commit();
            }
        }


    }

    //设置默认Fragment
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fgHome = new HomeFragment();
        transaction.replace(R.id.fl_main_container, fgHome);
        transaction.commit();
    }

    //设置监听器
    private void initListener() {
        rgMain.setOnCheckedChangeListener(this);

    }


    //初始化控件
    private void initView() {
        rgMain = (RadioGroup) findViewById(R.id.rg_main);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (checkedId) {
            case R.id.rb_home:
                if (fgHome == null) {
                    fgHome = new HomeFragment();
                }
                transaction.replace(R.id.fl_main_container, fgHome);
                break;
            case R.id.rb_wine:
                if (fgWine == null) {
                    fgWine = new WineFragment();
                }
                transaction.replace(R.id.fl_main_container, fgWine);
                break;
            case R.id.rb_store:
                if (fgStore == null) {
                    fgStore = new StoreFragment();
                }
                transaction.replace(R.id.fl_main_container, fgStore);
                break;
            case R.id.rb_community:
                if (fgCommunity == null) {
                    fgCommunity = new CommunityFragment();
                }
                transaction.replace(R.id.fl_main_container, fgCommunity);
                break;
            case R.id.rb_my:
                if (fgMy == null) {
                    fgMy = new MyFragment();
                }
                transaction.replace(R.id.fl_main_container, fgMy);
                break;
        }
        //事务提交
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.d("mainactivity.onActivityResult");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (requestCode == 1) {
            rgMain.check(R.id.rb_my);
            if (fgMy == null) {
                fgMy = new MyFragment();
            }
            transaction.replace(R.id.fl_main_container, fgMy);
            transaction.commit();
        }
    }
}
