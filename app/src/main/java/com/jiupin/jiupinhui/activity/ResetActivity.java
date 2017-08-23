package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chaychan.viewlib.PowerfulEditText;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.presenter.IResetActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.ResetActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IResetActivityView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetActivity extends BaseActivity implements IResetActivityView {

    @BindView(R.id.et_reset_mobile)
    PowerfulEditText etResetMobile;
    @BindView(R.id.et_reset_checkout)
    EditText etResetCheckout;
    @BindView(R.id.btn_reset_checkout)
    Button btnResetCheckout;
    @BindView(R.id.et_reset_password_one)
    PowerfulEditText etResetPasswordOne;
    @BindView(R.id.et_reset_password_two)
    PowerfulEditText etResetPasswordTwo;

    private int resetCheckoutTime = 60;

    private TimerTask resetTask;
    private Timer resetTimer;

    final int WHAT_RESET = 103;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_RESET:
                    int time1 = (int) msg.obj;
                    if (time1 == 0) {
                        resetCheckoutTime = 60;
                        btnResetCheckout.setClickable(true);
                        btnResetCheckout.setText("获取验证码");
                        btnResetCheckout.setBackgroundResource(R.drawable.uncheckouted);
                        resetTimer.cancel();
                        resetTask.cancel();
                    } else if (time1 > 0) {
                        btnResetCheckout.setText(msg.obj + "s");
                    }

                    break;
            }
        }
    };
    private IResetActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        ButterKnife.bind(this);

        presenter = new ResetActivityPresenterImpl(this);
    }

    @OnClick({R.id.btn_reset_checkout, R.id.btn_reset_bottom, R.id.ll_bottom})
    public void onViewClicked(View view) {

        //重置密码信息
        String resetMobile = etResetMobile.getText().toString();
        String resetCheckout = etResetCheckout.getText().toString();
        String resetPasswordOne = etResetPasswordOne.getText().toString();
        String resetPasswordTwo = etResetPasswordTwo.getText().toString();

        switch (view.getId()) {
            case R.id.btn_reset_checkout:

                if (!StringUtils.isEmpty(resetMobile) && StringUtils.isMobileNO(resetMobile)) {
                    btnResetCheckout.setClickable(false);
                    btnResetCheckout.setBackgroundResource(R.drawable.checkouted);

                    //获取验证码
                    presenter.getResetSecurityCode(resetMobile);

                    //60秒倒计时
                    resetTask = new TimerTask() {
                        @Override
                        public void run() {
                            resetCheckoutTime--;
                            Message message = new Message();
                            message.what = WHAT_RESET;
                            message.obj = resetCheckoutTime;
                            handler.sendMessage(message);
                        }
                    };

                    resetTimer = new Timer();
                    // 参数：
                    // 10，延时1秒后执行。
                    // 1000，每隔1秒执行1次task。
                    resetTimer.schedule(resetTask, 10, 1000);
                } else {
                    ToastUtils.showShort(this, "手机号码输入错误");
                }

                break;
            case R.id.btn_reset_bottom:

                if (StringUtils.isEmpty(resetMobile) || !StringUtils.isMobileNO(resetMobile)) {
                    ToastUtils.showShort(this, "手机号码输入错误");
                    return;
                }
                if (StringUtils.isEmpty(resetCheckout) || resetCheckout.length() < 6) {
                    ToastUtils.showShort(this, "验证码输入错误");
                    return;
                }
                if (StringUtils.isEmpty(resetPasswordTwo) || StringUtils.isEmpty(resetPasswordOne)
                        ) {
                    ToastUtils.showShort(this, "密码不能为空");
                    return;
                }
                if (resetPasswordOne.length() > 16 || resetPasswordOne.length() < 8) {
                    ToastUtils.showShort(this, "输入的密码要为8-16位数字或字母的字符串");
                    return;
                }

                if (!StringUtils.equals(resetPasswordOne, resetPasswordTwo)) {
                    ToastUtils.showShort(this, "密码输入不一致");
                    return;
                }

                //重置密码
                presenter.resetPwd(resetMobile, resetCheckout, resetPasswordOne);

                break;
            case R.id.ll_bottom:
                Intent intent = new Intent(ResetActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void resetPwdSuccess(ResponseBase responseBase) {
        if (ActivityUtils.isFinish(mContext))return;

        ToastUtils.showShort(this, "重置密码成功,请使用新密码登录");
    }

    @Override
    public void getSecurityCodeSuccess(String msg) {
//        ToastUtils.showShort(this, msg);
    }
}
