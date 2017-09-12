package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.VersionEntity;
import com.jiupin.jiupinhui.fragment.CommunityFragment;
import com.jiupin.jiupinhui.fragment.HomeFragment;
import com.jiupin.jiupinhui.fragment.MyFragment;
import com.jiupin.jiupinhui.fragment.StoreFragment;
import com.jiupin.jiupinhui.fragment.WineFragment;
import com.jiupin.jiupinhui.presenter.IMainActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.MainActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IMainActivityView;

import java.io.File;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,IMainActivityView {
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
    private IMainActivityPresenter presenter;
    private long firstTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenterImpl(this);
        presenter.getVersionInfo();

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
    public void onBackPressed() {

        if (System.currentTimeMillis() - firstTime > 2000) {
            firstTime = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        } else {
//            finish();
//            System.exit(0);
            super.onBackPressed();
        }
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

    @Override
    public void getVersionInfo(VersionEntity versionEntity) {
        if (ActivityUtils.isFinish(mContext))return;
        int curVerCode = AppUtils.getAppVersionCode();
        //测试下
        if(curVerCode<versionEntity.getVersionInNumber()){
            presenter.getApk(versionEntity.getVersionInString());
        }
    }

    @Override
    public void setApkUrl(String apkUrl,String versionName){
        if (ActivityUtils.isFinish(mContext))return;
        ToastUtils.showShort(mContext,"获取链接完成");
        //test7.0
        File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"/jiupinhui/apk/"+"jiupinhui_"+versionName+".apk");

        //可能已经下载完成了
        String filePath =mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/jiupinhui/apk/"+"jiupinhui_"+versionName+".apk";
        LogUtils.d("filePath = "+filePath);
        if (FileUtils.isFileExists(filePath)){//如果已经下载过了，直接提示用户安装
            ToastUtils.showShort(mContext,"文件已经下载过了");
            showDialog(filePath);
            return;
        }
        if(NetworkUtils.isWifiConnected()){
            presenter.installApp(apkUrl,versionName,file);
        }
    }

    @Override
    public void installApp(String apkUrl) {
        if (ActivityUtils.isFinish(mContext))return;
        ToastUtils.showShort(mContext,"下载完毕");
        showDialog(apkUrl);
    }

    public void showDialog(final String apkUrl){
        View view= LayoutInflater.from(mContext).inflate(R.layout.dialog_content, null);

        AlertDialog.Builder builder= new AlertDialog.Builder(mContext);

        builder.setView(view);

        final AlertDialog dialog=builder.create();

        TextView tvContent=(TextView)view.findViewById(R.id.tv_content);

        TextView tvCancel=(TextView)view.findViewById(R.id.tv_cancel);

        TextView tvEnsure=(TextView)view.findViewById(R.id.tv_ensure);

        tvContent.setText("酒品会有新版本需要更新，请问是否更新？");

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.dismiss();
            }
        });
        tvEnsure.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                ToastUtils.showShort(mContext,"安装app");
                String authority = "com.jiupin.jiupinhui.fileprovider";
                AppUtils.installApp(apkUrl,authority);
                //确定
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
