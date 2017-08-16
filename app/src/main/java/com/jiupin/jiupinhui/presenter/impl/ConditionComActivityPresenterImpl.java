package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.model.IConditionComActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.ConditionComActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IConditionComActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IConditionComActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ConditionComActivityPresenterImpl implements IConditionComActivityPresenter {

    private static final String TAG = "ConditionComActivityPresenterImpl";

    private IConditionComActivityModel model;
    private IConditionComActivityView view;

    public ConditionComActivityPresenterImpl(IConditionComActivityView view) {
        this.view = view;
        model = new ConditionComActivityModelImpl();
    }

    @Override
    public void sendCondition(String token, String content, String dynamicId) {
        model.sendCondition(token, content, dynamicId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.sendConditionComSuccess();
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context)view);
            }
        });
    }
}
