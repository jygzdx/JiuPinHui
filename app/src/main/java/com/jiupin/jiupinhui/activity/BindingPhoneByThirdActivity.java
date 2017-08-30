package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.presenter.IBindingPhoneByThirdActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.BindingPhoneByThirdActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IBindingPhoneByThirdActivityView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 第三方登录绑定手机号码
 */
public class BindingPhoneByThirdActivity extends BaseActivity implements IBindingPhoneByThirdActivityView {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_get_checkout)
    Button btnGetCheckout;
    @BindView(R.id.et_checkout)
    EditText etCheckout;

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
                        btnGetCheckout.setClickable(true);
                        btnGetCheckout.setText("获取验证码");
                        btnGetCheckout.setBackgroundResource(R.drawable.checkout_bg);
                        timer.cancel();
                        task.cancel();
                    } else if (time > 0) {
                        LogUtils.d("btnGetCheckout = " + btnGetCheckout + "msg = " + msg);
                        btnGetCheckout.setText(msg.obj + "s");
                    }

                    break;
            }
        }
    };
    private IBindingPhoneByThirdActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_phone_by_third);
        ButterKnife.bind(this);

        presenter = new BindingPhoneByThirdActivityPresenterImpl(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_next, R.id.btn_get_checkout, R.id.btn_check})
    public void onViewClicked(View view) {
        String phone = etPhone.getText().toString();
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_next:
                finish();
                break;
            case R.id.btn_get_checkout:

                if (!StringUtils.isEmpty(phone) && StringUtils.isMobileNO(phone)) {
                    btnGetCheckout.setClickable(false);
                    btnGetCheckout.setBackgroundResource(R.drawable.uncheckout_bg);

                    //获取验证码
                    presenter.getSecurityCode(phone);

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
            case R.id.btn_check:
                String token = (String) SPUtils.get(this, SPUtils.LOGIN_TOKEN, "");
                String sms = etCheckout.getText().toString();

                if (StringUtils.isEmpty(phone) || !StringUtils.isMobileNO(phone)) {
                    ToastUtils.showShort(this, "手机号码输入错误");
                    return;
                }
                if (StringUtils.isEmpty(sms) || sms.length() < 6) {
                    ToastUtils.showShort(this, "验证码输入错误");
                    return;
                }

                presenter.updateMobile(token, phone, sms);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }

        super.onDestroy();
    }

    @Override
    public void updatePhone(String responseBase) {
        ToastUtils.showShort(this, responseBase);
        finish();
    }

    @Override
    public void getSms(String responseBase) {
        ToastUtils.showShort(this, responseBase);
    }
}
