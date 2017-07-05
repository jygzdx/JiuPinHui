package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IMyFragmentModel;
import com.jiupin.jiupinhui.model.impl.MyFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IMyFragmentPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IMyFragmentView;

/**
 * 作者：czb on 2017/6/28 11:06
 */

public class MyFragmentPresenterImpl implements IMyFragmentPresenter {
    private IMyFragmentModel model;
    private IMyFragmentView view;
    public MyFragmentPresenterImpl(IMyFragmentView view) {
        this.view = view;
        model = new MyFragmentModelImpl();
    }

    @Override
    public void getTokenStatus(String token) {
        model.getTokenStatus(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.checkTokenBack((ResponseBase) success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d("error = "+error);
            }
        });
    }

    @Override
    public void getUserInfoByToken(String token) {
        model.getUserInfoByToken(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setUserInfo((UserEntity) success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d("error = "+error);
            }
        });
    }
}
