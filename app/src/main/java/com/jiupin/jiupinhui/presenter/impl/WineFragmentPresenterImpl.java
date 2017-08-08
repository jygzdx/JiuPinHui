package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.WineBrandEntity;
import com.jiupin.jiupinhui.entity.WineInfoEntity;
import com.jiupin.jiupinhui.fragment.WineFragment;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IWineFragmentModel;
import com.jiupin.jiupinhui.model.impl.WineFragmentModelImpl;
import com.jiupin.jiupinhui.presenter.IWineFragmentPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IWineFragmentView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineFragmentPresenterImpl implements IWineFragmentPresenter {
    private IWineFragmentView view;
    private IWineFragmentModel model;

    public WineFragmentPresenterImpl(IWineFragmentView view) {
        this.view = view;
        model = new WineFragmentModelImpl();
    }

    @Override
    public void getWineList(String page, String rows) {
        model.getWineList(page, rows, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setWineInfo(((List<WineInfoEntity>) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((WineFragment) view).getContext());
            }
        });
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
                HttpErrorUtils.manageErrorHttp(status,msg,((WineFragment) view).getContext());
            }
        });
    }

    @Override
    public void getBrandData() {
        model.getBrandData(new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setBrandData(((List<WineBrandEntity>) success));
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,((WineFragment) view).getContext());
            }
        });
    }
}
