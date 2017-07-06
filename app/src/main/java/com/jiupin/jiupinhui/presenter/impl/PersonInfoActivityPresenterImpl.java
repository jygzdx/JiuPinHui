package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IPersonInfoActivityModel;
import com.jiupin.jiupinhui.model.impl.PersonInfoActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IPersonInfoActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IPersonInfoActivityView;

import java.io.File;

/**
 * 作者：czb on 2017/6/28 11:06
 */

public class PersonInfoActivityPresenterImpl implements IPersonInfoActivityPresenter {
    private IPersonInfoActivityModel model;
    private IPersonInfoActivityView view;
    public PersonInfoActivityPresenterImpl(IPersonInfoActivityView view) {
        this.view = view;
        model = new PersonInfoActivityModelImpl();
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

    @Override
    public void pushPicture(File file, String name, String token) {
        model.pushPicture(file, name, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.pushPicture((ResponseBase)success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d("error = "+error);
            }
        });
    }
}
