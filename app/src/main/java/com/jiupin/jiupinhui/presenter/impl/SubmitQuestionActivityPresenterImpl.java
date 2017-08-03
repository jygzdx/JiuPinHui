package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.ISubmitQuestionActivityModel;
import com.jiupin.jiupinhui.model.impl.SubmitQuestionActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ISubmitQuestionActivityPresenter;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.ISubmitQuestionActivityView;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class SubmitQuestionActivityPresenterImpl implements ISubmitQuestionActivityPresenter {

    private static final String TAG = "SubmitQuestionActivityPresenterImpl";

    private ISubmitQuestionActivityModel model;
    private ISubmitQuestionActivityView view;

    public SubmitQuestionActivityPresenterImpl(ISubmitQuestionActivityView view) {
        this.view = view;
        model = new SubmitQuestionActivityModelImpl();
    }

    @Override
    public void submitQuestion(String token, String content, List<File> files) {
        model.submitQuestion(token,content, files, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.submitQuestionSuccess();
            }

            @Override
            public void onFailed(Object error) {
                ToastUtils.showShort((Context) view,"提交失败");
            }
        });
    }
}
