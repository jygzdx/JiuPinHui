package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jiupin.jiupinhui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 绑定手机号码
 */
public class BindingPhoneActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_register_checkout)
    EditText etRegisterCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_binding_checkout, R.id.btn_enture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_binding_checkout:

                break;
            case R.id.btn_enture:

                break;
        }
    }
}
