package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IResetActivityModel;
import com.jiupin.jiupinhui.model.impl.ResetActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IResetActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IResetActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ResetActivityPresenterImpl implements IResetActivityPresenter {

    private static final String TAG = "ResetActivityPresenterImpl";

    private IResetActivityModel model;
    private IResetActivityView view;

    public ResetActivityPresenterImpl(IResetActivityView view) {
        this.view = view;
        model = new ResetActivityModelImpl();
    }

    @Override
    public void getResetSecurityCode(String mobile) {
        model.getResetSecurityCode(mobile, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getSecurityCodeSuccess((String) success);
            }


            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void resetPwd(String mobile, String code, String pwd) {
        model.resetPwd(mobile, code,pwd,new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                LogUtils.d(TAG,"返回信息= "+((ResponseBase)success).getMsg());
                view.resetPwdSuccess((ResponseBase)success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
