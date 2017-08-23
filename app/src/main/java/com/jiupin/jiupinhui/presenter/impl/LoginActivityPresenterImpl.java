package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.RegisterEntity;
import com.jiupin.jiupinhui.model.ILoginActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.LoginActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ILoginActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
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
    public void loginUser(String mobile,String pwd, String way) {
        model.loginUser(mobile,pwd,way,new IModel.CallBack(){

            @Override
            public void onSuccess(Object success) {
                view.loginSuccess((RegisterEntity)success);
            }


            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
