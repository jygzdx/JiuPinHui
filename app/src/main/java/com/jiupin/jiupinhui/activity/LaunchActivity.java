package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.ILaunchActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.LaunchActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.view.ILaunchActivityView;

public class LaunchActivity extends BaseActivity implements ILaunchActivityView {

    private ILaunchActivityPresenter presenter;
    private static final int EMPTY_WHAT = 1;
    private static final int WELCOME_WHAT = 2;

    private static final int stop_time = 2000;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case EMPTY_WHAT:
                    Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                    intent.putExtra("status", MainActivity.HOME_STATUS);
                    mContext.startActivity(intent);
                    finish();
                    break;
                case WELCOME_WHAT:
                    gotoWelcomeActivity();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        presenter = new LaunchActivityPresenterImpl(this);
        String token = UserInfoManager.getInstance().getToken(this);
        presenter.getTokenStatus(token);
        //在setContentView()前检查是否第一次运行
        boolean isFirst = (Boolean) SPUtils.get(mContext,SPUtils.IS_FIRST_WELCOME,true);
        if (!isFirst){
            mHandler.sendEmptyMessageDelayed(EMPTY_WHAT, stop_time);
        }else{
            mHandler.sendEmptyMessageDelayed(WELCOME_WHAT, stop_time);
        }

    }

    private void gotoWelcomeActivity() {
        Intent i = new Intent(LaunchActivity.this,WelcomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void checkTokenBack(ResponseBase responseBase) {
        if (ActivityUtils.isFinish(this))return;
        String data = responseBase.getData().toString();
        if (data.equals("1")){
            UserInfoManager.getInstance().setLogin(true);
            String token = UserInfoManager.getInstance().getToken(this);
            presenter.getUserInfoByToken(token);
        }else{
            UserInfoManager.getInstance().setLogin(false);
        }
    }

    @Override
    public void setUserInfo(UserEntity userEntity) {
        LogUtils.d("LaunchActivity","denglu chenggong");
        UserInfoManager.getInstance().setUser(userEntity.getData());
    }
}
