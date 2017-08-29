package com.jiupin.jiupinhui.presenter.impl;

import android.content.Context;

import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.IWineShowActivityModel;
import com.jiupin.jiupinhui.model.impl.WineShowActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IWineShowActivityPresenter;
import com.jiupin.jiupinhui.utils.HttpErrorUtils;
import com.jiupin.jiupinhui.view.IWineShowActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WineShowActivityPresenterImpl implements IWineShowActivityPresenter {

    private static final String TAG = "WineShowActivityPresenterImpl";

    private IWineShowActivityModel model;
    private IWineShowActivityView view;

    public WineShowActivityPresenterImpl(IWineShowActivityView view) {
        this.view = view;
        model = new WineShowActivityModelImpl();
    }


    @Override
    public void getGoodsInfo(String goodsId) {
        model.getGoodsInfo(goodsId, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setData((GoodsEntity) success);
            }

            @Override
            public void onFailed(int status, String msg) {
                HttpErrorUtils.manageErrorHttp(status,msg,(Context) view);
            }
        });
    }
}
