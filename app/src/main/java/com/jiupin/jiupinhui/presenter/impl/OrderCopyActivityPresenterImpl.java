package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.OrderCopyEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IOrderCopyActivityModel;
import com.jiupin.jiupinhui.model.impl.OrderCopyActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IOrderCopyActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IOrderCopyActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class OrderCopyActivityPresenterImpl implements IOrderCopyActivityPresenter {

    private static final String TAG = "OrderCopyActivityPresenterImpl";

    private IOrderCopyActivityModel model;
    private IOrderCopyActivityView view;

    public OrderCopyActivityPresenterImpl(IOrderCopyActivityView view) {
        this.view = view;
        model = new OrderCopyActivityModelImpl();
    }

    @Override
    public void getDefaultAddress(String token) {
        model.getDefaultAddress(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setDefaultAddress((AddressEntity) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void submitForm(String token, String msg, String couponInfoId, String order_type, String addressId, String goodList,
                           String invoiceType, String invoice, String invoiceCode, String invoiceDesc) {
        model.submitForm(token, msg, couponInfoId, order_type, addressId, goodList, invoiceType,
                invoice, invoiceCode, invoiceDesc, new IModel.CallBack() {
                    @Override
                    public void onSuccess(Object success) {
                        view.submitFormSuccess((OrderCopyEntity) success);
                    }

                    @Override
                    public void onFailed(int status, String msg) {
                        HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
                    }
                });
    }

    //    @Override
//    public void submitForm(String userId, String storeId, String token, String msg,
//                           String couponInfoId, String order_type, String addressId, String goodList) {
//        model.submitForm(userId, storeId, token, msg, couponInfoId, order_type, addressId, goodList, new IModel.CallBack() {
//            @Override
//            public void onSuccess(Object success) {
//                view.submitFormSuccess((OrderSubmitEntity)success);
//            }
//
//            @Override
//            public void onFailed(int status, String msg) {
//                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
//            }
//        });
//    }
//
//    @Override
//    public void getAlipayInfo(String token, String orderId) {
//        model.getAlipayInfo(token, orderId, new IModel.CallBack() {
//            @Override
//            public void onSuccess(Object success) {
//                view.alipaySuccess(((ResponseBase) success));
//            }
//
//            @Override
//            public void onFailed(int status, String msg) {
//                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
//            }
//        });
//    }
//
//    @Override
//    public void getWeChatPayInfo(String token, String orderId) {
//        model.getWeChatPayInfo(token, orderId, new IModel.CallBack() {
//            @Override
//            public void onSuccess(Object success) {
//                view.weChatPaySuccess((WeChatPayEntity) success);
//            }
//
//            @Override
//            public void onFailed(int status, String msg) {
//                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
//            }
//        });
//    }
}
