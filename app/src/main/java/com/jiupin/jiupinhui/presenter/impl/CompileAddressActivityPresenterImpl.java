package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.model.ICompileAddressActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.CompileAddressActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ICompileAddressActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.ICompileAddressActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class CompileAddressActivityPresenterImpl implements ICompileAddressActivityPresenter {

    private static final String TAG = "CompileAddressActivityPresenterImpl";

    private ICompileAddressActivityModel model;
    private ICompileAddressActivityView view;

    public CompileAddressActivityPresenterImpl(ICompileAddressActivityView view) {
        this.view = view;
        model = new CompileAddressActivityModelImpl();
    }

    @Override
    public void saveAddress(String token, String area_id, String zip, String trueName,
                            String area_info, String id, String mobile, String area_main, boolean isDefault) {
        model.saveAddress(token, area_id, zip, trueName, area_info, id, mobile, area_main, isDefault, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.saveAddressSuccess();
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(error.toString());
                view.saveAddressFailed();
            }
        });
    }

    @Override
    public void deleteAddress(int id, String token) {
        model.deleteAddress(id, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.deleteAddressSuccess();
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(error.toString());
            }
        });
    }
}
