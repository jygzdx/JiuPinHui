package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.chaychan.viewlib.PowerfulEditText;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.presenter.IReviseNicknameActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.ReviseNicknameActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IReviseNicknameActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改昵称
 */
public class ReviseNicknameActivity extends AppCompatActivity implements IReviseNicknameActivityView{

    @BindView(R.id.et_nickname)
    PowerfulEditText etNickname;

    private IReviseNicknameActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_nickname);
        ButterKnife.bind(this);
        presenter = new ReviseNicknameActivityPresenterImpl(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_ensure:
                String token = (String) SPUtils.get(this, SPUtils.LOGIN_TOKEN, "");
                String nickname = etNickname.getText().toString();
                if (StringUtils.isEmpty(nickname)) {
                    ToastUtils.showShort(this, "昵称不能为空");
                    return;
                }
                if(nickname.length() > 20 || nickname.length() < 4){
                    ToastUtils.showShort(this, "请输入4-20位中英文或数字的昵称");
                    return;
                }

                presenter.updateNickname(token,nickname);

                break;
        }
    }

    @Override
    public void reviseNickname(ResponseBase responseBase) {
        if ("OK".equals(responseBase.getMsg())) {
            ToastUtils.show(this, "修改昵称成功", Toast.LENGTH_SHORT);
        } else {
            ToastUtils.show(this, "修改昵称失败", Toast.LENGTH_SHORT);
        }
    }
}
