package com.jiupin.jiupinhui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.utils.WindowUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.iv_login_qq)
    ImageView ivLoginQq;
    @BindView(R.id.iv_login_wechat)
    ImageView ivLoginWechat;
    @BindView(R.id.iv_login_weibo)
    ImageView ivLoginWeibo;
    @BindView(R.id.et_login_mobile)
    EditText etLoginMobile;
    @BindView(R.id.btn_login_bottom)
    Button btnLoginBottom;
    @BindView(R.id.tv_reset_password)
    TextView tvResetPassword;
    @BindView(R.id.rl_bottom_login)
    RelativeLayout rlBottomLogin;
    @BindView(R.id.et_register_mobile)
    EditText etRegisterMobile;
    @BindView(R.id.et_register_checkout)
    EditText etRegisterCheckout;
    @BindView(R.id.iv_register_checkout)
    Button ivRegisterCheckout;
    @BindView(R.id.et_register_password_one)
    EditText etRegisterPasswordOne;
    @BindView(R.id.et_register_password_two)
    EditText etRegisterPasswordTwo;
    @BindView(R.id.btn_register_deal)
    Button btnRegisterDeal;
    @BindView(R.id.btn_register_bottom)
    Button btnRegisterBottom;
    @BindView(R.id.rl_bottom_register)
    RelativeLayout rlBottomRegister;
    @BindView(R.id.et_reset_mobile)
    EditText etResetMobile;
    @BindView(R.id.et_reset_checkout)
    EditText etResetCheckout;
    @BindView(R.id.iv_reset_checkout)
    Button ivResetCheckout;
    @BindView(R.id.et_reset_password_one)
    EditText etResetPasswordOne;
    @BindView(R.id.et_reset_password_two)
    EditText etResetPasswordTwo;
    @BindView(R.id.btn_reset_bottom)
    Button btnResetBottom;
    @BindView(R.id.rl_bottom_reset)
    RelativeLayout rlBottomReset;
    private IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //微信登录的code
        String code = getIntent().getStringExtra("code");

        api = WXAPIFactory.createWXAPI(LoginActivity.this, Constant.APP_ID, true);
        api.registerApp(Constant.APP_ID);
    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.tv_reset_password,R.id.btn_login_bottom})
    void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                btnLogin.setBackgroundResource(R.drawable.login_clicked);
                btnRegister.setBackgroundResource(R.drawable.register_unclicked);
                if (rlBottomLogin.getVisibility() == View.GONE) {
                    showBottomLogin();
                } else {
                    hideAllBottomLayout();
                }
                break;
            case R.id.btn_register:
                btnLogin.setBackgroundResource(R.drawable.login_unclicked);
                btnRegister.setBackgroundResource(R.drawable.register_clicked);
                if (rlBottomRegister.getVisibility() == View.GONE) {
                    showBottomRegister();
                } else {
                    hideAllBottomLayout();
                }
                break;
            case R.id.tv_reset_password:
                if (rlBottomReset.getVisibility() == View.GONE) {
                    showBottomReset();
                }
                break;
            case R.id.btn_login_bottom:
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "123456";
                boolean status = api.sendReq(req);
                finish();
                break;
        }
    }

    /**
     * 展示底部重置密码界面
     */
    private void showBottomReset() {
        hideAllBottomLayout();
        rlBottomReset.setVisibility(View.VISIBLE);
    }

    /**
     * 展示底部注册界面
     */
    private void showBottomRegister() {
        hideAllBottomLayout();
        rlBottomRegister.setVisibility(View.VISIBLE);
        rlBottomRegister.clearAnimation();
        int wight = WindowUtils.getWindowWidth(this);
        ObjectAnimator rightToLeft = ObjectAnimator.ofFloat(rlBottomRegister, "translationX", wight, 0);
        rightToLeft.setInterpolator(new DecelerateInterpolator());
        rightToLeft.setDuration(200);
        rightToLeft.start();
    }


    /**
     * 展示底部登录界面
     */
    private void showBottomLogin() {
        hideAllBottomLayout();
        rlBottomLogin.setVisibility(View.VISIBLE);
        rlBottomLogin.clearAnimation();
        int wight = WindowUtils.getWindowWidth(this);
        ObjectAnimator leftToRight = ObjectAnimator.ofFloat(rlBottomLogin, "translationX", -wight, 0);
        leftToRight.setInterpolator(new DecelerateInterpolator());
        leftToRight.setDuration(200);
        leftToRight.start();
    }

    /**
     * 隐藏所有底部布局
     */
    private void hideAllBottomLayout() {
        rlBottomLogin.setVisibility(View.GONE);
        rlBottomRegister.setVisibility(View.GONE);
        rlBottomReset.setVisibility(View.GONE);
    }
    /**
     * 展示所有底部布局
     */
    private void showAllBottomLayout() {
        rlBottomLogin.setVisibility(View.VISIBLE);
        rlBottomRegister.setVisibility(View.VISIBLE);
        rlBottomReset.setVisibility(View.VISIBLE);
    }
}
