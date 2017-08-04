package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IPackageActivityModel;
import com.jiupin.jiupinhui.model.impl.PackageActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IPackageActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IPackageActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class PackageActivityPresenterImpl implements IPackageActivityPresenter {

    private static final String TAG = "LoginActivityPresenterI";

    private IPackageActivityModel model;
    private IPackageActivityView view;

    public PackageActivityPresenterImpl(IPackageActivityView view) {
        this.view = view;
        model = new PackageActivityModelImpl();
    }


    @Override
    public void getGoodsInfo(int id) {
        model.getGoodsInfo(id, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setData((GoodsEntity)success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
