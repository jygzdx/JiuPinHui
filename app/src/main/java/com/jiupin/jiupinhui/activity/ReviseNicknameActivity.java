package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jiupin.jiupinhui.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改昵称
 */
public class ReviseNicknameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_nickname);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_ensure:
                break;
        }
    }
}
