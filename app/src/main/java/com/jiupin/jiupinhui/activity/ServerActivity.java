package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServerActivity extends BaseActivity {

    @BindView(R.id.tv_consult)
    TextView tvConsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_consult)
    public void onViewClicked() {
        Intent intent = new Intent(mContext,ChatActivity.class);
        startActivity(intent);
    }
}
