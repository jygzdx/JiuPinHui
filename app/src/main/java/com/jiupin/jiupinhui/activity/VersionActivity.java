package com.jiupin.jiupinhui.activity;

import android.os.Bundle;

import com.jiupin.jiupinhui.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版本界面
 */
public class VersionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
