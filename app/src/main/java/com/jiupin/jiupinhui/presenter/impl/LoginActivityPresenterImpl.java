package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.RegisterEntity;
import com.jiupin.jiupinhui.entity.SecurityCodeEntity;
import com.jiupin.jiupinhui.model.ILoginActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.LoginActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ILoginActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.ILoginActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class LoginActivityPresenterImpl implements ILoginActivityPresenter {

    private static final String TAG = "LoginActivityPresenterI";

    private ILoginActivityModel model;
    private ILoginActivityView view;

    public LoginActivityPresenterImpl(ILoginActivityView view) {
        this.view = view;
        model = new LoginActivityModelImpl();
    }

    @Override
    public void getSecurityCode(String mobile) {
        model.getSecurityCode(mobile, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                LogUtils.d(TAG,"返回信息= "+((SecurityCodeEntity)success).getMsg());
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(TAG,"返回信息= "+error.toString());
            }
        });
    }

    @Override
    public void registerUser(String mobile, String code, String pwd) {
        model.registerUser(mobile, code, pwd, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.registerSuccess((RegisterEntity) success);
            }

            @Override
            public void onFailed(Object error) {
                view.registerFail((String) error);
            }
        });
    }

    @Override
    public void loginUser(String mobile,String pwd, String way) {
        model.loginUser(mobile,pwd,way,new IModel.CallBack(){

            @Override
            public void onSuccess(Object success) {
                view.loginSuccess((RegisterEntity)success);
            }

            @Override
            public void onFailed(Object error) {
                view.loginFail((String) error);
            }
        });
    }

    @Override
    public void isMobileUnique(String mobile) {
        model.isMobileUnique(mobile, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.isMobileUnique((String) success);
            }

            @Override
            public void onFailed(Object error) {

            }
        });
    }
}
