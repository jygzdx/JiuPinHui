package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.FormParticularEntity;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.WeChatPayEntity;
import com.jiupin.jiupinhui.model.IFormParticularActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.FormParticularActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IFormParticularActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
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
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
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
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void ensureGainGoods(String orderId, String token) {
        model.ensureGainGoods(orderId, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.ensureGainGoodsSuccess();
            }
            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void deleteForm(String orderId, String token) {
        model.deleteForm(orderId, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.deleteFormSuccess();
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
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

    @Override
    public void getCouponUrl(String token, String orderId) {
        model.getCouponUrl(token, orderId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getCouponUrlSuccess(((String) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
