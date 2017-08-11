package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.ILaunchActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.LaunchActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ILaunchActivityPresenter;
import com.jiupin.jiupinhui.view.ILaunchActivityView;

/**
 * 作者：czb on 2017/6/28 11:06
 */

public class LaunchActivityPresenterImpl implements ILaunchActivityPresenter {
    private ILaunchActivityModel model;
    private ILaunchActivityView view;
    public LaunchActivityPresenterImpl(ILaunchActivityView view) {
        this.view = view;
        model = new LaunchActivityModelImpl();
    }

    @Override
    public void getTokenStatus(String token) {
        model.getTokenStatus(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.checkTokenBack((ResponseBase) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                //不做处理
            }
        });
    }

}
