package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.WeChatPayEntity;
import com.jiupin.jiupinhui.model.IFormCopyActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.FormCopyActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IFormCopyActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IFormCopyActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FormCopyActivityPresenterImpl implements IFormCopyActivityPresenter {

    private static final String TAG = "FormCopyActivityPresenterImpl";

    private IFormCopyActivityModel model;
    private IFormCopyActivityView view;

    public FormCopyActivityPresenterImpl(IFormCopyActivityView view) {
        this.view = view;
        model = new FormCopyActivityModelImpl();
    }


    @Override
    public void getAlipayInfo(String token, String orderId) {
        model.getAlipayInfo(token, orderId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.alipaySuccess(((ResponseBase) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void getWeChatPayInfo(String token, String orderId) {
        model.getWeChatPayInfo(token, orderId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.weChatPaySuccess((WeChatPayEntity) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
