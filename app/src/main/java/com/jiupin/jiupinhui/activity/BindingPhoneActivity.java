package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.presenter.IBindingPhoneActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.BindingPhoneActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IBindingPhoneActivityView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 绑定手机号码
 */
public class BindingPhoneActivity extends BaseActivity implements IBindingPhoneActivityView {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_checkout)
    EditText etCheckout;
    @BindView(R.id.btn_binding_checkout)
    Button btnBindingCheckout;
    @BindView(R.id.btn_enture)
    Button btnEnture;

    private IBindingPhoneActivityPresenter presenter;

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
                        btnBindingCheckout.setClickable(true);
                        btnBindingCheckout.setText("获取验证码");
                        btnBindingCheckout.setBackgroundResource(R.drawable.uncheckouted);
                        timer.cancel();
                        task.cancel();
                    } else if (time > 0) {
                        btnBindingCheckout.setText(msg.obj + "s");
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_phone);
        ButterKnife.bind(this);

        presenter = new BindingPhoneActivityPresenterImpl(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_binding_checkout, R.id.btn_enture})
    public void onViewClicked(View view) {
        String phone = etPhone.getText().toString();
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_binding_checkout:
                if (!StringUtils.isEmpty(phone) && StringUtils.isMobileNO(phone)) {
                    btnBindingCheckout.setClickable(false);
                    btnBindingCheckout.setBackgroundResource(R.drawable.checkouted);

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
            case R.id.btn_enture:
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
                btnEnture.setClickable(false);

                presenter.updateMobile(token, phone, sms);

                break;
        }
    }

    @Override
    public void updatePhone(ResponseBase responseBase) {
        if ("OK".equals(responseBase.getMsg())) {
            ToastUtils.show(this, "修改成功，再次登录请使用新绑定手机号码登录", Toast.LENGTH_SHORT);
        } else {
            ToastUtils.show(this, "修改失败，请重新尝试一次", Toast.LENGTH_SHORT);
        }
    }
}
