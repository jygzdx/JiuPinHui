package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.RegisterEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IRegisterActivityModel;
import com.jiupin.jiupinhui.model.impl.RegisterActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IRegisterActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IRegisterActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class RegisterActivityPresenterImpl implements IRegisterActivityPresenter {

    private static final String TAG = "RegisterActivityPresenterImpl";

    private IRegisterActivityModel model;
    private IRegisterActivityView view;

    public RegisterActivityPresenterImpl(IRegisterActivityView view) {
        this.view = view;
        model = new RegisterActivityModelImpl();
    }

    @Override
    public void getSecurityCode(String mobile) {
        model.getSecurityCode(mobile, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getSecurityCodeSuccess(((String) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
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
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
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
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
