package com.jiupin.jiupinhui.presenter.impl;

import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.model.IGoodsActivityModel;
import com.jiupin.jiupinhui.model.IModel;
import com.jiupin.jiupinhui.model.impl.GoodsActivityModelImpl;
import com.jiupin.jiupinhui.presenter.IGoodsActivityPresenter;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.view.IGoodsActivityView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class GoodsActivityPresenterImpl implements IGoodsActivityPresenter {

    private static final String TAG = "GoodsActivityPresenterImpl";

    private IGoodsActivityModel model;
    private IGoodsActivityView view;

    public GoodsActivityPresenterImpl(IGoodsActivityView view) {
        this.view = view;
        model = new GoodsActivityModelImpl();
    }


    @Override
    public void getGoodsInfo(int id) {
        model.getGoodsInfo(id, new IModel.CallBack() {
            @Override
            public void onSuccess(Object success) {
                view.setData((GoodsEntity)success);
            }

            @Override
            public void onFailed(Object error) {
                LogUtils.d("getGoodsInfo-->onFailed");
            }
        });
    }
}
