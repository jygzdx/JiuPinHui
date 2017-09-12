package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.VersionEntity;
import com.jiupin.jiupinhui.model.IMainActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.MainActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IMainActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IMainActivityView;

import java.io.File;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MainActivityPresenterImpl implements IMainActivityPresenter {
    private static final String TAG = "MainActivityPresenterImpl";
    private IMainActivityView view;
    private IMainActivityModel model;

    public MainActivityPresenterImpl(IMainActivityView view) {
        this.view = view;
        model = new MainActivityModelImpl();
    }

    @Override
    public void getVersionInfo() {
        model.getVersionInfo(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.getVersionInfo(((VersionEntity) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void getApk(final String versionName) {
        model.getApk(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setApkUrl(((String) success), versionName);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context) view);
            }
        });
    }

    @Override
    public void installApp(String url, String versionName, File file) {
        model.installApp(url, versionName, file,new IModel.ProgressCallBack() {
            @Override
            public void onProgress(Object progress) {
//进度条
            }

            @Override
            public void onSuccess(Object success) {
                view.installApp(((String) success));
            }

            @Override
            public void onFailed(int status, String msg) {
//下载失败
                ToastUtils.showShort((Context) view,msg);
            }
        });

    }
}
