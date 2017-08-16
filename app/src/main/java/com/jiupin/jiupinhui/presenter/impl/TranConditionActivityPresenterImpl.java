package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.ITranConditionActivityModel;
import com.jiupin.jiupinhui.model.impl.TranConditionActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ITranConditionActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.ITranConditionActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class TranConditionActivityPresenterImpl implements ITranConditionActivityPresenter {

    private static final String TAG = "TranConditionActivityPresenterImpl";

    private ITranConditionActivityModel model;
    private ITranConditionActivityView view;

    public TranConditionActivityPresenterImpl(ITranConditionActivityView view) {
        this.view = view;
        model = new TranConditionActivityModelImpl();
    }

    @Override
    public void sendTranCondition(String token, String content, String dynamicId) {
        model.sendTranCondition(token, content, dynamicId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.sendTranConditionSuccess();
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context)view);
            }
        });
    }
}
