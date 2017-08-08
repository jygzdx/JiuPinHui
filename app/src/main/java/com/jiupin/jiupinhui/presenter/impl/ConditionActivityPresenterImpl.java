package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.model.IConditionActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.ConditionActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IConditionActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IConditionActivityView;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ConditionActivityPresenterImpl implements IConditionActivityPresenter {

    private static final String TAG = "ConditionActivityPresenterImpl";

    private IConditionActivityModel model;
    private IConditionActivityView view;

    public ConditionActivityPresenterImpl(IConditionActivityView view) {
        this.view = view;
        model = new ConditionActivityModelImpl();
    }

    @Override
    public void sendCondition(String token, String content, String dynamicType, List<File> files) {
        model.sendCondition(token, content, dynamicType, files, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.sendConditionSuccess();
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context)view);
            }
        });
    }
}
