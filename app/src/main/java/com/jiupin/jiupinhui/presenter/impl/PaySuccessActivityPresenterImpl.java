package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.FormParticularEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IPaySuccessActivityModel;
import com.jiupin.jiupinhui.model.impl.PaySuccessActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IPaySuccessActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IPaySuccessActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class PaySuccessActivityPresenterImpl implements IPaySuccessActivityPresenter {

    private static final String TAG = "FormParticularActivityPresenterImpl";

    private IPaySuccessActivityModel model;
    private IPaySuccessActivityView view;

    public PaySuccessActivityPresenterImpl(IPaySuccessActivityView view) {
        this.view = view;
        model = new PaySuccessActivityModelImpl();
    }

    @Override
    public void getFormInfo(String orderId, String token) {
        model.getFormInfo(orderId, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getFormSuccess((FormParticularEntity) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
