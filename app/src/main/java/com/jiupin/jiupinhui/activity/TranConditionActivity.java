package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.ITranConditionActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.TranConditionActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.ITranConditionActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 酒圈动态转发
 */
public class TranConditionActivity extends BaseActivity implements ITranConditionActivityView {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.iv_user_pic)
    ImageView ivUserPic;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_tran_content)
    TextView tvTranContent;
    private int dynamicId;
    private ITranConditionActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran_condition);
        ButterKnife.bind(this);
        String imgUrl = getIntent().getExtras().getString("imgUrl");
        String nickname = getIntent().getExtras().getString("nickname");
        String tranContent = getIntent().getExtras().getString("tranContent");
        dynamicId = getIntent().getExtras().getInt("dynamicId");
        tvTranContent.setText(tranContent);
        tvUserName.setText("@"+nickname);
        Glide.with(this)
                .load(imgUrl)
                .crossFade()
                .into(ivUserPic);
        presenter = new TranConditionActivityPresenterImpl(this);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_release:
                String content = etContent.getText().toString();
                if (StringUtils.isEmpty(content)) {
                    ToastUtils.showShort(this, "请输入转发内容");
                    return;
                }
                String token = UserInfoManager.getInstance().getToken(this);
                presenter.sendTranCondition(token, content, dynamicId + "");
                break;
        }
    }

    @Override
    public void sendTranConditionSuccess() {
        ToastUtils.showShort(mContext,"转发成功");
        finish();
    }
}
