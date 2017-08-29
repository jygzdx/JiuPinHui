package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.WineInfoEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IWineListActivityModel;
import com.jiupin.jiupinhui.model.impl.WineListActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IWineListActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IWineListActivityView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineListActivityPresenterImpl implements IWineListActivityPresenter {
    private IWineListActivityView view;
    private IWineListActivityModel model;

    public WineListActivityPresenterImpl(IWineListActivityView view) {
        this.view = view;
        model = new WineListActivityModelImpl();
    }

    @Override
    public void getWineListByBrandId(String brandId, String page, String rows) {
        model.getWineListByBrandId(brandId, page, rows, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setWineInfoById((List<WineInfoEntity>) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status, msg, (Context)view);
            }
        });
    }

}
