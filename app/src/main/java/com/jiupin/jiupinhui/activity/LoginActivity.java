package com.jiupin.jiupinhui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.jiupin.jiupinhui.entity.RegisterEntity;
import com.jiupin.jiupinhui.presenter.ILoginActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.LoginActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.utils.WindowUtils;
import com.jiupin.jiupinhui.view.ILoginActivityView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginActivityView {
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
    @BindView(R.id.btn_register_checkout)
    Button btnRegisterCheckout;
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

    private ILoginActivityPresenter presenter;

    private Timer timer;
    private TimerTask task;
    private int checkoutTime = 60;

    final int WHAT = 102;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT:
                    int time = (int) msg.obj;
                    if (time == 0) {
                        checkoutTime = 60;
                        btnRegisterCheckout.setClickable(true);
                        btnRegisterCheckout.setText("获取验证码");
                        btnRegisterCheckout.setBackgroundResource(R.drawable.uncheckouted);
                        timer.cancel();
                        task.cancel();
                    } else if (time > 0) {
                        btnRegisterCheckout.setText(msg.obj + "s");
                    }

                    break;
            }
        }
    };


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

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.tv_reset_password, R.id.btn_login_bottom, R.id.btn_register_checkout,
            R.id.btn_register_bottom
    })
    void onButtonClick(View view) {

        //注册信息
        String registerMobile = etRegisterMobile.getText().toString();
        String registerCheckout = etRegisterCheckout.getText().toString();
        String registerPasswordOne = etRegisterPasswordOne.getText().toString();
        String registerPasswordTwo = etRegisterPasswordTwo.getText().toString();
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
                //向微信发送请求
                boolean status = api.sendReq(req);
                break;
            case R.id.btn_register_checkout:

                if (!StringUtils.isEmpty(registerMobile) && StringUtils.isMobileNO(registerMobile)) {
                    btnRegisterCheckout.setClickable(false);
                    btnRegisterCheckout.setBackgroundResource(R.drawable.checkouted);

                    //获取验证码
                    presenter.getSecurityCode(registerMobile);

                    //60秒倒计时
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            checkoutTime--;
                            Message message = new Message();
                            message.what = WHAT;
                            message.obj = checkoutTime;
                            handler.sendMessage(message);
                        }
                    };

                    timer = new Timer();
                    // 参数：
                    // 10，延时1秒后执行。
                    // 1000，每隔1秒执行1次task。
                    timer.schedule(task, 10, 1000);
                } else {
                    ToastUtils.showShort(this, "手机号码输入错误");
                }

                break;
            case R.id.btn_register_bottom:
                if (StringUtils.isEmpty(registerMobile) || !StringUtils.isMobileNO(registerMobile)) {
                    ToastUtils.showShort(this, "手机号码输入错误");
                    return;
                }
                if (StringUtils.isEmpty(registerCheckout) || registerCheckout.length() < 6) {
                    ToastUtils.showShort(this, "验证码输入错误");
                    return;
                }
                if (StringUtils.isEmpty(registerPasswordTwo) || StringUtils.isEmpty(registerPasswordOne)
                        || registerPasswordOne.length() > 16 || registerPasswordOne.length() < 6) {
                    ToastUtils.showShort(this, "密码格式不正确");
                    return;
                }
                if (!StringUtils.equals(registerPasswordOne, registerPasswordTwo)) {
                    ToastUtils.showShort(this, "密码输入不一致");
                    return;
                }

                presenter.registerUser(registerMobile, registerCheckout, registerPasswordOne);


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

    @Override
    public void registerSuccess(RegisterEntity registerEntity) {
        ToastUtils.showShort(this, "注册成功");
    }

    @Override
    public void registerFail() {
        ToastUtils.showShort(this, "注册失败");
    }
}
