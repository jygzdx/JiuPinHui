package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.fragment.PersonInfoFragment;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IPersonInfoFragmentModel;
import com.jiupin.jiupinhui.model.impl.PersonInfoFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IPersonInfoFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IPersonInfoFragmentView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class PersonInfoFragmentPresenterImpl implements IPersonInfoFragmentPresenter {
    private IPersonInfoFragmentView view;
    private IPersonInfoFragmentModel model;

    public PersonInfoFragmentPresenterImpl(IPersonInfoFragmentView view) {
        this.view = view;
        model = new PersonInfoFragmentModelImpl();
    }

    @Override
    public void getUserInfo(String userId) {
        model.getUserInfo(userId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setUserInfo(((UserEntity.DataBean) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, ((PersonInfoFragment) view).getContext());
            }
        });
    }
}
