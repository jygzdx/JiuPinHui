package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑地址
 */
public class CompileAddressActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.sc_setting_default)
    Switch scSettingDefault;
    @BindView(R.id.btn_delete_address)
    Button btnDeleteAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_address);
        ButterKnife.bind(this);
        boolean isCompile = getIntent().getBooleanExtra("status", false);
        if (isCompile) {//是编辑地址
            tvTitleName.setText("编辑地址");
            btnDeleteAddress.setVisibility(View.GONE);
        } else {//是添加地址
            tvTitleName.setText("添加新地址");
            scSettingDefault.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btn_delete_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.btn_delete_address:
                break;
        }
    }
}
