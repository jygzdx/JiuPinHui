package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.ConditionCommentEntity;
import com.jiupin.jiupinhui.model.IConditionCommentListActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.ConditionCommentListActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IConditionCommentListActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IConditionCommentListActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ConditionCommentListActivityPresenterImpl implements IConditionCommentListActivityPresenter {

    private static final String TAG = "ConditionCommentListActivityPresenterImpl";

    private IConditionCommentListActivityModel model;
    private IConditionCommentListActivityView view;

    public ConditionCommentListActivityPresenterImpl(IConditionCommentListActivityView view) {
        this.view = view;
        model = new ConditionCommentListActivityModelImpl();
    }

    @Override
    public void getCommentList(String dynamicId, String page) {
        model.getCommentList(dynamicId, page, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setCommentList((List<ConditionCommentEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
