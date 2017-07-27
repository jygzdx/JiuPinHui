package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.model.IBindingPhoneActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.BindingPhoneActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IBindingPhoneActivityPresenter;
import com.jiupin.jiupinhui.view.IBindingPhoneActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class BindingPhoneActivityPresenterImpl implements IBindingPhoneActivityPresenter {

    private static final String TAG = "BindingPhoneActivityPresenterImpl";

    private IBindingPhoneActivityModel model;
    private IBindingPhoneActivityView view;

    public BindingPhoneActivityPresenterImpl(IBindingPhoneActivityView view) {
        this.view = view;
        model = new BindingPhoneActivityModelImpl();
    }

    @Override
    public void getSecurityCode(String mobile) {
        model.getSecurityCode(mobile, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getSms(((String) success));
            }

            @Override
            public void onFailed(Object error) {
                view.requestError(error.toString());
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
            public void onFailed(Object error) {
                view.requestError(error.toString());
            }
        });
    }
}
