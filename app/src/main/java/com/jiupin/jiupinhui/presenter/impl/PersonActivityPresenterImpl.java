package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IPersonActivityModel;
import com.jiupin.jiupinhui.model.impl.PersonActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IPersonActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IPersonActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class PersonActivityPresenterImpl implements IPersonActivityPresenter {
    private IPersonActivityView view;
    private IPersonActivityModel model;

    public PersonActivityPresenterImpl(IPersonActivityView view) {
        this.view = view;
        model = new PersonActivityModelImpl();
    }

    @Override
    public void getUserInfo(String userId) {
        model.getUserInfo(userId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setUserInfo(((UserEntity) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }
}
