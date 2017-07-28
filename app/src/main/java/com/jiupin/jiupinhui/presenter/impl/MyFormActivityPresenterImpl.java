package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.FormEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IMyFormActivityModel;
import com.jiupin.jiupinhui.model.impl.MyFormActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IMyFormActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IMyFormActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MyFormActivityPresenterImpl implements IMyFormActivityPresenter {

    private static final String TAG = "MyFormActivityPresenterImpl";

    private IMyFormActivityModel model;
    private IMyFormActivityView view;

    public MyFormActivityPresenterImpl(IMyFormActivityView view) {
        this.view = view;
        model = new MyFormActivityModelImpl();
    }

    @Override
    public void getFormInfo(String token, String orderStatus, String page, String rows) {
        model.getFormInfo(token, orderStatus, page, rows, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getFormInfoSuccess(((List<FormEntity>) success));
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(TAG, ((String) error));
            }
        });
    }

    @Override
    public void deleteForm(final int position , String orderId, String token) {
        model.deleteForm(orderId, token, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.deleteFormSuccess(position);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d(error.toString());
            }
        });
    }

}
