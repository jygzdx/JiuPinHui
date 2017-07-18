package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.FormParticularEntity;
import com.jiupin.jiupinhui.model.IFormParticularActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.FormParticularActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IFormParticularActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IFormParticularActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FormParticularActivityPresenterImpl implements IFormParticularActivityPresenter {

    private static final String TAG = "FormParticularActivityPresenterImpl";

    private IFormParticularActivityModel model;
    private IFormParticularActivityView view;

    public FormParticularActivityPresenterImpl(IFormParticularActivityView view) {
        this.view = view;
        model = new FormParticularActivityModelImpl();
    }

    @Override
    public void getFormInfo(String orderId, String token) {
        model.getFormInfo(orderId, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getFormSuccess((FormParticularEntity) success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(error.toString());
            }
        });
    }

    @Override
    public void cancelForm(String orderId, String token) {
        model.cancelForm(orderId, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.cancelFormSuccess();
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(error.toString());
            }
        });
    }
}
