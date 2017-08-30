package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.model.IBindingPhoneByThirdActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.BindingPhoneByThirdActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IBindingPhoneByThirdActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IBindingPhoneByThirdActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class BindingPhoneByThirdActivityPresenterImpl implements IBindingPhoneByThirdActivityPresenter {

    private static final String TAG = "BindingPhoneByThirdActivityPresenterImpl";

    private IBindingPhoneByThirdActivityModel model;
    private IBindingPhoneByThirdActivityView view;

    public BindingPhoneByThirdActivityPresenterImpl(IBindingPhoneByThirdActivityView view) {
        this.view = view;
        model = new BindingPhoneByThirdActivityModelImpl();
    }

    @Override
    public void getSecurityCode(String mobile) {
        model.getSecurityCode(mobile, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getSms(((String) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void updateMobile(String token, String moblie, String sms) {
        model.updateMobile(token, moblie, sms, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.updatePhone((String)success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
