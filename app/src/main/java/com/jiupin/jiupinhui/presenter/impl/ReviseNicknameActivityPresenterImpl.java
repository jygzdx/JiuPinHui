package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IReviseNicknameActivityModel;
import com.jiupin.jiupinhui.model.impl.ReviseNicknameActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IReviseNicknameActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IReviseNicknameActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ReviseNicknameActivityPresenterImpl implements IReviseNicknameActivityPresenter {

    private static final String TAG = "ReviseNicknameActivityPresenterImpl";

    private IReviseNicknameActivityModel model;
    private IReviseNicknameActivityView view;

    public ReviseNicknameActivityPresenterImpl(IReviseNicknameActivityView view) {
        this.view = view;
        model = new ReviseNicknameActivityModelImpl();
    }

    @Override
    public void updateNickname(String token, String nickName) {
        model.updateNickname(token, nickName, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.reviseNickname((ResponseBase)success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d("error = "+error);
            }
        });
    }
}
