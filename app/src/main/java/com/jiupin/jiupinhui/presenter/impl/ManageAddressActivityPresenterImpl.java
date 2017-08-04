package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.model.IManageAddressActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.ManageAddressActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IManageAddressActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IManageAddressActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ManageAddressActivityPresenterImpl implements IManageAddressActivityPresenter {

    private static final String TAG = "ManageAddressActivityPresenterImpl";

    private IManageAddressActivityModel model;
    private IManageAddressActivityView view;

    public ManageAddressActivityPresenterImpl(IManageAddressActivityView view) {
        this.view = view;
        model = new ManageAddressActivityModelImpl();
    }

    @Override
    public void getAddressList(String token) {
        model.getAddressList(token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setData((List<AddressEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void changeDefaultAddress(String token, String id) {
        model.changeDefaultAddress(token, id, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.changeDefaultAddressSuccess();
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }

    @Override
    public void deleteAddress(int id, String token) {
        model.deleteAddress(id, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.deleteAddressSuccess((List<AddressEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
