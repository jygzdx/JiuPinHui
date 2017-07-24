package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IIdeaBackActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.IdeaBackActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IIdeaBackActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈
 */
public class IdeaBackActivity extends BaseActivity implements IIdeaBackActivityView{

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_idea_content)
    EditText etIdeaContent;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private IIdeaBackActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_back);
        ButterKnife.bind(this);
presenter = new IdeaBackActivityPresenterImpl(this);

    }

    @OnClick({R.id.iv_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                String content = etIdeaContent.getText().toString();
                String token = UserInfoManager.getInstance().getToken(this);
                if(StringUtils.isEmpty(content.trim())){
                    ToastUtils.showShort(this,"请填入意见反馈信息");
                }
                presenter.submitIdea(token,content,"2");
                break;
        }
    }

    @Override
    public void submitIdeaSuccess(String content) {
        ToastUtils.showShort(this,content);
    }
}
