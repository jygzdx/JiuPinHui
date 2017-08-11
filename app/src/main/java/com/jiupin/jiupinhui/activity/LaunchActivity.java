package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.ILaunchActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.LaunchActivityPresenterImpl;
import com.jiupin.jiupinhui.view.ILaunchActivityView;

public class LaunchActivity extends BaseActivity implements ILaunchActivityView {

    private ILaunchActivityPresenter presenter;
    private static final int EMPTY_WHAT = 1;
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
        mHandler.sendEmptyMessageDelayed(EMPTY_WHAT, stop_time);
    }

    @Override
    public void checkTokenBack(ResponseBase responseBase) {
        UserInfoManager.getInstance().setLogin(true);
    }
}
