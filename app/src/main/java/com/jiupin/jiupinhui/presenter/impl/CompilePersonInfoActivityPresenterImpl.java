package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.model.ICompilePersonInfoActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.CompilePersonInfoActivityModelImpl;
import com.jiupin.jiupinhui.presenter.ICompilePersonInfoActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.ICompilePersonInfoActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class CompilePersonInfoActivityPresenterImpl implements ICompilePersonInfoActivityPresenter {

    private static final String TAG = "CompilePersonInfoActivityPresenterImpl";

    private ICompilePersonInfoActivityModel model;
    private ICompilePersonInfoActivityView view;

    public CompilePersonInfoActivityPresenterImpl(ICompilePersonInfoActivityView view) {
        this.view = view;
        model = new CompilePersonInfoActivityModelImpl();
    }

    @Override
    public void savePersonInfo(String token, String nickName, String sex, String location, String intro, String education) {
        model.savePersonInfo(token, nickName, sex, location, intro, education, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.savePersonInfo(((UserEntity.DataBean) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }
}
