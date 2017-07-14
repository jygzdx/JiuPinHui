package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.OrderSubmitEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IOrderActivityModel;
import com.jiupin.jiupinhui.model.impl.OrderActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IOrderActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IOrderActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class OrderActivityPresenterImpl implements IOrderActivityPresenter {

    private static final String TAG = "LoginActivityPresenterI";

    private IOrderActivityModel model;
    private IOrderActivityView view;

    public OrderActivityPresenterImpl(IOrderActivityView view) {
        this.view = view;
        model = new OrderActivityModelImpl();
    }

    @Override
    public void getDefaultAddress(String token) {
        model.getDefaultAddress(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setData((AddressEntity) success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d("getDefaultAddress-->onFailed"+error);
            }
        });
    }

    @Override
    public void submitForm(String userId, String storeId, String token, String msg,
                           String couponInfoId, String order_type, String addressId, String goodList) {
        model.submitForm(userId, storeId, token, msg, couponInfoId, order_type, addressId, goodList, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.submitFormSuccess((OrderSubmitEntity)success);
            }

            @Override
            public void onFailed(Object error) {
                view.submitFormFail();
            }
        });
    }
}
