package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chaychan.viewlib.PowerfulEditText;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.RegisterEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.ILoginActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.LoginActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.ILoginActivityView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginActivityView {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.et_login_mobile)
    PowerfulEditText etLoginMobile;
    @BindView(R.id.et_login_password)
    PowerfulEditText etLoginPassword;
    @BindView(R.id.tv_reset_password)
    TextView tvResetPassword;

    private IWXAPI api;

    private ILoginActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginActivityPresenterImpl(this);

        //微信登录的code
        String code = getIntent().getStringExtra("code");

        api = WXAPIFactory.createWXAPI(LoginActivity.this, Constant.APP_ID, true);
        api.registerApp(Constant.APP_ID);


    }

    @OnClick({R.id.btn_login_bottom, R.id.tv_reset_password, R.id.iv_login_qq, R.id.iv_login_wechat, R.id.iv_login_weibo, R.id.ll_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_bottom:
                //登录信息
                String loginMobile = etLoginMobile.getText().toString();
                String loginPassword = etLoginPassword.getText().toString();
                presenter.loginUser(loginMobile, loginPassword, "2");

                break;
            case R.id.tv_reset_password:
                Intent resetIntent = new Intent(LoginActivity.this,ResetActivity.class);
                startActivity(resetIntent);
                break;
            case R.id.iv_login_qq:
                break;
            case R.id.iv_login_wechat:
                //用户登录
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "123456";
                //向微信发送请求
                boolean status = api.sendReq(req);
                LogUtils.d("status = " + status);
                break;
            case R.id.iv_login_weibo:
                break;
            case R.id.ll_bottom:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void loginSuccess(RegisterEntity registerEntity) {
        if (ActivityUtils.isFinish(mContext))
            return;

        SPUtils.put(this, SPUtils.LOGIN_TOKEN, registerEntity.getData().getToken());
        SPUtils.put(this, SPUtils.USER_ID, registerEntity.getData().getUser().getId() + "");
        ToastUtils.showShort(this, "登录成功");
        UserInfoManager.getInstance().setLogin(true);
        UserInfoManager.getInstance().setUserId(registerEntity.getData().getUser().getId() + "");
        UserInfoManager.getInstance().setToken(registerEntity.getData().getToken());
        //添加user信息保存起来
        UserInfoManager.getInstance().setUser(registerEntity.getData().getUser());
        finish();
    }

}
