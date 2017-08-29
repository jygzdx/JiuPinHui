package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chaychan.viewlib.PowerfulEditText;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.RegisterEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IRegisterActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.RegisterActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IRegisterActivityView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterActivityView{
    private static final String TAG = "RegisterActivity";

    @BindView(R.id.et_register_mobile)
    PowerfulEditText etRegisterMobile;
    @BindView(R.id.et_register_checkout)
    EditText etRegisterCheckout;
    @BindView(R.id.et_register_password_one)
    PowerfulEditText etRegisterPasswordOne;
    @BindView(R.id.et_register_password_two)
    PowerfulEditText etRegisterPasswordTwo;
    @BindView(R.id.btn_register_checkout)
    Button btnRegisterCheckout;
    @BindView(R.id.btn_register_deal)
    Button btnRegisterDeal;

    //手机号码是否注册过
    private boolean isMobileUnique;

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
    private IRegisterActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        btnRegisterDeal.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        presenter = new RegisterActivityPresenterImpl(this);

    }

    @OnClick({R.id.btn_register_checkout, R.id.btn_register_deal, R.id.btn_register_bottom, R.id.ll_bottom})
    public void onViewClicked(View view) {

        //注册信息
        String registerMobile = etRegisterMobile.getText().toString();
        String registerCheckout = etRegisterCheckout.getText().toString();
        String registerPasswordOne = etRegisterPasswordOne.getText().toString();
        String registerPasswordTwo = etRegisterPasswordTwo.getText().toString();

        switch (view.getId()) {
            case R.id.btn_register_checkout:
                presenter.isMobileUnique(registerMobile);

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
            case R.id.btn_register_deal://用户协议


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
                        ) {
                    ToastUtils.showShort(this, "密码不能为空");
                    return;
                }
                if (registerPasswordOne.length() > 16 || registerPasswordOne.length() < 8) {
                    ToastUtils.showShort(this, "请输入8-16位数字或字母的密码");
                    return;
                }

                if (!StringUtils.equals(registerPasswordOne, registerPasswordTwo)) {
                    ToastUtils.showShort(this, "密码输入不一致");
                    return;
                }

                if (isMobileUnique) {
                    presenter.registerUser(registerMobile, registerCheckout, registerPasswordOne);
                } else {
                    ToastUtils.showShort(this, "手机号码已经被注册过了");
                }


                break;
            case R.id.ll_bottom:
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void registerSuccess(RegisterEntity registerEntity) {
        if (ActivityUtils.isFinish(mContext))return;

        ToastUtils.showShort(this, "注册成功");
        SPUtils.put(this, SPUtils.LOGIN_TOKEN, registerEntity.getData().getToken());
        SPUtils.put(this, SPUtils.USER_ID, registerEntity.getData().getUser().getId() + "");
        LogUtils.d(TAG + "token" + registerEntity.getData().getToken());
        UserInfoManager.getInstance().setLogin(true);
        UserInfoManager.getInstance().setToken(registerEntity.getData().getToken());
        //添加user信息保存起来
        UserInfoManager.getInstance().setUser(registerEntity.getData().getUser());
    }

    @Override
    public void getSecurityCodeSuccess(String msg) {
//        ToastUtils.showShort(this, msg);
    }

    @Override
    public void isMobileUnique(String data) {
        if ("0".equals(data)) {
            isMobileUnique = false;
        } else {
            isMobileUnique = true;
        }
    }
}
