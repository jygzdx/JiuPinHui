package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.impl.ConditionComActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IConditionComActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 评论用户的动态
 */
public class SendConditionComActivity extends BaseActivity implements IConditionComActivityView {

    @BindView(R.id.et_content)
    EditText etContent;
    private ConditionComActivityPresenterImpl presenter;
    private int dynamicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_condition_com);
        ButterKnife.bind(this);
        presenter = new ConditionComActivityPresenterImpl(this);

        dynamicId = getIntent().getExtras().getInt("dynamicId");
    }

    @OnClick({R.id.tv_cancel, R.id.tv_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_release:
                String content = etContent.getText().toString();
                if(StringUtils.isEmpty(content)){
                    ToastUtils.showShort(this,"请输入评论内容");
                    return;
                }
                String token = UserInfoManager.getInstance().getToken(this);
                presenter.sendCondition(token,content,dynamicId+"");
                break;
        }
    }

    @Override
    public void sendConditionComSuccess() {
        ToastUtils.showShort(this,"评论成功");
        finish();
    }
}
