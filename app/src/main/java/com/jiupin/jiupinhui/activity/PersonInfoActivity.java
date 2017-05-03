package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jiupin.jiupinhui.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人信息页面
 */
public class PersonInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_user_nickname, R.id.tv_binding_phone, R.id.btn_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_user_nickname:
                Intent intent1 = new Intent(mContext,ReviseNicknameActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_binding_phone:
                Intent intent2 = new Intent(mContext,BindingPhoneActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_exit_login:
                break;
        }
    }
}
